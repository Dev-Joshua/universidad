package model.dao;

import java.sql.PreparedStatement;

import model.ConexionDB;
import model.Estudiante;

public class EstudianteDao extends Estudiante {
  //CONSTRUCTOR
  public EstudianteDao(String nombre, String apellido, int edad, String cedula, char sexo, String codigo) {
    super(nombre, apellido, edad, cedula, sexo, codigo);
  }
  
  //CONSULTAS SQL
  public void insert(ConexionDB conexionDB, String nitUniversidad) {
    try {
      if(conexionDB.getConexion() != null) {
        String query = "INSERT INTO facultades(codigo, nombre, universidad_nit) VALUES(?,?,?)";
        //Preparar consulta
        PreparedStatement pst = conexionDB.getConexion().prepareStatement(query);
        pst.setString(1, getCodigo());
        pst.setString(2, getNombre());
        pst.setString(3, nitUniversidad);
        //Ejecutar consulta
        pst.executeUpdate();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
