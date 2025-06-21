package br.edu.ufersa.poo;

import br.edu.ufersa.poo.model.entities.Livro;
import br.edu.ufersa.poo.model.entities.Usuario;
import br.edu.ufersa.poo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
// classe teste
public class Main {
    public static void main(String[] args) {
        SessionFactory sf = HibernateUtil.getSessionFactory();

        try (Session session = sf.openSession()) {
            Transaction tx = session.beginTransaction();
            Usuario user = new Usuario("João", "jodbc@gmail.com", "1234abc");
            session.persist(user);
            tx.commit();
            System.out.println("ID gerado: " + user.getId());
            session.close();
        }

        try(Session session = sf.openSession()) {
           Transaction tx = session.beginTransaction();
           Livro livro = new Livro();
           livro.setAutor("Shakespeare");
           livro.setAnoPublicacao(2000);
           livro.setCategoria("Romance");
           livro.setTitulo("Romeu e Julieta");
           livro.setQtdPaginas(200);
           livro.setQtdExemplares(3);
           livro.setQtdDisponivelAluguel(3);
           livro.setValorAluguel(20);
           session.persist(livro);
           tx.commit();
           System.out.println("ID gerado: " + livro.getId());
           session.close();
        }

    }
}
