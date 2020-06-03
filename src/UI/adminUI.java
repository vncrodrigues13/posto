package UI;

import Funcionario.contrato;
import Funcionario.funcionario;
import java.io.FileNotFoundException;
import posto.exceptions.*;
import postoEntities.repositorioFuncionarios;
import postoEntities.repositorioContas;
import postoEntities.repositorioCompras;
import admin.gasto;
import admin.gerente;
import java.util.Scanner;

public class adminUI implements I_adminUI {

    private repositorioFuncionarios repFuncionarios;
    private repositorioContas repContas;
    private repositorioCompras repCompras;
    private gasto g;
    private final Scanner in;

    public adminUI() throws invalidItem, invalidPrice, invalidQtdItens, FileNotFoundException {
        repFuncionarios = repositorioFuncionarios.getInstance();
        repContas = repositorioContas.getInstance();
        repCompras = repositorioCompras.getInstance();
        g = new gasto();
        in = new Scanner(System.in);
        start();
        
    }

    public void start() throws invalidItem, invalidPrice, invalidQtdItens, FileNotFoundException {
        int resposta, opcao;
        boolean repeticao = false;
        do {
            System.out.println("Balança do posto, digite: 1");
            System.out.println("Lista de funcionarios, digite: 2");
            System.out.println("Lista de clientes, digite: 3");
            System.out.println("Contratar funcionario, digite 4: ");
            System.out.println("Demitir um funcionario, digite 5");
            System.out.println("Pagar um funcionario, digite 6");
            System.out.print("Resp --->");
            resposta = in.nextInt();

            if (resposta > 7 || resposta < 0) {
                System.out.println("Resposta invalida, tente novamente!");
            }

            switch (resposta) {
                case 1:
                    balancaEconomica();
                    break;
                case 2:
                    System.out.println(repFuncionarios);
                    break;
                case 3:
                    System.out.println(repContas);
                    break;
                case 4:
                    admissaoFuncionario();
                    break;
                case 5:
                    demissaoFuncionario();
                    break;
                case 7:
                    pagamentoFuncionario();
                    break;
            }
            do {
                System.out.println("Alguma outra ação, digite 1");
                System.out.println("Finalizar, digite 2");
                opcao = in.nextInt();

                if (opcao != 1 && opcao != 2) {
                    System.out.println("Opção invalida");
                }
            } while (opcao != 1 && opcao != 2);

            repeticao = (opcao == 1);

        } while (repeticao);
    }

    @Override
    public void balancaEconomica() throws invalidItem, invalidPrice, invalidQtdItens, FileNotFoundException {
        System.out.println("O posto teve: ");
        System.out.printf("R$%.2f de receita\n", repCompras.receita());
        System.out.printf("R$%.2f de gasto\n\n\n", g.getGasto());
    }

    @Override
    public void admissaoFuncionario() {

        String primeiro_nome, ultimo_nome, nome, cpf;
        double valor_Hora, valor_extra;
        int resposta;
        boolean opcao;

        System.out.print("Primeiro nome: ");
        primeiro_nome = in.next();
        System.out.print("Ultimo nome: ");
        ultimo_nome = in.next();
        nome = primeiro_nome + " " + ultimo_nome;
        do {
            System.out.print("CPF: ");
            cpf = in.next();

            if (cpf.length() != 11) {
                System.out.println("CPF invalido");
            }
            if (stringValid.temLetras(cpf)) {
                System.out.println("Contem letras no CPF");
            }
        } while (cpf.length() != 11 || stringValid.temLetras(cpf));

        do {
            System.out.print("Hora de trabalho: R$");
            valor_Hora = in.nextDouble();
            if (valor_Hora <= 0) {
                System.out.println("Valor invalido");
            }
        } while (valor_Hora <= 0);

        do {
            System.out.print("Hora extra: R$");
            valor_extra = in.nextDouble();
            if (valor_extra <= 0) {
                System.out.println("Valor invalido");
            }
        } while (valor_extra <= 0);

        do {
            System.out.println("Gerente, digite 1");
            System.out.println("Funcionario, digite 2");
            resposta = in.nextInt();
            switch (resposta) {
                case 1:
                    repFuncionarios.admissao(new gerente(cpf, nome, new contrato(valor_Hora, valor_extra), resposta));
                    opcao = false;
                    break;
                case 2:
                    repFuncionarios.admissao(new funcionario(cpf, nome, new contrato(valor_Hora, valor_extra)));
                    opcao = false;
                    break;
                default:
                    System.out.println("Opção inválida");
                    opcao = true;
                    break;
            }
        } while (opcao);
        System.out.println("Funcionario admitido");

    }

    @Override
    public void demissaoFuncionario() {

        String cpf;
        int resposta;
        boolean opcao;

        do {
            System.out.print("CPF: ");
            cpf = in.next();

            if (cpf.length() != 11) {
                System.out.println("CPF invalido");
            }
            if (stringValid.temLetras(cpf)) {
                System.out.println("Contem letras no CPF");
            }
        } while (cpf.length() != 11 || stringValid.temLetras(cpf));

        if (repFuncionarios.getFuncionario(cpf) != null) {
            repFuncionarios.demissao(repFuncionarios.getFuncionario(cpf));
        } else {
            System.out.println("Funcionario inexistente");
        }
    }

    @Override
    public void pagamentoFuncionario() {

        String cpf;
        int horasTrabalhadas, horasExtras;

        do {
            do {
                System.out.print("CPF: ");
                cpf = in.next();

                if (cpf.length() != 11) {
                    System.out.println("CPF invalido");
                }
                if (stringValid.temLetras(cpf)) {
                    System.out.println("Contem letras no CPF");
                }
            } while (cpf.length() != 11 || stringValid.temLetras(cpf));

            if (!repFuncionarios.existe(cpf)) {
                System.out.println("Funcionário não existe");
            }

        } while (!repFuncionarios.existe(cpf));

        do {
            System.out.print("Horas trabalhadas: ");
            horasTrabalhadas = in.nextInt();
            if (horasTrabalhadas < 0) {
                System.out.println("Quantidade invalida");
            }
        } while (horasTrabalhadas < 0);
        do {
            System.out.print("Horas extra trabalhadas: ");
            horasExtras = in.nextInt();
            if (horasExtras < 0) {
                System.out.println("Quantidade invalida");
            }
        } while (horasExtras < 0);
        g.addGasto(repFuncionarios.getFuncionario(cpf).calcularSalario(horasTrabalhadas, horasExtras));

    }

}
