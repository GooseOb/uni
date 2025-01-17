package pl.tnogaj.mieszkania.accommodation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import pl.tnogaj.mieszkania.photo.Photo;
import pl.tnogaj.mieszkania.user.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ACCOMMODATIONS")
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @CreationTimestamp
    private LocalDateTime dateAdded;
    private String name;
    private String description;
    private String street;
    private String city;
    private String zipCode;
    private String country;
    @OneToMany
    private List<Photo> photos;
    @OneToOne
    private User host;
    private BigDecimal price;
}
