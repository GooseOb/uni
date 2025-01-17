package pl.tnogaj.mieszkania.accommodation;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import pl.tnogaj.mieszkania.booking.BookingService;
import pl.tnogaj.mieszkania.user.User;
import pl.tnogaj.mieszkania.user.UserService;

import java.util.Optional;

@RestController
@RequestMapping("accommodations")
@AllArgsConstructor
public class AccommodationController {
    AccommodationRepository accommodationRepository;
    UserService userService;
    BookingService bookingService;

    @GetMapping
    public Iterable<Accommodation> getAccommodations(){
        return accommodationRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Accommodation> getAccommodation(@PathVariable("id") Integer id){
        Optional<Accommodation> accommodationOptional = accommodationRepository.findById(id);
        return accommodationOptional.isPresent()
                ? ResponseEntity.ok(accommodationOptional.get())
                : ResponseEntity.status(404).build();
    }

    @GetMapping("/hosts/{hostId}")
    public ResponseEntity<Iterable<Accommodation>> getAccommodationsByHostId(@PathVariable("hostId") Integer hostId){
        return ResponseEntity.ok(accommodationRepository.findByHost_Id(hostId));
    }

    @PostMapping
    public ResponseEntity<Accommodation> addAccommodation(@RequestBody Accommodation accommodation, @RequestHeader("Authorization") String auth){
        User host = userService.getUser(auth);
        if (host == null) {
            return ResponseEntity.status(403).build();
        }
        accommodation.setHost(host);
        return ResponseEntity.ok(accommodationRepository.save(accommodation));
    }

    @PutMapping
    public ResponseEntity<Accommodation> updateAccommodation(@RequestBody Accommodation accommodation, @RequestHeader("Authorization") String auth){
        User host = userService.getUser(auth);

        Optional<Accommodation> accommodationToUpdateOptional = this.accommodationRepository.findById(accommodation.getId());

        if(accommodationToUpdateOptional.isEmpty()) {
            return ResponseEntity.status(406).build();
        }

        Accommodation accommodationToUpdate = accommodationToUpdateOptional.get();

        if(host != null && accommodationToUpdate.getHost() == host) {

            if (accommodation.getDateAdded() != null) {
                accommodationToUpdate.setDateAdded(accommodation.getDateAdded());
            }
            if (accommodation.getName() != null) {
                accommodationToUpdate.setName(accommodation.getName());
            }
            if (accommodation.getDescription() != null) {
                accommodationToUpdate.setDescription(accommodation.getDescription());
            }
            if (accommodation.getStreet() != null) {
                accommodationToUpdate.setStreet(accommodation.getStreet());
            }
            if (accommodation.getCity() != null) {
                accommodationToUpdate.setCity(accommodation.getCity());
            }
            if (accommodation.getZipCode() != null) {
                accommodationToUpdate.setZipCode(accommodation.getZipCode());
            }
            if (accommodation.getCountry() != null) {
                accommodationToUpdate.setCountry(accommodation.getCountry());
            }
            if(accommodation.getPhotos() != null){
                accommodationToUpdate.setPhotos(accommodation.getPhotos());
                            }
            if (accommodation.getPrice() != null) {
                accommodationToUpdate.setPrice(accommodation.getPrice());
            }

            Accommodation updatedAccommodation = this.accommodationRepository.save(accommodationToUpdate);

            return ResponseEntity.ok(updatedAccommodation);
        }
        return ResponseEntity.status(406).build();
    }

    @DeleteMapping
    public ResponseEntity<Accommodation> deleteAccommodation(@RequestBody Accommodation accommodation, @RequestHeader("Authorization") String auth){
        User host = userService.getUser(auth);

        Integer accommodationId = accommodation.getId();

        Optional<Accommodation> accommodationToDeleteOptional = this.accommodationRepository.findById(accommodationId);

        if(accommodationToDeleteOptional.isEmpty()) {
            return ResponseEntity.status(406).build();
        }

        Accommodation accommodationToDelete = accommodationToDeleteOptional.get();

        if(host != null && accommodationToDelete.getHost() == host) {
            if (this.bookingService.deleteBookingsByAccommodationId(accommodationId)) {
                this.accommodationRepository.delete(accommodationToDelete);
                return ResponseEntity.ok(accommodation);
            }
        }

        return ResponseEntity.status(406).build();
    }
}
