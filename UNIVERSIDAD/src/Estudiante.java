public class Estudiante extends Persona{
  //Atributo
  private String codigo;

  public Estudiante(String nombre, String apellido, int edad, String cedula, char sexo, String codigo) {
    //Envia los parametros al constructor de la super clase Persona
    super(nombre, apellido, edad, cedula, sexo);
    this.codigo = codigo;
  }

  public String getCodigo() {
    return codigo;
  }
}
