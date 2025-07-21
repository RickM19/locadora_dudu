package br.edu.ufersa.poo.dudu.model.dao;


import br.edu.ufersa.poo.dudu.model.entities.Livro;
import br.edu.ufersa.poo.dudu.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class LivroRepositoryImpl implements LivroRepository {
    private final EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();

    @Override
    public Livro findById(long id) {
        try(EntityManager em = emf.createEntityManager()) {
            return em.find(Livro.class, id);
        } catch (Throwable e) {
            System.err.println("Falha ao criar o EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Livro> findAll() {
        try(EntityManager em = emf.createEntityManager()) {
            return em.createQuery("FROM Livro", Livro.class).getResultList();
        } catch (Throwable e) {
            System.err.println("Falha ao criar o EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Livro l) {
        try(EntityManager em = emf.createEntityManager()) {
            EntityTransaction ts = em.getTransaction();
            ts.begin();
            em.persist(l);
            ts.commit();
        } catch (Throwable e) {
            System.err.println("Falha ao criar o EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Livro l) {
        try(EntityManager em = emf.createEntityManager()) {
            EntityTransaction ts = em.getTransaction();
            ts.begin();
            em.merge(l);
            ts.commit();
        } catch (Throwable e) {
            System.err.println("Falha ao criar o EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Livro l) {
        try(EntityManager em = emf.createEntityManager()) {
            EntityTransaction ts = em.getTransaction();
            ts.begin();
            em.remove(em.contains(l) ? l : em.merge(l));
            ts.commit();
        } catch (Throwable e) {
            System.err.println("Falha ao criar o EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Livro findByTitle(String title) {
        try(EntityManager em = emf.createEntityManager()) {
            TypedQuery<Livro> q = em.createQuery("SELECT l from Livro WHERE l.titulo = :e", Livro.class);
            q.setParameter("e", title);
            return q.getResultStream().findFirst().orElse(null);
        } catch (Throwable e) {
            System.err.println("Falha ao criar o EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Livro findByGenre(String genre) {
        try(EntityManager em = emf.createEntityManager()) {
            TypedQuery<Livro> q = em.createQuery("SELECT l from Livro WHERE l.categoria = :e", Livro.class);
            q.setParameter("e", genre);
            return q.getResultStream().findFirst().orElse(null);
        } catch (Throwable e) {
            System.err.println("Falha ao criar o EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Livro findByYear(int year) {
        try(EntityManager em = emf.createEntityManager()) {
            TypedQuery<Livro> q = em.createQuery("SELECT l from Livro WHERE l.anoPublicacao = :e", Livro.class);
            q.setParameter("e", year);
            return q.getResultStream().findFirst().orElse(null);
        } catch (Throwable e) {
            System.err.println("Falha ao criar o EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Livro findByAuthor(String author) {
        try(EntityManager em = emf.createEntityManager()) {
            TypedQuery<Livro> q = em.createQuery("SELECT l from Livro WHERE l.autor = :e", Livro.class);
            q.setParameter("e", author);
            return q.getResultStream().findFirst().orElse(null);
        } catch (Throwable e) {
            System.err.println("Falha ao criar o EntityManager " + e);
            throw new RuntimeException(e);
        }
    }


}
