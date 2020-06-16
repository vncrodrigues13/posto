package postoEntities;

import buyEntities.produto;
import java.util.ArrayList;
import java.io.*;
import posto.exceptions.invalidItem;
import java.util.Iterator;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class repProduto implements createIterator {

    private LinkedHashSet<produto> lista_produtos;

    public repProduto() {
        lista_produtos = new LinkedHashSet<>();
        preencher();
    }

    public void preencher() {
        String path = ".\\src\\file\\produtos.txt";
        String line;
        String[] var;
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            line = br.readLine();
            while (line != null) {
                var = line.split(";");
                lista_produtos.add(new produto(Integer.parseInt(var[0]), var[1], Double.parseDouble(var[2]), var[3]));
                line = br.readLine();
            }
        } catch (IOException | NumberFormatException e) {
        }
    }

    public Iterator listarItens() {
        LinkedHashSet<produto> produtos = new LinkedHashSet<>();
        for (produto p : lista_produtos) {
            if (p.getId() > 5) {
                System.out.println(p);
                produtos.add(p);
            }
        }
        return produtos.iterator();
    }

    public produto getProduto(int id) throws invalidItem {
        for (produto p : lista_produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
        throw new invalidItem();
    }

    public Iterator listarCombustiveis() {
        LinkedHashSet<produto> produtos = new LinkedHashSet<>();
        for (produto p : lista_produtos) {
            if (p.getId() <= 5) {
                produtos.add(p);
            }
        }
        return produtos.iterator();
    }

    public Iterator getRepo() {
        return lista_produtos.iterator();
    }

}
