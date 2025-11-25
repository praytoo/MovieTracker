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
        System.out.println("What movie would you like to watch?");
        String movieName = scanner.nextLine();
        System.out.println("Loading movie...");
            String line = "NOW PLAYING… " + movieName;
            // pad or trim to 40 characters
            line = String.format("%-40s", line);

            System.out.println("                    ┌──────────────────────────────────────────┐");
            System.out.println("                    │                                          │");
            System.out.println("                    │  " + line + "│");
            System.out.println("                    │                                          │");
            System.out.println("                    └──────────────────────────────────────────┘");
            System.out.println();
            System.out.println();
        return movieName;
    }
}
