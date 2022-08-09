package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.ConexionDB;
import model.Universidad;
import model.dao.UniversidadDao;

public class UniversidadController {

  //Attributos:
  private ConexionDB conexionDB;
  private ArrayList<Universidad> universidades;

  //Metodo Constructor. El controller recibe la unica conexionDB(como parametro), la conexion la construye el mismo controller
  public UniversidadController(ConexionDB conexionDB) {
    this.conexionDB = conexionDB;
    this.universidades = new ArrayList<Universidad>();
  }


  //Acciones:
  
  //Metodo para crear una Universidad(Instanciamos el objeto por medio del dao)
  public boolean crearUniversidad(String nit, String nombre, String direccion, String email) throws SQLException {
    UniversidadDao objUniversidad = new UniversidadDao(nombre, nit, direccion, email);
    //Almaceno la universidad a la BD por medio del insert(metodo) para hacer la insercion.                        
    boolean insert = objUniversidad.insert(conexionDB);
    if(insert) {
      this.universidades.add(objUniversidad); //Si! se guardo en la BD entonces tambien lo guardo en el array(universidades)
    }
    return insert;
  }

  //Metodo para retornar en un ResultSet los registros en la base de datos (BD).
  public ResultSet obtenerUniversidades() {
    ResultSet result = null;
    result = UniversidadDao.getUniversidades(conexionDB);
    return result;
  }

  //Metodo para consultar universidad por medio del nit, haciendo la query desde la clase UniversidadDao.
  public ResultSet consultarUniversidad(String nit) {
    return UniversidadDao.selectPorNit(conexionDB, nit);
     
  }

  //Metodo para hacer update de cualquier universidad por medio de su nit, se obtiene el metodo desde la clase UniversidadDao
  public boolean actualizarUniversidad(String nit, String nombre, String direccion, String email) {
    return UniversidadDao.updateUniversidadByNit(conexionDB, nit, nombre, direccion, email);
  }

  //Metodo para hacer un delete de una universidad
  public boolean eliminarUniversidad(String nit) {
    return UniversidadDao.deleteByNit(conexionDB, nit);
  }

}

/*El controller es el puente entre la vista y el modelo. (La vista instancia al controlador y este instancia al modelo).
 *Al momento de crear una universidad(objeto), de actualizar, consultar, eliminar, siempre va a pedir un objeto de tipo connection
 *Por lo tanto manejo una sola conexion para la ejecucion de todo el programa:
 *---> El controlador principal creara un objeto de conexion para hacer los insert y comunicacion con la base de datos (BD).
 */
