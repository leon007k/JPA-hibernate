package br.com.alura.tienda.tests;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.tienda.dao.CategoriaDao;
import br.com.alura.tienda.dao.ProductoDao;
import br.com.alura.tienda.modelo.Categoria;
import br.com.alura.tienda.modelo.Producto;
import br.com.alura.tienda.utils.JPAUtils;

public class RegistroDeProducto {

	public static void main(String[] args) {
		Categoria celulares  = new Categoria("CELULARES");
		/*Producto celular= new Producto("Samsung","telefono usado",
										new BigDecimal("1000"), Categoria.CELULARES);*/
		Producto celular= new Producto("Samsung","telefono usado",
				new BigDecimal("1000"), celulares);

		EntityManager em = JPAUtils.getEntityManager();

		ProductoDao productoDao = new ProductoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);

	    em.getTransaction().begin();
		categoriaDao.guardar(celulares); // * Permitira guardar los datos de la categoria
	    productoDao.guardar(celular); // * Permitira guardar los datos del producto
	    // *em.getTransaction().commit();
		// * em.flush --> sincroniza los datos con la bd y permite realizar un roll back en caso de errores
		// * em.commit --> los datos que son enviados a la bd, no se podran alterar
	    // * em.close(); --> al usar close o clear, pasan a estado detached, lo cual ya no estarian consideradas para alteraciones o guardarse en la bd.
		// * em.clear(); --> Este a diferencia de close, no sera necesario instanciar de nuevo el EntityManager. Permite ahorrar espacio de memoria
		celulares.setNombre("LIBROS");
		em.flush();
		em.clear();

		// * Permite obtener las entidades en detached al estado managed, para poder hacer alteraciones en la bd
		celulares = em.merge(celulares);
		celulares.setNombre("SOFTWARE");

		em.flush();

	}

}
