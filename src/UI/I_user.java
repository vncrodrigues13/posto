package UI;

import posto.exceptions.*;
import java.io.FileNotFoundException;

public interface I_user {
    public void entrarContaCliente() throws FileNotFoundException, invalidItem, invalidPrice, invalidQtdItens, naoProduto, naoCombustivel, noHistory, invalidLogin, invalidAccess;
    public void criarContaCliente() throws invalidItem, invalidPrice, invalidQtdItens, naoProduto, naoCombustivel, noHistory, invalidLogin, invalidAccess, FileNotFoundException;
    public void entrarContaAdmin() throws invalidItem, invalidPrice, invalidQtdItens, FileNotFoundException;
    public void entrarContaGerente() throws invalidItem, invalidPrice, invalidQtdItens, FileNotFoundException;
}
