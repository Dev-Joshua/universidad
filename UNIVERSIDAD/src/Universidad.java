import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
//Map es una interfaz de Java. Se utiliza para almacenar datos en en pares 'clave/valor'

public class Universidad {
  //ATRIBUTOS
  private String nombre;
  private String nit;
  private String direccion;
  private ArrayList<String> telefonos;                //Arreglo vacio al que podemos añadir elementos de tipo String.(Arreglo de tamaño dinamico)
  private String email;
  private ArrayList<Facultad> facultades;             //Esta es una relacion con la clase Facultad.(facultades se convierte en un atributo de la clase UNIVERSIDAD)
  private ArrayList<Estudiante> estudiantes;          //Relacion con la clase Estudiante.(estudiantes se convierte atributo de la clase Universidad)
  private Map<String, ArrayList<String>> matriculas;
  //CONSTRUCTOR
  public Universidad(String nombre, String nit, String direccion, String email) {
    this.nombre = nombre;                             //Se inicializan las variables.
    this.nit = nit;
    this.direccion = direccion;
    this.email = email;
    this.telefonos = new ArrayList<String>();
    this.facultades = new ArrayList<Facultad>();      //El ArrayList<tipoDeDAto> se llamara facultades y almacenara objetos de tipo facultad
    this.matriculas = new  LinkedHashMap<String, ArrayList<String>>();          //Inicializamos el LinkedHashMap
  }

  //CONSULTORES
  public String getNombre() {
    return nombre;
  }

  public String getNit() {
    return nit;
  }

  public String getDireccion() {
    return direccion;
  }

  public ArrayList<String> getTelefonos() {
    return telefonos;
  }

  public String getEmail() {
    return email;
  }

  //MODIFICADORES
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public void setTelefonos(ArrayList<String> telefonos) {
    this.telefonos = telefonos;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  
}
