package postoEntities;

import java.util.Scanner;
import buyEntities.compras;
import posto.exceptions.*;
import java.io.FileNotFoundException;
import UI.*;

public class posto {

    private repositorioContas repContas; //repositorio contas

    private repositorioCompras repCompras; //repositorio compras
    
    private repositorioFuncionarios repFunc;
    private user logIn;
    private static posto self;
    

    public posto() throws invalidItem, invalidPrice, invalidQtdItens, naoProduto, naoCombustivel,FileNotFoundException, noHistory, invalidLogin, invalidAccess {
        repCompras = repositorioCompras.getInstance(); 
        repContas = repositorioContas.getInstance();
        repFunc = repositorioFuncionarios.getInstance();
    }
    public void addCompras(String cpf, compras car) {
        repContas.getCliente(cpf).addArrayList(car);
        repCompras.addRepositorio(car);
    }
    
    public void fullSave(){
        repCompras.save();
        repContas.save();
        repFunc.save();
    }
    
    
    
    
    
    
    
}
