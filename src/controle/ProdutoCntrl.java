package controle;


import dao.ProdutoDAO;
import java.util.ArrayList;
import modelo.Produto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel Carlos Soares
 */
public class ProdutoCntrl {
    public void salvar( String[] dados){
        Produto P = new Produto();
        P.setCodigo(Integer.parseInt(dados[0]));
        P.setNome(dados[1]);
        P.setPreco(Double.parseDouble(dados[2]));
        P.setUnidade(dados[3]);
        
        ProdutoDAO DAO = new ProdutoDAO();
        if(P.getCodigo() == 0) {
            DAO.inserir(P);
        }
        else{
            DAO.atualizar(P);
        }
    }
    
    public String[] recuperar(int codigo){
        ProdutoDAO DAO = new ProdutoDAO();
       
        Produto P = DAO.consultarProduto(codigo);
        
        return P.toVetor();
    }
    public String[][] recuperarTodos() {
        ProdutoDAO DAO = new ProdutoDAO();

        ArrayList<Produto> produtos = new ArrayList<>();
        produtos = DAO.consultarProdutos();
        String[][] matriz = new String[produtos.size()][5];
        for (int i = 0; i < produtos.size(); i++) {
            matriz[i][0] = String.valueOf(produtos.get(i).getCodigo());
            matriz[i][1] = produtos.get(i).getNome();
            matriz[i][2] = String.valueOf(produtos.get(i).getPreco());
            matriz[i][3] = produtos.get(i).getUnidade();

        }

        return matriz;
    }

    public String excluir(int codigo) {
       ProdutoDAO DAO = new ProdutoDAO();

       DAO.excluir(codigo);
       
       return "Produto excluido com sucesso";
    }

}
