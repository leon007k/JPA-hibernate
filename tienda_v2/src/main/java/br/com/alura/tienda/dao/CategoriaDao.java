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

    public void actualizar(Categoria categoria){
        this.em.merge(categoria);
    }

    public void eliminar(Categoria categoria){
        categoria = this.em.merge(categoria); // * Garantizamos que la categoria este en estado managed
        this.em.remove(categoria);
    }
}
