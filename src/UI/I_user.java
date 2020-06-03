package UI;

import posto.exceptions.*;
import java.io.FileNotFoundException;

public interface I_user {
    public void entrarContaCliente() throws FileNotFoundException, invalidItem, invalidPrice, invalidQtdItens;
    public void criarContaCliente();
    public void entrarContaAdmin();
    
}
