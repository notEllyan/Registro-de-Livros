package com.projfinal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLDataException;

import java.util.ArrayList;
import java.util.List;

import com.projfinal.classe.modelo.Autor;
import com.projfinal.classe.modelo.Livro;
import com.projfinal.classe.modelo.BeanAutorLivro;

import com.projfinal.conexaoJdbc.SingleConnection;

public class ClasseDao {
    private Connection connection;

    public ClasseDao(){
        connection = SingleConnection.getConnection();
    }
//---> METODOS PARA O LIVRO <---
    public void salvar(Livro livro){ //--->Salva o livro

        try{
            String sql = "INSERT INTO livrojdbc (titulo,codAutor) VALUES (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,livro.getTitulo());
            statement.setLong(2,livro.getCodAutor());
            statement.execute();
			connection.commit();
        }catch(Exception exception){
            try{
                connection.rollback();
            }catch(Exception exception1){
                exception1.printStackTrace();
            }
            exception.printStackTrace();
        }
    }

    public boolean verificaLivro(String tituloLivro){ //--->Valida se existe um Livro
        try{
            //Busca um livro pelo titulo, e retorna se esse livro existe (true) ou não (false)
            Livro livrobuscado = buscaLivro(tituloLivro);
            if(livrobuscado.getTitulo()==(tituloLivro)){
                return true;
            }
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return false; //--> O laço terminou não foi encontrado um livro com o Titulo especificado
    }


    public List<Livro> listarLivros() throws Exception{ //--->Lista todos os livro existentes
        List<Livro> list = new ArrayList<Livro>();
        String sql = "SELECT * FROM livrojdbc";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultado = statement.executeQuery(); //-----> Validação????
        while (resultado.next()){
            Livro livro = new Livro();
            livro.setCodAutor(resultado.getInt("codAutor"));
            livro.setCodLivro(resultado.getInt("codLivro"));
            livro.setTitulo(resultado.getString("titulo"));
            list.add(livro);
        }
        return list;
    }

    public Livro buscaLivro(String nomeLivro) throws Exception{ //---> Busca os livros pelo código
        Livro livro  = new Livro();

        String sql = "SELECT * FROM livrojdbc WHERE titulo ILIKE '"+nomeLivro+"'";

        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet resultado = statement.executeQuery();

        while (resultado.next()){
            livro.setCodLivro(resultado.getInt("codLivro"));
            livro.setCodAutor(resultado.getInt("codAutor"));
            livro.setTitulo(resultado.getString("titulo"));
        }
        return livro;
    }
    public Livro buscaLivroByID(Integer codLivro) throws Exception{ //---> Busca Livro pelo código
       
        Livro livro = new Livro();

        String sql = "SELECT * FROM livrojdbc WHERE codLivro ="+codLivro;

        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet resultado = statement.executeQuery();

        while (resultado.next()){
            livro.setTitulo(resultado.getString("titulo"));
            livro.setCodAutor(resultado.getInt("codAutor"));
            livro.setCodLivro(resultado.getInt("codLivro"));
        }
        return livro;
    }
    
    public void atualizarLivro(Livro livro){
        try{
            String sql = "UPDATE livrojdbc SET titulo = ?, codAutor = ? WHERE codLivro ="+livro.getCodLivro();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, livro.getTitulo());
            statement.setLong(2, livro.getCodAutor());
            statement.execute();
            connection.commit();
        }catch(Exception exception){
            try{
                connection.rollback();
            }catch(Exception exception1){
                exception1.printStackTrace();
            }
            exception.printStackTrace();
        }
    }

    public void deletarLivro(Integer codLivro){
        try{
            String sql = "DELETE FROM livrojdbc WHERE codLivro ="+codLivro;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
            connection.commit();
        }catch(Exception exception){
            try{
                connection.rollback();
            }catch(Exception exception1){
                exception1.printStackTrace();
            }
            exception.printStackTrace();
        }
    }

    public Integer ultimoRegistroLivro() throws SQLException{
        String sql = "SELECT * FROM livrojdbc ORDER BY codLivro DESC LIMIT 1";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Integer ultimoRegistro = resultSet.getInt("codLivro");
        /*resultSet.close();
        preparedStatement.close();
        connection.close();
        */
        return ultimoRegistro;
    }

//---> METODOS PARA O AUTOR <---
    public void novoAutor(Autor autor){
        try{
            String sql = "INSERT INTO autorjdbc (nome) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,autor.getNome());
            statement.execute();
			connection.commit();
        }catch(Exception exception){
            try{
                connection.rollback();
            }catch(Exception exception1){
                exception1.printStackTrace();
            }
            exception.printStackTrace();
        }
    }

    public boolean verificaAutor(Integer codigoAutor){ //--->Valida se existe um autor
            try{
                //Busca um livro pelo titulo, e retorna se esse livro existe (true) ou não (false)
                Autor autorbuscado = buscaAutor(codigoAutor);
                if(autorbuscado.getCodAutor()==codigoAutor){
                //Autor existe
                    return true;
                }
            }catch(Exception exception){
                exception.printStackTrace();
            }
            //Autor não existe
        return false; //--> O laço terminou não foi encontrado um autor com o ID especificado*/
    }

    public List<Autor> listarAutores() throws Exception{
        List<Autor> list = new ArrayList<Autor>();
        String sql = "SELECT * FROM autorjdbc";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultado = statement.executeQuery(); //-----> Validação????
        while (resultado.next()){
            Autor autor = new Autor();
            autor.setCodAutor(resultado.getInt("codAutor"));
            autor.setNome(resultado.getString("nome"));
            list.add(autor);
        }
        return list;
    }

    public Autor buscaAutor(Integer codAutor) throws Exception{ //---> Busca autor pelo código
       
        Autor autor = new Autor();

        String sql = "SELECT * FROM autorjdbc WHERE codAutor ="+codAutor;

        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet resultado = statement.executeQuery();

        while (resultado.next()){
            autor.setNome(resultado.getString("nome"));
            autor.setCodAutor(resultado.getInt("codAutor"));
        }
        return autor;
    }

    public Autor buscaAutorbyName(String nomeAutor) throws Exception{ //---> Busca autor pelo código
       
        Autor autor = new Autor();

        String sql = "SELECT * FROM autorjdbc WHERE nome ILIKE '"+nomeAutor+"'";

        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet resultado = statement.executeQuery();

        while (resultado.next()){
            autor.setNome(resultado.getString("nome"));
            autor.setCodAutor(resultado.getInt("codAutor"));
        }
        return autor;
    }

    public void atualizarAutor(Autor autor){
        try{
            String sql = "UPDATE autorjdbc SET nome = ? WHERE codAutor ="+autor.getCodAutor();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, autor.getNome());
            statement.execute();
            connection.commit();
        }catch(Exception exception){
            try{
                connection.rollback();
            }catch(Exception exception1){
                exception1.printStackTrace();
            }
            exception.printStackTrace();
        }
    }

    public void deletarAutor(Integer codAutor){ //--> !!!Deleta em cascata o autor e seus livros
        try{
        String sqlLivro = "DELETE FROM livrojdbc WHERE codAutor ="+codAutor;
        String sqlAutor = "DELETE FROM autorjdbc WHERE codAutor ="+codAutor;

        PreparedStatement statement = connection.prepareStatement(sqlLivro);
        statement.execute();
        connection.commit();

        statement = connection.prepareStatement(sqlAutor);
        statement.execute();
        connection.commit();
        
        }catch(Exception exception){
            try{
                connection.rollback();
            }catch(Exception exception1){
                exception1.printStackTrace();
            }
            exception.printStackTrace();
        }
    }

    public Integer ultimoRegistroAutor() throws SQLException{
        String sql = "SELECT * FROM autorjdbc ORDER BY codAutor DESC LIMIT 1";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Integer ultimoRegistro = resultSet.getInt("codAutor");

        /*resultSet.close();
        preparedStatement.close();
        connection.close();
        */
        return ultimoRegistro;
    }
//--->METODS PARA BEANAUTORLIVRO <---

    public List<BeanAutorLivro> listarAutorLivro(Integer codAutor) throws Exception{
        List<BeanAutorLivro> list = new ArrayList<BeanAutorLivro>();

        String sql = "SELECT * FROM livrojdbc AS livro";
        sql+=" INNER JOIN autorjdbc AS autor ";
        sql+=" ON livro.codAutor = autor.codAutor ";

        PreparedStatement statement =  connection.prepareStatement(sql);
        ResultSet resultado = statement.executeQuery();

        while(resultado.next()){
            BeanAutorLivro beanAutorLivro = new BeanAutorLivro();
            beanAutorLivro.setNome(resultado.getString("nome"));
            beanAutorLivro.setCodAutor(resultado.getInt("codAutor"));
            beanAutorLivro.setCodLivro(resultado.getInt("codLivro"));
            beanAutorLivro.setTitulo(resultado.getString("titulo"));
            list.add(beanAutorLivro);
        }
        return list;
    }
}
