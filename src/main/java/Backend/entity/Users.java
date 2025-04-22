package Backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "collection")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToMany
    @JoinTable(
            name = "user_collections",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    @Builder.Default
    private Set<Movie> collection = new HashSet<>();

    // Métodos de utilidad para gestionar la colección
    public void addToCollection(Movie movie) {
        collection.add(movie);
    }

    public void removeFromCollection(Movie movie) {
        collection.remove(movie);
    }
}