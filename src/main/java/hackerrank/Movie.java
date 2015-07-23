package hackerrank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Movie {
    private final int movieId;
    private final float rating;
    private List<Movie> similarMovies; // Similarity is bidirectional

    public Movie(int movieId, float rating) {
        this.movieId = movieId;
        this.rating = rating;
        similarMovies = new ArrayList<Movie>();
    }

    public int getId() {
        return movieId;
    }

    public float getRating() {
        return rating;
    }

    public void addSimilarMovie(Movie movie) {
        similarMovies.add(movie);
        movie.similarMovies.add(this);
    }

    public List<Movie> getSimilarMovies() {
        return similarMovies;
    }

    /*
     * Implement a function to return top rated movies in the network of movies
     * reachable from the current movie
     * eg:             A(Rating 1.2)
     *               /   \
     *            B(2.4)  C(3.6)
     *              \     /
     *                D(4.8)
     * In the above example edges represent similarity and the number is rating.
     * getMovieRecommendations(A,2) should return C and D (sorting order doesn't matter so it can also return D and C)
     * getMovieRecommendations(A,4) should return A, B, C, D (it can also return these in any order eg: B,C,D,A)
     * getMovieRecommendations(A,1) should return D. Note distance from A to D doesn't matter, return the highest rated.
     *
     *     @param movie
     *     @param numTopRatedSimilarMovies
     *                      number of movies we want to return
     *     @return List of top rated similar movies
     */
    public static List<Movie> getMovieRecommendations(Movie movie, int numTopRatedSimilarMovies) {
        // create empty list of the same type to leave the existing similarity list intact
        List<Movie> recomendations = Collections.emptyList();
        // copy existing similarities into it
        Collections.copy(recomendations, movie.getSimilarMovies());
        // add the movie itself to make it bidirectional
        Collections.addAll(recomendations, movie);

        // should throw some exception when numTopRatedSimilarMovies is more than size of similiarMovies + 1
        // *which is the recomendations size
        if (numTopRatedSimilarMovies > recomendations.size()) {
            // I remark this because it will change the signature of this method, but I suggest to add it.
            //throw new Exception("numTopRatedSimilarMovies is more than the movie collection size");
        }

        // sort movies by its rating, we may need to order it anyway as it would be a top rated recomendations
        // so order of the movies by its rating I assume it is matter.
        Collections.sort(recomendations, new Comparator<Movie>() {
            @Override
            public int compare(Movie first, Movie second) {
                // return the order value *positive means higher, negative means lower
                // I compare floats division using epsilon (could be doubled though) as
                // referred in http://en.wikipedia.org/wiki/Machine_epsilon#Values_for_standard_hardware_floating_point_arithmetics
                // this is the recommended solution for comparing float or double typed collection for presicion.
                return (Math.abs(first.getRating() / second.getRating()) < 5.96e-08) ?
                        -1 : 1;
            }
        });
        // Java 8
        //Collections.sort(recomendations, (Movie first, Movie second) -> (Math.abs(first.getRating() / second.getRating()) < 5.96e-08) ?
        //        -1 : 1);

        // sublist by value of numTopRatedSimilarMovies to return top numRatedSimilarMovies of the recomandations collection.
        // here I create a new implementation of a List<Movies> because Collection.subList will only be a view,
        // so the rest of the items would be GC-ed.
        return new ArrayList<>(recomendations.subList(0, numTopRatedSimilarMovies));
    }
}