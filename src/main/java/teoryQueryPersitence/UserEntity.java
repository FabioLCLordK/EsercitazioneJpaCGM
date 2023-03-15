package teoryQueryPersitence;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/*vorrei fare una tabella più simile possibile a questa

CREATE TABLE users(
    id BIGINT auto_increment,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    age INT,
    CONSTRAINT pk_id
        PRIMARY KEY(id)

*/

//l'annotazione Entity fa si che a questa classe corrisponda una tabella,
//e ogni oggetto della classe Entity deve essere serializzabile

@Entity
@Table(name = "UsersJPC")
public class UserEntity implements Serializable{
    //annotazioni tipo di dato
    @Id //corrisponde a crare la primarykey id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //praticamente auto_increment
    private Long id;
    
    @Column(name = "First_Name", columnDefinition = "VARCHAR(100)")
    private String firstname;
    
    @Column(name = "Last_Name", columnDefinition = "VARCHAR(100)")
    private String lastname; 
    
    @Column(name = "Age",columnDefinition = "Int")
    private Integer age;

    @Column(name = "BirthDate", columnDefinition = "Date")
    private LocalDate birthdate;
    public UserEntity(){       
    }
    
    //creo non delle colonne dove i dati non vengono salvati nelle 
    //colonne della tabe, ma in degli oggetti collegati
    @Lob
    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;
    
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte [] picture;

    public UserEntity(String firstname, String lastname, Integer age, LocalDate birthdate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.birthdate = birthdate;
    }

    public UserEntity(String firstname, String lastname, Integer age, LocalDate birthdate, String description, byte[] picture) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.birthdate = birthdate;
        this.description = description;
        this.picture = picture;
    }
    
    
    public UserEntity(String firstname, String lastname, Integer age) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserEntity{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", age=" + age + ", birthdate=" + birthdate + ", description=" + description + ", picture=" + picture + '}';
    }
    
    
    
    
    
    
    
}
