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

public class user implements I_user {

    public Scanner in = new Scanner(System.in);

    private repositorioContas repContas;
    private repositorioFuncionarios repFuncionarios;
    private repositorioAdmin repAdmin;
    private String cpf;
    private clienteUI userCliente;
    private adminUI userAdmin;
    private posto postoObj;
    private gerenteUI userGerente;
    public static void main(String[] args) throws invalidItem, invalidPrice, invalidQtdItens,
            naoProduto, naoCombustivel, invalidLogin, invalidAccess, noHistory, FileNotFoundException {
        user a = new user();

    }

    public user() throws invalidItem, invalidPrice, invalidQtdItens, FileNotFoundException, naoProduto, naoCombustivel, noHistory, invalidLogin, invalidAccess {
        postoObj = posto.getInstance();
        repFuncionarios = repositorioFuncionarios.getInstance();
        repContas = repositorioContas.getInstance();
        repAdmin = repositorioAdmin.getInstance();
        start();

    }

    public void start() throws FileNotFoundException, invalidItem, invalidPrice, invalidQtdItens, naoProduto, naoCombustivel, noHistory, invalidLogin, invalidAccess {
        boolean running = true;
        int resposta;
        do {
            System.out.println("Criar nova conta, digite 1");
            System.out.println("Acessar conta, digite 2");
            System.out.println("Entrar Admnistrador, digite 3");
            System.out.println("Entrar Gerente, digite 4");
            System.out.print("Resposta -> ");
            resposta = in.nextInt();

            switch (resposta) {
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
                    entrarContaGerente();
                    break;
                default:
                    System.out.println("Comando invalido");
            }
            postoObj.fullSave();
        } while (running);
    }

    @Override
    public void entrarContaCliente() throws FileNotFoundException, invalidItem, invalidPrice, invalidQtdItens,
            naoProduto, naoCombustivel, noHistory, invalidLogin, invalidAccess {
        System.out.print("Insira o seu CPF: ");
        cpf = in.next();

        if (repContas.existeConta(cpf)) {
            userCliente = new clienteUI(repContas.getCliente(cpf));
        }
        System.out.println("Conta inexistente");

    }

    @Override
    public void criarContaCliente() throws invalidItem, invalidPrice, invalidQtdItens, naoProduto, naoCombustivel, noHistory, invalidLogin, invalidAccess, FileNotFoundException {
        boolean repeticao = false;
        do {
            System.out.print("Insira o seu cpf: ");
            cpf = in.next();

            if (!stringValid.temLetras(cpf)) {
                if (cpf.length() == 11) {

                    if (repContas.existeConta(cpf)) {
                        System.out.println("Conta existente");
                        repeticao = true;
                    } else {
                        repContas.adicionarContaLista(cpf);
                        clienteUI a = new clienteUI(repContas.getCliente(cpf));
                    }
                } else {
                    System.out.println("CPF INVALIDO");
                    repeticao = true;
                }
            }else{
                System.out.println("CPF Invalido, contem letras");
                repeticao = true;
            }
        } while (repeticao);
    }

    @Override
    public void entrarContaAdmin() throws invalidItem, invalidPrice, invalidQtdItens, FileNotFoundException {
        boolean failConnect = false;

        do {
            System.out.print("Insira o seu CPF: ");
            cpf = in.next();

            if (cpf.length() == 11) {
                if (repAdmin.isAdmin(cpf)) { //verificar se é um admin

                    userAdmin = new adminUI();
                    break;
                } else {
                    System.out.println("Conta inexistente");    //se nao existir conta no repositorio de admin
                    failConnect = true;
                }

            } else {  //quantidade de digitos no cpf diferente de 11, o que nao existe
                System.out.println("Quantidade de digitos invalidos");
                failConnect = true;
            }

            if (failConnect) {
                System.out.println("Tente novamente");
            }

        } while (failConnect);

    }

    @Override
    public void entrarContaGerente() throws invalidItem, invalidPrice, invalidQtdItens, FileNotFoundException{
        boolean failConnect;
        do {
            System.out.print("Insira o seu CPF: ");
            cpf = in.next();

            if (cpf.length() == 11) {
                if (repFuncionarios.isGerente(cpf)) { //verificar se é um gerente

                    userGerente = new gerenteUI();
                    break;
                } else {
                    System.out.println("Conta inexistente");    //se nao existir conta no repositorio de gerente
                    failConnect = true;
                }

            } else {  //quantidade de digitos no cpf diferente de 11, o que nao existe
                System.out.println("Quantidade de digitos invalidos");
                failConnect = true;
            }

            if (failConnect) {
                System.out.println("Tente novamente");
            }

        } while (failConnect);
    }

}
