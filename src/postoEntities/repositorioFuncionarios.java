package postoEntities;

import Funcionario.*;
import admin.gerente;
import java.util.ArrayList;
import java.io.*;
import admin.gerente;
public class repositorioFuncionarios implements Runnable {

    private String path = ".\\src\\file\\funcionarios.txt";
    private String pathGerente = ".\\src\\file\\listagerente.txt";
    private ArrayList<funcionario> lista_funcionarios = new ArrayList<funcionario>();
    private String line;
    private String[] split;
    private double valorHora, valorHoraExtra;
    private int ano;

    private static repositorioFuncionarios self;

    private repositorioFuncionarios() {
        openFile();
    }

    public static synchronized repositorioFuncionarios getInstance(){
        if (self == null){
            self = new repositorioFuncionarios();
        }
        return self;
    }

    public funcionario getFuncionario(String cpf) {
        for (funcionario f : lista_funcionarios) {
            if (f.getCpf().equals(cpf)) {
                return f;
            }
        }
        return null;
    }
    public gerente getGerente(String cpf){
        for (funcionario f: lista_funcionarios){
            if (f.getCpf().equals(cpf)){
                return (gerente) f;
            }
        }
        return null;
    }

    public void admissao(funcionario f){
        lista_funcionarios.add(f);
    }

    public void demissao(funcionario f) {
        lista_funcionarios.remove(f);
    }

    public void openFile() {
        String line;
        String[] split = new String[4];

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            line = br.readLine();

            while (line != null) {
                if (line.trim().equals(" ")) {
                    line = br.readLine();
                }

                split = line.split(";");
                /*
                split[0] - cpf
                split[1] - nome
                split[2] - valorHora
                split[3] - valorHoraExtra
                 */
                String cpf = split[0];
                String nome = split[1];

                valorHora = (double) Double.parseDouble(split[2]);
                valorHoraExtra = (double) Double.parseDouble(split[3]);

                lista_funcionarios.add(new funcionario(cpf, nome, new contrato(valorHora, valorHoraExtra)));
                line = br.readLine();
            }

            br = new BufferedReader(new FileReader(pathGerente));
            line = br.readLine();
            this.split = new String[5];
            while (line != null) {
                split = line.split(";");
                String cpf = split[0];
                String nome = split[1];
                valorHora = Double.parseDouble(split[2]);
                valorHoraExtra = Double.parseDouble(split[3]);
                ano = Integer.parseInt(split[4]);
                lista_funcionarios.add(new gerente(cpf, nome, new contrato(valorHora, valorHoraExtra), ano));
                line = br.readLine();
            }

        } catch (IOException e) {

        }
    }

    public void save(){
        Thread save = new Thread(this);
        save.start();
    }
    

    @Override
    public void run(){
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(path));
                BufferedWriter bwg = new BufferedWriter(new FileWriter(pathGerente));
                for (funcionario func : lista_funcionarios) {
                    if (func instanceof gerente) {
                        bwg.write(func.addString());
                        bwg.newLine();
                        bwg.flush();
                    } else {
                        bw.write(func.addString());
                        bw.newLine();
                        bw.flush();
                    }
                }
            } catch (IOException e) {
                System.out.println("Error");
            }
    }

    public void listarFuncionarios() {
        for (funcionario f : lista_funcionarios) {
            if (!(f instanceof gerente)) {
                System.out.println(f);
            }
        }
    }

    public void listarGerentes() {
        for (funcionario f : lista_funcionarios) {
            if (f instanceof gerente) {
                System.out.println(f);
            }
        }
    }

    public boolean existe(String cpf) {
        for (funcionario f : lista_funcionarios) {
            if (f.getCpf().equals(cpf)) {
                return true;
            }
        }
        return false;
    }
    public boolean isGerente(String cpf){
        if (existe(cpf)){
            for (funcionario f: lista_funcionarios){
                if (f.getCpf().equals(cpf) && f instanceof gerente){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        for (funcionario f : lista_funcionarios) {
            System.out.println(f);
        }
        return "";
    }

}

