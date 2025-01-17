package pl.tnogaj.mieszkania.notification;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import pl.tnogaj.mieszkania.booking.Booking;

import java.time.LocalDateTime;

@Entity
@Table(name = "NOTIFICATIONS")
@Getter
@Setter
@NoArgsConstructor
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Booking targetBooking;
    @CreationTimestamp
    private LocalDateTime dateCreated;
    private NotificationType type;
}
