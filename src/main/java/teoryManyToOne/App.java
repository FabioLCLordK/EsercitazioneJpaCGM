/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teoryManyToOne;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import teoryRelazioniEntity.PassportEntity;
import teoryRelazioniEntity.PersonEntity;

public class App {
    public static void main(String[] args) {
        
        EntityManagerFactory factory= Persistence.createEntityManagerFactory("mysql-pu");
        
        EntityManager entityManager= factory.createEntityManager();
        EntityTransaction transaction= entityManager.getTransaction();
        
        try {
            transaction.begin();
            
           StateEntity italy= new StateEntity("Italy");
           entityManager.persist(italy);
           
           CityEntity turin = new CityEntity("Turin", italy);
           entityManager.persist(turin);
           
           CityEntity palermo = new CityEntity("Palermo", italy);
           entityManager.persist(palermo);
           

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            
            if(transaction.isActive()){
                transaction.rollback();
            }
        }
        
        entityManager.close();
        factory.close();
    }
}