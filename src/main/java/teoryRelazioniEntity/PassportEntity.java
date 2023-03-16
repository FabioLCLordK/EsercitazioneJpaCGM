/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teoryRelazioniEntity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.*;

@Entity 
@Table(name = "Passports")
public class PassportEntity implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "Code", columnDefinition = "VARCHAR(100)")
    private String code;
    
    
    // uso le annotazioni di java persitence per definire le relazioni
    @OneToOne(
            cascade = {
                CascadeType.PERSIST, 
                CascadeType.REMOVE}, //consente il remove a cascata
            fetch = FetchType.LAZY) //fa si che quando si crea la persistence di un record, si fa anche dei record collegati di altre tabelle
    // inoltre usando LAZY sul fetch 
    @JoinColumn(name = "person_id") //join applica la foreinKey alla tabella persons
    private PersonEntity person;

    public PassportEntity() {
    }

    public PassportEntity(String code, PersonEntity person) {
        this.code = code;
        this.person = person;
    }

    @Override
    public String toString() {
        return "PassportEntity{" + "Id=" + Id + ", code=" + code + ", person=" + person + '}';
    }
    
        
    
    
    public Long getId() {
        return Id;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }
 
    
    
}
