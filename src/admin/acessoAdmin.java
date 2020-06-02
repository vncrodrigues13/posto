package admin;

import posto.exceptions.invalidItem;
import posto.exceptions.invalidPrice;
import posto.exceptions.invalidQtdItens;
import postoEntities.repositorioContas;
import Cliente.cliente;
import buyEntities.compras;
import Funcionario.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
import postoEntities.repositorioFuncionarios;
import postoEntities.repositorioContas;
import postoEntities.repositorioCompras;
public class acessoAdmin {

    private Scanner in = new Scanner(System.in);
    private gasto g;


    private repositorioContas repContas;
    private repositorioFuncionarios repFuncionarios;
    private repositorioCompras repCompras;

    
    public acessoAdmin() throws invalidItem, invalidPrice, invalidQtdItens, FileNotFoundException {
        
        repCompras = repositorioCompras.getInstance();
        repFuncionarios = repositorioFuncionarios.getInstance();
        repContas = repositorioContas.getInstance();
        g = new gasto();
        int resposta;
        do {
            System.out.println("Olá Admnistrador,");
            System.out.println("Caso queira ver a balança do posto, digite: 1");
            System.out.println("Caso queira ver a lista de funcionarios, digite: 2");
            System.out.println("Caso queira ver a lista de clientes, digite: 3");
            System.out.println("Caso queira contratar um novo funcionario, digite 4: ");
            System.out.println("Caso queira contratar um novo gerente, digite: 5");
            System.out.println("Caso queira demitir um funcionario, digite: 6");
            System.out.println("Caso queira pagar um funcionario, digite: 7");
            System.out.println("Caso queira fechar, digite: 0");
            System.out.print("Resp --->");
            resposta = in.nextInt();

            if (resposta > 7 || resposta < 0) {
                System.out.println("Resposta invalida, tente novamente!");
            }

            switch (resposta) {
                case 1:
                    balancaPosto();
                    break;
                case 2:
                        System.out.println(repFuncionarios);
                    break;
                case 3:
                    System.out.println(repContas);
                    break;
                case 4:
                    admissao();
                    break;
                case 5:
                    admissaoGerente();
                    break;
                case 6:
                    demissao();
                    break;
                case 7:
                    pagamentoFuncionario();
                    break;
                    

            }

        } while (resposta != 0);

    }

    public void balancaPosto() throws invalidItem, invalidPrice, invalidQtdItens, FileNotFoundException {
        
        System.out.println("O posto teve: ");
        System.out.printf("R$%.2f de receita\n", repCompras.receita());
        System.out.printf("R$%.2f de gasto\n\n\n", g.getGasto());
    }

    public void admissao() {
        String primeiro_nome,
                ultimo_nome,
                nome, cpf;
        double valor_Hora, valor_extra;
        System.out.print("Qual o primeiro nome do funcionario: ");
        primeiro_nome = in.next();
        System.out.print("Qual o ultimo nome do funcionario: ");
        ultimo_nome = in.next();
        nome = primeiro_nome + " " + ultimo_nome;
        System.out.print("Qual o cpf do funcionario: ");
        cpf = in.next();
        System.out.print("Qual o valor da hora de trabalho R$:");
        valor_Hora = in.nextDouble();
        System.out.print("Qual o valor da hora extra R$");
        valor_extra = in.nextDouble();
        repFuncionarios.admissao(new funcionario(cpf, nome, new contrato(valor_Hora, valor_extra)));
        System.out.println("Admitido");
        
    }

    public void demissao() {
        System.out.println(repFuncionarios);
        String cpf;
        System.out.print("Qual o cpf do funcionario: ");
        cpf = in.next();
        if (repFuncionarios.existe(cpf)) {
            if (cpf.equals("11253378428")) {
                System.out.println("Impossivel exlcuir o dono");
            } else {
                repFuncionarios.demissao(repFuncionarios.getFuncionario(cpf));
                System.out.println("Demitido");
            }

        } else {
            System.out.println("Funcionario inexistente");
        }
    }
    
    public void pagamentoFuncionario(){
        System.out.println(repFuncionarios);
        System.out.print("Qual o cpf do funcionario que voce deseja fazer o pagamento? ");
        String cpf = in.next();
        if (repFuncionarios.existe(cpf)){
            System.out.print("Quantas horas ele trabalhou? ");
            int horasTrabalhadas = in.nextInt();
            System.out.print("Quantas horas extras houveram? ");
            int horasExtras = in.nextInt();
            g.addGasto(repFuncionarios.getFuncionario(cpf).calcularSalario(horasTrabalhadas, horasExtras)); 
        }
    }
    
    public void admissaoGerente(){
        gerente g;
        System.out.print("Qual o CPF do Gerente: ");
        String cpf = in.next();
        System.out.print("Qual o primeiro nome do gerente: ");
        String primeiroNome = in.next();
        System.out.print("Qual o ultimo nome do gerente: ");
        String ultimoNome = in.next();
        System.out.print("Qual o valor da hora de trabalho: R$");
        double valorHora = in.nextDouble();
        System.out.print("Qual o valor da hora extra de trabalho: R$");
        double valorExtra = in.nextDouble();
        System.out.print("Insira o ano de admissão: ");
        int anoAdmissao = in.nextInt();
        String nome = primeiroNome+" "+ultimoNome;
        g = new gerente(cpf, nome, new contrato(valorHora, valorExtra), anoAdmissao);
        repFuncionarios.admissao(g);
    }

    public void listarClientes() {
        System.out.println(repContas);
    }
}
