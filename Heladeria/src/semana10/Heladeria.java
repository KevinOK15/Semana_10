package semana10;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.Font;

public class Heladeria extends JFrame implements ActionListener {

	// Declaración de variables
	private static final long serialVersionUID = 9206324162700448001L;
	private JPanel contentPane;
	private JLabel lblHelado;
	private JLabel lblCantidad;
	private JComboBox<String> cboHelado;
	private JTextField txtCantidad;
	private JButton btnProcesar;
	private JButton btnBorrar;
	private JScrollPane scpScroll;
	private JTextArea txtS;

	// Lanza la aplicación
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Heladeria frame = new Heladeria();
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Crea la GUI
	public Heladeria() {
		setTitle("Heladeria");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblHelado = new JLabel("Helado");
		lblHelado.setBounds(10, 11, 80, 14);
		contentPane.add(lblHelado);

		lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(10, 36, 80, 14);
		contentPane.add(lblCantidad);

		cboHelado = new JComboBox<String>();
		cboHelado.setModel(new DefaultComboBoxModel<String>(new String[] {"Sol", "Fresa", "Mar", "Rico"}));
		cboHelado.setBounds(100, 8, 100, 20);
		contentPane.add(cboHelado);

		txtCantidad = new JTextField();
		txtCantidad.setBounds(100, 33, 100, 20);
		contentPane.add(txtCantidad);
		txtCantidad.setColumns(10);

		btnProcesar = new JButton("Procesar");
		btnProcesar.addActionListener(this);
		btnProcesar.setBounds(335, 7, 89, 23);
		contentPane.add(btnProcesar);

		btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(this);
		btnBorrar.setBounds(335, 32, 89, 23);
		contentPane.add(btnBorrar);

		scpScroll = new JScrollPane();
		scpScroll.setBounds(10, 61, 414, 190);
		contentPane.add(scpScroll);

		txtS = new JTextArea();
		txtS.setFont(new Font("Monospaced", Font.PLAIN, 12));
		scpScroll.setViewportView(txtS);
	}

	// Direcciona eventos de tipo ActionEvent
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnBorrar) {
			actionPerformedBtnBorrar(arg0);
		}
		if (arg0.getSource() == btnProcesar) {
			actionPerformedBtnProcesar(arg0);
		}
	}

	// Procesa la pulsación del botón Borrar
	protected void actionPerformedBtnBorrar(ActionEvent arg0) {
		txtCantidad.setText("");
		txtS.setText("");
		cboHelado.setSelectedIndex(0);
		txtCantidad.requestFocus();
	}

	// Procesa la pulsación del botón Procesar
	protected void actionPerformedBtnProcesar(ActionEvent arg0) {
		
		int cant = obtenerCantidad();
		String marc = obtenerMarca();
		double prec = obtenerPrecio(marc);
		double impC = calcularImpCompra(prec, cant);
		double impD = calcularImpDescuento(impC, cant);
		double impP = calcularImpPagar( impC, impD);
		int cara = obtenerCaramelos( marc, cant);
		
		txtS.setText("REPORTE DE HELADERIA 2023 \n\n");
		imprimir("Importe de Compra :" + decimalFormat(impC) );
		imprimir("Importe de Descuento :" + decimalFormat(impD));
		imprimir("Importe a Pagar :" + decimalFormat(impP) );
		imprimir("Caramelos :" + cara);
		
	}
	int obtenerCantidad() {
		return Integer.parseInt( txtCantidad.getText() );
	}
	String obtenerMarca() {
		return cboHelado.getSelectedItem().toString();
	
	}
	double obtenerPrecio(String marc){
		if ( marc == "Sol" ){ return 2.5; }
		else if ( marc == "Fresa" ){ return 1.3; }
		else if ( marc == "Mar" ){ return 2.0; }
		else { return 1.7; }
	}
	double calcularImpCompra(double prec, int cant){
		return prec*cant;
	}
	double calcularImpDescuento( double impC, int cant){
		if ( cant<10 ){ return impC*0.05; }
		else if ( cant>=10 && cant<20 ){ return impC*0.07; }
		else if ( cant>=20 && cant<30 ){ return impC*0.09; }
		else { return impC*0.11; }
	}
	double calcularImpPagar( double impC, double impD){
		return impC-impD;
	}
	int obtenerCaramelos( String marc, int cant){
		if ( marc=="Sol" ){ return 1*cant; }
		else if( marc=="Mar" ){ return 2*cant; }
		else {return 6; }
	}
	String decimalFormat(double p){
		return String.format("%.2f",p).replace(",", ".");
	}
	void imprimir(String s) {
		txtS.append(s + "\n");
	}
}