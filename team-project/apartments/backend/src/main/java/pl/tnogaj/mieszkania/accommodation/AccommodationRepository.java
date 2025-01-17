package pl.tnogaj.mieszkania.accommodation;

import org.springframework.data.repository.CrudRepository;

public interface AccommodationRepository extends CrudRepository<Accommodation, Integer> {
    Iterable<Accommodation> findByHost_Id(Integer hostId);
}
