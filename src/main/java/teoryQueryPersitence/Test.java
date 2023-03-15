package teoryQueryPersitence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import net.bytebuddy.description.ModifierReviewable;

public class Test {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        //inserisco ciò che ho inserito nel mio persistence.xml -> "mysql-pu"S
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql-pu");
        
        
        //per ogni thread un entity manager e un e. transaction
        EntityManager  entityManager= factory.createEntityManager();       
        EntityTransaction transaction= entityManager.getTransaction();
        
        //cancello la tabella così da ricrearla pulita
        //("DROP TABLE UseEntity");
        
        //creo lo stream del mio file immagine
        File path = new File("C:\\Users\\setti\\Downloads\\CittaEsfondi\\");
        File img= new File(path,"VanGogh.jpg");
        byte[] imgArray= new byte[(int) img.length()];
           
        InputStream inputStream= new FileInputStream(img);
        inputStream.read(imgArray);
                int data,i=0;
                while((data= inputStream.read()) !=-1){
                     imgArray[i]= (byte) data;
                     i++;
                 }
           
        inputStream.close();
        
        //creo lo stream del mio file testo
        String testo="";
        File description= new File(path, "testo.txt");
        if (description.exists()){
            int descriptionSize= (int) description.length();
            char[] descriptionArray= new char[descriptionSize];
            
            Reader reader = new BufferedReader(new FileReader(description));
            reader.read(descriptionArray);
            reader.close();
            String t= new String(descriptionArray);
            testo= t;
        }
        
       try{ 

        

           transaction.begin();
           UserEntity eric= new UserEntity( "Eric", "Cartman", 10,LocalDate.of(2013, Month.DECEMBER, 28));
           UserEntity mario= new UserEntity( "Mario", "Mario", 34,LocalDate.of(1978, Month.MARCH, 10));
           UserEntity fabio= new UserEntity( "Fabio", "Fabiii", 34,LocalDate.of(1988, Month.JULY, 10),testo,imgArray);
           
           
           
           // con questo SALVO il mio record creato rendendolo permanente
           entityManager.persist(eric);     
           entityManager.persist(mario); 
           entityManager.persist(fabio);
             
       
           transaction.commit();
       }catch(Exception e ){
           System.out.println(e.getMessage());
           transaction.rollback();
       }
        
        
        entityManager.clear();
        
        factory.close();
    }
}
