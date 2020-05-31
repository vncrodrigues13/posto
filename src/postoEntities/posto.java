package postoEntities;

import java.util.Scanner;
import buyEntities.compras;
import buyEntities.produto;
import posto.exceptions.*;
import java.util.ArrayList;
import java.util.Calendar;
import Cliente.*;
import java.io.*;
import admin.*;

public class posto {

    private Scanner in = new Scanner(System.in);

    private compras carrinhoTemp;

    private final repProduto repositorioItens = new repProduto(); //repositorioItens

    private repositorioContas repContas; //repositorio contas

    private repositorioCompras repCompras; //repositorio compras

    private usr loginUsr;

    private acessoAdmin acess;

    private admin.receita acessoReceita;
    
    private acessoGerente acessoGerente;

    public posto() throws invalidItem, invalidPrice, invalidQtdItens, naoProduto, naoCombustivel, invalidLogin, invalidAccess, noHistory, FileNotFoundException {
        loginUsr = new usr();
        repCompras = repositorioCompras.getInstance(); 
        repContas = repositorioContas.getInstance();
        
        acessoGerente = new admin.acessoGerente();
        acessoReceita = new admin.receita();
        acessoUsuario();
        //attArquivos v v v 
        
    }

    public void acessoUsuario() throws invalidItem, invalidPrice, invalidQtdItens, 
            invalidLogin, invalidAccess, naoProduto, naoCombustivel, noHistory, FileNotFoundException {
        String cpf = loginUsr.login();
        if (cpf.length() == 12) {
            acess = new acessoAdmin();
        }
        if (cpf.length() == 11) {
            logar(cpf);
        }
        if (cpf.length() == 13){
            acessoGerente.loginGerente();
        }
            
        

    }
    public void logar(String cpf) throws invalidItem, invalidPrice, invalidQtdItens, FileNotFoundException, naoProduto, naoCombustivel, noHistory{
        char resp = 's';
            int y;
            do {
                System.out.println("Olá,");
                System.out.println("Se voce quiser fazer novas compras, digite: 1");
                System.out.println("Se voce quiser ver seus historicos de compra, digite: 2");
                y = in.nextInt();
                if (y == 1) {
                    carrinhoTemp = new compras(cpf);
                    fazerCompras();
                    do {
                        System.out.print("Você deseja fazer mais alguma operação no posto (s/n): ");
                        resp = in.next().charAt(0);
                    } while (resp != 's' && resp != 'n');

                } else if (y == 2) {
                    repContas.getCliente(cpf).listarHistoricoDeCompras();
                    do {
                        System.out.print("Você deseja fazer mais alguma operação no posto (s/n): ");
                        resp = in.next().charAt(0);
                    } while (resp != 's' && resp != 'n');
                }
            } while (y != 1 && y != 2 || resp == 's');
    }

    
    public void fazerCompras() throws invalidItem, invalidPrice, invalidQtdItens, naoProduto, naoCombustivel {
        boolean rep = true;
        int id;
        double valorPago;
        System.out.println("Boa noite!");
        do {
            System.out.println("Caso vc queira adicionar novos itens, digite: 1");
            System.out.println("Caso vc queira adicionar combustivel, digite: 2");
            System.out.println("Caso vc queira remover algum item, digite: 3");
            System.out.println("Caso vc queira aumentar a quantidade de algum item, digite: 4");
            System.out.println("Caso vc queira decrementar a quantidade de algum item, digite: 5");
            System.out.println("Caso queira finalizar a conta, digite: 0");
            System.out.print("Resp:");
            int valor = in.nextInt();

            switch (valor) {
                case 0:
                    finalizarCompra();
                    repCompras.adicionarArq(carrinhoTemp); //add ao repositorio de compras
                    adcionarCompraConta(carrinhoTemp.getCPF(), carrinhoTemp);
                    rep = false;
                    break;
                case 1:
                    listarItens();
                    System.out.print("Qual a id do item que vc deseja adicionar: ");
                    id = in.nextInt();
                    if (id > 5) {
                        adicionarItem(id);
                    } else {
                        throw new naoProduto();
                    }
                    break;
                case 2:
                    listarCombustiveis();
                    System.out.print("Qual a id do combustivel que voce deseja adicionar: ");
                    id = in.nextInt();
                    System.out.print("Quanto de combustivel vc deseja colocar: ");
                    valorPago = in.nextDouble();
                    adicionarCombustivel(id, valorPago);
                    break;
                case 3:
                    if (!carrinhoTemp.isVazio()) {
                        carrinhoTemp.listarCarrinho();
                        System.out.print("Qual a id do item vc deseja remover: ");
                        id = in.nextInt();
                        removerItem(id);
                        break;
                    }

                case 4:
                    if (!carrinhoTemp.isVazio()) {
                        carrinhoTemp.listarCarrinhoitens();
                        System.out.print("Qual o item vc deseja aumentar a quantidade: ");
                        id = in.nextInt();
                        addQtd(id);
                        break;
                    }

                case 5:
                    if (!carrinhoTemp.isVazio()) {
                        carrinhoTemp.listarCarrinhoitens();
                        System.out.print("Qual o item vc deseja diminuir a quantidade: ");
                        id = in.nextInt();
                        decrQtd(id);
                        break;
                    }

            }
            System.out.println("\n\n\n\n\n\n\n\n");

        } while (rep);

    }

    public void finalizarCompra() {
        carrinhoTemp.setData_compra(Calendar.getInstance());
        carrinhoTemp.listarCarrinho();
        System.out.printf("\n\nValor total: %.2f", carrinhoTemp.getValorTot());
    }

    public void adicionarItem(int id) throws invalidItem, invalidQtdItens {
        try {
            int i;
            System.out.print("Quantos itens vc quer? ");
            i = in.nextInt();
            carrinhoTemp.addItem(repositorioItens.getProduto(id), i);
        } catch (invalidItem e) {
            throw new invalidItem();
        } catch (invalidQtdItens e) {
            throw new invalidQtdItens();
        }
    }

    public void removerItem(int id) throws invalidItem {
        try {
            carrinhoTemp.removerItem(repositorioItens.getProduto(id));
        } catch (invalidItem e) {
            throw new invalidItem();
        }
    }

    public void adicionarCombustivel(int id, double valor) throws invalidItem, invalidPrice {
        try {
            carrinhoTemp.addComb(repositorioItens.getProduto(id), valor);
        } catch (invalidItem e) {
            throw new invalidItem();
        } catch (invalidPrice e) {
            throw new invalidPrice();
        }
    }

    public void addQtd(int id) throws invalidItem {
        try {
            carrinhoTemp.addQtd(repositorioItens.getProduto(id));
        } catch (invalidItem e) {
            throw new invalidItem();
        }
    }

    public void decrQtd(int id) throws invalidItem {
        try {
            carrinhoTemp.decrQtd(repositorioItens.getProduto(id));
        } catch (invalidItem e) {
            throw new invalidItem();
        }
    }

    public void listarCombustiveis() {
        for (produto p : repositorioItens.repProdutos) {
            if (p.getId() <= 5) {
                System.out.println(p);
            }
        }
    }

    public void listarItens() {
        for (produto p : repositorioItens.repProdutos) {
            if (p.getId() > 5) {
                System.out.println(p);
            }
        }
    }

    public void adcionarCompraConta(String cpf, compras car) {
        repContas.getCliente(cpf).addArrayList(car);
        acessoReceita.addReceita(car.getValorTot());
    }
}
