package pl.tnogaj.mieszkania.booking;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import pl.tnogaj.mieszkania.accommodation.AccommodationService;
import pl.tnogaj.mieszkania.notification.NotificationService;
import pl.tnogaj.mieszkania.user.User;
import pl.tnogaj.mieszkania.user.UserService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@RestController
@RequestMapping("bookings")
@AllArgsConstructor
public class BookingController {
    BookingRepository bookingRepository;
    UserService userService;
    AccommodationService accommodationService;
    NotificationService notificationService;
    BookingService bookingService;

    @GetMapping
    public ResponseEntity<Iterable<Booking>> getBookings() {
        return ResponseEntity.ok(bookingRepository.findAll());
    }

    @GetMapping("{id}")
    public Optional<Booking> getBookingById(@PathVariable("id") Integer id) {
        return bookingRepository.findById(id);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<Iterable<Booking>> getBookingsByUserId(@PathVariable("userId") Integer userId) {
        return ResponseEntity.ok(bookingRepository.findByUser_IdOrderByDateStart(userId));
    }

    @GetMapping("/accommodations/all/{accommodationId}")
    public ResponseEntity<Iterable<Booking>> getBookingsByAccommodationId(@PathVariable("accommodationId") Integer accommodationId) {
        return ResponseEntity.ok(bookingService.getBookingsByAccommodationId(accommodationId));
    }

    @GetMapping("/accommodations/{accommodationId}")
    public ResponseEntity<Iterable<Booking>> getNotRejectedBookingsByAccommodationId(@PathVariable("accommodationId") Integer accommodationId) {
        return ResponseEntity.ok(bookingRepository.findByIsRejectedAndAccommodation_IdOrderByDateStart(false, accommodationId));
    }

    @GetMapping("/accommodations/present/{accommodationId}")
    public ResponseEntity<Iterable<Booking>> getPresentNotRejectedBookingsByAccommodationId(@PathVariable("accommodationId") Integer accommodationId) {
        return ResponseEntity.ok(bookingRepository.findByIsRejectedAndAccommodation_IdAndDateEndAfterOrderByDateStart(false, accommodationId, LocalDateTime.now()));
    }

    @PostMapping
    public ResponseEntity<Booking> addBooking(@RequestBody Booking booking, @RequestHeader("Authorization") String auth) {
        User host = userService.getUser(auth);
        if (host == null) {
            return ResponseEntity.status(403).build();
        }
        booking.setUser(host);
        booking.setAccommodation(accommodationService.getAccommodationById(booking.getAccommodation().getId()));
        if (booking.getDateEnd().isBefore(booking.getDateStart())
                || booking.getDateStart().isEqual(booking.getDateEnd())
                || booking.getDateStart().isBefore(LocalDateTime.now())){
            return ResponseEntity.status(409).build();
        }
            booking.setPrice(booking.getAccommodation().getPrice().multiply(BigDecimal.valueOf(ChronoUnit.DAYS.between(booking.getDateStart(), booking.getDateEnd()))));
        if (bookingService.isBookingConflict(booking)) {
            return ResponseEntity.status(409).build();
        }
        Booking bookingSaved = bookingRepository.save(booking);
        notificationService.addNewBookingNotification(bookingSaved);
        return ResponseEntity.ok(bookingSaved);
    }

    @PutMapping
    public ResponseEntity<Booking> updateBooking(@RequestBody Booking booking, @RequestHeader("Authorization") String auth) {
        User host = userService.getUser(auth);

        Optional<Booking> bookingToUpdateOptional = this.bookingRepository.findById(booking.getId());

        Booking bookingToUpdate = null;

        if (bookingToUpdateOptional.isEmpty())
            return ResponseEntity.status(406).build();
        else
            bookingToUpdate = bookingToUpdateOptional.get();

        if (bookingService.isBookingConflict(booking)) {
            return ResponseEntity.status(409).build();
        }

        if (host != null && bookingToUpdate.getUser() == host) {
            if (booking.getUser() != null) {
                bookingToUpdate.setUser(booking.getUser());
            }
            if (booking.getAccommodation() != null) {
                bookingToUpdate.setAccommodation(booking.getAccommodation());
            }
            if (booking.getDateStart() != null) {
                bookingToUpdate.setDateStart(booking.getDateStart());
            }
            if (booking.getDateEnd() != null) {
                bookingToUpdate.setDateEnd(booking.getDateEnd());
            }
            if (booking.getDateBooked() != null) {
                bookingToUpdate.setDateBooked(booking.getDateBooked());
            }
            if (booking.getPrice() != null) {
                bookingToUpdate.setPrice(booking.getPrice());
            }

            notificationService.removeBookingNotification(bookingToUpdate);
            Booking updatedBooking = this.bookingRepository.save(bookingToUpdate);
            notificationService.addNewBookingNotification(bookingToUpdate);
            return ResponseEntity.ok(updatedBooking);
        }

        return ResponseEntity.status(406).build();
    }

    @DeleteMapping
    public ResponseEntity<Booking> deleteBooking(@RequestBody Booking booking, @RequestHeader("Authorization") String auth) {
        User user = userService.getUser(auth);

        Optional<Booking> bookingToDeleteOptional = this.bookingRepository.findById(booking.getId());

        Booking bookingToDelete = null;

        if (bookingToDeleteOptional.isEmpty())
            ResponseEntity.status(406).build();
        else
            bookingToDelete = bookingToDeleteOptional.get();

        if (user != null && bookingToDelete != null && bookingToDelete.getAccommodation() != null &&
                (bookingToDelete.getUser() == user || bookingToDelete.getAccommodation().getHost() == user)) {
            notificationService.removeBookingNotification(bookingToDelete);
            this.bookingRepository.delete(bookingToDelete);
            return ResponseEntity.ok(bookingToDelete);
        }

        return ResponseEntity.status(406).build();
    }
}
