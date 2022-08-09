package view;

import javax.swing.JOptionPane;

import controller.UniversidadController;

//Interfaz para menu GUI
public class MenuGUI {
  //Atributo
  private UniversidadController uController;

  //Constructor
  public MenuGUI(UniversidadController uController) {
    this.uController = uController;
  }
  
  //Importando JOptionPane(clase) desde java.swing ya podemos acceder a sus metodos para crear el menu por medio de ventanas(alerts)
  public void crearMenu() {
    //Usamos el tipo de ventana que es para solicitar datos(JOptionPane.showInputDialog(null, "Mensaje de prueba"))
    //Crear objeto de tipo UniversidadVista, como parametro se envia el atributo uController.
    UniversidadGUI uVista = new UniversidadGUI(uController);

    //Opciones del menu
    String mensaje = "1) Crear universidad\n";
    mensaje += "2) Mostrar todas las universidades\n";
    mensaje += "3) Consultar universidad por nit\n";
    mensaje += "4) Actualizar universidad\n";
    mensaje += "5) Eliminar universidad\n";
    mensaje += "-1) Salir\n";
    mensaje += ">>> ";

    int opcion = 0;
    try {
      while(opcion != -1) {
        //Solicitar opcion al usuario por medio de JOptionPane y su metodo showInputDialog()
        // System.out.print(mensaje);                                                            //Esta linea nos muestra el menu en consola
        opcion = Integer.parseInt(JOptionPane.showInputDialog(null, mensaje));
        //Evaluar opcion
        switch (opcion) {
          case 1:
            uVista.crearUniversidad();                   //Referenciamos el objeto creado(uVista) para llamar al metodo(crearUniversidad) de su clase
            break;
          case 2:
            uVista.mostrarUniversidades();
            break;
          case 3:
            uVista.obtenerUniversidadXnit();
            break;
          case 4:
            uVista.actualizarUniversidad();              //Llamo a este metodo desde uVista(UniversidadVista.java)
            break;
          case 5:
            uVista.eliminarUniversidad();
            break;
          default:
            break;
        }
      }
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "Ups! Sucedio algo, por favor intenta mas tarde");
    }
  }
}
