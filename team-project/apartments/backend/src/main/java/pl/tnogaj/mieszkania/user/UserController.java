package pl.tnogaj.mieszkania.user;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserController {
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User userRegistered = userService.registerUser(user);
        return userRegistered != null
                ? ResponseEntity.ok(userRegistered)
                : ResponseEntity.status(406).build();
    }

    @GetMapping("/login")
    public ResponseEntity<User> loginUser(@RequestHeader("Authorization") String auth) {
        return userService.getUser(auth) != null
                ? ResponseEntity.ok(userService.getUser(auth))
                : ResponseEntity.status(406).build();
    }

    @PutMapping("/user")
    public ResponseEntity<User> updateUser(@RequestBody User user, @RequestHeader("Authorization") String auth) {
        User userToUpdate = userService.getUser(auth);
        if (userToUpdate == null) {
            return ResponseEntity.status(406).build();
        }
        User userUpdated = userService.updateUser(userToUpdate, user);
        return userUpdated != null
                ? ResponseEntity.ok(userUpdated)
                : ResponseEntity.status(406).build();
    }

    @DeleteMapping("/user")
    public ResponseEntity<User> deleteUser(@RequestHeader("Authorization") String auth) {
        User userToDelete = userService.getUser(auth);
        User userDeleted = userService.deleteUser(userToDelete);
        return userDeleted != null
                ? ResponseEntity.ok(userDeleted)
                : ResponseEntity.status(406).build();
    }
}
