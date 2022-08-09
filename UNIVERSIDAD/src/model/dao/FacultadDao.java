package model.dao;

import java.sql.PreparedStatement;

import model.ConexionDB;
import model.Facultad;

public class FacultadDao extends Facultad {
  //CONSTRUCTOR
  public FacultadDao(String codigo, String nombre) {
    super(codigo, nombre);
  }
  
  //CONSULTAS SQL
  public void insert(ConexionDB conexionDB, String nitUniversidad) {
    try {
      if(conexionDB.getConexion() != null) {
        String query = "INSERT INTO facultades(codigo, nombre, universidad_nit) VALUES(?,?,?,?)";
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
