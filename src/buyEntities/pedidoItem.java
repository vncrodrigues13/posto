package buyEntities;
import posto.exceptions.invalidQtdItens;
public class pedidoItem {

    private produto prod;
    private int quantidade;

    
    public pedidoItem(produto product, int quantidade)throws invalidQtdItens{
        prod = product;
        if (quantidade > 0){
            this.quantidade = quantidade;
            
        }else{
            throw new invalidQtdItens();
        }
    }

    public produto getProduto() {
        return prod;
    }

    public void addQtd() {
        this.quantidade++;
        System.out.println("Adicionado quantidade");
    }
    public void addQtd(int qtd){
        this.quantidade += qtd;
        System.out.println("Adicionado quantidade");
    }

    public void decrQtd(int qtd) {
        quantidade -= qtd;
        System.out.println("Removida quantidade");
    }

    public double getValor() {
        return prod.getPreco() * quantidade;
    }
    public int getQuantidade(){
        return this.quantidade;
    }

    @Override
    public String toString() {
        return prod + " x " + this.quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof pedidoItem)) {
            return false;
        }
        pedidoItem pi = (pedidoItem) o;

        return pi.prod.equals(this.prod)
                && this.quantidade == pi.quantidade;
    }
}
