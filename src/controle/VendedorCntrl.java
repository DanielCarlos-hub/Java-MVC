/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.VendedorDAO;
import java.util.ArrayList;
import modelo.Vendedor;

/**
 *
 * @author g3ra1d0
 */
public class VendedorCntrl {

    public void insert(String[] dados) {

        Vendedor v = new Vendedor();

        v.setNome(dados[0]);
        v.setCNPJ(dados[1]);
        v.setEndereco(dados[2]);
        v.setContato(dados[3]);
       
        VendedorDAO DAO = new VendedorDAO();
        if(v.getCodigo() == 0) {
            DAO.inserir(v);
        }
        else{
            DAO.atualizar(v);
        }

    }

    public String[] recuperar(int codigo) {

        VendedorDAO DAO = new VendedorDAO();

        Vendedor v = DAO.consultarVendedor(codigo);

        String[] vetor = new String[6];
        
        vetor[0] = String.valueOf(v.getCodigo());
        vetor[1] = v.getNome();
        vetor[2] = v.getCNPJ();
        vetor[3] = v.getEndereco();
        vetor[4] = v.getContato();

        return vetor;
    }

    public String[][] recuperarTodos() {
        VendedorDAO DAO = new VendedorDAO();

        ArrayList<Vendedor> vendedores = new ArrayList<>();
        vendedores = DAO.ListarVendedores();
        String[][] matriz = new String[vendedores.size()][5];
        for (int i = 0; i < vendedores.size(); i++) {
            matriz[i][0] = String.valueOf(vendedores.get(i).getCodigo());
            matriz[i][1] = vendedores.get(i).getNome();
            matriz[i][2] = vendedores.get(i).getCNPJ();
            matriz[i][3] = vendedores.get(i).getEndereco();
            matriz[i][4] = vendedores.get(i).getContato();
        }

        return matriz;
    }

    public String excluir(int codigo) {
       VendedorDAO DAO = new VendedorDAO();

       DAO.excluir(codigo);
       
       return "Vendedor excluido com sucesso";
    }

}