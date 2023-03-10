package teoryPersitence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZonedDateTime;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Test {
    public static void main(String[] args) {
        
        //inserisco ciò che ho inserito nel mio persistence.xml -> "mysql-pu"S
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql-pu");
        
        
        //per ogni thread un entity manager e un e. transaction
        EntityManager  entityManager= factory.createEntityManager();       
        EntityTransaction transaction= entityManager.getTransaction();
        
        //cancello la tabella così da ricrearla pulita
        //("DROP TABLE UseEntity");
        
        
            
       try{ 
//           transaction.begin();
//           UserEntity eric= new UserEntity( "Eric", "Cartman", 10);
//           
           transaction.begin();
           UserEntity eric= new UserEntity( "Eric", "Cartman", 10,LocalDate.of(2013, Month.DECEMBER, 28));
           UserEntity mario= new UserEntity( "Mario", "Mario", 34,LocalDate.of(1988, Month.JULY, 7));
           // con questo SALVO il mio record creato rendendolo permanente
           entityManager.persist(eric);     
           entityManager.persist(mario); 
             
       
        transaction.commit();
       }catch(Exception e ){
           System.out.println(e.getMessage());
           transaction.rollback();
       }
        
        
        entityManager.clear();
        
        factory.close();
    }
}
