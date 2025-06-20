package br.edu.ufersa.poo;

import br.edu.ufersa.poo.model.entities.Usuario;
import br.edu.ufersa.poo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        SessionFactory sf = HibernateUtil.getSessionFactory();

        try (Session session = sf.openSession()) {
            Transaction tx = session.beginTransaction();
            Usuario user = new Usuario("João", "jodbc@gmail.com", "1234abc");
            session.persist(user);
            tx.commit();
            System.out.println("ID gerado" + user.getId());
        }
    }
}
