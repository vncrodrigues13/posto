package Pessoa;


public abstract class pessoa {
    protected String cpf;
    
    public pessoa(String cpf){
        this.cpf = cpf;
        
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
}
