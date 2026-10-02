import java.sql.*;
import java.util.Scanner;

public class EstoqueApp {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n--- CONTROLE DE ESTOQUE COM BANCO ---");
            System.out.println("1 - Listar todos os produtos");
            System.out.println("2 - Buscar por nome");
            System.out.println("3 - Valor total do estoque");
            System.out.println("4 - Adicionar produto");
            System.out.println("5 - Remover produto");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> listar();
                case 2 -> buscar();
                case 3 -> valorTotal();
                case 4 -> adicionar();
                case 5 -> remover();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    static void listar() {
        String sql = "SELECT * FROM produtos ORDER BY id";
        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\nID | NOME | QTD | PREÇO");
            System.out.println("---------------------------");
            while (rs.next()) {
                System.out.printf("%d | %s | %d | R$ %.2f\n",
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("quantidade"),
                        rs.getDouble("preco"));
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }
    }

    static void buscar() {
        System.out.print("Digite o nome para buscar: ");
        String nome = sc.nextLine();
        String sql = "SELECT * FROM produtos WHERE nome ILIKE ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + nome + "%");
            ResultSet rs = ps.executeQuery();
            boolean achou = false;
            while (rs.next()) {
                achou = true;
                System.out.printf("-> %s | Qtd: %d | R$ %.2f\n",
                        rs.getString("nome"), rs.getInt("quantidade"), rs.getDouble("preco"));
            }
            if (!achou) System.out.println("Nenhum produto encontrado.");
        } catch (Exception e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
        }
    }

    static void valorTotal() {
        String sql = "SELECT SUM(quantidade * preco) as total FROM produtos";
        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                System.out.printf("\n>>> VALOR TOTAL EM ESTOQUE: R$ %.2f <<<\n", rs.getDouble("total"));
            }
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    static void adicionar() {
        System.out.print("Nome do produto: "); String nome = sc.nextLine();
        System.out.print("Quantidade: "); int qtd = sc.nextInt();
        System.out.print("Preço: "); double preco = sc.nextDouble();
        sc.nextLine();

        String sql = "INSERT INTO produtos (nome, quantidade, preco) VALUES (?,?,?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nome);
            ps.setInt(2, qtd);
            ps.setDouble(3, preco);
            ps.executeUpdate();
            System.out.println("Produto adicionado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar (nome já existe?): " + e.getMessage());
        }
    }

    static void remover() {
        System.out.print("Nome exato para remover: "); String nome = sc.nextLine();
        String sql = "DELETE FROM produtos WHERE nome = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nome);
            int linhas = ps.executeUpdate();
            System.out.println(linhas > 0 ? "Removido com sucesso!" : "Produto não encontrado.");
        } catch (Exception e) {
            System.out.println("Erro ao remover: " + e.getMessage());
        }
    }
}
