    public class Produto {

        private String nome;
        private double  preco;
        private  int estoque;

    public Produto(String nome, double preco, int estoque){
        this.nome = nome;
        this.preco = preco;
        this.estoque = estoque;
    }

    public String getNome(){
        return this.nome;
    }

    public double getPreco(){
        return this.preco;
    }

    public int getEtoque(){
        return this.estoque;
    }

    public void adicionarEstoque(int quantidade){
        this.estoque += quantidade;
    }

    public boolean removerEstoque(int quantidade){
        if (quantidade <= this.estoque) {
            this.estoque -= quantidade;
            return true;
        }
        return false;
    }

    public double valorTotalEmEstoque(){
        return this.preco * this.estoque;
    }

    public int getEstoque(){
        return this.estoque;
    }

}