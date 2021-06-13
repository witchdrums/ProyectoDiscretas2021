import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import static javax.swing.JOptionPane.showMessageDialog;

public class Mute extends JFrame {
//no sé qué es esto pero Eclipse me pone una advertencia si no lo agrego lmao:
/**
	 * 
	 */
	private static final long serialVersionUID = 5223729048483078117L;

JButton union,interseccion,potencia,cardinalidad,diferencia,cartesiano,flipFields;
JTextField setAElements,setBElements;
JTextArea resultados;
JLabel setALabel,setBLabel;
JPanel panelResultados;
GestionBoton gestionBoton;
Vector<Object> setA, setB, setR;

	public Mute() {
		super("Mute");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
	    ImageIcon img = new ImageIcon("1618429937357.jpg");
	    this.setIconImage(img.getImage());
		setA = new Vector<Object>();
		setB = new Vector<Object>();
		setR = new Vector<Object>();
		this.setBounds(40,40,381,170);
		gestionBoton = new GestionBoton(this);
		crearComponentes();
		
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    this.setVisible ( true );
	}
	
	public void crearComponentes() {
	    
	    //Crear botones///////////////////////////////////////////
	    
		union = new JButton("∪");
			union.setBounds(14,80,50,20);
			this.add(union);
			union.addActionListener(gestionBoton);
		interseccion = new JButton("∩");
			interseccion.setBounds(64,80,50,20);
			this.add(interseccion);
			interseccion.addActionListener(gestionBoton);
		potencia = new JButton("P");
			potencia.setBounds(114,80,50,20);
			this.add(potencia);
			potencia.addActionListener(gestionBoton);
		cardinalidad = new JButton("#");
			cardinalidad.setBounds(14,100,50,20);
			this.add(cardinalidad);
			cardinalidad.addActionListener(gestionBoton);
		diferencia = new JButton("-");
			diferencia.setBounds(64,100,50,20);
			this.add(diferencia);
			this.diferencia.addActionListener(gestionBoton);
		cartesiano = new JButton("x");
			cartesiano.setBounds(114,100,50,20);
			this.add(cartesiano);
			this.cartesiano.addActionListener(gestionBoton);
		flipFields = new JButton("↕");
			flipFields.setBounds(310,80,40,42);
			this.add(flipFields);
			flipFields.addActionListener(gestionBoton);
			
		//Crear campos de texto///////////////////////////////////
			
		setAElements = new JTextField();
			setAElements.setBounds(200,81,110,20);
			this.add(setAElements);
		setALabel = new JLabel(" A");
			setALabel.setBounds(180,81,20,20);
			setALabel.setForeground(Color.gray);
			setALabel.setBorder(new EtchedBorder());
			this.add(setALabel);
		setBElements = new JTextField();
			setBElements.setBounds(200,101,110,20);
			this.add(setBElements);
		setBLabel = new JLabel(" B");
			setBLabel.setBounds(180,101,20,20);
			setBLabel.setBorder(new EtchedBorder());
			setBLabel.setForeground(Color.gray);
			this.add(setBLabel);
	
		//Crear pantalla de resultados////////////////////////////
			
	    resultados = new JTextArea ( 2, 41 );
	    	resultados.setEditable ( false );
	    	resultados.setText("");
	    JScrollPane resultadosScroll = new JScrollPane ( resultados );
	    	resultadosScroll.setHorizontalScrollBarPolicy ( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS );
	    panelResultados = new JPanel ();
			panelResultados.setBorder ( new TitledBorder ( new EtchedBorder (), "Resultado" ) );
			panelResultados.setBackground(Color.white);
			panelResultados.add ( resultadosScroll );
	    
	    this.add ( panelResultados );
	    
	}


}
