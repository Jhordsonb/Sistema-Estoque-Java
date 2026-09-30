import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class EstoqueService {
    private List<Produto> produtos = new ArrayList<>();
    private final String ARQUIVO = "estoque.txt";

    public EstoqueService() {
        carregarDoArquivo();
    }

    public void cadastrar(Produto p) {
        produtos.add(p);
        salvarNoArquivo();
    }

    public List<Produto> listar() {
        return produtos;
    }

    public Produto buscarPorNome(String nome) {
        for (Produto p : produtos) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }

    public double valorTotalDaLoja() {
        double total = 0;
        for (Produto p : produtos) {
            total += p.valorTotalEmEstoque();
        }
        return total;
    }

    public void salvarNoArquivo() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ARQUIVO))) {
            for (Produto p : produtos) {
                pw.println(p.getNome() + ";" + p.getPreco() + ";" + p.getEstoque());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        }
    }

    private void carregarDoArquivo() {
        File file = new File(ARQUIVO);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine())!= null) {
                String[] partes = linha.split(";");
                String nome = partes[0];
                double preco = Double.parseDouble(partes[1]);
                int qtd = Integer.parseInt(partes[2]);
                produtos.add(new Produto(nome, preco, qtd));
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar: " + e.getMessage());
        }
    }
}