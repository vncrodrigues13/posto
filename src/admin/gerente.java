package admin;

import Funcionario.contrato;
import Funcionario.enumFuncionario;
import Funcionario.funcionario;

public class gerente extends funcionario{
    
    private int ano_admissao;
    
    public gerente(String cpf, String nome, contrato contratoFuncionario,int ano) {
        super(cpf, nome, contratoFuncionario);
        super.setCargo(enumFuncionario.GERENTE);
        ano_admissao = ano;
    }

    public int getAno_admissao() {
        return ano_admissao;
    }
    
    @Override 
    public String addString(){
        return super.addString()+ano_admissao+";";
    }
    @Override public String toString(){
        return super.toString()+" - Gerente ("+this.ano_admissao+")";
    }
    
}
