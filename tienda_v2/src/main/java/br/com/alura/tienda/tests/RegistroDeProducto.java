package br.com.alura.tienda.tests;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

	    em.getTransaction().begin();
	    productoDao.guardar(celular);
	    em.getTransaction().commit();
	    em.close();
	}

}
