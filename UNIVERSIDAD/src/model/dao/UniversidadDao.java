package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;

import model.ConexionDB;
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
  public boolean insert(ConexionDB conn) throws SQLException {

    //1) Metodo para registrar una Universidad en la BD.
    boolean insert = false;
    try {
      //Preparo consulta SQL.(Hacer la insercion de la nueva universidad a la BD)
      String query = "INSERT INTO universidades VALUES(?,?,?,?)";                   //Los valores(?) son nit, nombre, direccion, email.
      PreparedStatement pst = conn.getConexion().prepareStatement(query);
      //Setear la consulta SQL
      pst.setString(1, getNit());
      pst.setString(2, getNombre());
      pst.setString(3, getDireccion());
      pst.setString(4, getEmail());
      //Ejecutar
      insert = pst.executeUpdate() == 1 ? true : false;

    } catch (SQLDataException e) {
      e.printStackTrace();                                                          //StackTrace muestra la excepcion(error) en consola.
      // System.out.println(e.getMessage());
    }
    return insert;                                                                  //Retorna un valor boolean.(true si la accion es satisfactoria, false en caso contrario)
  }



  //2) Metodo para obtener las universidades de mi BD.
  //Cuando colocamos un metodo como estatico, se puede acceder a el sin crear un objeto de la clase.(simplemente referenciando la)
  public static ResultSet getUniversidades(ConexionDB conn) {
    ResultSet result = null;
    try {
      String query = "SELECT * FROM universidades";                                 //Consulta o query
      Statement st = conn.getConexion().createStatement();                          //Usamos Statement del objeto de conexion.(conn.createSt). -> xq' no concatenamos variables.(Solo seleccionar)
      result = st.executeQuery(query);                                              //En la variable result guardamos lo que retorne el executeQuery de la consulta(query) 'SELECT * FROM universidades'
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;                                                                //Retorna un ResultSet
  }



  //3) Metodo para obtener una universidad por nit(parametro)
  //Buscamos la universidad en la BD que corresponda con el nit que estamos enviando como parametro
  public static ResultSet selectPorNit(ConexionDB conn, String nit) {
    ResultSet result = null;
    try {
      //Preparo la consulta
      String query = "SELECT * FROM universidades WHERE nit = ?";               //(?) Representa el valor que va cambiar dependiendo del nit(parametro)
      PreparedStatement pst = conn.getConexion().prepareStatement(query);
      pst.setString(1, nit);                                      //Con set.String(seteo la consulta) y pongo el nit(que puse como parametro) en la consulta(?)
      result = pst.executeQuery();                                              //Ejecuto la consulta
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return result;
  }



  //4) Metodo para actualizar. Retornara un booleano para verificar si se actualizo el registro o no!
  public static boolean updateUniversidadByNit(ConexionDB conexionDB, String nit, String nombre, String direccion, String email) {
    boolean update = false;                                                                                          //update lmacena un true o un false
    
    try {
      String query = "UPDATE universidades SET nombre = ?, direccion = ?, email = ? WHERE nit = ?";                  //Afectamos una sola fila porque hacemos un update por la Primary Key(nit)
      PreparedStatement pst = conexionDB.getConexion().prepareStatement(query);
      //Setear la consulta
      pst.setString(1, nombre);                                  //El primer ? representara el nombre
      pst.setString(2, direccion);                              //El segundo ? representara el direccion
      pst.setString(3, email);
      pst.setString(4, nit);                                    //El cuarto ? signo despues del WHERE representa nit
      //Ejecutar la consulta
      update = pst.executeUpdate() == 1;                                       //Retorna cuantas filas fueron afectadas. SI! hay una fila afectada (update = true), etnonces el resultado lo almacena en la variable update.
    } catch (Exception e) {                                                 
        e.printStackTrace();                                                    //Si ninguna fila fue afectada devuelve falso. Catch.
    }
    return update;
  }



  //5) Metodo para elminar una universidad por medio de su nit
  public static boolean deleteByNit(ConexionDB conexionDB, String nit) {
    boolean delete = false;
    
    try {
      String query = "DELETE FROM universidades WHERE nit = ?";
      PreparedStatement pst = conexionDB.getConexion().prepareStatement(query);
      pst.setString(1, nit);
      //Ejecutar si el executeUpdate es true, si no se cumple devuelve falso
      delete = pst.executeUpdate() == 1 ? true : false;
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return delete;
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


