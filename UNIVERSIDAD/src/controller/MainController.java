package controller;

import model.ConexionDB;
// import view.MenuConsola;
import view.MenuGUI;
import view.MenuJFrame;

//Este sera mi controlador principal, va instanciar los demas controladores.
public class MainController {
  //CONSTRUCTOR
  public MainController() {
    //CREAR OBJETOS
    ConexionDB conexionDB = new ConexionDB();
    //CONSTRUIR CONTROLADORES
    UniversidadController uController = new UniversidadController(conexionDB);
    //CONSTRUIR VISTAS

    // MenuConsola mConsola = new MenuConsola(uController);
    // mConsola.construirMenu();

    // MenuGUI menuGui = new MenuGUI(uController);
    // menuGui.crearMenu();

    new MenuJFrame(uController);                        //mainController le envia el controlador a MenuJFrame y MenuJFrame le envia el controlador a UniversidadJFrame

    //Cerrar conexion
    try {
      // conexionDB.cerrarConexion();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

//Este controlador se encargara de construir las diferentes vistas(interfaz grafica) que tenga mi progrma.(vista por consola)
//Este paquete controller instancia la vista y a la vez instancia el modelo.

//Cuando tenemos el controlador y la conexion a la BD ya podemos crear un objeto de tipo MenuConsola.