import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EstoqueService loja = new EstoqueService();

// So cria os 3 se o arquivo estiver vazio (evita duplicar)
        if (loja.listar().isEmpty()) {
            loja.cadastrar(new Produto("Notebook", 3500.0, 10));
            loja.cadastrar(new Produto("Mouse", 80.0, 50));
            loja.cadastrar(new Produto("Teclado", 150.0, 30));
        }

        int opcao;
        do {
            System.out.println("\n===== LOJA - MENU =====");
            System.out.println("1 - Listar estoque");
            System.out.println("2 - Buscar produto");
            System.out.println("3 - Valor total da loja");
            System.out.println("4 - Cadastrar novo");
            System.out.println("5 - Adicionar estoque");
            System.out.println("6 - Remover estoque");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            if (opcao == 1) {
                System.out.println("\n----- ESTOQUE -----");
                for (Produto p : loja.listar()) {
                    System.out.println(p.getNome() + " | Qtd: " + p.getEstoque() + " | Total: R$ " + p.valorTotalEmEstoque());
                }
            }
            else if (opcao == 2) {
                System.out.print("Nome: ");
                String nome = sc.nextLine();
                Produto p = loja.buscarPorNome(nome);
                if (p == null) System.out.println("Nao encontrado.");
                else System.out.println(p.getNome() + " | R$ " + p.getPreco() + " | Qtd: " + p.getEstoque());
            }
            else if (opcao == 3) {
                System.out.println("Total da loja: R$ " + loja.valorTotalDaLoja());
            }
            else if (opcao == 4) {
                System.out.print("Nome: ");
                String nome = sc.nextLine();
                System.out.print("Preco: ");
                double preco = sc.nextDouble();
                System.out.print("Qtd: ");
                int qtd = sc.nextInt();
                sc.nextLine();
                loja.cadastrar(new Produto(nome, preco, qtd));
                System.out.println("Cadastrado!");
            }
            else if (opcao == 5) {
                System.out.print("Produto: ");
                String nome = sc.nextLine();
                Produto p = loja.buscarPorNome(nome);
                if (p == null) System.out.println("Nao encontrado.");
                else {
                    System.out.print("Qtd pra adicionar: ");
                    int qtd = sc.nextInt();
                    sc.nextLine();
                    p.adicionarEstoque(qtd);
                    loja.salvarNoArquivo();
                    System.out.println("Novo estoque: " + p.getEstoque());
                }
            }
            else if (opcao == 6) {
                System.out.print("Produto: ");
                String nome = sc.nextLine();
                Produto p = loja.buscarPorNome(nome);
                if (p == null) System.out.println("Nao encontrado.");
                else {
                    System.out.print("Qtd pra remover: ");
                    int qtd = sc.nextInt();
                    sc.nextLine();
                    if (p.removerEstoque(qtd)) {
                        System.out.println("Removido! Novo: " + p.getEstoque());
                        loja.salvarNoArquivo();
                    } else {
                        System.out.println("Erro! So tem " + p.getEstoque() + " no estoque.");
                    }
                }
            }
        } while (opcao != 0);

        sc.close();
        System.out.println("Fim do programa.");
    }
}