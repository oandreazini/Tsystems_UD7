import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import javax.swing.JOptionPane;

/*
 * Oriol Andreazini
 */

public class Ej1App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		double media = 0;
		
		//Creamos la tabla hash para guardar las notas medias y el número del alumno
		Hashtable<Integer, Double> alumnos= new Hashtable<Integer, Double>();
		
		String numeroAlumnos = JOptionPane.showInputDialog("Cuantos alumnos hay?");
		int numeroAlumn = Integer.parseInt(numeroAlumnos);
		
		String numeroAssignaturas = JOptionPane.showInputDialog("Cuantas assignaturas hay?");
		int numeroAssig = Integer.parseInt(numeroAssignaturas);
		
		//Con dos bucles for recorremos la cantidad de alumnos introducidos y la cantidad de asignaturas
		for(int i=1; i<=numeroAlumn; i++) {
	
			ArrayList<Integer> numeroAlum = new ArrayList<>();	
			
			for(int z=1; z<=numeroAssig; z++) {
				String nota = JOptionPane.showInputDialog("Introduce la nota de la assignatura " +z+ " del alumno " +i);
				int notaAlumno = Integer.parseInt(nota);
				numeroAlum.add(notaAlumno);			
				}
			
				int num;
				double suma=0;
				
				//Recorremos el arrayList para poder sumar todos sus valores y sacar la media
				Iterator<Integer> iterator = numeroAlum.iterator();
			
				while(iterator.hasNext()){
				num = iterator .next();
				suma+= num;
				media=suma/numeroAssig;
			}
				
			JOptionPane.showMessageDialog(null,  "El alumno " +i+ " tiene la nota media de " +media);
			
			//Guardamos en la tabla hash el número del alumno y la media correspondiente
			alumnos.put(i, media);
		}
	}
}
	
