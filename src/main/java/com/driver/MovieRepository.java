package com.driver;
import org.springframework.stereotype.Repository;
import java.util.*;


@Repository
public class MovieRepository {

    private HashMap<String, Movie> movieMap;
    private HashMap<String, Director> directorMap;
    private HashMap<String, List<String>> directorMovieMapping;
    // pair is  : DirectorName , List of Movie Name


    public MovieRepository(HashMap<String, Movie> movieMap, HashMap<String, Director> directorMap, HashMap<String, List<String>> directorMovieMapping) {
        this.movieMap = movieMap;
        this.directorMap = directorMap;
        this.directorMovieMapping = directorMovieMapping;
    }

//
//    public void saveMovieDirectorPair(String movie, String director) {
//        if( movieMap.containsKey(movie) && directorMap.containsKey(director)){
//            List<String> currentMovieByDirector = new ArrayList<>();
////            currentMovieByDirector.add(movie);
////            directorMovieMapping.put(director , currentMovieByDirector);
//            if(directorMovieMapping.containsKey(director)){
//              directorMovieMapping.get(director);
//            }
//            else{
//                currentMovieByDirector = new ArrayList<>();
//            }
//
//            currentMovieByDirector.add(movie);
//            directorMovieMapping.put(director , currentMovieByDirector);
//
//        }
//
//    }
//

//    public String deleteDirector(String director) {
//        if(directorMap.containsKey(director) || directorMovieMapping.containsKey(director)){
//            List<String> movie = new ArrayList<>();
//            if( directorMovieMapping.containsKey(director)){
//                movie = directorMovieMapping.get(director);
//                for(String m: movie){
//                    if(movieMap.containsKey(m)){
//                        movieMap.remove(m);
//                        // movir got removed
//                    }
//                }
//                directorMovieMapping.remove(director);
//                //director movie map got removed
//            }
//
//            if( directorMap.containsKey(director)){
//                directorMap.remove(director);
//            }
//
//            // director got removed
//
//            return "Successfully Deleted";
//        }
//        else{
//            return  "Director Not Found";
//        }
//    }
//


    public void saveMovie(Movie movie) {
        movieMap.put(movie.getName(), movie);
    }

    public void saveDirector(Director director) {
        directorMap.put(director.getName(), director);
    }

    public void saveMovieDirectorPair(String movie, String director) {

        List<String> currentMoviesByDirector = new ArrayList<>();

        if (movieMap.containsKey(movie) && directorMap.containsKey(director)) {

            if (directorMovieMapping.containsKey(director)) {
                currentMoviesByDirector = directorMovieMapping.get(director);
            }

            currentMoviesByDirector.add(movie);

            directorMovieMapping.put(director, currentMoviesByDirector);

        }

    }

    public Movie findMovie(String movieName) {
        Movie movie = null;
        if (movieMap.containsKey(movieName)) {
            movie = movieMap.get(movieName);
        }
        return movie;
    }

    public Director findDirector(String director) {
        return directorMap.get(director);
    }

    public List<String> findMoviesFromDirector(String director) {
        List<String> moviesList = new ArrayList<>();
        if (directorMovieMapping.containsKey(director)) {
            moviesList = directorMovieMapping.get(director);
        }
        return moviesList;
    }

    public List<String> findAllMovies() {
        return new ArrayList<>(movieMap.keySet());
    }

    public void deleteDirector(String director) {

        List<String> movies = new ArrayList<String>();
        if (directorMovieMapping.containsKey(director)) {
            //1. Find the movie names by director from the pair
            movies = directorMovieMapping.get(director);

            //2. Deleting all the movies from movieDb by using movieName
            for (String movie : movies) {
                if (movieMap.containsKey(movie)) {
                    movieMap.remove(movie);
                }
            }

            //3. Deleteing the pair
            directorMovieMapping.remove(director);
        }

        //4. Delete the director from directorDb.
        if (directorMap.containsKey(director)) {
            directorMap.remove(director);
        }
    }

    public void deleteAllDirector() {

        HashSet<String> moviesSet = new HashSet<String>();

        //Deleting the director's map
        directorMap = new HashMap<>();

        //Finding out all the movies by all the directors combined
        for (String director : directorMovieMapping.keySet()) {

            //Iterating in the list of movies by a director.
            for (String movie : directorMovieMapping.get(director)) {
                moviesSet.add(movie);
            }
        }

        //Deleting the movie from the movieDb.
        for (String movie : moviesSet) {
            if (movieMap.containsKey(movie)) {
                movieMap.remove(movie);
            }
        }
        //clearing the pair.
        directorMovieMapping = new HashMap<>();
    }

    //Get Director by Movie name from database
//    public String getDirectorByMovieName(String movieName) {
//
//        for (String directorName : directorMovieMapping.keySet()) {
//            List<String> movieList = new ArrayList<>();
//            movieList = directorMovieMapping.get(directorName);
//            for (String name : movieList) {
//                if (name.equals(movieName)) {
//                    return directorName;
//                }
//            }
//        }
//        return null;
//    }

    public String getDirectorByMovieName(String movieName) {
        String director = null ;
        if(movieMap.containsKey(movieName)){
            for( String d : directorMovieMapping.keySet()){
               for( String movie : directorMovieMapping.get(d)){
                   if(movie.equals(movieName)){
                       director = d;
                   }
               }
            }
       }
      return director;
    }

}