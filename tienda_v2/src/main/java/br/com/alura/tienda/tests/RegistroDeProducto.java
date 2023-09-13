package br.com.alura.tienda.tests;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.tienda.dao.CategoriaDao;
import br.com.alura.tienda.dao.ProductoDao;
import br.com.alura.tienda.modelo.Categoria;
import br.com.alura.tienda.modelo.Producto;
import br.com.alura.tienda.utils.JPAUtils;

public class RegistroDeProducto {

	private static EntityManager em = JPAUtils.getEntityManager();
	private static ProductoDao productoDao = new ProductoDao(em);
	private static CategoriaDao categoriaDao = new CategoriaDao(em);
	public static void main(String[] args) {
		guardarProductoPorEstados();


		/*// * Consultamos un producto por ID
		Producto producto = productoDao.consultaPorId(1L);
		System.out.println(producto.getNombre());

		// * consultamos toda la lista de productos
		List<Producto> productos = productoDao.consultarTodosProductos();
		productos.forEach(prod -> System.out.println("Obtenemos lista de productos: " + prod.getNombre()));

		// * Consultamos producto por su nombre
		List<Producto> productoPornombre = productoDao.consultaPorNombre("Iphone 14");
		productoPornombre.forEach(prod -> System.out.println("Filtramos por nombre de producto: " + prod.getNombre()));

		// * Consultamos listado de productos relacionados a una categoria, con el nombre de la categoria
		List<Producto> productosPorCategoria = productoDao.consultaPorNombreCategoria("SOFTWARE");
		productosPorCategoria.forEach(prod -> System.out.println("Listado de productos relacionados a una categoria: " + prod.getNombre()));

		// * Consulta de precio por nombre
		BigDecimal precioDelProducto = productoDao.consultarPrecioPorNombreDeProducto("Iphone 14");
		System.out.println("Obtenemos precio del producto por nombre: " + precioDelProducto);*/
	}

	private static void guardarProductoPorEstados() {
		Categoria celulares  = new Categoria("CELULARES");
		/*Producto celular= new Producto("Samsung","telefono usado",
										new BigDecimal("1000"), Categoria.CELULARES);*/
		Producto celular= new Producto("Samsung","telefono usado",
				new BigDecimal("1000"), celulares);

		em.getTransaction().begin();
		categoriaDao.guardar(celulares); // * Permitira guardar los datos de la categoria
		productoDao.guardar(celular); // * Permitira guardar los datos del producto
		// *em.getTransaction().commit();
		// * em.flush --> sincroniza los datos con la bd y permite realizar un roll back en caso de errores
		// * em.commit --> los datos que son enviados a la bd, no se podran alterar
		// * em.close(); --> al usar close o clear, pasan a estado detached, lo cual ya no estarian consideradas para alteraciones o guardarse en la bd.
		// * em.clear(); --> Este a diferencia de close, no sera necesario instanciar de nuevo el EntityManager. Permite ahorrar espacio de memori
		celulares.setNombre("LIBROS");
		celular.setNombre("Iphone 15");
		em.flush();
		em.clear();

		// * Permite obtener las entidades en detached al estado managed, para poder hacer alteraciones en la bd
		celulares = em.merge(celulares);
		celulares.setNombre("SOFTWARE");
		celular = em.merge(celular);
		celular.setNombre("Iphone 14");
		em.flush();

		// * em.remove --> Nos permitira eliminar un dato que este en estado managed y exista en la bd
		// * em.remove(celular);
		em.flush();
	}

}
