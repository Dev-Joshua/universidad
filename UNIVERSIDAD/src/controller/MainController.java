package controller;

import model.ConexionDB;
import view.MenuConsola;


public class MainController {

  //CONSTRUCTOR
  public MainController() {
    //CREAR CONEXION A BD
    ConexionDB conexionDB = new ConexionDB();
    //CONSTRUIR CONTROLADORES
    UniversidadController uController = new UniversidadController(conexionDB);
    //CONSTRUIR VISTAS
    MenuConsola mConsola = new MenuConsola(uController);
    mConsola.construirMenu();
  }
}

//Este controlador se encargara de construir las diferentes vistas(interfaz grafica) que tenga mi progrma.(vista por consola)
