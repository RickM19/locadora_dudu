package br.edu.ufersa.poo.dudu.model.dao;

import br.edu.ufersa.poo.dudu.model.entities.Usuario;
import br.edu.ufersa.poo.dudu.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UsuarioRepositoryImpl implements UsuarioRepository {
    private final EntityManagerFactory emf = JPAUtil.getEntityManagerFactory();

    @Override
    public Usuario findById(Usuario u) {
        try(EntityManager em = emf.createEntityManager()) {
            return em.find(Usuario.class, u.getId());
        } catch (Throwable e) {
            System.err.println("Falha ao criar o EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Usuario> findAll() {
        try(EntityManager em = emf.createEntityManager()) {
            return em.createQuery("FROM Usuario", Usuario.class).getResultList();
        } catch (Throwable e) {
            System.err.println("Falha ao criar o EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Usuario u) {
        try(EntityManager em = emf.createEntityManager()) {
            EntityTransaction ts = em.getTransaction();
            ts.begin();
            em.persist(u);
            ts.commit();
        } catch (Throwable e) {
            System.err.println("Falha ao criar o EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Usuario u) {
        try(EntityManager em = emf.createEntityManager()) {
            EntityTransaction ts = em.getTransaction();
            ts.begin();
            em.merge(u);
            ts.commit();
        } catch (Throwable e) {
            System.err.println("Falha ao criar o EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Usuario u) {
        try(EntityManager em = emf.createEntityManager()) {
            EntityTransaction ts = em.getTransaction();
            ts.begin();
            em.remove(em.contains(u) ? u : em.merge(u));
            ts.commit();
        } catch (Throwable e) {
            System.err.println("Falha ao criar o EntityManager " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Usuario findByUserName(Usuario u) {
        try(EntityManager em = emf.createEntityManager()) {
            TypedQuery<Usuario> q = em.createQuery("SELECT u from Usuario WHERE u.nomeUsuario = :e", Usuario.class);
            q.setParameter("e", u.getNomeUsuario());
            return q.getResultStream().findFirst().orElse(null);
        } catch (Throwable e) {
            System.err.println("Falha ao criar o EntityManager " + e);
            throw new RuntimeException(e);
        }
    }
}
