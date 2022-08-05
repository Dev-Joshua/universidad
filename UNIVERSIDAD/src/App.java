public class App {
    public static void main(String[] args) throws Exception {
        ConexionDB conn = new ConexionDB();
        //Creo el objeto de tipo Universidad(objUni)
        Universidad objUni = new Universidad("UTP", "12345", "CRA 100", "info@utp.edu.co");
        Universidad objUni_2 = new Universidad("UNAD", "54321", "CRA 10", "info@unad.edu.co");
        //Almacenar la universidad en la BD
        // boolean insert = objUni.insert(conn.getConexion());
        // if(insert == true){
        //     System.out.println("Universidad registrada con exito");
        // }else{
        //     System.out.println("¡Ups! Sucedio un error. Intenta mas tarde");
        // }
        boolean insert = objUni_2.insert(conn.getConexion());
        if(insert == true){
            System.out.println("Universidad registrada con exito");
        }else{
            System.out.println("¡Ups! Sucedio un error. Intenta mas tarde");
        }
    }
}
