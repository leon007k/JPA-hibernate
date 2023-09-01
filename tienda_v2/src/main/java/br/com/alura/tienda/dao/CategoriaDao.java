package br.com.alura.tienda.dao;

import br.com.alura.tienda.modelo.Categoria;

import javax.persistence.EntityManager;

public class CategoriaDao {

    private EntityManager em;

    public CategoriaDao(EntityManager em){
        this.em = em;
    }

    public void guardar(Categoria categoria){
        this.em.persist(categoria);
    }
}
