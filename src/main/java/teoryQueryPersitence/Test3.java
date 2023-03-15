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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import net.bytebuddy.description.ModifierReviewable;

public class Test3 {
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
//           UserEntity eric= new UserEntity( "Eric", "Cartman", 10,LocalDate.of(2013, Month.DECEMBER, 28));
//           UserEntity mario= new UserEntity( "Mario", "Mario", 34,LocalDate.of(1978, Month.MARCH, 10));
//           UserEntity fabio= new UserEntity( "Fabio", "Fabiii", 34,LocalDate.of(1988, Month.JULY, 10),testo,imgArray);
//           
//           entityManager.persist(eric);     
//           entityManager.persist(mario); 
//           entityManager.persist(fabio);
            System.out.println("\n \n \n");
            
            // MODO UNO
//            // nella query in JPQL u sostituisce *, puoi usare qualunque cosa al posto di u
//            Query query1= entityManager.createQuery("SELECT u.firstname FROM UserEntity u");
//            
//            //Faccio un cast dalla lista del get Result alla lista di UserEntity
//            List<UserEntity> results= (List<UserEntity>) query1.getResultList();
            
            
            // MODO DUE
//            TypedQuery<UserEntity> query2= entityManager.createQuery("SELECT u FROM UserEntity u where u.lastname like '%man'", UserEntity.class);
//            
//            List<UserEntity> results= query2.getResultList();
//            for(UserEntity entity: results){
//                     System.out.println(entity);
//            }

            // MODO TRE
            TypedQuery<Tuple> query3= entityManager.createQuery("SELECT u.firstname,u.lastname FROM UserEntity u",Tuple.class);
            List<Tuple> results= query3.getResultList();  
            
            for(Tuple tuple: results){
                System.out.printf("%s %s\n",tuple.get(0),tuple.get(1));
            }
            
           transaction.commit();
       }catch(Exception e ){
           System.out.println(e.getMessage());
           transaction.rollback();
       }
        
        
        entityManager.clear();
        
        factory.close();
    }
}
