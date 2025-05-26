# Sistema de Apoio a Testes Exploratório em Video Games

**Desenvolvimento de Software para a Web 1**  
Diogo Conforti Vaz Bellini
João Paulo Morais Rangel
Luiz Fellipe de Lima Barbosa
Pedro Vinicius Ferreira Santos

---

## Tecnologias Utilizadas

- Backend: Java Servlet, JSP, JSTL, JDBC  
- Frontend: JavaScript, CSS  
- Banco de Dados: **MySql**  
- Build e Deployment: Maven  
- Controle de versão: Git (preferencialmente GitHub)

---

## Como Fazer o Build, Deployment e Testar

### Passos para build e execução

1. Clone o repositório:

   ```bash
   git clone https://github.com/seu-usuario/seu-projeto.git
   cd seu-projeto

2. Crie o banco de dados e as tabelas executando o script SQL

   ```bash
   mysql -u root -p
   mysql> source <caminho_para_projeto>/projeto1_DSW/create.sql;

3. Suba o Apache Tomacat
4. Dentro da pasta do projeto

   ```bash
   ./mvnw tomcat7:deploy

5. Acesse
   [http://localhost:8080/projeto1_DSW](http://localhost:8080/projeto1_DSW)
