package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.ConexionDB;
import model.dao.UniversidadDao;

public class UniversidadController {

  //Attributos:
  private ConexionDB conexionDB;

  //Metodo Constructor
  public UniversidadController(ConexionDB conexionDB) {
    this.conexionDB = conexionDB;
  }

  //Acciones
  //Metodo para crear una Universidad(Instanciamos el objeto por medio del dao)
  public boolean crearUniversidad(String nombre, String nit, String direccion, String email) throws SQLException {
    UniversidadDao objUniversidad = new UniversidadDao(nombre, nit, direccion, email);
    //Almaceno la universidad a la BD                            
    //return objUniversidad.insert(conexionDB.getConexion());
    boolean insert = objUniversidad.insert(conexionDB.getConexion());
    return insert;
  }


  //Metodo para retornar en un ResultSet los registros en la base de datos (BD).
  public ResultSet obtenerUniversidades() {
    ResultSet result = null;
    result = UniversidadDao.getUniversidades(conexionDB.getConexion());
    return result;
  }

}

/*El controller es el puente entre la vista y el modelo. (La vista instancia al controlador y este instancia al modelo).
 *Al momento de crear una universidad(objeto), de actualizar, consultar, eliminar, siempre va a pedir un objeto de tipo connection
 *Por lo tanto manejo una sola conexion para la ejecucion de todo el programa:
 *---> El controlador principal creara un objeto de conexion para hacer los insert y comunicacion con la base de datos (BD).
 */
