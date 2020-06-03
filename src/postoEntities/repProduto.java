package postoEntities;
import buyEntities.produto;
import java.util.ArrayList;
import java.io.*;
import posto.exceptions.invalidItem;

public class repProduto {
    private ArrayList<produto> lista_produtos;
    
    public repProduto(){
        lista_produtos = new ArrayList<>();
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
                lista_produtos.add(new produto(Integer.parseInt(var[0]), var[1], Double.parseDouble(var[2]), var[3]));
                line = br.readLine();
            }
        }catch (IOException e){
        }catch (NumberFormatException e){
        }
    }
    
    public void listar(){
        for (produto p: lista_produtos){
            System.out.println(p);
        }
    }
    public void listarCombustiveis(){
        for(produto p: lista_produtos){
            if (p.getId() <= 5){
                System.out.println(p);
            }
        }
    }
    public void listarItens(){
        for(produto p: lista_produtos){
            if (p.getId() > 5){
                System.out.println(p);
            }
        }
    }
    
    public ArrayList<produto> getRepProduto(){
        return this.lista_produtos;
    }

    public produto getProduto(int id) throws invalidItem{
        for (produto p: lista_produtos){
            if (p.getId() ==id){
                return p;
            }
        }
        throw new invalidItem();
    }
    
}
