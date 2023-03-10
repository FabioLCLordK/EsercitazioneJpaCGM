package esercizioPersistenceJPA;

import com.mysql.cj.xdevapi.Result;
import com.sun.source.tree.LabeledStatementTree;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class PersonDAO {
    private Connection connection;
    private PreparedStatement crEnStatement;
    private PreparedStatement fAEStatement;
    private PreparedStatement fEBStatement;
    private PreparedStatement fEbFeLabeledStatement ;

    public PersonDAO(Connection connection) throws SQLException {
        this.connection = connection;
        this.crEnStatement = connection.prepareStatement("Insert into persons(firstname,lastname) VALUES(?,?)");
        this.fAEStatement = connection.prepareStatement("Select * from persons");
        this.fEBStatement = connection.prepareStatement("Select * from persons WHERE id=?");
        this.fEbFeLabeledStatement = connection.prepareStatement("Select * from persons WHERE firstname like '%A' & lastname like '%E%'");
    }
    
    public void create(PersonEntity entity) throws SQLException{
        crEnStatement.setString(1, entity.getFirstname());
        crEnStatement.setString(2, entity.getLastname());
        crEnStatement.executeUpdate();

    }
    
    public Collection <PersonEntity> readAll() throws SQLException{
        
        List<PersonEntity> pEntities= new LinkedList<>();
        
        ResultSet resSet= fAEStatement.executeQuery();
        while(resSet.next()){
            Long id = resSet.getLong("id");
            String firstname= resSet.getString("firstname");
            String lastname= resSet.getString("lastname");
            
            pEntities.add(new PersonEntity(id, firstname, lastname));
        }
        
        
        
        return pEntities;
        }
    }

