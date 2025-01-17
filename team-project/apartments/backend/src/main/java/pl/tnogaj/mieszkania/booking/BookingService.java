package pl.tnogaj.mieszkania.booking;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@AllArgsConstructor
public class BookingService {
    private BookingRepository bookingRepository;

    public Booking acceptBooking(Booking booking) {
        booking.setAccepted(true);
        return bookingRepository.save(booking);
    }

    public Booking rejectBooking(Booking booking) {
        booking.setRejected(true);
        return bookingRepository.save(booking);
    }

    public boolean isBookingConflict(Booking booking) {
        Iterable<Booking> bookings = bookingRepository.findByAccommodation_IdOrderByDateStart(booking.getAccommodation().getId());
        for (Booking existingBooking: bookings) {
            if (booking.getDateStart() != null && booking.getDateEnd() != null &&
            existingBooking.getDateStart() != null && existingBooking.getDateEnd() != null) {
                if (booking.getDateStart().isBefore(existingBooking.getDateEnd()) &&
                        booking.getDateEnd().isAfter(existingBooking.getDateStart())) {
                    return true;
                }
            }
        }
        return false;
    }

    public Iterable<Booking> getBookingsByAccommodationId(Integer accommodationId) {
        return bookingRepository.findByAccommodation_IdOrderByDateStart(accommodationId);
    }

    public boolean deleteBookingsByAccommodationId(Integer accommodationId) {
        Iterable<Booking> bookings = this.getBookingsByAccommodationId(accommodationId);

        for (Booking booking : bookings) {
            if (booking.isAccepted()) {
                return false;
            }
        }

        bookingRepository.deleteAll(bookings);
        return true;
    }
}
