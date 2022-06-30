package Class;
/**
 * @author Tomás y Carlos
 */
public final class GaussJ {
    /**
     * Constructor para la clase GaussJ, y no permite crear un objeto de esta clase
     */
    private GaussJ(){
    }
    
    /**
     * Operacion de transformar una matriz a su forma escalonada reducida, por medio del metodo de Gauss Jordan
     * @param a el objeto matriz, en la que se va a realizar la operacion
     * @return un objeto de la clase matriz, que es la matriz entregada pero en su forma escalonada reducida
     */
    public static Matriz escalonadaReducida(Matriz a){
        
        Matriz result = new Matriz(a.getM(),a.getN());
        double[][] r = new double[a.getM()][a.getN()];

        for(int i = 0; i < a.getM(); i ++){
            for(int j = 0; j < a.getN(); j ++){
                r[i][j] = a.getEntradas()[i][j];
            }
        }
        
        if(a.esEscalonadaReducida()) return a;
        if(a.esNula()) return a;

        int filasNulas =0;
        for(int i = 0; i < a.getM(); i ++){
            if(a.esFilaNula(i)){
                filasNulas ++;
                intercambiarFilas(r, i + 1, r.length - filasNulas);
            }
        }

        for(int i = 0; i < a.getM(); i ++){
            boolean bandera = true;
            for(int j = 0; j < a.getN() && bandera; j ++){
                if(r[i][j] != 0){
                    if(r[i][j] != 1){
                        mulFila(r, i + 1, 1/r[i][j]);
                        i --;
                        bandera = false;
                    }
                    else{
                        for(int f = 0; f < a.getM() && bandera; f ++){
                            if(i != f && r[f][j] != 0)
                                mulFilaYSuma(r, -(r[f][j]), i + 1, f + 1);
                        }
                        bandera = false;
                    }
                }
            }
        }
        result.setEntradas(r);
        return result;
    }
    
    /**
     * Operacion de calcular la inversa de una matriz, creando la matriz ampliada a la identidad y aplicando la 
     * operacion de transformar a forma escalonada reducida 
     * @param a el objeto matriz, en la que se va a realizar la operacion
     * @return n objeto de la clase matriz, que es la inversa de la matriz entregada
     */
    public static Matriz inversa(Matriz a){

        if(!a.esInvertible()) return null;

        Matriz result = new Matriz(a.getM(), a.getN());
        double[][] r = new double[a.getM()][a.getN()];
        Matriz ampliada = new Matriz(a.getM(), a.getN() * 2);
        double[][] amplIdent = new double[a.getM()][a.getN() * 2];
        double[][] identidad = new double[a.getM()][a.getN()];

        for(int i = 0; i < a.getM(); i ++){
            for(int j = 0; j < a.getN(); j ++){
                if(i == j) identidad[i][j] = 1;
                else identidad[i][j] = 0;
            }
        }

        for(int i = 0; i < a.getM(); i ++){
            for(int j = 0; j < a.getN(); j ++){
                amplIdent[i][j] = a.getEntradas()[i][j];
                amplIdent[i][j + a.getN()] = identidad[i][j]; 
            }
        }

        ampliada.setEntradas(amplIdent);
        Matriz temp = GaussJ.escalonadaReducida(ampliada);

        for(int i = 0 ; i < a.getM(); i ++){
            for(int j = 0; j < a.getN(); j ++){
                r[i][j] = temp.getEntradas()[i][j + a.getN()]; 
            }
        }

        result.setEntradas(r);
        return result;
    }

    /**
     * Operacion de intercambiar filas de una matriz
     * @param a la matriz en la que se va a realizar la operacion
     * @param x1 el numero de la primera fila a intercambiar
     * @param x2 el numero de la segunda fila a intercabiar
     */
    public static void intercambiarFilas(double[][] a, int x1, int x2){
        double temp = 0;
        
        for(int i = 0; i < a[x1 - 1].length; i ++){
            temp = a[x1 - 1][i];
            a[x1 - 1][i] = a[x2 - 1][i];
            a[x2 - 1][i] = temp;
        }
        
    }

    /**
     * Operacion de intercambiar filas de una matriz
     * @param a el objeto matriz, en la que se va a realizar la operacion
     * @param x1 el numero de la primera fila a intercambiar
     * @param x2 el numero de la segunda fila a intercabiar
     */
    public static void intercarmbiarFilas(Matriz a, int x1, int x2){
        double temp = 0;
        
        for(int i = 0; i < a.getEntradas()[x1 - 1].length; i ++){
            temp = a.getEntradas()[x1 - 1][i];
            a.getEntradas()[x1 - 1][i] = a.getEntradas()[x2-1][i];
            a.getEntradas()[x2 - 1][i] = temp;
        }
        
        
    }
    
    /**
     * Operacion de multiplicacion de un escalar por cualquier fila de una matriz
     * @param a la matriz en la que se va a realizar la operacion
     * @param x el numero de la fila en la que se va a realizar la operacion
     * @param k el escalar
     */
    public static void mulFila(double[][] a, int x, double k){
        for(int i = 0; i < a[x - 1].length; i ++){
            a[x - 1][i] *= k;
        }
        
    }
    
    /**
     * Operacion de multiplicacion de un escalar por cualquier fila de una matriz
     * @param a el objeto matriz, en la que se va a realizar la operacion
     * @param x el numero de la fila en la que se va a realizar la operacion
     * @param k el escalar 
     */
    public static void mulFila(Matriz a, int x, double k){
        for(int i =0 ; i < a.getEntradas()[x - 1].length; i ++){
            a.getEntradas()[x - 1][i] *= k;
        }
        
    }
    
    /**
     * Operacion de multiplicacion de un escalar por cualquier fila de una matriz y sumar los resultados en otra fila
     * @param a la matriz en la que se va a realizar la operacion
     * @param k el escalar
     * @param x1 el numero de la fila en la que se va a multiplicar
     * @param x2 el numero de la fila en la que se va a sumar y registrar el resultado
     */
    public static void mulFilaYSuma(double[][] a, double k, int x1, int x2){
        for(int i = 0; i < a[x2 - 1].length; i ++){
            a[x2 - 1][i]+= a[x1 - 1][i] * k;
        }       
        
    }
    
    /**
     * Operacion de multiplicacion de un escalar por cualquier fila de una matriz y sumar los resultados en otra fila
     * @param a el objeto matriz, en la que se va a realizar la operacion
     * @param k el escalar
     * @param x1 el numero de la fila en la que se va a multiplicar
     * @param x2 el numero de la fila en la que se va a sumar y registrar el resultado
     */
    public static void mulFilaYSuma(Matriz a, double k, int x1, int x2){
        for(int i = 0; i < a.getEntradas()[x2 - 1].length; i ++){
            a.getEntradas()[x2 - 1][i]+= a.getEntradas()[x1 - 1][i] * k;
        }       
        
    }    
}