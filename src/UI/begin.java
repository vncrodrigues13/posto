package UI;

import java.io.FileNotFoundException;
import posto.exceptions.invalidAccess;
import posto.exceptions.invalidItem;
import posto.exceptions.invalidLogin;
import posto.exceptions.invalidPrice;
import posto.exceptions.invalidQtdItens;
import posto.exceptions.naoCombustivel;
import posto.exceptions.naoProduto;
import posto.exceptions.noHistory;

import postoEntities.posto;
import Cliente.cliente;
import admin.repositorioAdmin;
import java.util.Scanner;
import postoEntities.repositorioContas;
import postoEntities.repositorioFuncionarios;

import admin.acessoAdmin;
import admin.acessoGerente;
import java.util.logging.Level;
import java.util.logging.Logger;

public class begin implements I_userInterface {

    public Scanner in = new Scanner(System.in);
    
    private repositorioContas rc;
    private repositorioFuncionarios repFuncionarios;
    private repositorioAdmin repAdmin;
    private String cpf;

    public static void main(String[] args) throws invalidItem, invalidPrice, invalidQtdItens,
            naoProduto, naoCombustivel, invalidLogin, invalidAccess, noHistory, FileNotFoundException {
        posto p = new posto();
        
    }

    public void start(){
        boolean running = true;
        int resposta; 
        do{
            System.out.println("Criar nova conta, digite 1");
            System.out.println("Acessar conta, digite 2");
            System.out.println("Entrar Admnistrador, digite 3");
            System.out.println("Entrar Gerente, digite 4");
            System.out.print("Resposta -> ");
            resposta = in.nextInt();
            
            switch (resposta){
                case 1:
                    criarContaCliente();
                    break;
                case 2:
                    entrarContaCliente();
                    break;
                case 3:
                    entrarContaAdmin();
                    break;
                case 4:
                    //falta criar metodo entrarContaGerente
                    break;
                default:
                    System.out.println("Comando invalido");
            }
            
            
            
        }while(running);
    }
    public void entrarContaCliente() {
        System.out.print("Insira o seu CPF: ");
        cpf = in.next();

        if (rc.existeConta(cpf)) {
            return rc.getCliente(cpf); //chamar clienteUI
        }
        System.out.println("Conta inexistente");
        
    }
    
    public void criarContaCliente() {
        do {
            System.out.print("Insira o seu cpf: ");
            cpf = in.next();

            if (cpf.length() == 11) {

                if (rc.existeConta(cpf)) {
                    System.out.println("Conta existente");
                } else {
                    rc.adicionarContaLista(cpf);
                    rc.getCliente(cpf); //chamar clienteUI
                }
            } else {
                System.out.println("CPF INVALIDO");
            }
        } while (true);
    }

    public void entrarContaAdmin() {
        boolean failConnect = false;
        char resp = 0;
        repAdmin = repositorioAdmin.getInstance();
        do {
            System.out.print("Insira o seu CPF: ");
            cpf = in.next();

            if (cpf.length() == 11) {
                if (repAdmin.isAdmin(cpf)) { //verificar se é um admin
                    try {
                        acessoAdmin a = new acessoAdmin(); //abrindo menu de admin
                    } catch (invalidItem ex) {
                        ex.printStackTrace();
                    } catch (invalidPrice ex) {
                        ex.printStackTrace();
                    } catch (invalidQtdItens ex) {
                        ex.printStackTrace();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    break;
                } else {
                    System.out.println("Conta inexistente");    //se nao existir conta no repositorio de admin
                    failConnect = true;
                }
                
            }else{  //quantidade de digitos no cpf diferente de 11, o que nao existe
                System.out.println("Quantidade de digitos invalidos"); 
                failConnect = true;                                                                         
            }
            
            if (failConnect){
                System.out.println("Tente novamente");
            }
            
        } while (failConnect);

    }

    

}
