import java.util.InputMismatchException;

import javax.swing.JOptionPane;

public class Ej4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/**
		 *Cramos un menu, con un bucle while y la sentencia switch, el bucle nos permite 
		 *tener la opción de salir, y el switch nos permite seleccionar la opción del menú
		 **/
		boolean salir = false;

		while (!salir) {

			String numero = JOptionPane.showInputDialog("Introduce: 1->Entrar ventas 2->Control stock 3->Salir" );
			int num = Integer.parseInt(numero);

			switch (num) {
				case 1:
					//Ejecutamos toda la sección de compra
					Ej2App.main(args);
					break;
				case 2:
					//Ejecutamos toda la sección de almazen
					Ej3App.main(args);;
					break;
				case 3:
					salir = true;
					break;
				default:
					JOptionPane.showMessageDialog(null, "Números entre 1 --> 3");
				} 		
			}
		}
	}

