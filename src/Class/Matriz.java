package Class;
/**
 * @author Tomás y Carlos
 */
public class Matriz {
    // Atributos
    
    //Corresponde a una matriz estatica que contiene las entradas de una matriz A,subI,subJ. 
    private double[][] entradas;    
    //Corresponde a el numero de filas de la matriz.
    private int m;
    //Corresponde a el numero de columnas de la matriz.
    private int n;
    // Corresponde al nombre de la matriz
    private String nombre;
    
    /**
     * Constructor de la clase Matriz, que permite crear objetos de la clase Matriz 
     * @param m
     * @param n 
     */
    public Matriz(int m, int n, String nombre){
        this.m = m;
        this.n = n;
        this.entradas = new double[m][n];
        this.nombre = nombre;
    }
    
    public Matriz(int m, int n){
        this.m = m;
        this.n = n;
        this.entradas = new double[m][n];
    }
    
    //Getters

    /**
     * Get del atributo entradas
     * @return una matriz de tipo double, que corresponde al atributo entradas
     */
    public double[][] getEntradas() {
        return entradas;
    }

    /**
     * Get del atributo n
     * @return un entero que corresponde al atributo m
     */
    public int getM() {
        return m;
    }

    /**
     * Get del atributo n
     * @return un entero que corresponde al atributo n
     */
    public int getN() {
        return n;
    }

    //Setter
    /**
     * Set del atributo entradas
     * @param entradas una matriz  con las entradas que se quieren que se actualicen en el atributo entradas
     * @return true si se logran actualizar las entradas y false sino se logran actualizar las entradas
     */
    public boolean setEntradas(double[][] entradas) {
        
        if(entradas.length == this.m && entradas[0].length == this.n){
            this.entradas = entradas;
            return true;
        }
        else{
            return false;
        }
            
    }
    
    //Metodos - Retornan un booleano de si una matriz es de determinado tipo

    /**
     * Dice si una matriz es cuadrada o no. Es decir, si es de longitud nxn
     * @return true si es cuadra, y false si no es cuadrada
     */
    public boolean esCuadrada(){
        return this.getM() == this.getN();
    }

    /**
     * Dice si una matriz es nula o no. Es decir, si todas sus entradas son iguales a cero
     * @return true si es nula, y false si no es nula
     */
    public boolean esNula(){
        for(int i = 0; i < this.m; i ++){
            if(!this.esFilaNula(i)) return false;
        }
        return true;
    }

    /**
     * Dice si una matriz es identidad o no. Es decir, que esta formada por unos en su diagonal principal 
     * y ceros en todas las demás entradas
     * @return true si es identidad, y false si no es identidad
     */
    public boolean esIdentidad(){
        if(!this.esCuadrada()) return false; 
        for(int i = 0; i < this.m; i ++){
            for(int j = 0; j < this.n; j ++){
                if( i == j && this.getEntradas()[i][j] != 1) return false;
                if(i != j && this.getEntradas()[i][j] != 0) return false; 
            }
        }
        return true;
    }

    /**
     * Dice si una matriz es simetrica o no. Es decir, si es cuadrada y su transpuesta es igual a ella misma
     * @return true si es simetrica, y false si no es simetrica
     */
    public boolean esSimetrica() {
        for(int i = 0; i < this.m; i ++) {
            for(int j = 0; j < this.n; j ++) {
                if(!(this.entradas[i][j] == Operaciones.trans(this).entradas[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Dice si una matriz es antisimetrica o no. Es decir, si es cuadrada y su transpuesta es igual a ella misma
     * pero multiplicada por -1, o en otras palabras, ella misma negativa 
     * @return true si es antisimetrica, y false si no es antisimetrica
     */
    public boolean esAntisimetrica() {
        for(int i = 0; i < this.m; i ++) {
            for(int j = 0; j < this.n; j ++) {
                if(!(-this.entradas[i][j] == Operaciones.trans(this).entradas[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Dice si una matriz es diagonal o no. Es decir, si es cuadrada y todas sus entradas diferentes a la de 
     * la diagonal principal son iguales a cero
     * @return true si es diagonal, y false si no es diagonal
     */
    public boolean esDiagonal() {
        if(!this.esCuadrada()) return false;
        for(int i = 0; i < this.m; i ++) {
            for(int j = 0; j < this.n; j ++) {
                if(i != j) {
                    if(this.entradas[i][j] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Dice si una matriz es triangular superior o no. Es decir, si es cuadrada y si todas las entradas por debajo
     * de su diagonal principal son iguales a cero
     * @return true si es triangular superior, y false si no es triangular superior
     */
    public boolean esTriangularSuperior() {
        if(!this.esCuadrada()) return false;
        for(int i = 0; i < this.m; i ++) {
            for(int j = 0; j < this.n; j ++) {
                if( i > j) {
                    if(this.entradas[i][j] != 0) return false;
                }
            }
        }
        return true;
    }

    /**
     * Dice si una matriz es triangular inferior o no. Es decir, si es cuadrada y si todas las entradas por encima
     * de su diagonal principal son iguales a cero
     * @return true si es triangular inferior, y false si no es triangular inferior
     */
    public boolean esTriangularInferior() {
        if(!this.esCuadrada()) return false;
        for(int i = 0; i < this.m; i ++) {
            for(int j = 0; j < this.n; j ++) {
                if( i < j) {
                    if(this.entradas[i][j] != 0) return false;
                }
            }
        }
        return true;
    }

    /**
     * Dice si una matriz esta en su forma escalonada o no. Es decir, si todas las filas nulas (si las hay),
     * deben ubicarse en la parte mas inferior de la matriz y dentro de las filas no nulas (si las hay), el 
     * pivote de una fila debe estar más a la derecha que el pivote de la fila anterior
     * @return true si esta en su forma escalonada, y false si no esta en su forma escalonada
     */
    public boolean esEscalonada() {
        for(int i = 0; i < this.m; i ++) {
            int tc = 0;
            for(int j = 0; j < this.n && i < this.m; j ++) {
                if(this.entradas[i][j] != 0) {
                    tc = j;
                    i ++;
                    for(j = 0; j < this.n && i < this.m; j ++) {
                        if(this.entradas[i][j] != 0) {
                            if(j <= tc) return false;
                            break;
                        }
                    }
                    //revisa si hay alguna fila nula, y si la hay revisar que todas en adelante sean nulas
                    if(i < this.m && esFilaNula(i)) {
                        for(int k = i + 1; k < this.m; k ++) {
                            if(!esFilaNula(k)) {
                                return false;
                            }
                        }
                        return true;
                    }
                    j = tc;
                }
            }
        }
        return true;
    }

    /**
     * Dice si una fila es nula o no. Es decir, si es todas las entradas de las filas son iguales a cero
     * @param f la posicion de fila que se va a revisar si es nula o no
     * @return true si es nula, y false si no es nula
     */
    public boolean esFilaNula(int f) {
        int n = 0;
        for(int i = 0; i < this.n; i ++) {
            if(this.getEntradas()[f][i] != 0) n ++;
        }
        if(n == 0) return true;
        else return false;
    }

    /**
     * Dice si una matriz esta en su forma escalonada reducida. Es decir, que este en forma escalonada, que todos
     * los pivotes sean iguales a uno y por arriba y por debajo de los pivotes solamente haya cero en esas entradas
     * @return true si esta en su forma escalonada reducida, y false si no esta en su forma escalonada reducida
     */
    public boolean esEscalonadaReducida() {
        if(this.esEscalonada()) {
            for(int i = 0; i < this.m; i ++) {
                for(int j = 0; j < this.n && i < this.m; j ++) {
                    if(this.entradas[i][j] == 1) {
                        i ++;
                        for(int k = 0; k < this.m  && i < this.m; k ++) {
                            if(k != i -1) {
                                if(!(entradas[k][j] == 0)) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Dice si una matriz es invertible o no. Es decir, si es posible encontrar una matriz que sea "el inverso
     * multiplicativo" tal que estas multiplicadas de como resultado la matriz identidad de longitud n
     * @return true si es invertible, y false si no es invertible
     */
    public boolean esInvertible(){
        if(!this.esCuadrada()) return false;

        Matriz r = new Matriz(this.m, this.n);
        r = GaussJ.escalonadaReducida(this);
        return r.esIdentidad();
    }

    /**
     * Dice si una matriz es involutiva o no. Es decir, si su inversa es ella misma
     * @return true si es involutiva, y false si no es involutiva
     */
    public boolean esInvolutiva() {
        Matriz r = GaussJ.inversa(this);
        for(int i = 0; i < this.m; i ++) {
            for(int j = 0; j < this.n; j ++) {
                if(this.entradas[i][j] != Math.round(r.entradas[i][j])) return false;
            }
        }
        return true;
    }
}
