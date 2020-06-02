package Cliente;
import posto.exceptions.*;

public interface I_ClienteUI {
    public void fazerCompras() throws invalidItem,invalidPrice, invalidQtdItens, naoProduto, naoCombustivel;
    public void listarHistoricoCompras() ;
}
