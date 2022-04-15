package com.projfinal.controler;

import java.sql.SQLException;
import java.util.List;

import com.projfinal.classe.modelo.Autor;
import com.projfinal.classe.modelo.Livro;
import com.projfinal.conexaoJdbc.SingleConnection;
import com.projfinal.classe.modelo.BeanAutorLivro;
import com.projfinal.dao.ClasseDao;

public class Control {
    public static void initBanco(){
        SingleConnection.getConnection();
        System.out.println("\n----->>>  BANCO CONECTADO <<<-----");
    }

//-->Referentes a Livro
    public int initSalvar(String titulo,Integer codAutor){
        /*Objetivo: Verificar se existe um autor, se não registrar um autor
        Problema: Eu preciso de um retorno do banco para essa validação
        */
        try{
            Livro livro = new Livro();
            ClasseDao classeDao = new ClasseDao();
            livro.setCodAutor(codAutor);
            livro.setTitulo(titulo);
            
            if(validarLivro(livro.getTitulo())==true){ 
                return 0; //--> O Livro já existe
            }else{
                if(validarAutor(livro.getCodAutor())==true){
                    classeDao.salvar(livro);
                    return 1; //-->O Autor existe e o livro é cadastrado com sucesso
                }else{ 
                   return 2; //-->O autor não existe
                }
            }
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return 3; //-->Algo deu errado
    }

    public boolean validarLivro(String tituloLivro){
        try{    
            ClasseDao classeDao = new ClasseDao();
            if(classeDao.verificaLivro(tituloLivro)==true){
            //---Livro existente---"
                return true;
            }
        }catch(Exception exception){
            exception.printStackTrace();
        }
        //Livro não cadastrado
        return false;
    }


    public  List<Livro> initListLivro(){
            try{
                ClasseDao classeDao = new ClasseDao();
                List <Livro> list = classeDao.listarLivros();

                return list;
            }catch(Exception exception){
                exception.printStackTrace();
            }
            return null;
    }

    public Livro initBuscarLivro(String nomeLivro){  
        try{
            ClasseDao classeDao = new ClasseDao();
            if(classeDao.buscaLivro(nomeLivro).getCodLivro()==null){
                return null;
            }else{
            Livro livro = classeDao.buscaLivro(nomeLivro);
            return livro;
        }
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return null;

    }

    public Livro initBuscarLivroByID(Integer codLivro){
        try{
            ClasseDao classeDao = new ClasseDao();
            Livro livro = classeDao.buscaLivroByID(codLivro);
            return livro;
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return null;
    }

    public int initAtualizarLivro(Integer codLivro, Integer codAutor, String titulo){
        try{
            ClasseDao classeDao = new ClasseDao();
            Livro livro = new Livro();

            if(classeDao.buscaLivroByID(codLivro).getCodLivro()==null){
                return 0;//-->Livro não existe
            }
            else if(classeDao.buscaLivro(titulo).getTitulo()==(titulo)){
                return 1; //-->Já existe um livro com este titulo
            }else if(classeDao.buscaAutor(codAutor).getCodAutor()==null){
                return 2; //-->Autor não registrado
            }else{
                livro.setCodAutor(codAutor);
                livro.setTitulo(titulo);
                livro.setCodLivro(codLivro);
                classeDao.atualizarLivro(livro);
                return 3;
            }
            
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return 4; //--> Algo deu errado
    }

    public int initDeletarLivro(Integer codLivro){
        try{
            ClasseDao classeDao = new ClasseDao();
            if(classeDao.buscaLivroByID(codLivro).getCodLivro()==null){
                return 0; //--> Livro não encontrado
            }else{
                classeDao.deletarLivro(codLivro);
                return 1; //-->Livro removido
            }
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return 2;
    }

    public Integer initUltimoRegistroLivro() throws SQLException{
        ClasseDao classeDao = new ClasseDao();
        Integer codLivro = classeDao.ultimoRegistroLivro();
        return codLivro;
    }

//--> Referentes a Autor

    public int initNautor(String Nome){
        try{
            Autor autor = new Autor();
            ClasseDao classeDao = new ClasseDao();
            autor.setNome(Nome);
            if(classeDao.buscaAutorbyName(autor.getNome()).getNome()!=null){
                return 0; //-->Já existe um autor com ese nome
            }else{
            classeDao.novoAutor(autor);
            return 1; //-->Autor cadastrado com sucesso
            }
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return 2;
    }

    public boolean validarAutor(Integer codAutor){
        try{    
            ClasseDao classeDao = new ClasseDao();
            if(classeDao.verificaAutor(codAutor)==true){
            //Autor válido
                return true;
            }
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return false;
    }

    public  List<Autor> initListAutor(){
        try{
            ClasseDao classeDao = new ClasseDao();
            List <Autor> list = classeDao.listarAutores();

            return list;
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return null;
}

    public Autor initBuscarAutor(Integer codAutor){
        try{
            ClasseDao classeDao = new ClasseDao();
            Autor autor = classeDao.buscaAutor(codAutor);
            return autor;
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return null;
    }

    public Autor initBuscarAutorByName(String nomeAutor){
        try{
            ClasseDao classeDao = new ClasseDao();
            Autor autor = classeDao.buscaAutorbyName(nomeAutor);
            return autor;
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return null;
    }

    public int initAtualizarAutor(Integer codAutor, String novoNome){
        try{
            ClasseDao classeDao = new ClasseDao();
            Autor autor = classeDao.buscaAutor(codAutor);
            if(autor.getCodAutor()==null){
                //-->Autor não existe
                return 0;
            }else if(classeDao.buscaAutorbyName(novoNome).getNome()!=null){
                return 1; //--->Já existe um autor com este nome cadastrado
            }else{
            autor.setNome(novoNome);
            classeDao.atualizarAutor(autor);
            return 2; //-->Autor atualizado com sucesso
            }
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return 3; //--->Algo deu errado
    }

    public int initDeletarAutor(Integer codAutor){
        try{
            ClasseDao classeDao = new ClasseDao();
            if(classeDao.buscaAutor(codAutor).getCodAutor()==null){
                return 0; //--> Autor não encontrado
            }else{
                classeDao.deletarAutor(codAutor);
                return 1; //-->Autor removido
            }
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return 2;
    }
//--> Inner Join Autor Livro
    public List<BeanAutorLivro> initListarBeanAutorLivro(Integer codAutor){
        try{
            ClasseDao classeDao = new ClasseDao();
            List<BeanAutorLivro> list = classeDao.listarAutorLivro(codAutor);

            return list;
        }catch(Exception exception){
            exception.printStackTrace();
        }
        return null;
    }

    public Integer initUltimoRegistroAutor() throws SQLException{
        ClasseDao classeDao = new ClasseDao();
        Integer ultimoAutor = classeDao.ultimoRegistroAutor();
        return ultimoAutor;
    }
}
