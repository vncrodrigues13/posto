package Cliente;

import buyEntities.compras;
import java.io.*;
import java.util.ArrayList;
import posto.exceptions.invalidItem;
import posto.exceptions.invalidPrice;
import posto.exceptions.invalidQtdItens;
import postoEntities.repositorioCompras;

public class preencherPessoas {

    private static repositorioCompras repC;
    private static ArrayList<cliente> listaClientes;
    
    public static ArrayList<cliente> getListaCompleta() throws invalidItem, invalidPrice, invalidQtdItens, FileNotFoundException{
        repC = repositorioCompras.getInstance();
        listaClientes = new ArrayList<>();
        preencherLista();
        preencherContas();
        return listaClientes; 
    }

    private static void preencherLista() {
        String line;
        String path = ".\\src\\file\\clientes.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            line = br.readLine();
            while (line != null) {
                if (line.length() == 11) {
                    listaClientes.add(new cliente(line));
                }
                line = br.readLine();
            }

        } catch (IOException e) {

        }
        
    }

    private static void preencherContas() throws invalidItem, invalidPrice, invalidQtdItens {

        ArrayList<compras> lista_compras = repC.getHistoricoCompras();
        for (compras c : lista_compras) {
            if (getCliente(c.getCPF()) != null) {
                getCliente(c.getCPF()).addArrayList(c);
            }
        }
    }

    private static cliente getCliente(String cpf) {
        for (cliente c : listaClientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }
    
    

}
