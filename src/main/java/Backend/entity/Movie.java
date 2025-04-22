package Backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_seq")
    @SequenceGenerator(name = "movie_seq", sequenceName = "movie_sequence", allocationSize = 1)
    @Column(name = "id_movie")
    private Long idMovie;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String nameMovie;

    private String category;
    private String image;

    @ManyToMany(mappedBy = "collection")
    private Set<Users> usersWhoCollected = new HashSet<>();
}
