package teoryPersitence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test {
    public static void main(String[] args) {
        
        //inserisco ciò che ho inserito nel mio persistence.xml -> "mysql-pu"S
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("mysql-pu");
        
        
        factory.close();
    }
}
