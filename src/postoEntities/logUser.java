package postoEntities;
import postoEntities.repositorioContas;
import Cliente.cliente;

import java.io.FileNotFoundException;
import posto.exceptions.invalidItem;
import posto.exceptions.invalidPrice;
import posto.exceptions.invalidQtdItens;

import admin.gerente;
import admin.repositorioAdmin;


public class logUser {

    

    public static cliente pegarCliente(String cpf)
            throws FileNotFoundException, invalidItem, invalidPrice, invalidQtdItens {
        repositorioContas repContas = repositorioContas.getInstance();
        return repContas.getCliente(cpf);
    }
    public static gerente pegarGerente(String cpf){
        repositorioFuncionarios repFunc = repositorioFuncionarios.getInstance();
        return repFunc.getGerente(cpf);
    }
    
}