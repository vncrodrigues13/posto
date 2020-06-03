package UI;

import Cliente.cliente;

import buyEntities.compras;

import buyEntities.produto;

import java.util.Calendar;

import postoEntities.repositorioContas;
import posto.exceptions.invalidItem;
import posto.exceptions.invalidPrice;
import posto.exceptions.invalidQtdItens;
import java.io.FileNotFoundException;

import posto.exceptions.*;

import postoEntities.repProduto;
import java.util.Scanner;

public class clienteUI implements I_ClienteUI {

    private Scanner in = new Scanner(System.in);

    private cliente user;

    private Calendar calendar; //usar na hora de setar a dada das compras

    private repositorioContas repContas;

    private compras carrinhoTemp = null;

    private repProduto repProduto;

    private int id;

    public clienteUI(cliente c) throws invalidItem, invalidPrice, invalidQtdItens, FileNotFoundException {
        this.user = c;
        repContas = repositorioContas.getInstance();
        repProduto = new repProduto();
    }

    @Override
    public void fazerCompras() throws invalidItem, naoProduto, invalidPrice, invalidQtdItens {
        boolean rep = true;
        double valorPago;

        carrinhoTemp = new compras(user.getCpf());

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
                /*
                case 0:
                    finalizarCompra();
                    repCompras.addRepositorio(carrinhoTemp); //add ao repositorio de compras
                    adcionarCompraConta(carrinhoTemp.getCPF(), carrinhoTemp);
                    rep = false;
                    break;
                 */
                case 1:
                    repProduto.listarItens();
                    adicionarItem();
                    break;
                case 2:

                    repProduto.listarCombustiveis();
                    System.out.print("Qual a id do combustivel que voce deseja adicionar: ");
                    id = in.nextInt();
                    System.out.print("Quanto de combustivel vc deseja colocar: ");
                    valorPago = in.nextDouble();
                    addCombustivel(id, valorPago);
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
                        try {
                            addQtd(id);
                        } catch (invalidItem ex) {
                            throw new invalidItem();
                        }
                        break;
                    }

                case 5:
                    if (!carrinhoTemp.isVazio()) {
                        carrinhoTemp.listarCarrinhoitens();
                        System.out.print("Qual o item vc deseja diminuir a quantidade: ");
                        id = in.nextInt();
                        try {
                            decrQtd(id);
                        } catch (invalidItem ex) {
                            throw new invalidItem();
                        }
                        break;
                    }

            }
            System.out.println("\n\n\n\n\n\n\n\n");

        } while (rep);
    }

    @Override
    public void listarHistoricoCompras() {
        try {
            repContas.listarHistoricoComprasCPF(user.getCpf());
        } catch (noHistory ex) {
            System.out.println("Carrinho Zerado");
        }
    }

    public void finalizarCompra() {
        carrinhoTemp.setData_compra(Calendar.getInstance());
        carrinhoTemp.listarCarrinho();
        System.out.printf("\n\nValor total: %.2f", carrinhoTemp.getValorTot());
    }

    @Override
    public void adicionarItem(int id) throws invalidItem, invalidQtdItens {
        /*
        System.out.print("Qual a id do item que vc deseja adicionar: ");
                    id = in.nextInt();
                    if (id > 5) {
                        adicionarItem(id);
                    } else {
                        throw new naoProduto();
                    }
        
        try {
            int i;
            System.out.print("Quantos itens vc quer? ");
            i = in.nextInt();
            carrinhoTemp.addItem(repProduto.getProduto(id), i);
        } catch (invalidItem e) {
            throw new invalidItem();
        } catch (invalidQtdItens e) {
            throw new invalidQtdItens();
        }
         */
        do {
            System.out.print("Insira a ID do item em que você deseja adicionar: ");
            id = in.nextInt();
            
            if (repProduto.getProduto(id) == null){
                System.out.println("Item invalido");
            }
        } while (repProduto.getProduto(id) == null);
    }

    public void removerItem(int id) throws invalidItem {
        try {
            carrinhoTemp.removerItem(repProduto.getProduto(id));
        } catch (invalidItem e) {
            throw new invalidItem();
        }
    }

    public void addCombustivel(int id, double valor) {
        try {
            carrinhoTemp.addComb(repProduto.getProduto(id), valor);
        } catch (invalidItem e) {
            System.out.println("Item invalido");
        } catch (invalidPrice e) {
            System.out.println("Valor de Abastecimento invalido");
        }
    }

    public void addQtd(int id) throws invalidItem {
        try {
            carrinhoTemp.addItem(repProduto.getProduto(id));
        } catch (invalidItem e) {
            throw new invalidItem();
        }
    }

    public void decrQtd(int id) throws invalidItem {
        try {
            carrinhoTemp.diminuirItem(repProduto.getProduto(id));
        } catch (invalidItem e) {
            throw new invalidItem();
        }
    }

    public void adcionarCompraConta(String cpf, compras car) {
        repContas.getCliente(cpf).addArrayList(car);
    }

    @Override
    public void finalizarCompras() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addCarrinhoToConta(cliente c, compras car) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
