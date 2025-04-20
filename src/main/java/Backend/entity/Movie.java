package Backend.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    private Long idMovie;

    @Column(nullable = false)
    private String nameMovie;
    private String category;
    private String image;

    @ManyToMany(mappedBy = "collection")
    private Set<Users> usersWhoCollected = new HashSet<>();
}
