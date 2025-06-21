package br.edu.ufersa.poo.model.dao;

import br.edu.ufersa.poo.model.entities.Usuario;
import br.edu.ufersa.poo.util.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UsuarioRepositoryImpl implements UsuarioRepository {
    private static final SessionFactory sf = HibernateUtil.getSessionFactory();

    @Override
    public Usuario findById(long id) {
        try(EntityManager em = sf.createEntityManager()) {
            return em.find(Usuario.class, id);
        }
    }

    @Override
    public List<Usuario> findAll() {
        try(EntityManager em = sf.createEntityManager()) {
            return em.createQuery("FROM Usuario", Usuario.class).getResultList();
        }
    }

    @Override
    public void save(Usuario u) {

    }

    @Override
    public void update(Usuario u) {

    }

    @Override
    public void delete(Usuario u) {

    }

    @Override
    public void findByUserName(String userName) {

    }

    @Override
    public Usuario findById()

}
