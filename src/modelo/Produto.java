package modelo;

public class Produto {
    private int codigo;
    private String nome;
    private double preco;
    private String unidade;
    
    public int getCodigo(){
        return codigo;
    }
    public void setCodigo(int codigo){
        this.codigo = codigo;
    }
    
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
        
    }
    
    public double getPreco(){
        return preco;
    }
    public void setPreco(double preco){
        this.preco = preco;
    }
    
    public String getUnidade(){
        return unidade;
    }
    public void setUnidade(String unidade){
        this.unidade = unidade;
    }
    
    public String[] toVetor(){
        String vetor[] = new String[4];
       
        vetor[0] = String.valueOf(getCodigo());
        vetor[1] = getNome();
        vetor[2] = String.valueOf(getPreco());
        vetor[3] = getUnidade();
        
        return vetor;
    }
    
    
}
