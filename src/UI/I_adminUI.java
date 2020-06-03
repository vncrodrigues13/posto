package UI;

import java.io.FileNotFoundException;
import posto.exceptions.*;

public interface I_adminUI {
    public void balancaEconomica() throws invalidItem, invalidPrice, invalidQtdItens, FileNotFoundException;
    public void admissaoFuncionario();
    public void demissaoFuncionario();
    public void pagamentoFuncionario();
}
