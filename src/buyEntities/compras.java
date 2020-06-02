package buyEntities;

import java.util.ArrayList;
import java.util.Calendar;
import posto.exceptions.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class compras {
    private DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    private String cpf;
    private ArrayList<pedidoItem> pedidoItens;
    private ArrayList<pedidoComb> pedidosCombustiveis;
    private Calendar data_compra = Calendar.getInstance();

    public compras(String cpf) {
        this.cpf = cpf;
        pedidoItens = new ArrayList<>();
        pedidosCombustiveis = new ArrayList<>();
    }

    public void addItem(produto p, int qtd) throws invalidItem, invalidQtdItens {

        if (existe(p)) { //existe na lista?
            getPedido(p).addQtd(qtd);
            
        } else {
            pedidoItens.add(new pedidoItem(p, qtd));
            
            
        }
    }

    public void addItem(produto p) throws invalidItem{
        if (existe(p)) {
            getPedido(p).addQtd();
        }
    }

    public void diminuirItem(produto p)throws invalidItem {
        if (existe(p)) {
            if (getPedido(p).getQuantidade() > 1) {
                getPedido(p).decrQtd(1);
            } else {
                removerItem(p);
            }
        }
    }

    public void removerItem(produto p)throws invalidItem {
        if (existe(p)) {
            pedidoItens.remove(getPedido(p));
            
        }
    }

    public boolean existe(produto p) {
        if (getPedido(p) != null) {
            return true;
        } else {
            return false;
        }
    }

    public pedidoItem getPedido(produto p) {

        for (pedidoItem pi : pedidoItens) {
            if (pi.getProduto().equals(p)) {
                return pi;
            }
        }
        return null;

    }

    public void addComb(produto p, double valorPedido) throws invalidItem,invalidPrice {
        if (p.getId() <= 5 && p.getId() > 0) {
            if (valorPedido > 0) {
                pedidosCombustiveis.add(new pedidoComb(p, valorPedido));
                
            }else{
                throw new invalidPrice();
            }
        } else {
            throw new invalidItem();
        }
    }

    public void listarCarrinho() {
        for (pedidoItem p : pedidoItens) {
            System.out.println(p);
        }
        for (pedidoComb p : pedidosCombustiveis) {
            System.out.println(p);
        }
    }

    public void listarCarrinhoitens() {
        for (pedidoItem p : pedidoItens) {
            System.out.println(p);
        }
    }
    public void listarCarrinhocomb(){
        for (pedidoComb p: pedidosCombustiveis){
            System.out.println(p);
        }
    }
    
    public double getValorTot(){
        double saldo = 0;
        for (pedidoItem p: pedidoItens){
            saldo +=  p.getValor();
        }
        for (pedidoComb p: pedidosCombustiveis){
            saldo += p.valorTotal();
        }
        return saldo;
    }

    public Calendar getData_compra() {
        return data_compra;
    }
    public void setData(int dia,int mes){
        data_compra.set(Calendar.DATE, dia);
        data_compra.set(Calendar.MONTH, mes-1);
        data_compra.set(Calendar.YEAR, 2020);
    }

    public void setData_compra(Calendar data_compra) {
        this.data_compra = data_compra;
    }
    public String getCPF(){
        return this.cpf;
    }

    public ArrayList<pedidoItem> getPedidoItens() {
        return pedidoItens;
    }

    public ArrayList<pedidoComb> getPedidosCombustiveis() {
        return pedidosCombustiveis;
    }
    
    public boolean isVazio(){
        if (pedidosCombustiveis == null &&
                pedidoItens == null){
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    public String toString(){
        System.out.printf("%s --- Valor total: R$:%.2f\n\n",df.format(this.data_compra.getTime()),getValorTot());
        for (pedidoItem p: pedidoItens){
            System.out.println(p);
        }
        for (pedidoComb p: pedidosCombustiveis){
            System.out.println(p);
        }
        return "";
    }
}
