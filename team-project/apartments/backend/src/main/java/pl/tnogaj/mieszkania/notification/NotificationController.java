package pl.tnogaj.mieszkania.notification;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tnogaj.mieszkania.booking.BookingService;
import pl.tnogaj.mieszkania.user.User;
import pl.tnogaj.mieszkania.user.UserService;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/notifications")
@AllArgsConstructor
public class NotificationController {
    private NotificationRepository notificationRepository;
    private UserService userService;
    private BookingService bookingService;

    @GetMapping
    public ResponseEntity<Iterable<Notification>> getUserNotifications(@RequestHeader("Authorization") String auth) {
        User user = userService.getUser(auth);
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(notificationRepository.findAllByTargetBooking_Accommodation_Host_Id(user.getId()));
    }

    @PostMapping("/accept")
    public ResponseEntity<Notification> acceptNotification(@RequestBody Notification notification, @RequestHeader("Authorization") String auth) {
        User user = userService.getUser(auth);
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        Optional<Notification> notificationFromRepositoryOptional = notificationRepository.findById(notification.getId());
        if (notificationFromRepositoryOptional.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        Notification notificationFromRepository = notificationFromRepositoryOptional.get();
        if (!Objects.equals(notificationFromRepository.getTargetBooking().getAccommodation().getHost().getId(), user.getId())) {
            return ResponseEntity.status(401).build();
        }
        if (notificationFromRepository.getType() == NotificationType.NEW_BOOKING) {
            if (bookingService.acceptBooking(notificationFromRepository.getTargetBooking()) == null) {
                return ResponseEntity.status(500).build();
            }
        }
        notificationRepository.delete(notificationFromRepository);
        return ResponseEntity.ok(notificationFromRepository);
    }

    @PostMapping("/reject")
    public ResponseEntity<Notification> rejectNotification(@RequestBody Notification notification, @RequestHeader("Authorization") String auth) {
        User user = userService.getUser(auth);
        if (user == null) {
            return ResponseEntity.status(401).build();
        }
        Optional<Notification> notificationFromRepositoryOptional = notificationRepository.findById(notification.getId());
        if (notificationFromRepositoryOptional.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        Notification notificationFromRepository = notificationFromRepositoryOptional.get();
        if (!Objects.equals(notificationFromRepository.getTargetBooking().getAccommodation().getHost().getId(), user.getId())) {
            return ResponseEntity.status(401).build();
        }
        if (notificationFromRepository.getType() == NotificationType.NEW_BOOKING) {
            if (bookingService.rejectBooking(notificationFromRepository.getTargetBooking()) == null) {
                return ResponseEntity.status(500).build();
            }
        }
        notificationRepository.delete(notificationFromRepository);
        return ResponseEntity.ok(notificationFromRepository);
    }
}
