package br.com.alura.tienda.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// * Nos permite conectarnos a la bd
public class JPAUtils {

    private static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("tienda");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }
}
