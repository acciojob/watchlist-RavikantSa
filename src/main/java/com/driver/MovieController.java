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
//    http://localhost:8099/movies/add-movie

    @PostMapping( "add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        movieService.addDirector(director);
        return  new ResponseEntity<>("New director added successfully", HttpStatus.CREATED);
    }
//    http://localhost:8099/movies/add-director

//    @GetMapping("/get-movie-by-name/{name}")
//    public ResponseEntity<Movie> getMovieByName(@PathVariable  String movieName){
//        Movie  resultMovie  = movieService.findMovie(movieName);
//        return    new ResponseEntity<>(resultMovie , HttpStatus.FOUND);
//    }




    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable("name") String name){
        Movie movie =  movieService.findMovie(name);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }
//    http://localhost:8099/movies/get-movie-by-name/A

    @GetMapping("/get-director-by-name/{name}")
    public  ResponseEntity<Director> getDirectorByName(@PathVariable String name){
        Director director =  movieService.findDirector(name);
        return  new ResponseEntity<>(director , HttpStatus.FOUND);
    }
//    http://localhost:8099/movies/get-director-by-name/Rohit Shety


    @GetMapping("get-all-movies")
    public ResponseEntity<List<String>> findAllMovies (){
        List<String> movies = movieService.findAllMovies();
        return  new ResponseEntity<>( movies , HttpStatus.FOUND);
    }
//    http://localhost:8099/movies/get-all-movies

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestParam("movie") String movie , @RequestParam("director") String director){
        movieService.createMovieDirectorPair(movie, director);
        return new ResponseEntity<>("New movie-director pair added Successfully" , HttpStatus.CREATED);
    }

//    http://localhost:8099/movies/add-movie-director-pair?movie=A&director=R
//    http://localhost:8099/movies/add-movie-director-pair?movie=D &director=R

    @GetMapping("get-movies-by-director-name/{director}")
    public ResponseEntity<List<String>> getMoviesByDirectorName (@PathVariable String director){
        List<String> movie = movieService.getMovieByDirector(director);
        return  new ResponseEntity<>(movie , HttpStatus.CREATED);
    }

    @GetMapping("/get-director-by-movieName/{movie}")
    public ResponseEntity<String> getDirectorByMovieName(@PathVariable String  movieName){
       String s = movieService.getDirectorByMovieName(movieName);
        return  new ResponseEntity<>( s, HttpStatus.FOUND);
    }



    @DeleteMapping ("delete-director-by-name")
    public ResponseEntity<String>  deleteDirectorByName (@RequestParam("director") String director){
        movieService.DeleteDirector(director);
        return  new ResponseEntity<>( director + "removed Successfully", HttpStatus.FOUND);
    }
    @DeleteMapping("/delete-all-director")
    public  ResponseEntity<String> deleteAllDirectors(){
        movieService.deleteAllDirector();
        return  new ResponseEntity<>("All director delete successfully" , HttpStatus.ACCEPTED);
    }

}
