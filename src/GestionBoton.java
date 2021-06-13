import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import java.util.Vector;

public class GestionBoton implements ActionListener {
private Mute mute;
	public GestionBoton() {}
	public GestionBoton(Mute m) { this.mute = m; }

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mute.union) { Union(); }
		if (e.getSource() == mute.interseccion) { interseccion(); }
		if (e.getSource() == mute.potencia) { potencia(); }
		if (e.getSource() == mute.cardinalidad) { cardinalidad(); }
		if(e.getSource() == mute.diferencia) { diferencia(); }
		if(e.getSource() == mute.cartesiano) { cartesiano(); }
		
		/*Mute aplica la Potencia y P. Cartesiano sólo sobre el conjunto A. Por otro lado, A-B != B-A.
		Entonces utilizamos este algoritmo para intercambiar los elementos de ambos conjuntos, así
		podemos calcular la potencia y cartesiano de B, así como B-A:*/
		if(e.getSource() == mute.flipFields) { flipFields(); }
	}
	
	/*Algoritmo para convertir las entradas en datos que Mute pueda manejar de manera estable, así
	no importa que tantos espacios o elementos repetidos haya dentro de las entradas, Mute
	podrá manejarlos sin problemas:*/
	public Vector<Object> tratarConjunto(String strSet) {
		//eliminar espacios
		String single = strSet.replaceAll("\\s", "");
		
		//convertir string a vector
		Scanner scanner = new Scanner(single);
		scanner.useDelimiter(",");
		scanner.tokens();
		Vector<Object> set = new Vector<Object>();
		while(scanner.hasNext()) {
			set.add(scanner.next());
		}
		scanner.close();
		
		//eliminar elementos repetidos
		for (int i = 0; i<set.size(); i++) {
			for (int j = i + 1; j<set.size(); j++) {
				if (set.get(i).equals(set.get(j))) {
					set.removeElementAt(j);
					--j;
				}
			}
		}
		return set;
	}
	//Mute usa el toString() de la clase Vector para mostrar resultados
	//El toString() de la clase Vector envuelve los elementos en corchetes, pero la representación clásica de un conjunto usa llaves
	//Este algoritmo cambia los corchetes por llaves:
	public String cambiarCorchetes(String toString) {
		String str1 = toString.replace('[', '{');
		String str2 = str1.replace(']', '}');
		return str2;
	}
	public void Union() {
		mute.setA = tratarConjunto(mute.setAElements.getText());
		mute.setB = tratarConjunto(mute.setBElements.getText());
		mute.setR.addAll(mute.setA);
		for (int i = 0; i < mute.setB.size(); i++) {
			for (int j = 0; j < mute.setR.size(); j++) {
				if (mute.setB.get(i).equals(mute.setR.get(j))) {
					break;
				}else if (j == mute.setR.size() - 1) {
					mute.setR.add(mute.setB.get(i));
				}
			}
		}
		String strResultado = cambiarCorchetes(mute.setR.toString());
		mute.resultados.setText(strResultado);
		mute.setR.clear();
	}
	public void interseccion() {
		mute.setA = tratarConjunto(mute.setAElements.getText());
		mute.setB = tratarConjunto(mute.setBElements.getText());
		for (int i = 0; i < mute.setA.size(); i++) {
			for (int j = 0; j < mute.setB.size(); j++) {
				if (mute.setA.get(i).equals(mute.setB.get(j))) {
					mute.setR.add(mute.setA.get(i));
				}
			}
		}
		String strResultado = cambiarCorchetes(mute.setR.toString());
		mute.resultados.setText(strResultado);
		mute.setR.clear();
	}
	public void potencia() {
		mute.setA = tratarConjunto(mute.setAElements.getText());
		mute.setB = tratarConjunto(mute.setBElements.getText());
		double cardinalidad = Math.pow(2.0, mute.setA.size()); 
		int i, j;
		for(i = 0; i < cardinalidad; i++) { 
			String elemento = "{";
			for (j = 0; j<mute.setA.size(); j++) {
				System.out.println(1 << j);
				if(((1 << j) & i )>0) {
					//System.out.println(1 << j);
					elemento+= mute.setA.get(j) + ", ";
				}
			}
			elemento += "}";
			mute.setR.add(elemento);
		}
		String strResultado = cambiarCorchetes(mute.setR.toString());
		mute.resultados.setText(strResultado);
		mute.setR.clear();
	}
	public void cardinalidad() {
		mute.setA = tratarConjunto(mute.setAElements.getText());
		mute.setB = tratarConjunto(mute.setBElements.getText());
		int setASize = 0;
		int setBSize = 0;
		for (Object a : mute.setA) ++setASize;
		for (Object b : mute.setB) ++setBSize;
		
		mute.resultados.setText("#A = " + setASize + ", #B = " + setBSize);
		mute.setR.clear();
	}
	public void diferencia() {
		mute.setA = tratarConjunto(mute.setAElements.getText());
		mute.setB = tratarConjunto(mute.setBElements.getText());
		for (int i = 0; i<mute.setA.size(); i++) {
			for (int j = 0; j<mute.setB.size(); j++) {
				if (mute.setA.get(i).equals(mute.setB.get(j))) 
					break;
				else if(j == mute.setB.size() - 1) 
					mute.setR.add(mute.setA.get(i));
			}
		}
		String strResultado = cambiarCorchetes(mute.setR.toString());
		mute.resultados.setText(strResultado);
		mute.setR.clear();
	}
	public void cartesiano() {
		mute.setA = tratarConjunto(mute.setAElements.getText());
		mute.setB = tratarConjunto(mute.setBElements.getText());
		for (int i = 0; i<mute.setA.size(); i++) {
			String element = "";
			for (int j = 0; j<mute.setB.size(); j++) {
				element += "(" + mute.setA.get(i) + ", " + mute.setB.get(j) + ")";
				if (j!=mute.setB.size()-1) element += ", ";
			}
			mute.setR.add(element);
		}
		String strResultado = cambiarCorchetes(mute.setR.toString());
		mute.resultados.setText(strResultado);
		mute.setR.clear();
	}
	public void flipFields() {
		String help = mute.setAElements.getText();
		mute.setAElements.setText( mute.setBElements.getText());
		mute.setBElements.setText(help);
	}
	
	
}
