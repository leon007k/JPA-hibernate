package br.com.alura.tienda.dao;

import br.com.alura.tienda.modelo.Producto;

import javax.persistence.EntityManager;

public class ProductoDao {

    private EntityManager em;

    public ProductoDao(EntityManager em) {
        this.em = em;
    }

    public void guardar(Producto producto){
        this.em.persist(producto);
    }
}
