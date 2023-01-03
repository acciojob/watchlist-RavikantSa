package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class MovieRepository {

  private   HashMap< String , Movie> movieMap;
 private  HashMap <String  , Director> directorMap;
 private  HashMap < String , List<String>> directorMovieMapping;
 // pair is  : DirectorName , List of Movie Name

    public MovieRepository() {
        this.movieMap = new HashMap< String , Movie>() ;
        this.directorMap = new HashMap< String , Director>();
        this.directorMovieMapping = new HashMap< String  , List<String>>();
    }

    public void saveMovie(Movie movie) {
        movieMap.put( movie.getName() , movie);
    }
    public void saveDirector( Director director) {
       directorMap.put( director.getName() , director);
    }

    public Movie findMovie(String movieName) {
        return  movieMap.getOrDefault(movieName , null);

    }
    public Director findDirector(String directorName) {
        return  directorMap.get(directorName);
    }

    public void saveMovieDirectorPair(String movie, String director) {
        if( movieMap.containsKey(movie) && directorMap.containsKey(director)){
            List<String> currentMovieByDirector = directorMovieMapping.get(director);
            currentMovieByDirector.add(movie);
            directorMovieMapping.put(director , currentMovieByDirector);
        }
    }
    public  List<String> findMoviesFromDirector(String director){
        List<String> movieList = new ArrayList<>();
        if(directorMovieMapping.containsKey(director)) {
            movieList = directorMovieMapping.get(director);
        }
        return movieList;
    }

    public List<String> findAllMovies() {
        return  new ArrayList<>(movieMap.keySet());
    }

    public String deleteDirector(String director) {
        if(directorMap.containsKey(director) || directorMovieMapping.containsKey(director)){
            List<String> movie = new ArrayList<>();
            if( directorMovieMapping.containsKey(director)){
                movie = directorMovieMapping.get(director);
                for(String m: movie){
                    if(movieMap.containsKey(m)){
                        movieMap.remove(m);
                        // movir got removed
                    }
                }
                directorMovieMapping.remove(director);
                //director movie map got removed
            }

            if( directorMap.containsKey(director)){
                directorMap.remove(director);
            }

            // director got removed

            return "Successfully Deleted";
        }
        else{
            return  "Director Not Found";
        }
    }

    public void deleteAllDirector() {
        HashSet<String> movieset = new HashSet<>();

        directorMap = new HashMap<>();

        for( String director : directorMovieMapping.keySet()){
            for ( String movie: directorMovieMapping.get(director)){
                movieset.add(movie);
            }
        }

        for ( String movie: movieset){
            if(movieMap.containsKey(movie)){
                movieMap.remove(movie);
            }
        }
        directorMovieMapping.clear();
    }
}
