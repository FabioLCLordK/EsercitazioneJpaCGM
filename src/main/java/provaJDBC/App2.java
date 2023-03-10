package provaJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App2 {
      public static void main(String[] args) throws SQLException {
        
            
            // JDBC va sempre, poi il tipo di database tra : : quindi l'indirizzo
            String url= "jdbc:mysql://localhost:3306/lezionedb";
            String user= "ciccio";
            String password= "ciaociao";
            Connection dbConnection= DriverManager.getConnection(url, user, password);
            
            dbConnection.createStatement();          
            
            // aggiunge lo statemen te richiede l'id generato
            PreparedStatement statement= dbConnection.prepareStatement(
                    "Insert into authors(firstname, lastname) values (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            
       try {
           dbConnection.setAutoCommit(false);
            //aggiungo l'autore
            String authorFirstname= "Giacomo";
            String authorLastname= "Leopardi";
            
            statement.setString(1, authorFirstname);
            statement.setString(2, authorLastname);
            
            int result= statement.executeUpdate();
            System.out.println("result: "+result);
            
            //acquisisco la chiave generata, richiesta in precedenza e la stampo
            ResultSet lastInsertIdResultSet= statement.getGeneratedKeys();
            lastInsertIdResultSet.next();
            long lastInsertId= lastInsertIdResultSet.getLong(1);
            System.out.println("LastInsertId: "+ lastInsertId);
            
            
//            ResultSet rows= statement.executeQuery(
//            "SELECT * FROM authors"
//            );
//            
//            while(rows.next()) {
//                Long id= rows.getLong("id");
//                String lastname= rows.getString("lastname");
//                
//                System.out.println(id+ " "+ lastname);
//            }
         
            //si chiudono tutti gli statement e i database
            statement.close();
            
            dbConnection.commit();
            dbConnection.close();
        } catch (SQLException ex) {
            dbConnection.rollback();
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        
    
 }   
}
