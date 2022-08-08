import controller.MainController;


public class App {
    public static void main(String[] args) throws Exception {
        new MainController();
    }  
}

/*NOTA: Todos los metodos que creemos dentro de la misma clase del main deben de ser estaticos para poderlos llamar dentro de main */
//Construyo el objeto menu para que a su vez se construya otros objetos y el resto del programa, por medio de metodos...
//Hay que dejar el main de APP lo mas liviano posible
