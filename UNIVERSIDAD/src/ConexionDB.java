import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionDB {
  //ATRIBUTOS
  private Connection conexion;
  
  //CONSTRUCTOR
  public ConexionDB(){
    try {
      this.conexion = DriverManager.getConnection("jdbc:sqlite:universidad_grupo_33");
      System.out.println("Conexion exitosa a la base de datos");
      } catch (Exception e) {
        e.printStackTrace();
      }
  }

  //CONSULTOR
  public Connection getConexion(){
    return conexion;
  }

  //ACCIONES
  public void cerrarConexion() throws SQLException {
    conexion.close();
  }

  public void insertar(String query) throws SQLException {
    Statement st = conexion.createStatement();
    st.execute(query);
  }
  //El Statement se crea de la conexion. Se debe de importar!!
  //El objeto Statement va a ejecutar la consulta(query) que recibimos como parametro
  //El ResulSet captura esa tabla virtual st.excute(query) con la respuesta a esa consulta SQL
}
