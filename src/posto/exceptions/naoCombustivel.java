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
public class naoCombustivel extends Exception{
    public naoCombustivel(){
        System.out.println("Esse item não é um combustivel");
    }
}
