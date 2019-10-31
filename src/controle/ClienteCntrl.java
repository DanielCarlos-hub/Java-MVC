/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.ClienteDAO;
import java.util.ArrayList;
import modelo.Cliente;

/**
 *
 * @author g3ra1d0
 */
public class ClienteCntrl {

    public void insert(String[] dados) {

        Cliente c = new Cliente();

        c.setNome(dados[0]);
        c.setCPF(dados[1]);
        c.setSexo(dados[2]);
        c.setIdade(Integer.parseInt(dados[3]));
        c.setContato(dados[4]);
       
        ClienteDAO DAO = new ClienteDAO();
        if(c.getCodigo() == 0) {
            DAO.inserir(c);
        }
        else{
            DAO.atualizar(c);
        }

    }

    public String[] recuperar(int codigo) {

        ClienteDAO DAO = new ClienteDAO();

        Cliente c = DAO.consultarCliente(codigo);

        String[] vetor = new String[6];
        
        vetor[0] = String.valueOf(c.getCodigo());
        vetor[1] = c.getNome();
        vetor[2] = c.getCPF();
        vetor[3] = c.getSexo();
        vetor[4] = String.valueOf(c.getIdade());
        vetor[5] = c.getContato();

        return vetor;
    }

    public String[][] recuperarTodos() {
        ClienteDAO DAO = new ClienteDAO();

        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes = DAO.ListarClientes();
        String[][] matriz = new String[clientes.size()][5];
        for (int i = 0; i < clientes.size(); i++) {
            matriz[i][0] = String.valueOf(clientes.get(i).getCodigo());
            matriz[i][1] = clientes.get(i).getNome();
            matriz[i][2] = clientes.get(i).getCPF();
            matriz[i][3] = clientes.get(i).getSexo();
            matriz[i][4] = String.valueOf(clientes.get(i).getIdade());
            matriz[i][5] = clientes.get(i).getContato();
        }

        return matriz;
    }

    public String excluir(int codigo) {
       ClienteDAO DAO = new ClienteDAO();

       DAO.excluir(codigo);
       
       return "Cliente Excluido com sucesso";
    }

}