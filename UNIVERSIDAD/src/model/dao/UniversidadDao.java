package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import model.Universidad;

public class UniversidadDao extends Universidad {

  //Constructor
  public UniversidadDao(String nombre, String nit, String direccion, String email) {
    super(nombre, nit, direccion, email);
  }
  
  
  /**********************
    *QUERYS
    *CONSTRUYO LAS CONSULTAS(querys) SQL DE ESTA CLASE(UNIVERSIDAD) 
  ***********************/

  //1) Metodo para registrar esta "" Universidad en mi base de datos 
  //Recibo el objeto de conexion como parametro.
  public boolean insert(Connection conn) throws SQLException {

    //Metodo para registrar una Universidad en la BD.
    boolean insert = false;
    try {
      //Preparo consulta SQL.(Hacer la insercion de la nueva universidad a la BD)
      String query = "INSERT INTO universidades VALUES(?,?,?,?)";                   //Los valores(?) son nit, nombre, direccion, email.
      PreparedStatement pst = conn.prepareStatement(query);
      //Setear la consulta SQL
      pst.setString(1, getNit());
      pst.setString(2, getNombre());
      pst.setString(3, getDireccion());
      pst.setString(4, getEmail());
      //Ejecutar
      insert = pst.executeUpdate() == 1;

    } catch (SQLDataException e) {
      e.printStackTrace();                                                          //StackTrace muestra la excepcion(error) en consola.
      // System.out.println(e.getMessage());
    }
    return insert;                                                                  //Retorna un valor boolean.(true si la accion es satisfactoria, false en caso contrario)
  }



  //2) Metodo para obtener las universidades de mi BD.
  public static ResultSet getUniversidades(Connection conn) {
    ResultSet result = null;
    try {
      String query = "SELECT * FROM universidades";                                 //Consulta o query
      Statement st = conn.createStatement();                                        //Usamos Statement del objeto de conexion.(conn.createSt). -> xq' no concatenamos variables.(Solo seleccionar)
      result = st.executeQuery(query);                                              //En la variable result guardamos lo que retorne el executeQuery de la consulta(query) 'SELECT * FROM universidades'
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;                                                                //Retorna un ResultSet
  }

  //3) Metodo para obtener una universidad por nit(parametro)
  //Buscamos la universidad en la BD que corresponda con el nit que estamos enviando como parametro
  public static ResultSet getUniversidadByNit(Connection conn, String nit) {
    ResultSet result = null;
    try {
      //Preparo la consulta
      String query = "SELECT * FROM universidades WHERE nit = ?";               //(?) Representa el valor que va cambiar dependiendo del nit(parametro)
      PreparedStatement pst = conn.prepareStatement(query);
      pst.setString(1, nit);                                      //Con set.String(seteo la consulta) y pongo el nit(que puse como parametro) en la consulta(?)
      result = pst.executeQuery();                                              //Ejecuto la consulta
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
  }


  //4) Metodo para actualizar. Retornara un booleano para verificar si se actualizo el registro o no!
  public static boolean updateUniversidad(Connection conn, String nit, String nombre, String direccion, String email) {
    boolean update = false;                                                                                          //update lmacena un true o un false
    try {
      String query = "UPDATE universidades SET nombre = ?, direccion = ?, email = ? WHERE nit = ?";                  //Afectamos una sola fila porque hacemos un update por la Primary Key(nit)
      PreparedStatement pst = conn.prepareStatement(query);
      //Setear la consulta
      pst.setString(1, nombre);                                  //El primer ? representara el nombre
      pst.setString(1, direccion);                              //El segundo ? representara el direccion
      pst.setString(1, email);
      pst.setString(1, nit);                                    //El cuarto ? signo despues del WHERE representa nit
      //Ejecutar la consulta
      update = pst.executeUpdate() == 1;                                       //Retorna cuantas filas fueron afectadas. SI! hay una fila afectada (update = true), etnonces el resultado lo almacena en la variable update.
    } catch (Exception e) {                                                 
        e.printStackTrace();                                                    //Si ninguna fila fue afectada devuelve falso. Catch.
    }
    return update;
  }
}




/*UniversidadDao es la representacion de la entidad Universidad en mi base de datos SQl, esta va a heredar de esa clase
 *Cada que heredamos de una clase tambien debemos poenr su metodo constructor junto con sus parametros.
 *AQUI EN EL DAO ALMACENO UNICAMENTE MIS CONSULTAS SQL
 */

/*NOTA: Siempre que nos vayamos a comunicar con una BD tendremos un manejador de secciones.
      (Porque puede quedar mal la estructura SQL, nombrar mal la tabla, generar error, etc...)
 *NOTA: No concatenar variables directamente con una consultaSQL porque genera problemas(sql Injection)!!
  
 *NOTA: *Se usa PreparedStatement cuando se van a concatenar variables en una consulta
        *Se usa Statement cuando la consulta es fija
 
  -- es un comentario(almohadilla) de SQLite, # es de mySQL
	String user = "";
  String password = "\" OR 1=1--";

  SELECT * FROM users WHERE name = user AND pass = password;
  SELECT * FROM users WHERE name = "anders" AND pass = " " OR 1=1--;
*/


