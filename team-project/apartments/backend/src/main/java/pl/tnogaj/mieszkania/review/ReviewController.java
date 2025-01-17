package pl.tnogaj.mieszkania.review;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("reviews")
@AllArgsConstructor
public class ReviewController {
    ReviewRepository reviewRepository;

    @GetMapping("{id}")
    public Optional<Review> getReviewById(@PathVariable("id") Integer id){
        return reviewRepository.findById(id);
    }

    @GetMapping("authors/{id}")
    public Optional<Iterable<Review>> getReviewsByAuthorId(@PathVariable("id") Integer authorId){
        return reviewRepository.findAllByAuthor_Id(authorId);
    }

    @GetMapping("accommodations/{id}")
    public Optional<Iterable<Review>> getReviewsByAccommodationId(@PathVariable("id") Integer id){
        return reviewRepository.findAllByTypeAndReviewedId(ReviewType.ACCOMMODATION, id);
    }

    @GetMapping("guests/{id}")
    public Optional<Iterable<Review>> getReviewsByGuestId(@PathVariable("id") Integer id){
        return reviewRepository.findAllByTypeAndReviewedId(ReviewType.GUEST, id);
    }

    @GetMapping("hosts/{id}")
    public Optional<Iterable<Review>> getReviewsByHostId(@PathVariable("id") Integer id){
        return reviewRepository.findAllByTypeAndReviewedId(ReviewType.HOST, id);
    }

    @PostMapping
    public Review addReview(@RequestBody Review review){
        return reviewRepository.save(review);
    }
}
