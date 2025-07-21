package br.edu.ufersa.poo.dudu.util;


import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
    private static final String PERSISTENCE_UNIT_NAME = "Dudu";
    private static final EntityManagerFactory EMF = buildEntityManagerFactory();

    private static EntityManagerFactory buildEntityManagerFactory() {
        try {
            return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        } catch (Throwable ex) {
            System.err.println("Falha ao criar EntityManager");
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return EMF;
    }

    public static void shutdown() {
        if (EMF != null && EMF.isOpen()) {
            EMF.close();
        }
    }
}
