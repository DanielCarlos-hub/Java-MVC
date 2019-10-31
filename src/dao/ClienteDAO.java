
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Cliente;

public class ClienteDAO {
    public void inserir(Cliente cliente){
        System.out.println("Persistindo Objeto: " + cliente.toString());
        Connection objConexao = FabricaConexao.GeraConexao();
        
        try {
            Statement objSTM = objConexao.createStatement();
            objSTM.execute("INSERT INTO cliente (nome, cpf, sexo, idade, contato) VALUES('" + cliente.getNome() +
                    "','" + cliente.getCPF() +
                    "','" + cliente.getSexo() +
                    "','" + cliente.getIdade() + 
                    "','" + cliente.getContato() +"')");
            objSTM.close();
        } catch (SQLException e) {
            String errorMsg = "Erro ao Persistir: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    public void atualizar(Cliente cliente){
        System.out.println("Atualizando Objeto: " + cliente.toString());
        Connection objConexao = FabricaConexao.GeraConexao();
        try {
            Statement objSTM = objConexao.createStatement();
            objSTM.execute("UPDATE cliente " +
                    "SET " +
                    "nome = '" + cliente.getNome() + "', " +
                    "cpf = '" + cliente.getCPF() + "', " +
                    "sexo = '" + cliente.getSexo() + "' " +
                    "idade = '" + cliente.getIdade() + "' " +
                    "contato = '" + cliente.getContato() + "' " +
                    " WHERE id = '" + cliente.getCodigo() + "'");
            objSTM.close();
        } catch (SQLException e) {
            String errorMsg = "Erro ao Atualizar: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }
    }    
    public void excluir(int chave){
        System.out.println("Excluindo a Cliente: " + chave);
        Connection objConexao = FabricaConexao.GeraConexao();
        try {
            Statement objSTM = objConexao.createStatement();
            objSTM.execute("DELETE FROM cliente WHERE id = '   " + chave + "      '");
            objSTM.close();
        } catch (SQLException e) {
            String errorMsg = "Erro ao Deletar: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Cliente consultarCliente(int codigo){      
        Connection objConexao = FabricaConexao.GeraConexao();
        Cliente objCliente = new Cliente();

        try {
            Statement objSTM = objConexao.createStatement();
            objSTM.executeQuery("SELECT id, nome, cpf, sexo, idade, contato " +
                    "FROM cliente " +
                    "WHERE id = " + codigo
            );

            ResultSet objResultSet = objSTM.getResultSet();
            while (objResultSet.next()) {
                int pCodigo = objResultSet.getInt("id");
                String pNome = objResultSet.getString("nome");
                String pCPF = objResultSet.getString("cpf");
                String pSexo = objResultSet.getString("sexo");
                int pIdade = objResultSet.getInt("idade");
                String pContato = objResultSet.getString("contato");
                objCliente.setCodigo(pCodigo);
                objCliente.setNome(pNome);
                objCliente.setCPF(pCPF);
                objCliente.setSexo(pSexo);
                objCliente.setIdade(pIdade);
                objCliente.setContato(pContato);
            }
            objResultSet.close();
            objSTM.close();
        } catch (SQLException e) {
            String errorMsg = "Erro ao recuperar: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }
        return objCliente;
    }    
    
    public ArrayList<Cliente> ListarClientes(){
        
        Connection objConexao = FabricaConexao.GeraConexao();
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        Cliente objCliente = new Cliente();
        try {
            Statement objSTM = objConexao.createStatement();
            objSTM.executeQuery("SELECT id, nome, cpf, sexo, idade, contato " +
                    "FROM cliente "
                    );

            ResultSet objResultSet = objSTM.getResultSet();
            while (objResultSet.next()) {
                int pCodigo = objResultSet.getInt("id");
                String pNome = objResultSet.getString("nome");
                String pCPF = objResultSet.getString("cpf");
                String pSexo = objResultSet.getString("sexo");
                int pIdade = objResultSet.getInt("idade");
                String pContato = objResultSet.getString("contato");

                objCliente.setCodigo(pCodigo);
                objCliente.setNome(pNome);
                objCliente.setCPF(pCPF);
                objCliente.setSexo(pSexo);
                objCliente.setIdade(pIdade);
                objCliente.setContato(pContato);
                clientes.add(objCliente);
            }
            objResultSet.close();
            objSTM.close();
        } catch (SQLException e) {
            String errorMsg = "Erro ao recuperar: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }
        return clientes;
        
    }

}