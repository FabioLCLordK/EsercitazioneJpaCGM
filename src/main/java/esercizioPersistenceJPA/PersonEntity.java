package esercizioPersistenceJPA;
public class PersonEntity {
    private long id;
    private String firstname;
    private String lastname;
    
    public PersonEntity(){
        
    }

    public PersonEntity(long id, String firstname, String lastname) {
        this.firstname = firstname;
        this.id = id;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "PersonEntity{" + "firstname=" + firstname + ", id=" + id + ", lastname=" + lastname + '}';
    }
    
    
}
