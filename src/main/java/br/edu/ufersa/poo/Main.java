package br.edu.ufersa.poo;


import br.edu.ufersa.poo.model.entities.Usuario;
import br.edu.ufersa.poo.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

// classe teste
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();
        try(EntityManager em = emf.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            Usuario u = new Usuario("Dudu", "Dudu1234@gmail.com", "abc132");
            em.persist(u);
            tx.commit();
            System.out.println("ID GERADO: " + u.getId());


        }
        JPAUtil.shutdown();
    }
}