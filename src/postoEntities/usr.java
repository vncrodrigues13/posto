package postoEntities;
import java.util.Scanner;
import posto.exceptions.*;
import admin.repositorioAdmin;
import java.io.FileNotFoundException;
public class usr {
    public Scanner in = new Scanner(System.in);
    public int resposta;
    private repositorioContas rc;
    private repositorioFuncionarios repFuncionarios;
    private repositorioAdmin verificadorAcesso;


    public usr() throws FileNotFoundException, invalidItem, invalidPrice, invalidQtdItens {
        rc = repositorioContas.getInstance();
    }

    public String login() throws invalidItem, invalidPrice, invalidQtdItens, invalidLogin, invalidAccess, FileNotFoundException{
        
        boolean repeticao = true;
        String cpf;
        
        do{
            
            System.out.println("Bem vindo ao posto!");
            System.out.println("Caso queira criar uma nova conta, digite: 1");
            System.out.println("Caso queira entrar na sua conta, digite: 2");
            System.out.println("Caso queira entrar (admin), digite: 3");
            System.out.println("Caso queira entrar como gerente, digite: 4");
            System.out.println("Caso queira sair, digite: 0");
            try{
                resposta = in.nextInt();
            }catch (Exception e){
                System.out.println("Resposta invalida, reenicie o programa");   
            }
            
            
            switch(resposta){
                case 0:
                    System.exit(0);
                    return null;
                case 1:
                    System.out.println("Qual o seu CPF para podermos criar a conta: ");
                    cpf = in.next();
                    if (!rc.existeConta(cpf)){
                        criarConta(cpf);
                        return cpf;
                    }else{
                        System.out.println("Sua conta já existe, estamos conectando você\n\n");

                        return cpf;
                    }
                    break;
                case 2:
                    cpf = entrarConta();
                    if (rc.existeConta(cpf)){
                        return cpf;
                    }else{
                        sugest();
                    }
                    break;
                case 3:
                    verificadorAcesso = repositorioAdmin.getInstance();
                    System.out.print("Insira o seu cpf: ");
                    cpf = in.next();
                    if (verificadorAcesso.isAdmin(cpf)){
                        return '*'+cpf;
                    }else{
                        System.out.println("Sua conta nao existe\n\n");
                    }
                    break;
                case 4:
                    repFuncionarios = repositorioFuncionarios.getInstance();
                    System.out.print("Insira o seu cpf: ");
                    cpf = in.next();
                    if (repFuncionarios.isGerente(cpf)){
                        return "**"+cpf;
                    }else{
                        System.out.println("Gerente inexistente");
                    }
                    break;
                    
            }
            
        } while(repeticao);
        return "";
    }
    
    public void criarConta(String cpf) throws invalidItem, invalidPrice, invalidQtdItens, invalidLogin, invalidAccess{
        rc.adicionarContaLista(cpf);
    }
    public String entrarConta(){
        System.out.print("Qual o seu cpf: ");
        String cpf = in.next();
        return cpf;
    }
    public String entrarAdmin(){
        System.out.println("Qual o seu cpf: ");
        String cpf = in.next();
        return cpf;
    }
    public void sugest() throws invalidItem, invalidPrice, invalidQtdItens, invalidLogin, invalidAccess{
        char resp;
        System.out.println("Não existe conta com esse cpf, você gostaria de criar uma? (s/n)");
        resp = in.next().charAt(0);
        
        if (resp == 's'){
            System.out.print("Otimo, qual o seu cpf: ");
            String cpf = in.next();
            criarConta(cpf);
        }else{
            System.out.println("Até mais");
            System.exit(0);
        }
    }
    
    
}
