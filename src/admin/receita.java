package admin;

import java.io.*;

import postoEntities.repositorioCompras;

public class receita {

    
    private static String path = ".\\src\\file\\receita.txt";
    private double valorArquivado = 0;
    private repositorioCompras repositorio;

    public receita(){
        lerReceita();
    }

    public void addReceita(double valor) {
        double valorTot;
        if (valor > 0) {
            try {
                valorTot = this.valorArquivado + valor;
                BufferedWriter bw = new BufferedWriter(new FileWriter(path));
                bw.write(Double.toString(valorArquivado));
                bw.flush();

            } catch (IOException e) {

            }
        }
    }

    public void lerReceita() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            this.valorArquivado = Double.parseDouble(br.readLine());
        } catch (IOException e) {
        }
    }
    
    public double getReceita(){
        return valorArquivado;
    }
    

}
