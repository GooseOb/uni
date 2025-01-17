package pl.tnogaj.mieszkania.notification;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.tnogaj.mieszkania.booking.Booking;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Integer> {
    Iterable<Notification> findAllByTargetBooking_Accommodation_Host_Id(Integer hostId);
    Notification findByTargetBooking(Booking booking);
}
