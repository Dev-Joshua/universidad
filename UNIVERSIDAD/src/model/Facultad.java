package model;


public class Facultad {
  //Atributos
  private String codigo;
  private String nombre;

  //Constructor
  public Facultad(String codigo, String nombre){
    this.codigo = codigo;
    this.nombre = nombre;
  }

  //Consultrores
  public String getCodigo() {
    return codigo;
  }

  public String getNombre() {
    return nombre;
  }

  //Modificador
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  //ACCIONES
  
}
