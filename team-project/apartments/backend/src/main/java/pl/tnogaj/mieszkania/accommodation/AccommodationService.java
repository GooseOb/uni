package pl.tnogaj.mieszkania.accommodation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AccommodationService {
    AccommodationRepository accommodationRepository;

    public Accommodation getAccommodationById(Integer id) {
        return accommodationRepository.findById(id).isPresent() ? accommodationRepository.findById(id).get() : null;
    }
}
