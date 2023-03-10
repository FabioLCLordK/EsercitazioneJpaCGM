package teoryPersitence;

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
        
        transaction.begin();
            
        
        //operazioni da inviare
        //operazione 1
        //operazione 2
        //operazione 3
        transaction.commit();
        entityManager.clear();
        
        factory.close();
    }
}
