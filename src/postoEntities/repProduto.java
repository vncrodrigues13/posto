package postoEntities;
import buyEntities.produto;
import java.util.ArrayList;
import java.io.*;
import posto.exceptions.invalidItem;

public class repProduto {
    ArrayList<produto> repProdutos;
    
    public repProduto(){
        repProdutos = new ArrayList<>();
        preencher();
    }
    public void preencher(){
        String path = ".\\src\\file\\produtos.txt";
        String line;
        String [] var = new String[4];
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            line = br.readLine();
            while (line != null){
                var = line.split(";");
                repProdutos.add(new produto(Integer.parseInt(var[0]), var[1], Double.parseDouble(var[2]), var[3]));
                line = br.readLine();
            }
        }catch (IOException e){
        }catch (NumberFormatException e){
        }
    }
    
    public void listar(){
        for (produto p: repProdutos){
            System.out.println(p);
        }
    }
    
    public produto getProduto(int id) throws invalidItem{
        for (produto p: repProdutos){
            if (p.getId() ==id){
                return p;
            }
        }
        throw new invalidItem();
    }
}
