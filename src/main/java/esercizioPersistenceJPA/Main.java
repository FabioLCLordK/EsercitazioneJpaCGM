/*
// classe PersonDAO
// metodo create(), metodo findById(), metodo findByFirstnameAndLastname()


// classe AddressDAO
// metodo create(), metodo findByPersonId()

create le tabelle manualmente persons e addresses



*/
package esercizioPersistenceJPA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;

public class Main {
    public static void main(String[] args) throws SQLException {
        
        String url= "jdbc:mysql://localhost:3306/lezionedb";
        String user= "ciccio";
        String password= "ciaociao";
        Connection connection= DriverManager.getConnection(url, user, password);
        
        PersonDAO pDao= new PersonDAO(connection);
        
        PersonEntity fabio= new PersonEntity(1,"Fabio","La Ciura");
        pDao.create(fabio);
        
        PersonEntity sara= new PersonEntity(2,"Sara","Ciao");
        pDao.create(sara);
        
        
        Collection<PersonEntity> foundPentities= pDao.readAll();
        for(PersonEntity fPentity: foundPentities){
            System.out.println(fPentity);
        }
        
        
    }
}
