package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.UniversidadController;

import java.awt.event.*;
import java.sql.SQLException;


public class UniversidadJFrame extends JFrame{
  //ATRIBUTOS
  //El label sera el texto que mostramos al solicitar un dato
  private JLabel labelNombre;
  private JLabel labelNit;
  private JLabel labelDireccion;
  private JLabel labelEmail;
  //Atrbitos para los campos de texto para los labels creados
  private JTextField txtNombre;
  private JTextField txtNit;
  private JTextField txtDireccion;
  private JTextField txtEmail;
  //Boton para registrar
  private JButton btnRegistrar;
  private UniversidadController uController;
  
  //CONSTRUCTOR ->Recibe uController 
  public UniversidadJFrame(UniversidadController uController) {
    this.uController = uController;
    setTitle("Formulario de registro");
    //Seteo el layout para trabaajar mejor por coordenadas
    getContentPane().setLayout(null);
    setBounds(100, 100, 250, 300);
    init();
    //Mostrar la ventana
    setVisible(true);
  }

  //Metodo para inicializar los elementos(mis atributos) que saldran en la ventana(crear universidad)
  public void init() {
    //Le pongo el texto que llevara el labbel
    labelNombre = new JLabel("Nombre: ");
    //Ubico el label en la ventana
    labelNombre.setBounds(10, 10, 80, 30);
    //Creo el campo del label. El le da un tamaño predeterminado
    txtNombre = new JTextField();
    //Ubico el campo en la ventana
    txtNombre.setBounds(81, 10, 100, 30);
    //----------------------------------------------------
    labelNit = new JLabel("Nit: ");
    labelNit.setBounds(10, 50, 60, 30);                          //Este elemento quiero que quede debajo del otro label
  
    txtNit = new JTextField();
    txtNit.setBounds(81, 50, 100, 30);
    //-----------------------------------------------------
    labelDireccion = new JLabel("Direccion");
    labelDireccion.setBounds(10, 90, 60, 30);

    txtDireccion = new JTextField();
    txtDireccion.setBounds(81, 90, 100, 30);
    //------------------------------------------------------
    labelEmail = new JLabel("Email");
    labelEmail.setBounds(10, 130, 60, 30);

    txtEmail = new JTextField();
    txtEmail.setBounds(81, 130, 100, 30);
    //--------------------------------------------------------

    //Creo el boton para registrar universidad
    btnRegistrar = new JButton("Registrar");
    btnRegistrar.setBounds(71, 190, 100, 40);


    //MANEJADOR DE EVENTOS CON ACTIONLISTENER
    //Para registrar una universidad necesito enviar los datos al controlador por lo tanto necesito esta clase va a recibir UniversidadController
    btnRegistrar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {    
          try {
            registrarUniversidad();
          } catch (SQLException e1) {
            e1.printStackTrace();
          }
      }
    });

    //Añadir los elementos a la ventana(interfaz de usuario)
    add(labelNombre);
    add(txtNombre);
    add(labelNit);
    add(txtNit);
    add(labelDireccion);
    add(txtDireccion);
    add(labelEmail);
    add(txtEmail);
    add(btnRegistrar);
  }

  //No retorna nada
  public void registrarUniversidad() throws SQLException {
    //Obtener los datos de los campos de txt, con.getText() retorna ese string y enviamos los datos por uController a (universidades)Dao
    //Capturamos el booleano que retorna crea uController.crearU 
    boolean insert = uController.crearUniversidad(txtNit.getText(), txtNombre.getText(), txtDireccion.getText(), txtEmail.getText());
    
    if(insert) {
      limpiarCampos();
      //Muestro una ventana de mensajes(enviamos this porque el padre es esta misma clase JFrame)
      JOptionPane.showMessageDialog(this, "Universidad registrada con éxito");
    } else {
      JOptionPane.showMessageDialog(this, "Ups! algo sucedio, intenta mas tarde");
    }
  }

  public void limpiarCampos() {
    //Seteamos los campos de txt
    txtNombre.setText("");
    txtNit.setText("");
    txtDireccion.setText("");
    txtEmail.setText("");
  }
}

//Para crear una universidad por JFrame implementamos un formulario para solicitar los datos