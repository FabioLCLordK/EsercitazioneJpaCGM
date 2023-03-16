/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teoryRelazioniEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author LordKazor
 */
public class Main {
    public static void main(String[] args) {
        
        EntityManagerFactory factory= Persistence.createEntityManagerFactory("mysql-pu");
        
        EntityManager entityManager= factory.createEntityManager();
        EntityTransaction transaction= entityManager.getTransaction();
        
        try {
            transaction.begin();
            
            
            PersonEntity batman= new PersonEntity("Fabio","Waine");
            //entityManager.persist(batman);

            PassportEntity batmanPass= new PassportEntity("01A1001", batman);
            entityManager.persist(batmanPass);


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
