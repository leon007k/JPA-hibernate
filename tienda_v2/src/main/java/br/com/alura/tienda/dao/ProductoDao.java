package br.com.alura.tienda.dao;

import br.com.alura.tienda.modelo.Producto;

import javax.persistence.EntityManager;
import java.util.List;

public class ProductoDao {

    private EntityManager em;

    public ProductoDao(EntityManager em) {
        this.em = em;
    }

    public void guardar(Producto producto){
        this.em.persist(producto);
    }

    public void actualizar(Producto producto){
        this.em.merge(producto);
    }

    public void eliminar(Producto producto){
        producto = this.em.merge(producto);
        this.em.remove(producto);
    }

    public Producto consultaPorId(Long id) {
        return em.find(Producto.class, id);
    }

    public List<Producto> consultarTodosProductos(){
        // * jpql --> son querys que se usan para jpa-hibernate. No se usa '*' porque se debe usar el alias de la tabla
        String jpql = "SELECT P FROM Producto AS P";
        return em.createQuery(jpql, Producto.class).getResultList();
    }
}
