package Class;
/**
 * @author Tomás y Carlos
 */
public final class Operaciones {
    /**
     * Constructor para la clase Operaciones, y no permite crear un objeto de esta clase
     */
    private Operaciones() {
    }

    /**
     * Realiza la operacion de suma de matrices
     * @param a la primera matriz a sumar
     * @param b la segunda matriz a sumar
     * @return un objeto de la clase matriz, que es la resultante de la operacion efectuada
     */
    public static Matriz suma(Matriz a, Matriz b){

        Matriz result = new Matriz(a.getM(), a.getN());
        double[][] c = new double[a.getM()][a.getN()];
        
        for(int i = 0; i < a.getM(); i ++){
            for (int j = 0; j < b.getN(); j ++) {
                c[i][j]= a.getEntradas()[i][j] + b.getEntradas()[i][j];
            }
        }

        result.setEntradas(c);
        return result;
    }

    /**
     * Realiza la operacion de resta de matrices
     * @param a la primera matriz a restar
     * @param b la segunda matriz a restar
     * @return un objeto de la clase matriz, que es la resultante de la operacion efectuada
     */
    public static Matriz resta(Matriz a, Matriz b){
        
        Matriz result = new Matriz(a.getM(), a.getN());
        double[][] c = new double[a.getM()][a.getN()];
        
        for(int i = 0; i < a.getM(); i ++){
            for (int j = 0; j < b.getN(); j ++) {
                c[i][j]= a.getEntradas()[i][j] - b.getEntradas()[i][j];
            }
        }

        result.setEntradas(c);
        return result;
    }
    
    /**
     * Realiza la operacion de multiplicacion entre matrices
     * @param a la primera matriz a multiplicar
     * @param b la segunda matriz a multiplicar
     * @return un objeto de la clase matriz, que es la resultante de la operacion efectuada
     */
    public static Matriz mul(Matriz a, Matriz b){
        Matriz result = new Matriz(a.getM(), b.getN());
        double[][] c = new double[a.getM()][b.getN()];
         
        for(int i = 0; i < a.getM(); i ++) {
            for(int j = 0; j < b.getN(); j ++) {
                double suma = 0;
                for(int k = 0; k < b.getM(); k ++) {
                    suma = suma + a.getEntradas()[i][k] * b.getEntradas()[k][j];
                }
                c[i][j] = suma;
            }
        }
         
        result.setEntradas(c);
        return result;
    }

    /**
     * Realiza la operacion de ultiplicacion de un escalar por una matriz
     * @param a la matriz a multiplicar
     * @param k el escalar 
     * @return un objeto de la clase matriz, que es la resultante de la operacion efectuada
     */
    public static Matriz mul(Matriz a, double k) {
        Matriz result = new Matriz(a.getM(), a.getN());
        double[][] c = new double[a.getM()][a.getN()];

        for(int i = 0; i < a.getM(); i ++){
            for (int j = 0; j < a.getN(); j ++) {
                c[i][j]= a.getEntradas()[i][j] * k;
            }
        }
        result.setEntradas(c);
        return result;
    }

    /**
     * Realiza la operacion de matriz transpuesta
     * @param a la matriz a transponer
     * @return un objeto de la clase matriz, que es la resultante de la operacion efectuada
     */
    public static Matriz trans(Matriz a) {
        Matriz result = new Matriz(a.getN(), a.getM());
        double[][] c = new double[a.getN()][a.getM()];
        for(int i = 0; i < a.getM(); i ++){
            for (int j = 0; j < a.getN(); j ++) {
                c[j][i]= a.getEntradas()[i][j];
            }
        }
        result.setEntradas(c);
        return result;
    }

    /**
     * Realiza la operacion de potenciacion de una matriz
     * @param a la matriz a potenciar
     * @param exp la potencia
     * @return un objeto de la clase matriz, que es la resultante de la operacion efectuada
     */
    public static Matriz pow(Matriz a, int exp) {
        Matriz result = new Matriz(a.getM(), a.getN());
        result = a;
        for(int i = 1; i < exp; i ++) {
            result = mul(result, a);
        }
        return result;
    }
}
