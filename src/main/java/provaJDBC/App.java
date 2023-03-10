package provaJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

public class App {
    public static void main(String[] args) {
        try {
            
            // JDBC va sempre, poi il tipo di database tra : : quindi l'indirizzo
            String url= "jdbc:mysql://localhost:3306/lezionedb";
            String user= "ciccio";
            String password= "ciaociao";
            Connection dbConnection= DriverManager.getConnection(url, user, password);
            
            dbConnection.createStatement();
            
            //create statement crea un contentitore dove scrivere uno statement SQL
            //Statement statement= dbConnection.createStatement();
            //statement.executeQuery("");
            
            PreparedStatement statement= dbConnection.prepareStatement(
                    "SELECT * FROM authors WHERE lastname=?");
            
                   
            //inseriramo quindi i comandi SQL
            ResultSet rows= statement.executeQuery(
            "SELECT * FROM users"
            );
            
            while(rows.next()) {
                Long id= rows.getLong("id");
                String lastname= rows.getString("lastname");
                
                System.out.println(id+ " "+ lastname);
            }
            
            //si chiudono tutti gli statement e i database
            statement.close();
            dbConnection.close();
        } catch (SQLException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    
 }   
}
