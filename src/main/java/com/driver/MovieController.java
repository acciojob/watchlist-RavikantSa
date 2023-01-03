package com.driver;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping( "add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        movieService.addMovie(movie);
        return  new ResponseEntity<>("New movie added successfully", HttpStatus.CREATED);
    }

    @PostMapping( "add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        movieService.addDirector(director);
        return  new ResponseEntity<>("New director added successfully", HttpStatus.CREATED);
    }

//    @GetMapping("/get-movie-by-name/{name}")
//    public ResponseEntity<Movie> getMovieByName(@PathVariable  String movieName){
//        Movie  resultMovie  = movieService.findMovie(movieName);
//        return    new ResponseEntity<>(resultMovie , HttpStatus.FOUND);
//    }

    @PostMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie") String movie , @RequestParam("director") String director){
        movieService.createMovieDirectorPair(movie, director);
        return new ResponseEntity<>("New movie-director pair added Successfully" , HttpStatus.CREATED);
    }


    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@RequestParam("name") String name){
//        Student student = null; // Assign student by calling service layer method
        Movie movie =  movieService.findMovie(name); // Assign student by calling service layer method

        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }
    @GetMapping("/get-director-by-name/{name}")
    public  ResponseEntity<Director> getDirectorByName(@PathVariable String name){
        Director director =  movieService.findDirector(name);
        return  new ResponseEntity<>(director , HttpStatus.FOUND);
    }

    @GetMapping("get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName (@PathVariable String director){
        List<String> movie = movieService.getMovieByDirector(director);
        return  new ResponseEntity<>(movie , HttpStatus.CREATED);
    }
    @GetMapping("get-all-movies")
    public ResponseEntity<List<String>> findAllMovies (){
        List<String> movies = movieService.findAllMovies();
        return  new ResponseEntity<>( movies , HttpStatus.FOUND);

    }

    @DeleteMapping ("delete-director-by-name")
    public ResponseEntity<String>  deleteDirectorByName (@RequestParam String director){
        movieService.DeleteDirector(director);
        return  new ResponseEntity<>( director + "removed Successfully", HttpStatus.FOUND);
    }
    @DeleteMapping("/delete-all-director")
    public  ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllDirector();
        return  new ResponseEntity<>("All director delete successfully" , HttpStatus.ACCEPTED);
    }

}
