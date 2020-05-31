package admin;
import java.util.ArrayList;
import Cliente.*;
import java.io.*;
import java.util.List;
import java.util.Scanner;


public class repositorioAdmin {

    private ArrayList<String> lista = new ArrayList<>();
    private String path = ".\\src\\file\\listadmin.txt";
    private Scanner in;
    
    private static repositorioAdmin self;

    private repositorioAdmin (){
        abrirArquivo();
        preencherAdmin();
    }

    public static synchronized repositorioAdmin getInstance(){
        if (self == null){
            self = new repositorioAdmin();
        }
        return self;
    }

    public void abrirArquivo(){
        try{
            in = new Scanner(new File(path));
        }catch (Exception e){
        }
    }
    public  void preencherAdmin(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = br.readLine();
            while (line != null){
                lista.add(line);
                line = br.readLine();
            }
        }catch (IOException e){
        }
    }
    
    public boolean isAdmin(String cpf){
        for (String x: lista){
            if (cpf.equals(x)){
                return true;
            }
        }
        return false;
    }
}
