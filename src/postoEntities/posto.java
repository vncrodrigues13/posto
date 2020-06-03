package postoEntities;

import java.util.Scanner;
import buyEntities.compras;
import posto.exceptions.*;
import java.io.FileNotFoundException;
import UI.*;

public class posto {

    private final repProduto repositorioItens; //repositorioItens

    private repositorioContas repContas; //repositorio contas

    private repositorioCompras repCompras; //repositorio compras
    
    private repositorioFuncionarios repFunc;
    
    private user logIn;

    private posto() throws invalidItem, invalidPrice, invalidQtdItens, naoProduto, naoCombustivel,FileNotFoundException {
        repositorioItens = new repProduto();
        repCompras = repositorioCompras.getInstance(); 
        repContas = repositorioContas.getInstance();
        repFunc = repositorioFuncionarios.getInstance();
        //acessoUsuario(); <-----------------------------
        //attArquivos v v v 
        
    }
    
    private static posto self;
    
    public static synchronized posto getInstance() throws invalidItem, invalidPrice, invalidQtdItens, naoProduto, naoCombustivel, FileNotFoundException{
        if (self == null){
            self = new posto();
        }
        return self;
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
