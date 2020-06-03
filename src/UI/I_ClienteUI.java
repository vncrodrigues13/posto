package UI;

import posto.exceptions.*;
import buyEntities.compras;
import Cliente.cliente;

public interface I_ClienteUI {
    public void fazerCompras() throws invalidItem,invalidPrice, invalidQtdItens, naoProduto, naoCombustivel;
    public void listarHistoricoCompras() ;
    public void finalizarCompras();
    public void adicionarItem() throws invalidItem, invalidQtdItens;
    public void removerItem(int id) throws invalidItem ;
    public void addCombustivel(int id, double valor);
    public void addQtd(int id) throws invalidItem;
    public void decrQtd(int id) throws invalidItem;
    public void addCarrinhoToConta(cliente c, compras car);
}
