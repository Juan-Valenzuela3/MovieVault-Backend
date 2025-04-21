package Backend.controller;

import Backend.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
@RestController
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    // Endpoint para guardar una película
    @PostMapping
    public Movie saveMovie(@RequestBody Movie movie, @RequestHeader("user-id") String userId) {
        return movieService.saveMovie(movie, userId);
    }

    // Endpoint para obtener las películas de un usuario
    @GetMapping
    public List<Movie> getMoviesByUser(@RequestHeader("user-id") String userId) {
        return movieService.getMoviesByUser(userId);
    }
}
*/