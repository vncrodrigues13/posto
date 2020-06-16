package Cliente;
import java.util.Iterator;
import Pessoa.pessoa;
import java.util.List;
import java.util.ArrayList;
import buyEntities.*;
import posto.exceptions.noHistory;

public class cliente extends pessoa {

    private ArrayList<compras> historico_de_compras = new ArrayList<>();

    public cliente(String cpf) {
        super(cpf);
    }

    public void addArrayList(compras c) {
        historico_de_compras.add(c);
    }

    public Iterator listarHistoricoDeCompras() throws noHistory {
        
        if (historico_de_compras.isEmpty()) {
            throw new noHistory();
        } 
        return this.historico_de_compras.iterator();
    }

    public boolean identificacao(String cpf) {
        return cpf.trim().equals(this.cpf.trim());
    }

    @Override
    public String toString() {
        return this.cpf;
    }

    public Iterator getListaCompras() {
        return this.historico_de_compras.iterator();
    }

}
