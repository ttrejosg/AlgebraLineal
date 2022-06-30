package Class;
/**
 * @author Tomás y Carlos
 */
public class Main {
    public static void main(String[] args) {
        
        Matriz a = new Matriz(2, 2);
        UserMedia.scan(a);
        System.out.println(a.esInvolutiva());
        
        //Ventana v1 = new Ventana();
        
        //v1.setVisible(true);// hacemos visible la ventana.
        
    }
}
