import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;

import javax.swing.JOptionPane;

public class Ej2App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * Inicializamos las Hashtable de articulos i preciosIva
		 * Llamamos a los métodos necesarios para ejecutar el main
		 * Inicializamos los arrays necesarios
		 */
		Hashtable<Integer, String> articulos= stock();
		Hashtable<Integer, Double> preciosIva= preciosIva();
		
		ivaApli(articulos, preciosIva);
		
		ArrayList<Integer> carrito = carrito(articulos);
		ArrayList<Double> arraySinIva = totalSinIva(carrito, preciosBruto());
		
		double precioTotalIva = totalConIva(arraySinIva, preciosIva);
		articulosComprados(carrito);
		double pagado = cantidadPagada(precioTotalIva);
		cambioCliente(pagado, precioTotalIva);
	}
	
	public static Hashtable<Integer, String> stock() {
		
		Hashtable<Integer, String> articulos= new Hashtable <Integer, String>();
		
		articulos.put(1, "Samsung");
		articulos.put(2, "Iphone");
		articulos.put(3,"Sony");
		articulos.put(4, "LG");
		articulos.put(5, "Lenovo");
		
		return articulos;
	}
	
	public static Hashtable<Integer, Double> preciosBruto() {
		
		Hashtable<Integer, Double> preciosBruto= new Hashtable <Integer, Double>();
		
		preciosBruto.put(1, 15.99);
		preciosBruto.put(2, 10.0);
		preciosBruto.put(3, 12.25);
		preciosBruto.put(4, 19.99);
		preciosBruto.put(5, 6.99);
		
		return preciosBruto;
	}
	
	public static Hashtable<Integer, Double> preciosIva() {
		
		Hashtable<Integer, Double> preciosIVA= new Hashtable <Integer, Double>();
		
		preciosIVA.put(1, 0.21);
		preciosIVA.put(2, 0.04);
		preciosIVA.put(3, 0.04);
		preciosIVA.put(4, 0.21);
		preciosIVA.put(5, 0.04);
		
		return preciosIVA;
	}
	
	public static void ivaApli (Hashtable<Integer, String> articulos, Hashtable<Integer, Double> preciosIva ) {
		
		Enumeration<String> enumeration=articulos.elements();
		Enumeration<Double> enumeration1=preciosIva.elements();
		
		while (enumeration.hasMoreElements() && enumeration.hasMoreElements()) {
			
			JOptionPane.showMessageDialog(null,"El tipo de Iva aplicado a " +enumeration.nextElement()+ " es " +(enumeration1.nextElement()*100) + " %");
		}
		
		
	}
	public static ArrayList<Integer> carrito (Hashtable<Integer, String> articulos) {
		
		ArrayList<Integer> carrito = new ArrayList<>();
		
		Enumeration<String> enumeration=articulos.elements();
		
		while (enumeration.hasMoreElements()) {
			String productos = JOptionPane.showInputDialog("Cuantas unidades quieres del producto " +enumeration.nextElement());
			int numProductos = Integer.parseInt(productos);
			carrito.add(numProductos);	
		}
		return carrito;
	}
	
	public static ArrayList<Double>  totalSinIva (ArrayList<Integer> carrito, Hashtable<Integer, Double> preciosBruto) {
		
		double precioBruto= 0;
		double sumaBruto =0;
		carrito = new ArrayList<> (carrito);	
		
		ArrayList<Double> arraySinIva = new ArrayList<>();
		
		Enumeration<Double> enumeration = preciosBruto.elements();
		
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
		
		carrito = new ArrayList<> (carrito);
		
		for(int i=0; i<carrito.size(); i++) {
			int unidades = carrito.get(i);
			numArticulos+=unidades;
		}
		JOptionPane.showMessageDialog(null, "El total de artículos comprados son: " +numArticulos);
		
		return numArticulos;
	}
	
	public static double cantidadPagada (double precioTotalIva ) {
			
		String pago = JOptionPane.showInputDialog("Introduce la cantidad para pagar:");
		double pagado = Double.parseDouble(pago);
		
		while (pagado < precioTotalIva) {
			JOptionPane.showMessageDialog(null, "El importe pagado "+ pagado+ " es inferior al valor de compra " +precioTotalIva);
			
			String pago1 = JOptionPane.showInputDialog("Introduce una cantidad superior:");
			pagado = Double.parseDouble(pago1);
		}
		if(pagado == precioTotalIva) {
			JOptionPane.showMessageDialog(null,"No se tiene que devolver cambio");		
			} else {
				if(pagado > precioTotalIva) {
					JOptionPane.showMessageDialog(null, "El importe pagado " +pagado+ " es superior al valor de compra " +precioTotalIva);
				}
			}
		return pagado;
	}
	
	public static double cambioCliente(double pagado, double precioTotalIva) {
		
		double cambio =0;
		
		cambio = pagado - precioTotalIva;
		
		JOptionPane.showMessageDialog(null, "El cambio es :" +String.format(java.util.Locale.US,"%.2f", cambio));

		return cambio;
	}
}
