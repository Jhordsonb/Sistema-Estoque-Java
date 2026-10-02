import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    public static Connection conectar() {
        try {
            String url = "jdbc:postgresql://localhost:5432/estoque_db";
            String usuario = "postgres";
            String senha = "1234"; // TROCA AQUI PELA SUA SENHA!
            return DriverManager.getConnection(url, usuario, senha);
        } catch (Exception e) {
            System.out.println("Erro ao conectar no banco: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}