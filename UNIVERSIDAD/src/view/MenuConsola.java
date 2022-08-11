package view;

import java.util.Scanner;

import controller.UniversidadController;

//Para que quede mas modular el codigo creo otra vista con el menu del programa
public class MenuConsola {
  //Atributo - La conexion la construye la clase controller
  private UniversidadController uController;

  //Constructor - Le pasamos como parametro el controlador
  public MenuConsola(UniversidadController uController) {
    this.uController = uController;
  }


  //Menu de las universidades
  public void construirMenu() {
    //Crear objeto de tipo UniversidadVista, como parametro se envia el atributo uController.
    UniversidadVista uVista = new UniversidadVista(uController);
    //Opciones del menu
    String mensaje = "1) Crear universidad\n";
    mensaje += "2) Mostrar todas las universidades\n";
    mensaje += "3) Consultar universidad por nit\n";
    mensaje += "4) Actualizar universidad\n";
    mensaje += "5) Eliminar universidad\n";
    mensaje += "-1) Salir\n";
    mensaje += ">>> ";

    int opcion = 0;
    Scanner sc = new Scanner(System.in);
    try {
      //Iteramos opcion mientras sea diferente a -1
      while(opcion != -1) {
        //Solicitar opcion al usuario y capturamos ese valor en la variable opcion.
        System.out.print(mensaje);
        opcion = sc.nextInt();
        //Evaluar opcion
        switch (opcion) {
          case 1:
            uVista.crearUniversidad(sc);                     //Referenciamos el objeto creado(uVista) para llamar al metodo(crearUniversidad) de su clase
            break;
          case 2:
            uVista.mostrarUniversidades();
            break;
          case 3:
            uVista.obtenerUniversidadXnit(sc);
            break;
          case 4:
            uVista.actualizarUniversidad(sc);              //Llamo a este metodo desde uVista(UniversidadVista.java)
            break;
          case 5:
            uVista.eliminarUniversidad(sc);
            break;
          default:
            break;
        }
      }
      sc.close();
    } catch (Exception e) {
      System.out.println("Por favor ingrese valores numericos en las opciones del menu");
    }
  }
}

//Esta clase unicamente se encargara del menu
