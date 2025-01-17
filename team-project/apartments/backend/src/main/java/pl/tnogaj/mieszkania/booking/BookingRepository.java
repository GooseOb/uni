package pl.tnogaj.mieszkania.booking;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface BookingRepository extends CrudRepository<Booking, Integer> {
    Iterable<Booking> findByUser_IdOrderByDateStart(Integer userId);
    Iterable<Booking> findByAccommodation_IdOrderByDateStart(Integer accommodationId);
    Iterable<Booking> findByIsRejectedAndAccommodation_IdOrderByDateStart(Boolean isRejected, Integer accommodationId);
    Iterable<Booking> findByIsRejectedAndAccommodation_IdAndDateEndAfterOrderByDateStart(Boolean isRejected, Integer accommodationId, LocalDateTime dateTime);
}
