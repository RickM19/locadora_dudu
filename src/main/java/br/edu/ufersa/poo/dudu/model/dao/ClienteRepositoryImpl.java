package br.edu.ufersa.poo.dudu.model.dao;

import br.edu.ufersa.poo.dudu.model.entities.Cliente;
import br.edu.ufersa.poo.dudu.util.JPAUtil;
import jakarta.persistence.*;

import java.util.List;

public class ClienteRepositoryImpl implements ClienteRepository {
    private final EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();

    @Override
    public Cliente findByCpf(String cpf){
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Cliente.class, cpf);
        }catch (Throwable e) {
           System.err.println("Falha ao criar EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Cliente findByName(String nome){
        try (EntityManager em = emf.createEntityManager()) {
            return em.find(Cliente.class, nome);
        }catch (Throwable e) {
            System.err.println("Falha ao criar EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Cliente> findAll(){
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("FROM Cliente", Cliente.class).getResultList();
        }catch (Throwable e){
            System.err.println("Falha ao criar EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Cliente cliente){
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction ts = em.getTransaction();
            ts.begin();
            em.persist(cliente);
            ts.commit();
        }catch (Throwable e){
            System.err.println("Falha ao criar EntityManager " + e);
            throw new RuntimeException(e);
            }
        }

        @Override
    public void update(Cliente cliente) {
            try (EntityManager em = emf.createEntityManager()) {
                EntityTransaction ts = em.getTransaction();
                ts.begin();
                em.merge(cliente);
                ts.commit();
            }catch (Throwable e) {
                System.err.println("Falha ao criar EntityManager " + e);
                throw new RuntimeException(e);
                }
            }

        @Override
    public void delete(Cliente cliente) {
            try (EntityManager em = emf.createEntityManager()) {
                EntityTransaction ts = em.getTransaction();
                ts.begin();
                em.remove(em.contains(cliente) ? cliente : em.merge(cliente));
                ts.commit();
            } catch (Throwable e) {
                System.err.println("Falha ao criar EntityManager " + e);
                throw new RuntimeException(e);
            }
        }
}


