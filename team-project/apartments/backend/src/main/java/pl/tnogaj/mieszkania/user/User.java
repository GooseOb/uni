package pl.tnogaj.mieszkania.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import pl.tnogaj.mieszkania.photo.Photo;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private LocalDateTime birthDate;
    @CreationTimestamp
    private LocalDateTime dateRegistered;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    @OneToOne
    private Photo photo;
}
