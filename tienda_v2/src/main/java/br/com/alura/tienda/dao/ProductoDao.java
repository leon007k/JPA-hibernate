package br.com.alura.tienda.dao;

import br.com.alura.tienda.modelo.Categoria;
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

    public List<Producto> consultaPorNombre(String nombre){
        String jpql = "SELECT P FROM Producto AS P " +
                "WHERE P.nombre =: nombre";
        return em.createQuery(jpql).setParameter("nombre", nombre).getResultList();
    }

    public List<Producto> consultaPorNombreCategoria(String nombre){
        /*
        * En este query logramos obtener el listado de productos
        * relacionados a una categoria. En el query indicamos la tabla producto
        * pero agregamos un filtro donde se le proporciona el nombre de la categoria
        * para obtener todos los productos relacioandas a esta
        * */
        String jpql = "SELECT P FROM Producto AS P WHERE P.categoria.nombre=:nombre";
        return em.createQuery(jpql).setParameter("nombre",nombre).getResultList();
    }
}
