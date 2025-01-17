package pl.tnogaj.mieszkania.review;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ReviewRepository extends CrudRepository<Review, Integer> {
    Optional<Iterable<Review>> findAllByAuthor_Id(Integer authorId);
    Optional<Iterable<Review>> findAllByTypeAndReviewedId(ReviewType reviewType, Integer reviewedId);
}
