package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import controller.UniversidadController;

//Esta clase vista tendra todo lo que se refiera a la Universidad
public class UniversidadVista {
  //Atributos
  //Vista depende de la existencia del controlador.
  private UniversidadController uController;


  public UniversidadVista(UniversidadController uController) {
    this.uController = uController;
  }


  public void crearUniversidad(Scanner sc) throws SQLException  {
    System.out.println("--------------CREAR UNIVERSIDAD--------------");
    System.out.println("Por favor ingrese la siguiente informacion");
    // System.out.println(crearEncabezado("crear universidad"));
    //Solicito nit
    System.out.print("Nit: ");
    String nit = sc.next();
    sc.nextLine();
    //nombre
    System.out.print("Nombre: ");
    String nombre = sc.next();
    sc.nextLine();
    //direccion
    System.out.print("Direccion: ");
    String direccion = sc.next();
    sc.nextLine();
    //solicito email
    System.out.print("Email: ");
    String email = sc.next();
    sc.nextLine();

    //Crear universidad
    //Capturo el valor booleano que retorna crearUniversidad()
    boolean insert = uController.crearUniversidad(nit, nombre, direccion, email);
    //Si! se realizo la insercion entonces:
    if(insert) {
      System.out.println("Universidad creada con exito");
    } else {
      System.out.println("Por favor intenta mas tarde");
    }
  }


  // //Para no duplicar el codigo lo ponemos en otro metodo
  // public String crearEncabezado(String mensaje) {
  //   String encabezado = "---------------" + mensaje.toUpperCase() + "---------------\n";
  //   encabezado += "Por favor ingrese la siguiente informacion: \n";
  //   return encabezado;
  // } 

  
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
        //Mostrar universidades                                                                                          
        System.out.println(info);                                                                   
    }catch (Exception e) {
        e.printStackTrace();
    }   
  }


  //Metodo par aobtener universidad por Nit. Se necesita obtener el objeto de(conexionDB)
  public void obtenerUniversidadXnit(Scanner sc) {
      //Encabezado
      System.out.println("---------------UNIVERSIDAD POR NIT----------------");
      System.out.print("Nit: ");
      String nit = sc.next();
      sc.nextLine();
      //Obtener la universidad
      ResultSet result = uController.consultarUniversidad(nit);

      //Como solo vamos a obtener 1 universidad no es necesasrio un ciclo. Utilizo una conficional
      try {
          //SI! hay por lo menos un registro(nit) para acceder a esa info, capturamos el nit, nombre, etc...(result.getString("nit"))
          if(result.next()){
              String info = result.getString("nombre");                                          //Obtener todos los campos
              info += "\n" + result.getString("nit");
              info += "\n" + result.getString("direccion");
              info += "\n" + result.getString("email");
              info += "\n-------------------------------------------------\n";
              System.out.println(info);                                                                      //Se muestra la informacion
          }
        //Excepcion
      } catch (Exception e) {
          e.printStackTrace();;
      }      
  }


  //Metodo par actualizar una universidad. Capturamos el objeto(Scanner sc)
  public void actualizarUniversidad(Scanner sc) {
    System.out.println("-----------------ACTUALIZAR UNIVERSIDAD-------------------");
    //Solicitar datos
    System.out.print("Nit: ");
    String nit = sc.next();
    sc.nextLine();
    //nombre
    System.out.print("Nombre: ");
    String nombre = sc.next();
    sc.nextLine();
    //direccion
    System.out.print("Direccion: ");
    String direccion = sc.next();
    sc.nextLine();
    //solicito email
    System.out.print("Email: ");
    String email = sc.next();
    sc.nextLine();

    //Actualizar universidad por medio del controller
    boolean update = uController.actualizarUniversidad(nit, nombre, direccion, email);
    //Si! update es true:
    if(update) {
      System.out.println("Universidad actualizada con exito");
    } else {
      System.out.println("Por favor intenta mas tarde");
    }
  }



  //Metodo para eliminar universidad
  public void eliminarUniversidad(Scanner sc) {
    System.out.println("--------Eliminar universidad--------");
    System.out.print("Nit: ");
    String nit = sc.next();
    sc.nextLine();

    //Eliminar universidad
    boolean delete = uController.eliminarUniversidad(nit);
    if(delete) {
      System.out.println("Universidad eliminada con exito");
    } else {
      System.out.println("Por favor intenta mas tarde ");
    }
  }
}

/*Esta clase unicamente se encargara de la vista.(lo que se mostrar a a ususario por consola) */
//El controlador va instanciar la vista, de esta manera puedo cambiar por varias vistas de usuario.
