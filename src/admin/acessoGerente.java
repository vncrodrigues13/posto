package admin;
import java.io.FileNotFoundException;
import posto.exceptions.invalidItem;
import posto.exceptions.invalidPrice;
import posto.exceptions.invalidQtdItens;
import postoEntities.repositorioFuncionarios;
import postoEntities.repositorioContas;
import java.util.Scanner;
public class acessoGerente {
    private Scanner in = new Scanner(System.in);
    private repositorioFuncionarios repFuncionarios;
    private repositorioContas repClientes;
    
    public acessoGerente(){
        
    }
    
    
    public void loginGerente() throws invalidItem, invalidPrice, invalidQtdItens, FileNotFoundException{

        repClientes = repositorioContas.getInstance();
        repFuncionarios = repositorioFuncionarios.getInstance();
        int resposta;
        do{
        System.out.println("Olá gerente,");
        System.out.println("Listar os funcionários, digite: 1");
        System.out.println("Listar os gerentes, digite: 2");
        System.out.println("Listar os clientes, digite: 3");
        System.out.println("Finalizar acesso, digite: 0");
        System.out.print("---->> ");
        resposta = in.nextInt();
        switch (resposta){
            case 1:
                repFuncionarios.listarFuncionarios();
                break;
            case 2:
                repFuncionarios.listarGerentes();
                break;
            case 3:
                System.out.println(repClientes);
                break;
            case 0:
                System.exit(0);
                break;
        }
            System.out.println("\n\n");
        }while (true);
    }
}
