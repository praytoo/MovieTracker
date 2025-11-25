import java.sql.*;
import java.util.Scanner;

public class WishList {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        // get the connection
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_tracker", "root", "yearup");
        //displayAllMovies(connection);
        displayAllMovies(connection);
        String movieName = promptMovieName();
        connection.close();
        scanner.close();
    }

    public static void displayAllMovies(Connection connection) throws SQLException {
        // create statement
        String query = "SELECT * FROM movies";
        PreparedStatement statement = connection.prepareStatement(query);

        // execute query
        ResultSet rs = statement.executeQuery();

        // process results
        while(rs.next()) {
            System.out.println("Movie id: " + rs.getString("movie_id"));
            System.out.println("Date Released: " + rs.getString("date_released"));
            System.out.println("Genre: " + rs.getString("genre"));
            System.out.println("Cast: " + rs.getString("cast"));
            System.out.println("Title: " + rs.getString("title"));
            System.out.println("Average Percent Rating: " + rs.getInt("avg_percentage_rating"));
            System.out.println("Parental Rating: " + rs.getString("parental_rating"));
            System.out.println("Individual Star Rating: " + rs.getInt("individual_star_rating"));
            System.out.println("Description: " + rs.getString("description"));
            System.out.println("Wishlist id: " + rs.getInt("wishlist_id"));
            System.out.println("Watched List id: " + rs.getInt("watched_id"));
            System.out.println("--------------------");
        }
    }

    public static String promptMovieName() {
        System.out.println("What movie id would you like to add to your wish list?");
        String movieName = scanner.nextLine();
        System.out.println("Adding movie to wishlist...");
        return movieName;
    }

    public static /*Movie*/ Object findMovieByTitle(Connection connection, String movieName) throws SQLException {
        String query = "SELECT * FROM movies WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, movieName);
        // execute query
        ResultSet rs = statement.executeQuery();

        while(rs.next()){
            return null;
            //replace with movie object once we have that
        }
        return null;
        //null stays there in case no movie found
    }

    public static void addMovieToWishList(Connection connection, Object movie) throws SQLException {
        String query = "INSERT INTO wish_list (movie_id, user_id) VALUES (?, ?)";
        //replace x y z with real columns
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, "movie.getMovie_id");
        statement.setString(2, "movie.getUser_id");
        //execute query
        statement.executeUpdate();
    }
}
