package view;

import java.util.Scanner;

import controller.UniversidadController;

public class MenuConsola {
  
  //Atributo
  private UniversidadController uController;

  //Constructor
  public MenuConsola(UniversidadController uController) {
    this.uController = uController;
  }


  public void construirMenu() {
    //Crear objeto universidadVista
    UniversidadVista uVista = new UniversidadVista(uController);

    //Opciones del menu
    String mensaje = "1) Crear universidad\n";
    mensaje += "2) Mostrar todas las universidades\n";
    mensaje += "3) Actualizar universidad\n";
    mensaje += "4) Eliminar universidad\n";
    mensaje += "-1) Salir\n";
    mensaje += ">>> ";

    try(Scanner sc = new Scanner(System.in)) {
      int opcion = 0;
      while(opcion != -1) {
        //Solicitar opcion al usuario y capturamos ese valor en la variable opcion.
        System.out.print(mensaje);
        opcion = sc.nextInt();
        //Evaluar opcion
        switch (opcion) {
          case 1:
            uVista.crearUniversidad(sc);
            break;
          case 2:
            uVista.mostrarUniversidades();
            break;
          default:
            break;
        }
      }
    } catch (Exception e) {
      System.out.println("Por favor ingrese valores numericos en las opciones del menu");
    }
  }
}

//Esta clase unicamente se encargara del menu
