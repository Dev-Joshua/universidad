package model;

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
  private Map<String, ArrayList<String>> matriculas;  //Un Map hace el trabajo de los ciclos(iterar). Matriculas sera un array con clave/valor
  
  
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
  
  public ArrayList<Facultad> getFacultades() {
    return facultades;
  }

  public ArrayList<Estudiante> getEstudiantes() {
    return estudiantes;
  }

  public Map<String, ArrayList<String>> getMatriculas() {
    return matriculas;
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
  
  public void setFacultades(ArrayList<Facultad> facultades) {
    this.facultades = facultades;
  }

  public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
    this.estudiantes = estudiantes;
  }


  /*****************************
   *ACCIONES(metodos):
  ******************************/

  //Copiamos los datos que recibe el constructor del Estudiante, y creamos un objeto de tipo Estudiante
  public void registrarEstudiante(String nombre, String apellido, int edad, String cedula, char sexo, String codigo) {
    Estudiante estudiante = new Estudiante(nombre, apellido, edad, cedula, sexo, codigo);
    //Almacenar objeto estudiante en el array
    this.estudiantes.add(estudiante);
  }

  public void crearFacultad(String codigo, String nombre) {
    //Crear objeto de tipo facultad
    Facultad facultad = new Facultad(codigo, nombre);
    this.facultades.add(facultad);                                                  //facultades es un Map<ArrayList>    
  }
 
  public void matricularEstudiante(String cedula, String codigoFacultad) {
    //Validar si ya existe un estudiante matriculado en esa facultad.(Obtener valores del Map a partir de la key)
    //Si! ya contiene la key(codigoFacultad) añade un elemento mas a dicha facultad
    if(matriculas.containsKey(codigoFacultad)){
      //Obtener arrayList y adicionar nuevo elemento(cedula del estudiante)
      matriculas.get(codigoFacultad).add(cedula);
    } else{                                                                        //SI NO existe la facultad, entonces añade una nueva con el estudiante(cedula)
      ArrayList<String> cedulas = new ArrayList<String>();                         //Creo el objeto que contendra el Map como valor
      cedulas.add(cedula);                                                         //Adicionar cedula al arrayList(cedulas), put() tambien adiciona a matriculas(arrayList)
      matriculas.put(codigoFacultad, cedulas);                                     //Si aplico un put() el me crea otro elemento con clave/valor(codigoFacultad/cedulas)
    }
  }
}
