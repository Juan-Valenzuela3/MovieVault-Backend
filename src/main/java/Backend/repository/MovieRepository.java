package Backend.repository;

import Backend.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    boolean existsByIdMovie(Long idMovie);

    List<Movie> findByUserId(String userId);
}
