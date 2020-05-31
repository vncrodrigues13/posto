package Funcionario;
import Pessoa.pessoa;
public class funcionario extends pessoa{
    private String nome;
    private contrato contratoFuncionario;
    private int qtdHoras;
    private enumFuncionario cargo;
    public funcionario(String cpf, String nome, contrato contratoFuncionario) {
        super(cpf);
        this.nome = nome;
        this.contratoFuncionario = contratoFuncionario;
        this.cargo = enumFuncionario.FUNCIONARIO;
    }
    public double calcularSalario(int quantidade_horas, int horas_extras){
        return (contratoFuncionario.getValorPorHora() * quantidade_horas) + 
                (horas_extras * contratoFuncionario.getBonificacaoPorHoraExtra());
       
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public contrato getContratoFuncionario() {
        return contratoFuncionario;
    }

    public void setContratoFuncionario(contrato contratoFuncionario) {
        this.contratoFuncionario = contratoFuncionario;
    }

    public int getQtdHoras() {
        return qtdHoras;
    }

    public void setQtdHoras(int qtdHoras) {
        this.qtdHoras = qtdHoras;
    }

    public String getCpf() {
        return cpf;
    }
    
    public String addString(){
        return this.cpf +";"+this.nome+";"+contratoFuncionario.getValorPorHora()+";"+contratoFuncionario.getBonificacaoPorHoraExtra()+";";
    }

    public void setCargo(enumFuncionario cargo) {
        this.cargo = cargo;
    }
    
    public enumFuncionario getCargo(){
        return this.cargo;
    }
    
    @Override public boolean equals(Object o){
        if (!(o instanceof funcionario)){
            return false;
        }
        funcionario f = (funcionario)o;
        
        return f.nome.equals(this.nome) && 
                f.cpf.equals(this.cpf);
    }
    
    @Override public String toString(){
        return cpf+" - "+nome;
    }
    
}
