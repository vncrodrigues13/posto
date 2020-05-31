package buyEntities;

public class produto {
    private int id;
    private String nome;
    private double preco;
    private String marca;
    
    public produto(int id, String nome, double preco, String marca){
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.marca = marca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatricula() {
        return nome;
    }

    public void setMatricula(String matricula) {
        this.nome = matricula;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    public String getNome(){
        return this.nome;
    }
    
    @Override public boolean equals(Object o){
        if (!(o instanceof produto)){
            return false;
        }
        produto p = (produto)o;
        
        return this.id == p.id &&
                this.nome.equals(p.nome) &&
                this.preco == p.preco;
    }
    
    @Override public String toString(){
        System.out.printf("%s - %s - %s - R$%.2f",this.id,this.nome,this.marca,this.preco);
        return "";
    }       
    
    
}
