
package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Produto;


public class ProdutoDAO {
    public void inserir(Produto prod){
        System.out.println("Persistindo Objeto: " + prod.toString());
        Connection objConexao = FabricaConexao.GeraConexao();
        
        try {
            Statement objSTM = objConexao.createStatement();
            objSTM.execute("INSERT INTO produto( nome, preco, unidade) VALUES('" + prod.getNome() +
                    "','" + prod.getPreco() +
                    "','" + prod.getUnidade() + "')");
            objSTM.close();
        } catch (SQLException e) {
            String errorMsg = "Erro ao Persistir: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void atualizar(Produto prod){
        System.out.println("Atualizando Objeto: " + prod.toString());
        Connection objConexao = FabricaConexao.GeraConexao();
        try {
            Statement objSTM = objConexao.createStatement();
            objSTM.execute("UPDATE produto " +
                    "SET " +
                    "nome = '" + prod.getNome() + "', " +
                    "preco = '" + prod.getPreco() + "', " +
                    "unidade = '" + prod.getUnidade() + "' " +
                    " WHERE id = '" + prod.getCodigo() + "'");
            objSTM.close();
        } catch (SQLException e) {
            String errorMsg = "Erro ao Atualizar: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void excluir(int chave){
        System.out.println("Excluindo o Produto: " + chave);
        Connection objConexao = FabricaConexao.GeraConexao();
        try {
            Statement objSTM = objConexao.createStatement();
            objSTM.execute("DELETE FROM produto WHERE id = '   " + chave + "      '");
            objSTM.close();
        } catch (SQLException e) {
            String errorMsg = "Erro ao Deletar: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public Produto consultarProduto(int codigo){      
        Connection objConexao = FabricaConexao.GeraConexao();
        Produto objProduto = new Produto();

        try {
            Statement objSTM = objConexao.createStatement();
            objSTM.executeQuery("SELECT codigo, nome, preco, unidade " +
                    "FROM produto " +
                    "WHERE id = " + codigo
            );

            ResultSet objResultSet = objSTM.getResultSet();
            while (objResultSet.next()) {
                int pCodigo = objResultSet.getInt("id");
                String pNome = objResultSet.getString("nome");
                double pPreco = objResultSet.getDouble("preco");
                String pUnidade = objResultSet.getString("unidade");
                objProduto = new Produto();
                objProduto.setCodigo(pCodigo);
                objProduto.setNome(pNome);
                objProduto.setPreco(pPreco);
                objProduto.setUnidade(pUnidade);
            }
            objResultSet.close();
            objSTM.close();
        } catch (SQLException e) {
            String errorMsg = "Erro ao recuperar: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }
        return objProduto;
    }
    
    public ArrayList<Produto> consultarProdutos(){
        
        Connection objConexao = FabricaConexao.GeraConexao();
        ArrayList<Produto> produtos = new ArrayList<Produto>();
        try {
            Statement objSTM = objConexao.createStatement();
            objSTM.executeQuery("SELECT codigo, nome, preco, unidade " +
                    "FROM produto "
                    );

            ResultSet objResultSet = objSTM.getResultSet();
            Produto objProduto;
            while (objResultSet.next()) {
                int pCodigo = objResultSet.getInt("codigo");
                String pNome = objResultSet.getString("nome");
                double pPreco = objResultSet.getDouble("preco");
                String pUnidade = objResultSet.getString("unidade");
                objProduto = new Produto();
                objProduto.setCodigo(pCodigo);
                objProduto.setNome(pNome);
                objProduto.setPreco(pPreco);
                objProduto.setUnidade(pUnidade);
                produtos.add(objProduto);
            }
            objResultSet.close();
            objSTM.close();
        } catch (SQLException e) {
            String errorMsg = "Erro ao recuperar: " + e.getMessage();
            JOptionPane.showMessageDialog(null, errorMsg, "Mensagem", JOptionPane.ERROR_MESSAGE);
        }
        return produtos;
        
    }
}
