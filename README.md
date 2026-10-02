# 📦 Controle de Estoque - Java + PostgreSQL

> Evolução de projeto de faculdade (ArrayList/arquivo .txt) para sistema profissional com persistência em Banco de Dados Relacional.

### 🚀 Stack
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![JDBC](https://img.shields.io/badge/JDBC-Connection-blue?style=for-the-badge)

### ✨ O que o sistema faz
- **Persistência Real:** Dados salvos em `estoque_db` (PostgreSQL), não apagam ao fechar
- **Listagem via SQL:** `SELECT * FROM produtos`
- **Busca Inteligente:** `SELECT ... WHERE nome ILIKE '%termo%'`
- **Cálculo Patrimonial:** `SELECT SUM(quantidade * preco) FROM produtos` -> R$ 43.350,00
- **Padrão DAO:** Classe `Conexao.java` separada da regra de negócio

### 🖥️ Prova de funcionamento
- Listagem de 4 produtos direto do banco
- Valor total em estoque calculado no banco

### 🔧 Como rodar
1. Crie o banco `estoque_db` no pgAdmin
2. Rode o script `CREATE TABLE produtos (...)`
3. Configure senha em `Conexao.java`
4. Adicione o driver `postgresql-42.7.3.jar` em Project Structure > Libraries
5. Run `EstoqueApp.java`

Desenvolvido por você - De ArrayList para PostgreSQL!