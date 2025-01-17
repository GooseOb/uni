package pl.tnogaj.mieszkania.notification;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tnogaj.mieszkania.booking.Booking;

@Service
@AllArgsConstructor
public class NotificationService {
    private NotificationRepository notificationRepository;

    public Notification addNewBookingNotification(Booking booking) {
        Notification notification = new Notification();
        notification.setTargetBooking(booking);
        notification.setType(NotificationType.NEW_BOOKING);
        return notificationRepository.save(notification);
    }

    public Notification removeBookingNotification(Booking booking) {
        Notification notification = notificationRepository.findByTargetBooking(booking);
        if (notification != null) {
            notificationRepository.delete(notification);
        }
        return notification;
    }
}
