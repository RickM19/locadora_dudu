package br.edu.ufersa.poo.dudu.model.dao;

import br.edu.ufersa.poo.dudu.model.entities.Aluguel;
import br.edu.ufersa.poo.dudu.model.entities.Cliente;
import br.edu.ufersa.poo.dudu.util.JPAUtil;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

public class AluguelRepositoryImpl implements AluguelRepository {
    private final EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();

    @Override
    public Aluguel findById(Aluguel aluguel) {
        try (EntityManager em = emf.createEntityManager()){
            return em.find(Aluguel.class, aluguel.getIdAluguel());
        }catch (Throwable e){
            System.err.println("Falha ao criar EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Aluguel> findByClient(Cliente cliente) {
        try (EntityManager em = emf.createEntityManager()){
            return em.createQuery(
                    "SELECT a FROM Aluguel a WHERE a.cliente = :cliente", Aluguel.class)
                    .setParameter("cliente", cliente)
                    .getResultList();
        }catch (Throwable e){
            System.err.println("Falha ao criar EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Aluguel> findByPeriod(LocalDate dataInicio, LocalDate dataFim) {
        if(!dataFim.isBefore(dataInicio)){
            try (EntityManager em = emf.createEntityManager()) {
                return em.createQuery(
                        "SELECT a FROM Aluguel a WHERE a.dataInicio BETWEEN :inicio AND :fim", Aluguel.class)
                        .setParameter("inicio", dataInicio)
                        .setParameter("fim", dataFim)
                        .getResultList();
            } catch (Throwable e) {
                System.err.println("Falha ao criar EntityManager " + e);
                throw new RuntimeException(e);
            }
        } else
            throw new IllegalArgumentException("A data de início deve ser antes da data de fim.");
    }

    @Override
    public List<Aluguel> findActive() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery(
                    "SELECT a FROM Aluguel a WHERE a.finalizado = false",
                    Aluguel.class).getResultList();
        }catch (Throwable e){
            System.err.println("Falha ao criar EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Aluguel> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("FROM Aluguel", Aluguel.class).getResultList();
        }catch (Throwable e){
            System.err.println("Falha ao criar EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Aluguel aluguel) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(aluguel);
            em.getTransaction().commit();
        }catch(Throwable e){
            System.err.println("Falha ao criar EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Aluguel aluguel) {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction ts = em.getTransaction();
            ts.begin();
            em.merge(aluguel);
            ts.commit();
        }catch (Throwable e) {
            System.err.println("Falha ao criar EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Aluguel aluguel) {
        try (EntityManager em = emf.createEntityManager()) {
            EntityTransaction ts = em.getTransaction();
            ts.begin();
            em.remove(em.contains(aluguel) ? aluguel : em.merge(aluguel));
            ts.commit();
        } catch (Throwable e) {
            System.err.println("Falha ao criar EntityManager " + e);
            throw new RuntimeException(e);
        }
    }
}
