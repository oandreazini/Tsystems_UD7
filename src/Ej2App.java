import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.InputMismatchException;

import javax.swing.JOptionPane;

public class Ej2App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * Inicializamos las Hashtable de articulos, preciosBruto y preciosIva
		 * Llamamos a los métodospara llenar las hashtable
		 */
		Hashtable<Integer, String> articulos= new Hashtable <Integer, String>();
		stock(articulos);

		Hashtable<Integer, Double> preciosBruto= new Hashtable <Integer, Double>();
		preciosBruto(preciosBruto);

		Hashtable<Integer, Double> preciosIVA= new Hashtable <Integer, Double>();
		preciosIva( preciosIVA);

		/**
		 *Cramos un menu, con un bucle while y la sentencia switch, el bucle nos permite 
		 *tener la opción de salir, y el switch nos permite seleccionar la opción del menú
		 **/
		boolean salir = false;

		while (!salir) {

			String numero = JOptionPane.showInputDialog("Introduce: 1->Mostrar IVA productos 2->Compra 3->Salir" );
			int num = Integer.parseInt(numero);

			try {	
				switch (num) {
				case 1:
					ivaApli(articulos, preciosIva( preciosIVA));
					break;
				case 2:
					ArrayList<Integer> carrito = carrito(articulos);
					ArrayList<Double> arraySinIva = totalSinIva(carrito, preciosBruto(preciosBruto));

					double precioTotalIva = totalConIva(arraySinIva, preciosIva( preciosIVA));
					articulosComprados(carrito);
					double pagado = cantidadPagada(precioTotalIva);
					cambioCliente(pagado, precioTotalIva);
					break;
				case 3:
					salir = true;
					break;
				default:
					JOptionPane.showMessageDialog(null, "Números entre 1 --> 3");
				} 
			}catch (InputMismatchException e) {
				numero = JOptionPane.showInputDialog("Escribe un numero");
				num = Integer.parseInt(numero);
			}
		}
	}

	public static Hashtable<Integer, String> stock(Hashtable<Integer, String> articulos) {
		//Introducimos los 5 productos que tenemos en stock
		articulos.put(1, "samsung");
		articulos.put(2, "iphone");
		articulos.put(3,"sony");
		articulos.put(4, "lg");
		articulos.put(5, "lenovo");

		return articulos;
	}

	public static Hashtable<Integer, Double> preciosBruto(Hashtable<Integer, Double> preciosBruto) {
		//Introducimos los 5 precios brutos de cada articulo
		preciosBruto.put(1, 15.99);
		preciosBruto.put(2, 10.0);
		preciosBruto.put(3, 12.25);
		preciosBruto.put(4, 19.99);
		preciosBruto.put(5, 6.99);

		return preciosBruto;
	}

	public static Hashtable<Integer, Double> preciosIva(Hashtable<Integer, Double>  preciosIVA) {
		//Introducimos los 5 precios con IVA de cada articulo
		preciosIVA.put(1, 0.21);
		preciosIVA.put(2, 0.04);
		preciosIVA.put(3, 0.04);
		preciosIVA.put(4, 0.21);
		preciosIVA.put(5, 0.04);

		return preciosIVA;
	}

	public static void ivaApli (Hashtable<Integer, String> articulos, Hashtable<Integer, Double> preciosIva ) {
		//Recorremos las 2 hashtable (articulos y precioIVA) para mostrar el producto y su % aplicado de IVA
		Enumeration<String> enumeration=articulos.elements();
		Enumeration<Double> enumeration1=preciosIva.elements();

		while (enumeration.hasMoreElements() && enumeration.hasMoreElements()) {

			JOptionPane.showMessageDialog(null,"El tipo de Iva aplicado a " +enumeration.nextElement()+ " es " +(enumeration1.nextElement()*100) + " %");
		}
	}


	public static ArrayList<Integer> carrito (Hashtable<Integer, String> articulos) {
		//Creamos un arrayLits y introducimos la cantidad de ariculos que el
		//cliente seleccione de cada articulo
		ArrayList<Integer> carrito = new ArrayList<>();

		Enumeration<String> enumeration=articulos.elements();
		//Recorremos el hashtable para ir pidiendo las cantidades de cada producto
		while (enumeration.hasMoreElements()) {
			String productos = JOptionPane.showInputDialog("Cuantas unidades quieres del producto " +enumeration.nextElement());
			int numProductos = Integer.parseInt(productos);
			carrito.add(numProductos);	
		}
		return carrito;
	}

	public static ArrayList<Double>  totalSinIva (ArrayList<Integer> carrito, Hashtable<Integer, Double> preciosBruto) {
		//Creamos un arrayLits para introducir el precio sin IVA
		double precioBruto= 0;
		double sumaBruto =0;
		carrito = new ArrayList<> (carrito);	

		ArrayList<Double> arraySinIva = new ArrayList<>();

		Enumeration<Double> enumeration = preciosBruto.elements();
		//Recorremos el hashtable para ir calculado el precio Bruto respecto a las cantidades de cada producto
		while (enumeration.hasMoreElements()) {
			for(int i=0; i<carrito.size(); i++) {
				int unidades= carrito.get(i);
				precioBruto = unidades*enumeration.nextElement();
				arraySinIva.add(precioBruto);
				sumaBruto += precioBruto;
			}
		}	
		JOptionPane.showMessageDialog(null, "El precio bruto total de la compra es: " +String.format(java.util.Locale.US,"%.2f",sumaBruto));
		return arraySinIva;
	}

	public static double totalConIva (ArrayList<Double> arraySinIva, Hashtable<Integer, Double> preciosIva) {
		double precio=0;
		double precioTotalIva=0;
		arraySinIva = new ArrayList<>(arraySinIva);

		Enumeration<Double> enumeration=preciosIva.elements();
		//Recorremos el arrayList del precio sin IVA y el hashtable 
		//para ir calculado el precio con IVA respecto a las cantidades de cada producto
		while (enumeration.hasMoreElements()) {
			for(int i=0; i<arraySinIva.size(); i++) {
				double precioIva= 0;
				precio= arraySinIva.get(i);
				precioIva+= (precio*enumeration.nextElement())+precio;
				precioTotalIva+= precioIva;
			}
		}	
		JOptionPane.showMessageDialog(null, "El precio total con IVA de la compra es: " +String.format(java.util.Locale.US,"%.2f", precioTotalIva));
		return precioTotalIva;
	}

	public static int articulosComprados (ArrayList<Integer> carrito) {

		int numArticulos=0;
		//Con el arrayList de carrito, lo recorremos
		//y lo sumamos para mostrar la cantidad total de productos comprados
		carrito = new ArrayList<> (carrito);

		for(int i=0; i<carrito.size(); i++) {
			int unidades = carrito.get(i);
			numArticulos+=unidades;
		}
		JOptionPane.showMessageDialog(null, "El total de artículos comprados son: " +numArticulos);

		return numArticulos;
	}

	public static double cantidadPagada (double precioTotalIva ) {
		/*
		 * Le pedimos al cliente que entre una cantidad de dinero y respecto a es cantidad
		 * el switch mostrara si se tiene que devolver cambio o no, o si es inferior al precio 
		 */
		String pago = JOptionPane.showInputDialog("Introduce la cantidad para pagar:");
		double pagado = Double.parseDouble(pago);

		while (pagado < precioTotalIva) {
			JOptionPane.showMessageDialog(null, "El importe pagado "+ pagado+ " "
					+ "es inferior al valor de compra " +String.format(java.util.Locale.US,"%.2f",precioTotalIva));

			String pago1 = JOptionPane.showInputDialog("Introduce una cantidad superior:");
			pagado = Double.parseDouble(pago1);
		}
		if(pagado == precioTotalIva) {
			JOptionPane.showMessageDialog(null,"No se tiene que devolver cambio");		
		} else {
			if(pagado > precioTotalIva) {
				JOptionPane.showMessageDialog(null, "El importe pagado " +pagado+ ""
						+ " es superior al valor de compra " +String.format(java.util.Locale.US,"%.2f",precioTotalIva));
			}
		}
		return pagado;
	}

	public static double cambioCliente(double pagado, double precioTotalIva) {
		//Calcula el cambio a devolver
		double cambio =0;

		cambio = pagado - precioTotalIva;

		JOptionPane.showMessageDialog(null, "El cambio es :" +String.format(java.util.Locale.US,"%.2f", cambio));

		return cambio;
	}
}
