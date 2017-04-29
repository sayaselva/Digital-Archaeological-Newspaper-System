/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package digital.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Sayanthini
 */
public class DBService {
    
    public static void main(String[] args) throws SQLException {
       DBService service = new DBService();
       
       Summary Sumary = new Summary();
       Sumary.setSummary("Some text");
        Sumary.setAuthor("Sayanthini");
       service.insert( Sumary);
    }
    
    public void insert(Summary summary) throws SQLException{
        Connection conn = DBConnection.getConnection();
        
        String query = " insert into summary ( Sumary, Date, Author)"
        + " values (?, ?, ?)";

      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      preparedStmt.setString(1, summary.getSumary());
      preparedStmt.setDate(2, summary.getDate());
      preparedStmt.setString(3, summary.getAuthor());
//      preparedStmt.setBoolean(4, false);
//      preparedStmt.setInt(5, 5000);

      // execute the preparedstatement
      preparedStmt.execute();
      
      conn.close();
    }
}
