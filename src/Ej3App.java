import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Ej3App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Creamos la hashtable de 10 articulos
		Hashtable<String, Integer> articulos= new Hashtable <String, Integer>();
		stock(articulos);
		/**
		 *Cramos un menu, con un bucle while y la sentencia switch, el bucle nos permite 
		 *tener la opción de salir, y el switch nos permite seleccionar la opción del menú
		 **/
		boolean salir = false;

		while (!salir) {

			String numero = JOptionPane.showInputDialog("Introduce: 1->Añadir producto 2->Añadir Cantidad"
					+ " 3->Consultar producto 4->Cantidades totales 5-> Eliminar productos 6->Salir" );
			int num = Integer.parseInt(numero);

			switch (num) {
			case 1:
				añadirProducto(articulos);
				break;
			case 2:
				añadirCantidad(articulos);
				break;
			case 3:
				consultarProducto(articulos);
				break;
			case 4:	
				listarProductos(articulos);
				break;
			case 5:
				eliminarProductos(articulos);
				break;
			case 6:
				salir = true;
				break;
			default:
				JOptionPane.showMessageDialog(null, "Números entre 1 --> 6");
			} 
		}
	}


	public static Hashtable<String, Integer> stock(Hashtable<String, Integer> articulos) {

		//Introducimos los 10 productos que tenemos en stock
		articulos.put("samsung", 15);
		articulos.put("iphone", 10);
		articulos.put("sony", 60);
		articulos.put("lg", 19);
		articulos.put("lenovo", 30);
		articulos.put("xiaomi", 45);
		articulos.put("nokia", 32);
		articulos.put("huawei", 25);
		articulos.put("motorola", 99);
		articulos.put("oppo", 69);

		return articulos;

	}

	public static void añadirProducto(Hashtable<String, Integer> articulos) {

		//Pedimos por teclado el nombre del articulo
		String producto = JOptionPane.showInputDialog("Escribe el nombre del producto");
		producto = producto.toLowerCase();

		//Recorremos el hashtable, sino lo encuentra lo añade al hashtable
		if(articulos.containsKey(producto)) {
			JOptionPane.showMessageDialog(null, "Ya existe el producto");
		} else {
			articulos.put(producto, 0);
			JOptionPane.showMessageDialog(null, "Se ha introducido el producto");
		}
	}

	public static void añadirCantidad(Hashtable<String, Integer> articulos) {

		int stockActual;

		//Pedimos por teclado el nombre del articulo
		String producto = JOptionPane.showInputDialog("Escribe el nombre del producto");
		producto = producto.toLowerCase();

		/**
			Recorremos el hashtable, cuando lo encuantra nos pide la cantidad a introducir
			si la cantidad introducida es superior a 0 añadira estoc al producto
			por el contrario no indica que la entrada es incorrecta, 
			también nos indicara si el producto no existe
		 **/
		if (articulos.containsKey(producto)) {
			String cantidad = JOptionPane.showInputDialog("Introduce la cantidad");
			int stock = Integer.parseInt(cantidad);

			if (stock>0) {
				stockActual = articulos.get(producto);
				articulos.put(producto, stockActual+stock);
				JOptionPane.showMessageDialog(null, "Has añadido " +stock+ " del producto " +producto);
			} else {
				JOptionPane.showMessageDialog(null, "El número introducido es negativo");
			}
		} else {
			JOptionPane.showMessageDialog(null, "El producto no existe");
		}
	}

	public static void consultarProducto(Hashtable<String, Integer> articulos){

		String producto = JOptionPane.showInputDialog("Escribe el nombre del producto que quiere consultar");
		producto = producto.toLowerCase();
		/**
		 * Si el producto introducido existe, nos mostrará el artículo y su estoc, si no nos muestra un
		 * banner diciendo que no existe
		 */
		if(articulos.containsKey(producto)) {
			JOptionPane.showMessageDialog(null, "El producto " +producto+ " tiene " +articulos.get(producto)+ " articulos");
		} else {
			JOptionPane.showMessageDialog(null, "El producto no existe");
		}
	}

	public static void listarProductos(Hashtable<String, Integer> articulos) {

		JOptionPane.showMessageDialog(null, "Se va a listar todos los productos en stock");
		//Con un bucle for recorre todo el hashtable y nos muestra todos los productos y su stock
		for(String p : articulos.keySet()) {

			int stock = articulos.get(p);

			JOptionPane.showMessageDialog(null, "El articulo " +p+ ", tiene " +stock+ " de stock" );
		}
	}

	public static void eliminarProductos(Hashtable<String, Integer> articulos) {

		String producto = JOptionPane.showInputDialog(null, "Escribe el producte que quieres eliminar");
		producto = producto.toLowerCase();

		/**
		 * Si el producto introducido existe, eliminaral el artículo y su estoc, si no nos muestra un
		 * banner diciendo que no existe
		 */
		if(articulos.containsKey(producto)) {
			articulos.remove(producto);
			JOptionPane.showMessageDialog(null, "El " +producto+ ", se elimino del stock");
		} else {
			JOptionPane.showMessageDialog(null, "El " +producto+ ", no existe");
		}
	}
}
