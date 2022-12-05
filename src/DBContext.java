import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {
   // private static final Logger log = LoggerFactory.getLogger(DBContext.class);
    private Connection conn = null;
    public void close() {
        if (conn != null) {
            try {
                //log.info("Closing database connection to bookStoreDB");
                System.out.println("Closing database connection to DB");
                conn.close();
            } catch (SQLException ex) {
                //log.error("Unable to close connection", ex);
                System.err.format("Closing database connection to DB", ex.getSQLState(), ex.getMessage());
            }
        conn = null;
        }
    }  
    public Connection getConnection() throws SQLException {
        if (conn == null) {
            //log.info("Opening connection to bookStoreDB");
            System.out.println("Opening connection to DB");
            conn = DriverManager.getConnection(
                "jdbc:oracle:thin:@ora4.ii.pw.edu.pl:1521/pdb1.ii.pw.edu.pl", "z21", "n7x7cs");
        }
        return conn;
    }
}      