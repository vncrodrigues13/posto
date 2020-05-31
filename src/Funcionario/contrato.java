package Funcionario;

public class contrato  {
    private double valorPorHora;
    private double bonificacaoPorHoraExtra;
    
    public contrato(double valor_Hora, double valor_Extra){
        this.valorPorHora = valor_Hora;
        this.bonificacaoPorHoraExtra = valor_Extra;
    }

    public double getValorPorHora() {
        return valorPorHora;
    }

    public void setValorPorHora(double valorPorHora) {
        this.valorPorHora = valorPorHora;
    }

    public double getBonificacaoPorHoraExtra() {
        return bonificacaoPorHoraExtra;
    }

    public void setBonificacaoPorHoraExtra(double bonificacaoPorHoraExtra) {
        this.bonificacaoPorHoraExtra = bonificacaoPorHoraExtra;
    }
    
    
}
