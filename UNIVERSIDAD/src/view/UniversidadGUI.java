package view;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import controller.UniversidadController;

//Esta clase vista tendra todo lo que se refiera a la Universidad.(INTERFAZ GUI)
public class UniversidadGUI {
  //Atributos
  //Vista depende de la existencia del controlador.
  private UniversidadController uController;


  public UniversidadGUI(UniversidadController uController) {
    this.uController = uController;
  }


  public void crearUniversidad() throws SQLException  {
    String encabezado = "--------------CREAR UNIVERSIDAD--------------\n";
    encabezado += "Por favor ingrese la siguiente informacion\n";
    //Solicitar datos al usuario.
    String nit = JOptionPane.showInputDialog(null, encabezado+"\nNit: ");   //--> Va concatenando el encabezado con cada linea(variable)
    String nombre = JOptionPane.showInputDialog(null, encabezado+"\nNombre: ");
    String direccion = JOptionPane.showInputDialog(null, encabezado+"\nDireccion: ");
    String email = JOptionPane.showInputDialog(null, encabezado+"\nEmail: ");

    //Crear universidad
    //Capturo el valor booleano que retorna crearUniversidad()
    boolean insert = uController.crearUniversidad(nit, nombre, direccion, email);
    //Si! se realizo la insercion entonces:
    if(insert) {
      //Hago uso del JOptionPane para mostrar ventanas de dialogo(null, mensaje)
      JOptionPane.showMessageDialog(null, "\n\nUniversidad creada con exito");  
    } else {
      JOptionPane.showMessageDialog(null, "\n\nPor favor intenta mas tarde");
    }
  }

  
  //Metodo para obtener y mostrar en consola las universidades que contiene la BD
  public void mostrarUniversidades() {
    ResultSet universidades = uController.obtenerUniversidades();                         //En el resulset almacenamos las universidades. Haciendo la conexion por medio del metodo obtenerUniversidades de la clase UniversidadController.
    try {                                                                                                                                                          
        String info = "--------------UNIVERSIDADES----------------\n";                    //Creo la variable que va a concatenar mi variable universidades
        //Iterar sobre ArrayList(universidades)
        while(universidades.next()){                                                       //A universidades le aplico .next() para saber si hay registros por iterar(cada fila), si retorna true ejecuta la sentencia dentro del while
            String nit = universidades.getString("nit");                      //Obtengo el nit y el nombre de la universidad                                       
            String nombre = universidades.getString("nombre");                //Concatenamos con la variable info
            info += "Nit: " + nit;
            info += "\nNombre: " + nombre;
            info += "\n--------------------------\n";                                      //Salto de linea para hacer la division entre cada universidad
        }                                                                                  //Cuando el .next no vea mas registros por iterar en result, el retornara falso y termina el ciclo.
        //Mostrar universidades  en GUI                                                                                        
        JOptionPane.showMessageDialog(null, info);                                                                   
    }catch (Exception e) {
        e.printStackTrace();
    }   
  }


  //Metodo par aobtener universidad por Nit. Se necesita obtener el objeto de(conexionDB)
  public void obtenerUniversidadXnit() {
      //Encabezado
      String info = "---------------UNIVERSIDAD POR NIT----------------\n";
      //Solicito el nit
      String nit = JOptionPane.showInputDialog(null, info+"\nNit: ");
      //Obtener la universidad
      ResultSet result = uController.consultarUniversidad(nit);

      //Como solo vamos a obtener 1 universidad no es necesasrio un ciclo. Utilizo una condicional
      try {
          //SI! hay por lo menos un registro(nit) para acceder a esa info, capturamos el nit, nombre, etc...(result.getString("nit"))
          if(result.next()){
              info += result.getString("nombre");                                          //Obtener todos los campos
              info += "\n" + result.getString("nit");
              info += "\n" + result.getString("direccion");
              info += "\n" + result.getString("email");
              info += "\n-------------------------------------------------\n";
              JOptionPane.showMessageDialog(null, info);                              //Mostramos por medio de GUI                                                  //Se muestra la informacion
          }
        //Excepcion
      } catch (Exception e) {
          e.printStackTrace();;
      }      
  }


  //Metodo par actualizar una universidad con la interfaz GUI
  public void actualizarUniversidad() {
    String info = "-----------------ACTUALIZAR UNIVERSIDAD-------------------\n";
    //Solicitar datos
    String nit = JOptionPane.showInputDialog(null, info+"Nit: ");
    //nombre
    String nombre = JOptionPane.showInputDialog(null, info+"Nombre: ");
    //direccion
    String direccion = JOptionPane.showInputDialog(null, info+"Direccion: ");
    //solicito email
    String email = JOptionPane.showInputDialog(null, info+"Email: ");

    //Actualizar universidad por medio del controller
    boolean update = uController.actualizarUniversidad(nit, nombre, direccion, email);
    //Si! update es true:
    if(update) {
      JOptionPane.showMessageDialog(null, "Universidad actualizada con exito");       //Mostrar aviso por GUI
    } else {
      JOptionPane.showMessageDialog(null,"Por favor intenta mas tarde");
    }
  }



  //Metodo para eliminar universidad por nit
  public void eliminarUniversidad() {
    String info = "--------Eliminar universidad--------\n";
    //Solicitar nit y concatenarlo con info
    String nit = JOptionPane.showInputDialog(null, info+"Nit: ");

    //Eliminar universidad
    //Llamo al controlador para usar el metodo eliminarUniversidad y le envio el nit(parametro)
    //el retorna un booleano que capturamos en delete(variable)
    boolean delete = uController.eliminarUniversidad(nit);
    //Si! delete es true entonces:
    if(delete) {
      JOptionPane.showMessageDialog(null, "Universidad eliminada con exito");
    } else {
      JOptionPane.showMessageDialog(null, "Por favor intenta mas tarde ");
    }
  }
}

/*Esta clase unicamente se encargara de la vista.(lo que se mostrar a a ususario por consola) */
//El controlador va instanciar la vista, de esta manera puedo cambiar por varias vistas de usuario.

