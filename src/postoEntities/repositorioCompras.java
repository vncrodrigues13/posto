package postoEntities;

import java.util.ArrayList;
import buyEntities.*;
import java.io.*;
import posto.exceptions.invalidItem;
import posto.exceptions.invalidPrice;
import posto.exceptions.invalidQtdItens;
import Cliente.cliente;
import java.util.Calendar;
import java.util.Scanner;

public class repositorioCompras implements Runnable{

    private repProduto rp = new repProduto();
    private Scanner in;
    private ArrayList<compras> lista_de_compras;
    private compras carrinhoTemp;
    private String path = ".\\src\\file\\historico_de_compras_usuarios.txt";

    private static repositorioCompras self;

    private repositorioCompras() throws invalidItem, invalidPrice, invalidQtdItens, FileNotFoundException {
        lista_de_compras = new ArrayList<>();
        leituraArquivo();
    }

    public static synchronized repositorioCompras getInstance() throws invalidItem, invalidPrice, invalidQtdItens, FileNotFoundException {
        if (self == null){
            self = new repositorioCompras();
        }
        return self;
    }

    public void leituraArquivo() throws invalidItem, invalidPrice, invalidQtdItens, FileNotFoundException {
        String[] split = new String[3];
        String line, title = null;
        compras carrinhoTemp = null;
        int id = 0,
                qtd = 0;
        
        double preco = 0;

        in = new Scanner(new File(path));

        while (in.hasNext()) {
            line = in.next();
            
            split = line.split(";");
            
            if (split[0].length() == 11) {
                if (carrinhoTemp == null) {
                    carrinhoTemp = new compras(split[0]);
                    title = line;
                    carrinhoTemp.setData(Integer.parseInt(split[1]), Integer.parseInt(split[2]));
                } else {
                    if (title.equals(line)) {
                        lista_de_compras.add(carrinhoTemp);
                        carrinhoTemp = null;
                    }
                }
            } else {
                id = Integer.parseInt(split[0]);
                
                if (id <= 5) {
                    preco = Double.parseDouble(split[1]);
                    
                    carrinhoTemp.addComb(rp.getProduto(id), preco);
                } else {
                    qtd = Integer.parseInt(split[1]);
                    carrinhoTemp.addItem(rp.getProduto(id), qtd);
                }
            }
        }
    }
    
    public void addRepositorio(compras c) {
        lista_de_compras.add(c);
    }
    

    public void save(){
        Thread c = new Thread(this);
        c.start();
    }
    
    public void listarCompras() {
        System.out.println("\n\n\n\n\n\n\n\n\n*************");
        for (compras c : lista_de_compras) {
            System.out.println(c + "\n");
        }
    }

    public ArrayList<compras> getHistoricoCompras() {
        return lista_de_compras;
    }

    public double receita() {
        double gasto = 0;
        ArrayList<compras> lista = getHistoricoCompras();

        for (compras c : lista) {
            gasto += c.getValorTot();
        }
        return gasto;
    }


    @Override
    public void run(){
        for (compras c: lista_de_compras){
            String cpf = c.getCPF();
            Calendar date = c.getData_compra();
            ArrayList<pedidoItem> pi = c.getPedidoItens();
            ArrayList<pedidoComb> pc = c.getPedidosCombustiveis();
            String path = ".\\src\\file\\historico_de_compras_usuarios.txt";
            String title = cpf + ";" + date.get(Calendar.DATE) + ";" + date.get(Calendar.MONTH) + ";";
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write(title);
            bw.newLine();
            bw.flush();
            for (pedidoItem pedido : pi) {
                int id = pedido.getProduto().getId();
                int qtd = pedido.getQuantidade();
                String line = (id + ";" + qtd + ";" + c.getCPF() + ";");
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
            for (pedidoComb pedido : pc) {
                int id = pedido.getProd().getId();
                double preco = pedido.valorTotal();
                String line = (id + ";" + preco + ";" + c.getCPF() + ";");
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
            bw.write(title);
            bw.newLine();
            bw.flush();
            } catch (IOException e) {

            }
        }
    }
}
