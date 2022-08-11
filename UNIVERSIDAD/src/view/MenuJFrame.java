package view;

import javax.swing.JButton;                 //javax.swing permite manejar elementos de la interfaz Grafica
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.UniversidadController;

import java.awt.*;
import java.awt.event.*;                    //Esta libreria es para manejar eventos


//Creo la clase para la nueva interfaz de la ventana
public class MenuJFrame extends JFrame{

  //ATRIBUTOS
  private JButton btnCrear;
  private JButton btnMostrar;
 
  //CONSTRUCTOR
  public MenuJFrame(UniversidadController uController) {
    //Añadir titulo. ---> setSize: Le doy medidas a mi ventana
    setTitle("REGISTRO DE UNIVERSIDADES");
    setLayout(new FlowLayout());
    setSize(470, 300); 
    //Indicar que debe finalizar el programa al cerrar la ventana.(Utilizando un meetodo y una constante que hace parte de la super clase)
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    //Setear layout que viene por defecto.(Asi trabajamos tranquilamente con coordenadas)
    getContentPane().setLayout(null);
    //Ubico la ventana en el centro de la pantalla
    setLocationRelativeTo(null);
    //Poner la ventana visiible
    setVisible(true);

    //Inicializar los elementos
    btnCrear = new JButton("Crear universidad");
    //Dar coordenadas y tamaño al boton para ubicarlo en mi ventana
    btnCrear.setBounds(20, 20, 200, 40);
    //Añadir elemento a la ventana
    add(btnCrear);

    btnMostrar = new JButton("Mostrar universidaddes");
    btnMostrar.setBounds(230, 20, 200, 40);
    add(btnMostrar);

    
    //Manejadores de eventos

    //Este boton va quedar a la escucha
    btnCrear.addActionListener(new ActionListener() {
      //Debido a la interfaz que tiene ACtionListener debemos utilizar una clase de tipo actionPerformed -> Recibe un parametro(actionEvent)
      public void actionPerformed(ActionEvent e) {
        //Aca va ir todo lo que queremos que haga el boton crear(btnCrear) al darle CLICK
        //Creo una ventana de tipo UniversidadJFrame.(Esta clase tambien necesita  uController)
        new UniversidadJFrame(uController);
      };
    }); 

    btnMostrar.addActionListener(new ActionListener() {
      //Debido a la interfaz que tiene ACtionListener debemos utilizar una clase de tipo actionPerformed -> Recibe un parametro(actionEvent)
      public void actionPerformed(ActionEvent e) {
        //Aca va ir todo lo que queremos que haga el boton crear(btnCrear) al darle CLICK
        JOptionPane.showMessageDialog(null, "Hola Mundo");
      };
    }); 
  }
}

//El FlowLayout coloca los elementos de izuquierda a derecha(por defecto)
//Como heredamos todos los metodos de la super clase(JFrame) podemos llamar un metodo (setTitle)
//Un JFrame es una ventana a la cual le podemos dar tamaño, mientras que el JOption toma toma el tamaño segun su contenido.
//Aqui se usara absolutLayout que permite acomodar los elementos por coordenadas.(mas flexible)