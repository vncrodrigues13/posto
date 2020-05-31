package admin;

import java.io.*;
import posto.exceptions.*;
public class gasto {
    
    
    private BufferedReader br;
    private BufferedWriter bw;
    private double valorArquivado;
    private String path = ".\\src\\file\\gastos.txt";

    
    public gasto() throws invalidItem, invalidPrice, invalidQtdItens{
        openFile();
    }

    public void addGasto(double valor) {
        if (valor > 0) {
            valorArquivado += valor;
            try {
                bw = new BufferedWriter(new FileWriter(path));
                bw.write(Double.toString(valorArquivado));
                bw.flush();
            } catch (IOException e) {
            }
        }

    }
    

    public double getGasto() {
        return this.valorArquivado;
    }

    public void openFile(){
        String line;
        try {
            br = new BufferedReader(new FileReader(path));
            line = br.readLine();
            this.valorArquivado = Double.parseDouble(line);
        } catch (IOException e) {
        }
    }
    

}
