package model;

//Esta clase extiende de Persona
public class Estudiante extends Persona{
  //Atributo
  private String codigo;

  public Estudiante(String nombre, String apellido, int edad, String cedula, char sexo, String codigo) {
    //Envia los parametros al constructor de la super clase Persona
    super(nombre, apellido, edad, cedula, sexo);
    this.codigo = codigo;
  }

  //CONSULTOR
  public String getCodigo() {
    return codigo;
  }

  //MODIFICADOR
  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }
}
