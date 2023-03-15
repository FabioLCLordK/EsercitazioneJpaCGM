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

public class Test2 {
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

//        
//
             transaction.begin();
            //ALTRO MODO DI FARE FOUND ENDITY
//           System.out.println("\n \n \n");
//           UserEntity foundE=entityManager.find(UserEntity.class,3L);
//           System.out.println(foundE);
//           
//           UserEntity foundE2=entityManager.find(UserEntity.class,3L);
//           System.out.println(foundE2);
       
           //UserEntity eric = new UserEntity("Eric", "Cartman"); // new //entityManager.persist(eric); // managed 
            UserEntity foundEntity = entityManager.find(UserEntity.class, 10);
            System.out.println(foundEntity); 
            entityManager.detach(foundEntity); 
            // operazioni su foundEntity 
            System.out.println("foundEntity nello stato detached"); 
            foundEntity.setFirstname("Kenny"); 
            /** 
             * UserEntity foundEntityAgain = entityManager.find(UserEntity.clas , foundEntity.getId()); 
             * foundEntityAgaXn passa nello stato managed 
             * persistence context confronta i valori dei campi in foundEntity.again con i valori dei campi in foundEntity 
             * persistence context se rileva variazioni nei campi foundEndityAgain fa un update
             * viene eliminato dal persistence context found entity
             */ 
            entityManager.merge(foundEntity); 

           
           
           
           transaction.commit();
       }catch(Exception e ){
           System.out.println(e.getMessage());
           transaction.rollback();
       }
        
        
        entityManager.clear();
        
        factory.close();
    }
}
