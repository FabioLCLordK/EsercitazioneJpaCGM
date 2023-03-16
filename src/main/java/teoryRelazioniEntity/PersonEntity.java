
package teoryRelazioniEntity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name= "persons")
public class PersonEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "First_Name", columnDefinition = "VARCHAR(100)")
    private String firstname;
    @Column(name = "Last_Name", columnDefinition = "VARCHAR(100)")
    private String lastename;
    
    //il mappedBy sta sempre sull'inner side della relazione
    @OneToOne(mappedBy= "person", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST) //contiene il nome del campo su cui è applicato il mappedby
    //facendo questa cosa possiamo evitare tutto il discorso cascade fatta su passport Entity
    private PassportEntity passport;
    public PersonEntity() {
    }

    public PersonEntity(String firstname, String lastename) {
        this.firstname = firstname;
        this.lastename = lastename;
    }

    public long getId() {
        return id;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastename() {
        return lastename;
    }

    public void setLastename(String lastename) {
        this.lastename = lastename;
    }

    @Override
    public String toString() {
        return "PersonEntity{" + "id=" + id + ", firstname=" + firstname + ", lastename=" + lastename + '}';
    }
    
    
    
}
