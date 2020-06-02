package Cliente;
import buyEntities.compras;
public interface I_Compras {
    public void finalizarCompras();
    public void adcionarItem(int id);
    public void removerItem(int id);
    public void addCombustivel(int id, double valor);
    public void addQtd(int id);
    public void decrQtd(int id);
    public void listarCombustiveis();
    public void listarItens();
    public void addCarrinhoToConta(cliente c, compras car);
}
