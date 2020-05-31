package buyEntities;

public class pedidoComb {
    private produto prod;
    private double litros, valorTotal;
    
    
    public pedidoComb(produto p, double valorPedido){
        this.valorTotal = valorPedido;
        this.prod = p;
        this.litros = valorPedido/p.getPreco();
    }
    
    public double getLitros(){
        return litros;
    }
    public double valorTotal(){
        return valorTotal;
    }

    public produto getProd() {
        return prod;
    }
    
    
    @Override
    public String toString(){
        return prod.getId()+ " - "+prod.getNome()+" - Combustivel - "+valorTotal;
    }
}
