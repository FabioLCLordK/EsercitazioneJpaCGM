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
public class Main1 {
    public static void main(String[] args) {
        
        EntityManagerFactory factory= Persistence.createEntityManagerFactory("mysql-pu");
        
        EntityManager entityManager= factory.createEntityManager();
        EntityTransaction transaction= entityManager.getTransaction();
        
        try {
            transaction.begin();
            System.out.println("\n LA MIA TRANSACTION \n");
            //carica i dati dalla tabella passaporto corrispondente a quell'ID
            PassportEntity foundPassportEntity= entityManager.find(PassportEntity.class, 2L); 
            System.out.println(foundPassportEntity);
            
            System.out.println(foundPassportEntity.getId());
            System.out.println(foundPassportEntity.getCode());
            System.out.println(foundPassportEntity.getPerson().getFirstname());
            
            
            System.out.println("\n FINE TRANSACTION \n");
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
