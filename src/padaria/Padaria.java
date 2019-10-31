/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padaria;

import controle.ProdutoCntrl;
import dao.FabricaConexao;
import java.sql.Connection;


/**
 *
 * @author Daniel
 */
public class Padaria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String dados[] = new String[4];
        
        dados[0] = "0";
        dados[1] = "Pão de Sal";
        dados[2] = "0.50";
        dados[3] = "und";
        
        ProdutoCntrl controle = new ProdutoCntrl();
        controle.salvar(dados);
        
        dados = controle.recuperar(1);
        
        System.out.println("Retorno do Recuperar" + dados);
        
        System.out.println("Fim!!!!!!");
        
    }
    
}
