package br.edu.ufersa.poo.dudu.model.dao;

import br.edu.ufersa.poo.dudu.model.entities.Disco;
import br.edu.ufersa.poo.dudu.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class DiscoRepositoryImpl implements DiscoRepository{
    private final EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();

    @Override
    public Disco findByBandName(Disco d){
        try(EntityManager em = emf.createEntityManager()) {
            TypedQuery<Disco> tq = em.createQuery("SELECT disco from Disco disco WHERE disco.nomeBanda = :e", Disco.class);
            tq.setParameter("e", d.getNomeBanda());
            return tq.getResultStream().findFirst().orElse(null);
        }catch(Throwable e){
            System.err.println("Falha ao criar EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Disco findById(Disco d){
        try(EntityManager em = emf.createEntityManager()){
            return em.find(Disco.class, d.getId());
        }catch (Throwable e){
            System.err.println("Falha ao criar EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Disco> findAll(){
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("FROM Disco", Disco.class).getResultList();
        }catch (Throwable e){
            System.err.println("Falha ao criar EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Disco findByTitle(Disco d){
        try(EntityManager em = emf.createEntityManager()) {
            TypedQuery<Disco> tq = em.createQuery("SELECT disco from Disco disco WHERE disco.titulo = :e", Disco.class);
            tq.setParameter("e", d.getTitulo());
            return tq.getResultStream().findFirst().orElse(null);
        }catch(Throwable e){
            System.err.println("Falha ao criar EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Disco findByGenre(Disco d){
        try(EntityManager em = emf.createEntityManager()) {
            TypedQuery<Disco> tq = em.createQuery("SELECT disco from Disco disco WHERE disco.categoria = :e", Disco.class);
            tq.setParameter("e", d.getCategoria());
            return tq.getResultStream().findFirst().orElse(null);
        }catch(Throwable e){
            System.err.println("Falha ao criar EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Disco disco){
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction ts = em.getTransaction();
            ts.begin();
            em.persist(disco);
            ts.commit();
        }catch (Throwable e){
            System.err.println("Falha ao criar EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Disco disco) {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction ts = em.getTransaction();
            ts.begin();
            em.merge(disco);
            ts.commit();
        }catch (Throwable e) {
            System.err.println("Falha ao criar EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Disco disco) {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction ts = em.getTransaction();
            ts.begin();
            em.remove(em.contains(disco) ? disco : em.merge(disco));
            ts.commit();
        } catch (Throwable e) {
            System.err.println("Falha ao criar EntityManager " + e);
            throw new RuntimeException(e);
        }
    }
}
