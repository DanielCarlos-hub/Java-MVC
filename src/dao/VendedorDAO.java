
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Vendedor;

public class VendedorDAO {
    public void inserir(Vendedor vendedor){
        System.out.println("Persistindo Objeto: " + vendedor.toString());
        Connection objConexao = FabricaConexao.GeraConexao();
        
        try {
            Statement objSTM = objConexao.createStatement();
            objSTM.execute("INSERT INTO vendedor (nome, cnpj, endereco, contato) VALUES('" + vendedor.getNome() +
                    "','" + vendedor.getCNPJ() +
                    "','" + vendedor.getEndereco() +
                    "','" + vendedor.getContato() +"')");
            objSTM.close();
        } catch (SQLException e) {
            String errorMsg = "Erro ao Persistir: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    public void atualizar(Vendedor vendedor){
        System.out.println("Atualizando Objeto: " + vendedor.toString());
        Connection objConexao = FabricaConexao.GeraConexao();
        try {
            Statement objSTM = objConexao.createStatement();
            objSTM.execute("UPDATE vendedor " +
                    "SET " +
                    "nome = '" + vendedor.getNome() + "', " +
                    "cnpj = '" + vendedor.getCNPJ() + "', " +
                    "endereco = '" + vendedor.getEndereco() + "' " +
                    "contato = '" + vendedor.getContato() + "' " +
                    " WHERE id = '" + vendedor.getCodigo() + "'");
            objSTM.close();
        } catch (SQLException e) {
            String errorMsg = "Erro ao Atualizar: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }
    }    
    public void excluir(int chave){
        System.out.println("Excluindo a Vendedor: " + chave);
        Connection objConexao = FabricaConexao.GeraConexao();
        try {
            Statement objSTM = objConexao.createStatement();
            objSTM.execute("DELETE FROM vendedor WHERE id = '   " + chave + "      '");
            objSTM.close();
        } catch (SQLException e) {
            String errorMsg = "Erro ao Deletar: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Vendedor consultarVendedor(int codigo){      
        Connection objConexao = FabricaConexao.GeraConexao();
        Vendedor objVendedor = new Vendedor();

        try {
            Statement objSTM = objConexao.createStatement();
            objSTM.executeQuery("SELECT id, nome, cnpj, endereco, contato " +
                    "FROM vendedor " +
                    "WHERE id = " + codigo
            );

            ResultSet objResultSet = objSTM.getResultSet();
            while (objResultSet.next()) {
                int pCodigo = objResultSet.getInt("id");
                String pNome = objResultSet.getString("nome");
                String pCNPJ = objResultSet.getString("cnpj");
                String pEndereco = objResultSet.getString("endereco");
                String pContato = objResultSet.getString("contato");
                objVendedor.setCodigo(pCodigo);
                objVendedor.setNome(pNome);
                objVendedor.setCNPJ(pCNPJ);
                objVendedor.setEndereco(pEndereco);
                objVendedor.setContato(pContato);
            }
            objResultSet.close();
            objSTM.close();
        } catch (SQLException e) {
            String errorMsg = "Erro ao recuperar: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }
        return objVendedor;
    }    
    
    public ArrayList<Vendedor> ListarVendedores(){
        
        Connection objConexao = FabricaConexao.GeraConexao();
        ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();
        Vendedor objVendedor = new Vendedor();
        try {
            Statement objSTM = objConexao.createStatement();
            objSTM.executeQuery("SELECT id, nome, cnpj, endereco, contato " +
                    "FROM vendedor "
                    );

            ResultSet objResultSet = objSTM.getResultSet();
            while (objResultSet.next()) {
                int pCodigo = objResultSet.getInt("id");
                String pNome = objResultSet.getString("nome");
                String pCNPJ = objResultSet.getString("cnpj");
                String pEndereco = objResultSet.getString("endereco");
                String pContato = objResultSet.getString("contato");

                objVendedor.setCodigo(pCodigo);
                objVendedor.setNome(pNome);
                objVendedor.setCNPJ(pCNPJ);
                objVendedor.setEndereco(pEndereco);
                objVendedor.setContato(pContato);
                vendedores.add(objVendedor);
            }
            objResultSet.close();
            objSTM.close();
        } catch (SQLException e) {
            String errorMsg = "Erro ao recuperar: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }
        return vendedores;
        
    }

}