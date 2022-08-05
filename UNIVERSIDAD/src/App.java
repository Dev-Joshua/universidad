import java.sql.ResultSet;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws Exception {
        ConexionDB conn = new ConexionDB();
        // crearUniversidad(conn);
        // mostrarUniversidades(conn);
        obtenerUniversidadXnit(conn);

        // conn.cerrarConexion();
    }

    //Metodo para crear una universidad y guardarla en BD.
    public static void crearUniversidad(ConexionDB conn) throws SQLException {
        //Creo el objeto de tipo Universidad(objUni)
        Universidad objUni = new Universidad("UNAL", "98765", "CRA 45", "info@udal.edu.co");
        // Universidad objUni_2 = new Universidad("UNAD", "54321", "CRA 10", "info@unad.edu.co");
        // Universidad objUni_3 = new Universidad("UDEA", "56789", "CL 67 #53-108", "info@udea.edu.co");

        //Almacenar la universidad en la BD
        boolean insert = objUni.insert(conn.getConexion());
        if(insert == true){
            System.out.println("Universidad registrada con exito");
        }else{
            System.out.println("¡Ups! Sucedio un error. Intenta mas tarde");
        }

    }

    //Metodo para obtener y mostrar en consola las universidades que contiene la BD
    public static void mostrarUniversidades(ConexionDB conn) {
        try {                                                                                                //Se llama al metodo estatico getUniversidades() referenciando solo al nombre de la clase Universidad. y le pasamos un objeto conexion.
            ResultSet result = Universidad.getUniversidades(conn.getConexion());                            //En el resulset almacenamos de universidad.getUniv... las universidades. Haciendo la conexion                   
            String info = "--------------UNIVERSIDADES----------------\n";                                 //Creo la variable que va a concatenar mi resultado
            //Iterar sobre el resultado(universidades)
            while(result.next()){                                                                         //Del resultado obtenido le aplico .next() para saber si hay registros por iterar(cada fila), si retorna true ejecuta la sentencia dentro del while
                String nit = result.getString("nit");                                        //Obtengo el nit y el nombre de la universidad                                       
                String nombre = result.getString("nombre");                                  //Concatenamos con la variable info
                info += "Nit: " + nit;
                info += "\nNombre: " + nombre;
                info += "\n--------------------------\n";                                                //Salto de linea para hacer la division entre cada universidad
            }                                                                                            //Cuando el .next no vea mas registros por iterar en result, el retornara falso y termina el ciclo.
            //Mostrar universidades                                                                                          
            System.out.println(info);                                                                   
        }catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    //Metodo par aobtener universidad por Nit. Se necesita obtener el objeto de(conexionDB)
    public static void obtenerUniversidadXnit(ConexionDB conn) {
        //Obtener la universidad con el numero de nit ""
        ResultSet result = Universidad.getUniversidadByNit(conn.getConexion(), "12345");               //Como parametro se envia el getConexon() y el nit(universidad). Retorna un resulset
        try {
            //SI! hay por lo menos un registro(nit) para acceder a esa info, capturamos el nit, nombre, etc...(result.getString("nit"))
            if(result.next()){
                String nit = result.getString("nit");                                          //Obtener todos los campos
                String nombre = result.getString("nombre");
                String direccion = result.getString("direccion");
                String email = result.getString("email");
                //Concatenar informacion en una sola variable info
                String info = "Nit: " + nit;
                info += "\nNombre: " + nombre;
                info += "\nDireccion: " + direccion;
                info += "\nEmail: " + email;
                System.out.println(info);
            }
        } catch (Exception e) {
            e.printStackTrace();;
        }      
    }
}

/*NOTA: Todos los metodos que creemos dentro de la misma clase del main deben de ser estaticos para poderlos llamar dentro de main */
