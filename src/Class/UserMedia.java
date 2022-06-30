package Class;
import java.util.*;
/**
 * @author Tomás y Carlos
 */
public final class UserMedia {
    /**
     * Constructor para la clase UserMedia, y no permite crear un objeto de esta clase
     */
    private UserMedia(){
        
    }

    /**
     * Recibe las entradas de una matriz por consola y los guarda en la matriz dada
     * @param a Matriz dada
     */
    public static void scan(Matriz a){
        double[][] result = new double[a.getM()][a.getN()]; 
        Scanner scan = new Scanner(System.in);
        
        for(int i = 0; i < a.getM(); i ++){
            for(int j = 0; j < a.getN(); j ++){
                result[i][j] = scan.nextDouble();
            }
        }
            
        a.setEntradas(result);
    }
    
    /**
     * Toma los datos de una matriz dada y los imprime en consola
     * @param a La matriz dada
     */
    public static void imprimir(Matriz a){
        for(int i = 0; i < a.getM(); i ++){
            for(int j = 0; j < a.getN(); j ++){
                System.out.print("|");
                System.out.print(a.getEntradas()[i][j] + " ");
            }
            System.out.println("|");
        }
    }
}
