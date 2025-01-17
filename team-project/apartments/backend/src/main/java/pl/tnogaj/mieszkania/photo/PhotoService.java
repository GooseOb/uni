package pl.tnogaj.mieszkania.photo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@AllArgsConstructor
@Service
public class PhotoService {
    private PhotoRepository photoRepository;

    public byte[] getPhotoByUUID(String UUID) throws IOException {
        File file = new File(System.getenv("PHOTOS_PATH") + UUID);
        FileInputStream fis = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        fis.read(bytes);
        fis.close();
        return bytes;
    }
}
