import java.sql.*;
import java.util.HashMap;
import java.util.Map;

    public class JDBC {
        public static void main(String[] args) {

            try(Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/movie_tracker", "root", "yearup");
                PreparedStatement statement = connection.prepareStatement("SELECT movie_title FROM movies");
            ) {
                Map<String, String> columns = new HashMap();
                try(ResultSet rs = statement.executeQuery()) {
                    for(int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        columns.put(rs.getMetaData().getColumnName(i), rs.getMetaData().getColumnTypeName(i));
                    }

                    while (rs.next()) {
                        for(String columnKey : columns.keySet()) {
                            if(columns.get(columnKey).equals("VARCHAR") || columns.get(columnKey).toUpperCase().contains("INT")) {
                                System.out.println(rs.getString(columnKey));
                            }
                        }
                        // make it throw exception, handled by catch of the outer block

                    }
                }

            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        }
}
