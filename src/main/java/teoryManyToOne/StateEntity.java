/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teoryManyToOne;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author LordKazor
 */
@Entity 
@Table(name= "States")
public class StateEntity implements Serializable {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    
    @OneToMany(mappedBy = "state", fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private List<CityEntity> cities;
    
    
    public StateEntity(String name) {
        this.name = name;
    }

    public StateEntity() {
        cities= new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
    
}
