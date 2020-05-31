package Cliente;

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

    public void listarHistoricoDeCompras() throws noHistory {
        
        if (historico_de_compras == null) {
            throw new noHistory();
        } else {
            for (compras c : historico_de_compras) {
                System.out.println(c);
                System.out.println("******************************************************");
            }
        }
    }

    public boolean identificacao(String cpf) {
        return cpf.trim().equals(this.cpf.trim());
    }

    @Override
    public String toString() {
        return this.cpf;
    }

    public ArrayList<compras> getListaCompras() {
        return this.historico_de_compras;
    }

}
