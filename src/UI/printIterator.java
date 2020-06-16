package UI;
import java.util.Iterator;
import Funcionario.funcionario;
import buyEntities.compras;
import buyEntities.produto;
import Cliente.cliente;

public class printIterator {
    
    public static void printFunc(Iterator it){
        while(it.hasNext()){
            funcionario f = (funcionario)it.next();
            System.out.println(f);
        }
    }
    
    public static void printCompras(Iterator it){
        while (it.hasNext()){
            compras c = (compras) it.next();
            System.out.println(c);
        }
    }
    
    public static void printConta(Iterator it){
        while(it.hasNext()){
            cliente c = (cliente) it.next();
            System.out.println(c);
        }
    }
    
    public static void printProd(Iterator it){
        while (it.hasNext()){
            produto p = (produto) it.next();
            System.out.println(p);
        }
    }
}
