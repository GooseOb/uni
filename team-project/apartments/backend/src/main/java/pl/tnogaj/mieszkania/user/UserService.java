package pl.tnogaj.mieszkania.user;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()
                || userRepository.findByEmail(user.getEmail()).isPresent()) {
            return null;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUser(String auth) {
        auth = String.format(auth).substring(6);
        byte[] authDecodedBytes = Base64.getDecoder().decode(auth);
        String authDecoded = new String(authDecodedBytes);
        String username = authDecoded.substring(0, authDecoded.indexOf(':'));
        String password = authDecoded.substring(authDecoded.indexOf(':') + 1);
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return null;
        }
        if (BCrypt.checkpw(password, userOptional.get().getPassword())) {
            return userOptional.get();
        }
        return null;
    }

    public User updateUser(User userToUpdate, User user) {
        if (user == null) {
            return null;
        }
        if (user.getPassword() != null) {
            userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if (user.getBirthDate() != null) {
            userToUpdate.setBirthDate(user.getBirthDate());
        }
        if (user.getFirstName() != null) {
            userToUpdate.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            userToUpdate.setLastName(user.getLastName());
        }
        if (user.getEmail() != null) {
            userToUpdate.setEmail(user.getEmail());
        }
        if (user.getPhoneNumber() != null) {
            userToUpdate.setPhoneNumber(user.getPhoneNumber());
        }
        if (user.getPhoto() != null) {
            userToUpdate.setPhoto(user.getPhoto());
        }
        return userRepository.save(userToUpdate);
    }

    public User deleteUser(User userToDelete) {
        if (userRepository.findByUsername(userToDelete.getUsername()).isEmpty()) {
            return null;
        }
        userRepository.delete(userToDelete);
        return userToDelete;
    }
}
