/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package posto.exceptions;

/**
 *
 * @author vinic
 */
public class noHistory extends Exception{
    public noHistory(){
        System.out.println("Conta sem nenhum historico");
    }
}
