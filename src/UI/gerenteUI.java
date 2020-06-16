package UI;


import postoEntities.repositorioFuncionarios;
import postoEntities.repositorioContas;
import java.util.Scanner;

import java.io.FileNotFoundException;
import posto.exceptions.invalidItem;
import posto.exceptions.invalidPrice;
import posto.exceptions.invalidQtdItens;
import postoEntities.createIterator;

public class gerenteUI {

    private repositorioFuncionarios repFuncionarios;
    private repositorioContas  repContas;
    private Scanner in;

    public gerenteUI() throws invalidItem, invalidPrice, invalidQtdItens, FileNotFoundException {
        repContas = repositorioContas.getInstance();
        repFuncionarios = repositorioFuncionarios.getInstance();
        in = new Scanner(System.in);
        start();
    }

    public void start() {
        int resposta, awnserRepeticao;
        boolean opcao, failAwnser = false, failAwnserRepeticao;
        do {
            do {
                System.out.println("Listar funcionarios, digite 1");
                System.out.println("Listar clientes, digite 2");
                System.out.print("---->>>");
                resposta = in.nextInt();
                failAwnser = resposta != 1 && resposta != 2; 
                switch (resposta) {
                    case 1:
                        printIterator.printFunc(repFuncionarios.getRepo());
                        break;
                    case 2:
                        printIterator.printConta(repContas.getRepo());
                        break;
                    default:
                        System.out.println("Resposta invalida");
                }
                

            } while (failAwnser);

            do {
                System.out.println("Alguma outra ação, digite 1");
                System.out.println("Finalizar, digite 2");
                System.out.print("---->>>");
                awnserRepeticao = in.nextInt();

                failAwnserRepeticao = awnserRepeticao != 1 && awnserRepeticao != 2;

            } while (failAwnserRepeticao);

            opcao = awnserRepeticao == 1;
        } while (opcao);
    }   
}
