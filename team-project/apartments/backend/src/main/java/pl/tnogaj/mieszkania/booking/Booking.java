package pl.tnogaj.mieszkania.booking;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import pl.tnogaj.mieszkania.accommodation.Accommodation;
import pl.tnogaj.mieszkania.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "BOOKINGS")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private User user;
    @OneToOne
    private Accommodation accommodation;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    @CreationTimestamp
    private LocalDateTime dateBooked;
    private BigDecimal price;
    private boolean isAccepted;
    private boolean isRejected;
}
