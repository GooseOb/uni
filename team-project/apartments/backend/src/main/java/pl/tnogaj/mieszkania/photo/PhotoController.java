package pl.tnogaj.mieszkania.photo;

import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.tnogaj.mieszkania.user.User;
import pl.tnogaj.mieszkania.user.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("photos")
@AllArgsConstructor
public class PhotoController {
    PhotoRepository photoRepository;
    UserService userService;
    PhotoService photoService;

    @GetMapping("/{name}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable("name") String name) {
        try{
            byte[] bytes = photoService.getPhotoByUUID(name);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            headers.setContentLength(bytes.length);
            headers.setContentDisposition(ContentDisposition.builder("attachment").filename(name).build());

            return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
        } catch (IOException e){
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping
    public ResponseEntity<Photo> uploadPhoto(@RequestParam("photo") MultipartFile photo, @RequestHeader("Authorization") String auth) {
        try {
            byte[] bytes = photo.getBytes();
            String name = UUID.randomUUID().toString();
            Path path = Paths.get(System.getenv("PHOTOS_PATH") + name);
            Files.write(path, bytes);

            Photo photoToRepository = new Photo();
            photoToRepository.setName(name);
            photoToRepository.setAuthorId(userService.getUser(auth).getId());

            return ResponseEntity.ok(photoRepository.save(photoToRepository));
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Photo> deletePhoto(@RequestBody Photo photo, @RequestHeader("Authorization") String auth) {
        User user = userService.getUser(auth);
        Optional<Photo> photoOptional = photoRepository.findById(photo.getId());
        if (photoOptional.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        Photo photoFromRepository = photoOptional.get();
        if (photoFromRepository.getAuthorId() != user.getId()) {
            return ResponseEntity.status(406).build();
        }
        try {
            Files.delete(Paths.get(System.getenv("PHOTOS_PATH") + photoFromRepository.getName()));
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
        photoRepository.delete(photoFromRepository);
        return ResponseEntity.ok(photoFromRepository);
    }
}
