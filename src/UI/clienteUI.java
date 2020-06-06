package UI;

import Cliente.cliente;

import buyEntities.compras;

import java.util.Calendar;

import postoEntities.repositorioContas;
import posto.exceptions.invalidItem;
import posto.exceptions.invalidPrice;
import posto.exceptions.invalidQtdItens;
import java.io.FileNotFoundException;

import posto.exceptions.*;

import postoEntities.repProduto;
import java.util.Scanner;
import postoEntities.posto;

public final class clienteUI implements I_ClienteUI {

    private Scanner in = new Scanner(System.in);

    private final cliente user;

    private repositorioContas repContas;

    private compras carrinhoTemp = null;

    private repProduto repProduto;

    private int id;

    private posto p;

    public clienteUI(cliente c) throws invalidItem, invalidPrice, invalidQtdItens, naoProduto, naoCombustivel, noHistory, invalidLogin, invalidAccess, FileNotFoundException {
        p = new posto();
        this.user = c;
        repContas = repositorioContas.getInstance();
        repProduto = new repProduto();
        start();
    }

    public void start() throws invalidItem, naoProduto, invalidPrice, invalidQtdItens, naoCombustivel, noHistory, invalidLogin, invalidAccess {
        int resp, awnser = 1;
        boolean rept = false;
        do {
            do {
                System.out.println("Compras/Abastecer, digite 1");
                System.out.println("Historico de Compras, digite 2");
                System.out.print("--->>> ");
                resp = in.nextInt();
                if (resp != 1 && resp != 2) {
                    System.out.println("RESPOSTA INVALIDA\n");
                }
            } while (resp != 1 && resp != 2);

            switch (resp) {
                case 1:
                    fazerCompras();
                    break;
                case 2:
                    listarHistoricoCompras();
                    break;
            }
            do {
                System.out.println("Alguma outra ação, digite 1");
                System.out.println("Finalizar programa, digite 2");
                System.out.print("--->");
                awnser = in.nextInt();
                
                if (awnser != 1 && awnser != 2)
                    System.out.println("Resposta invalida");
            } while (awnser != 1 && awnser != 2);
            
            if (awnser == 1) {
                rept = true;
            }
            if (awnser == 2) {
                rept = false;
            }

        } while (rept);
    }

    @Override
    public void listarHistoricoCompras() {
        try {
            repContas.listarHistoricoComprasCPF(user.getCpf());
        } catch (noHistory ex) {
        }
    }

    @Override
    public void fazerCompras() throws invalidItem, invalidPrice, invalidQtdItens, naoProduto, naoCombustivel {
        boolean rep = true;
        double valorPago;

        carrinhoTemp = new compras(user.getCpf());

        do {
            System.out.println("Caso vc queira adicionar novos itens, digite: 1");
            System.out.println("Caso vc queira remover algum item, digite: 2");
            System.out.println("Caso vc queira adicionar combustivel, digite: 3");
            System.out.println("Caso queira finalizar a conta, digite: 0");
            System.out.print("Resp:");
            int valor = in.nextInt();

            switch (valor) {
                case 0:
                    try {
                        finalizarCompras();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    rep = false;
                    break;
                case 1:
                    repProduto.listarItens();
                    adicionarItem();
                    break;
                case 2:
                    if (!carrinhoTemp.isVazio()) {
                        removerItem();
                    } else {
                        System.out.println("Seu carrinho está vazio");
                    }
                    break;
                case 3:
                    repProduto.listarCombustiveis();
                    addCombustivel();
                    break;

            }
            System.out.println("\n\n");

        } while (rep);
    }

    @Override
    public void adicionarItem() throws invalidItem, invalidQtdItens {
        int qtd;
        do {
            System.out.print("Insira a ID do item em que você deseja adicionar: ");
            id = in.nextInt();
            if (repProduto.getProduto(id) == null) {
                throw new invalidItem();
            }
        } while (repProduto.getProduto(id) == null);

        do {
            System.out.print("Quantidade: ");
            qtd = in.nextInt();

            if (qtd <= 0) {
                throw new invalidQtdItens();
            }
        } while (qtd <= 0);
        carrinhoTemp.addItem(repProduto.getProduto(id), qtd);
    }

    @Override
    public void removerItem() throws invalidItem {
        carrinhoTemp.listarCarrinho();
        do {
            System.out.print("ID do item para remover: ");
            id = in.nextInt();
            if (carrinhoTemp.getPedido(repProduto.getProduto(id)) == null) {
                System.out.println("Item invalido");
            }
        } while (carrinhoTemp.getPedido(repProduto.getProduto(id)) == null);

        carrinhoTemp.removerItem(repProduto.getProduto(id));
    }

    @Override
    public void addCombustivel() throws invalidItem, invalidPrice {
        double valorAbastecimento;
        do {
            System.out.print("ID do combustivel que deseja abastecer: ");
            id = in.nextInt();

            if (id > 5 || repProduto.getProduto(id) == null) {
                throw new invalidItem();
            }
        } while (id > 5 || repProduto.getProduto(id) == null);

        do {
            System.out.print("Valor de abastecimento: ");
            valorAbastecimento = in.nextDouble();

            if (valorAbastecimento <= 0) {
                throw new invalidPrice();
            }

        } while (valorAbastecimento <= 0);

        carrinhoTemp.addComb(repProduto.getProduto(id), valorAbastecimento);
    }

    @Override
    public void finalizarCompras() throws invalidItem, invalidPrice, invalidQtdItens, naoProduto, naoCombustivel, FileNotFoundException {
        carrinhoTemp.setData_compra(Calendar.getInstance());
        carrinhoTemp.listarCarrinho();
        System.out.printf("\n\nValor Total: R$%.2f", carrinhoTemp.getValorTot());
        addCarrinhoToConta();
    }

    private void addCarrinhoToConta() throws invalidItem, invalidPrice, invalidQtdItens, naoProduto, naoCombustivel, FileNotFoundException {
        p.addCompras(user.getCpf(), carrinhoTemp);
    }

}
