package postoEntities;

import Cliente.cliente;
import Cliente.preencherPessoas;
import buyEntities.compras;
import java.util.ArrayList;
import posto.exceptions.invalidItem;
import posto.exceptions.invalidPrice;
import posto.exceptions.invalidQtdItens;
import java.io.*;
import java.util.Iterator;
import posto.exceptions.noHistory;

public class repositorioContas implements Runnable, createIterator {
    
    private ArrayList<cliente> lista_clientes; //lista de todos os clientes

    private static repositorioContas self;

    private repositorioContas() throws invalidItem, invalidPrice, invalidQtdItens, FileNotFoundException {
        lista_clientes = preencherPessoas.getListaCompleta();
    }

    public static synchronized repositorioContas getInstance()throws invalidItem, invalidPrice, invalidQtdItens, FileNotFoundException {
        if (self == null){
            self = new repositorioContas();
        }
        return self;
    }

    public cliente getCliente(String cpf) {

        for (cliente c : lista_clientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }

    public boolean existeConta(String cpf) {
        for (cliente c : lista_clientes) {
            if (c.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }

    public void adicionarContaLista(String cpf){
        if (cpf.length() == 11) {
            lista_clientes.add(new cliente(cpf));
            
        } else {
            System.out.println("CPF invalido");
        }
    }

    public void save(){
        Thread c = new Thread(this);
        c.start();
    }

    @Override public void run(){
        String path = ".\\src\\file\\clientes.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (cliente c: lista_clientes){
                String cpf = c.getCpf();
                bw.write(cpf);
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    

    public ArrayList<cliente> getListaClientes() {
        return this.lista_clientes;
    }

    public Iterator listarHistoricoComprasCPF(String cpf) throws noHistory {
        return getCliente(cpf).listarHistoricoDeCompras();
    }

    

    @Override
    public String toString() {
        for (cliente c : lista_clientes) {
            System.out.println(c);
        }
        return "";
    }

    @Override
    public Iterator getRepo() {
        return lista_clientes.iterator();
    }
}
