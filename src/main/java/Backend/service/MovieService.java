package Backend.service;

import Backend.entity.Movie;
import Backend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // Método para guardar una película en la base de datos
    public Movie saveMovie(Movie movie, String userId) {
        // Aquí puedes agregar lógica adicional si es necesario
        movie.setUserId(userId);
        return movieRepository.save(movie);
    }

    // Método para obtener las películas de un usuario
    public List<Movie> getMoviesByUser(String userId) {
        return movieRepository.findByUserId(userId);
    }

    // Método para eliminar películas de un usuario
    public void deleteMovie(Long idMovie, String userId) {
        if (movieRepository.existsByIdMovie(idMovie)) {
            movieRepository.deleteById(idMovie);
        } else {
            throw new RuntimeException("La película no existe");
        }
    }

    public Movie getMovieById(Long idMovie) {
        return movieRepository.findById(idMovie)
                .orElseThrow(() -> new RuntimeException("Película no encontrada con id: " + idMovie));
    }

    public Movie updateMovie(Long idMovie, Movie movieDetails, String userId) {
        Movie movie = getMovieById(idMovie);

        // Verifica que la película pertenezca al usuario
        if (!movie.getUserId().equals(userId)) {
            throw new RuntimeException("No tienes permiso para modificar esta película");
        }

        // Actualiza solo el estado de la película
        if (movieDetails.getStatus() != null) {
            movie.setStatus(movieDetails.getStatus());
        }

        return movieRepository.save(movie);
    }
}
