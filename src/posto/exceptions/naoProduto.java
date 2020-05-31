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
public class naoProduto extends Exception{
    public naoProduto(){
        System.out.println("O item que voce escolheu nâo é um produto");
    }
}
