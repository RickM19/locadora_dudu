package br.edu.ufersa.poo.dudu.model.dao;

import br.edu.ufersa.poo.dudu.model.entities.Aluguel;
import br.edu.ufersa.poo.dudu.model.entities.ItemAlugado;
import br.edu.ufersa.poo.dudu.util.JPAUtil;
import jakarta.persistence.*;

import java.util.List;

public class ItemAlugadoRepositoryImpl implements ItemAlugadoRepository {
    private final EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();

    @Override
    public ItemAlugado findById(ItemAlugado item) {
        try (EntityManager em = emf.createEntityManager()){
            return em.find(ItemAlugado.class, item.getId());
        }catch (Throwable e){
            System.err.println("Falha ao criar EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public ItemAlugado findByName(ItemAlugado item) {
        try (EntityManager em = emf.createEntityManager()){
            return em.find(ItemAlugado.class, item.getNomeItem());
        }catch (Throwable e){
            System.err.println("Falha ao criar EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ItemAlugado> findByRental(Aluguel aluguel) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery(
                    "SELECT i FROM ItemAlugado i WHERE i.aluguel = :aluguel", ItemAlugado.class)
                    .setParameter("aluguel", aluguel)
                    .getResultList();
        }catch (Throwable e){
            System.err.println("Falha ao criar EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ItemAlugado> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("FROM ItemAlugado", ItemAlugado.class).getResultList();
        }catch (Throwable e){
            System.err.println("Falha ao criar EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(ItemAlugado item) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(item);
            em.getTransaction().commit();
        }catch(Throwable e){
            System.err.println("Falha ao criar EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(ItemAlugado item) {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction ts = em.getTransaction();
            ts.begin();
            em.merge(item);
            ts.commit();
        }catch (Throwable e) {
            System.err.println("Falha ao criar EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(ItemAlugado item) {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction ts = em.getTransaction();
            ts.begin();
            em.remove(em.contains(item) ? item : em.merge(item));
            ts.commit();
        } catch (Throwable e) {
            System.err.println("Falha ao criar EntityManager " + e);
            throw new RuntimeException(e);
        }
    }
}
