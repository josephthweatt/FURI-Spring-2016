import java.sql.*;

public class MakeDB {
  public static void main( String args[]) {
    Connection c = null;
    Statement stmt = null;
    try {
      //'forName' is a loader for sqlite.JDBC class
      Class.forName("org.sqlite.JDBC");
      //establishes connection to Database
      c = DriverManager.getConnection("jdbc:sqlite:WeatherData.db");
      System.out.println("Opened database successfully");
      
      String table = "WEATHER " 
              + "(POSTAL INT, "
              + "TIMESTAMP CHAR(26), "
              + "TEMPAVG REAL)";
      
      //statement needs to be connected to the DB
      stmt = c.createStatement();
      //creates command
      String sql = "CREATE TABLE " + table; 
      stmt.executeUpdate(sql);
      //Now we push a new command to add values
      sql = "INSERT INTO WEATHER VALUES " + GrabAPIInfo.giveData();
      stmt.executeUpdate(sql);
      System.out.println("Data written to DB");
      stmt.close();
      c.close();
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
   System.out.println("created table");
  }
}