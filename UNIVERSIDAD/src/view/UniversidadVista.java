package view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import controller.UniversidadController;

public class UniversidadVista {
  //Atributos
  //Vista depende de la existencia del controlador.
  private UniversidadController uController;


  public UniversidadVista(UniversidadController uController) {
    this.uController = uController;
  }

  public void crearUniversidad(Scanner sc) throws SQLException  {
    System.out.println(crearEncabezado("crear universidad"));
    //Solicito nombre
    System.out.println("Nombre: ");
    String nombre = sc.next();
    sc.nextLine();
    //nit
    System.out.println("Nit: ");
    String nit = sc.next();
    sc.nextLine();
    //direccion
    System.out.println("Direccion: ");
    String direccion = sc.next();
    sc.nextLine();
    //solicito email
    System.out.println("Email: ");
    String email = sc.next();
    sc.nextLine();

    //Crear universidad
    //Capturo el valor booleano que retorna crearUniversidad()
    boolean insert = uController.crearUniversidad(nombre, nit, direccion, email);
    if(insert) {
      System.out.println("Universidad creada con exito");
    } else {
      System.out.println("Por favor intetna mas tarde");
    }
  }

  //Para no duplicar el codigo lo ponemos en otro metodo
  public String crearEncabezado(String mensaje) {
    String encabezado = "---------------" + mensaje.toUpperCase() + "---------------\n";
    encabezado += "Por favor ingrese la siguiente informacion: \n";
    return encabezado;
  } 

  
    //Metodo para obtener y mostrar en consola las universidades que contiene la BD
    public void mostrarUniversidades() {
      try {                                                                                                
          ResultSet result = uController.obtenerUniversidades();                                         //En el resulset almacenamos las universidades. Haciendo la conexion por medio del metodo obtenerUniversidades de la clase UniversidadController.                  
          String info = "--------------UNIVERSIDADES----------------\n";                                 //Creo la variable que va a concatenar mi resultado
          //Iterar sobre el resultado(universidades)
          while(result.next()){                                                                         //Del resultado obtenido le aplico .next() para saber si hay registros por iterar(cada fila), si retorna true ejecuta la sentencia dentro del while
              String nit = result.getString("nit");                                        //Obtengo el nit y el nombre de la universidad                                       
              String nombre = result.getString("nombre");                                  //Concatenamos con la variable info
              info += "Nit: " + nit;
              info += "\nNombre: " + nombre;
              info += "\n--------------------------\n";                                                //Salto de linea para hacer la division entre cada universidad
          }                                                                                            //Cuando el .next no vea mas registros por iterar en result, el retornara falso y termina el ciclo.
          //Mostrar universidades                                                                                          
          System.out.println(info);                                                                   
      }catch (Exception e) {
          e.printStackTrace();
      }
      
  }

  // //Metodo par aobtener universidad por Nit. Se necesita obtener el objeto de(conexionDB)
  // public static void obtenerUniversidadXnit(ConexionDB conn) {
  //     //Obtener la universidad con el numero de nit ""
  //     ResultSet result = Universidad.getUniversidadByNit(conn.getConexion(), "12345");               //Como parametro se envia el getConexon() y el nit(universidad). Retorna un resulset
  //     try {
  //         //SI! hay por lo menos un registro(nit) para acceder a esa info, capturamos el nit, nombre, etc...(result.getString("nit"))
  //         if(result.next()){
  //             String nit = result.getString("nit");                                          //Obtener todos los campos
  //             String nombre = result.getString("nombre");
  //             String direccion = result.getString("direccion");
  //             String email = result.getString("email");
  //             //Concatenar informacion en una sola variable info
  //             String info = "Nit: " + nit;
  //             info += "\nNombre: " + nombre;
  //             info += "\nDireccion: " + direccion;
  //             info += "\nEmail: " + email;
  //             System.out.println(info);
  //         }
  //     } catch (Exception e) {
  //         e.printStackTrace();;
  //     }      
  // }
}

/*Esta clase unicamente se encargara de la vista.(lo que se mostrar a a ususario por consola) */
//El controlador va instanciar la vista, de esta manera puedo cambiar por varias vistas de usuario.
