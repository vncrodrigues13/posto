package UI;

import posto.exceptions.*;
import buyEntities.compras;
import Cliente.cliente;
import java.io.FileNotFoundException;

public interface I_ClienteUI {
    public void listarHistoricoCompras() ;
    public void fazerCompras() throws invalidItem,invalidPrice, invalidQtdItens, naoProduto, naoCombustivel;
    public void adicionarItem() throws invalidItem, invalidQtdItens;
    public void removerItem() throws invalidItem ;
    public void addCombustivel() throws invalidItem, invalidPrice;
    public void finalizarCompras() throws invalidItem, invalidPrice, invalidQtdItens, naoProduto, naoCombustivel, invalidLogin, invalidAccess, noHistory, FileNotFoundException;
    
}
