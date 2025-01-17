package pl.tnogaj.mieszkania.review;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import pl.tnogaj.mieszkania.user.User;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "REVIEWS")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer reviewedId;
    @OneToOne
    private User author;
    private ReviewType type;
    @CreationTimestamp
    private LocalDateTime dateAdded;
    private String header;
    private String text;
    private Integer rating;
}
