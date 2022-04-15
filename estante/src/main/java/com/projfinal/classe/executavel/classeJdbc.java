package com.projfinal.classe.executavel;

import com.projfinal.controler.Control;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class classeJdbc {
    public static void main(String[] args) {
        int resposta;
        Object[] initSystemOptions = { "Confirmar", "Cancelar" };
        resposta = JOptionPane.showOptionDialog(null, "Acessar o Sistema?",
                    "Informação", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, initSystemOptions, initSystemOptions[0]);
            if(resposta==JOptionPane.YES_OPTION){
                Control startApp = new Control();
                boolean remain = false;
                startApp.initBanco();
                while(remain!=true){
                    switch (menu()) {
                        case 0:
                            singUp();
                            
                            break;
                        case 1:
                            list();
                            break;
                        case 2:
                            update();
                            break;
                        case 3:
                            remove();
                            break;
                        case 4:
                            remain=true;
                            break;
                }
           }
            }else{
                JOptionPane.showMessageDialog(null,
                 "Sistema finalizado", "Estante",JOptionPane.INFORMATION_MESSAGE);
            }
    }
    public static int menu(){
        Object[] initFunctonOptions = {"Cadastrar", "Buscar", "Atualizar", "Remover","Encerrar"};
        int resposta = JOptionPane.showOptionDialog(null, "Selecione a opcção desejada:",
            "Estante: O que deseja fazer? ", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
            null,initFunctonOptions,initFunctonOptions[0]);
        return resposta;
        }
//Registrar
    public static void singUp(){
        Object[] initSystemOptions = { "Novo Autor", "Novo Livro", "Cancelar" };
        int resposta = JOptionPane.showOptionDialog(null, "Acessar o Sistema?",
                    "Informação", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, initSystemOptions, initSystemOptions[0]);
        switch (resposta) {
            case 0:
                newAutor();
                break;
            case 1:
                newLivro();
                break;
            default:
                break;
        }
    }
    public static void newLivro(){
        JTextField titulo = new JTextField();
        JTextField codAutor = new JTextField();
        Object[] message = {
            "Titulo:", titulo,
            "Código Autor:", codAutor,
            "Consulte o autor no menu de busca"
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Novo Livro", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try{
                Integer codLivroConverter = Integer.parseInt(codAutor.getText());
                   try{
                       Control cadLivro = new Control();
                        switch (cadLivro.initSalvar(titulo.getText(),codLivroConverter)) {
                            case 0:
                            JOptionPane.showMessageDialog(null,
                            "O Livro já está cadastrado",
                            "Livro já existe",JOptionPane.INFORMATION_MESSAGE);
                            
                                break;
                            case 1:
                            JOptionPane.showMessageDialog(null,
                            "O Livro foi cadastrado com Sucesso!",
                            "Concluído!",JOptionPane.INFORMATION_MESSAGE);
        
                                break;
                            case 2:
                            JOptionPane.showMessageDialog(null,
                            "Autor inexistente",
                            "Autor não encontrado, verifique as informações ou tente cadastrá-lo",
                            JOptionPane.INFORMATION_MESSAGE);
                      
                            break;
                            default:
                                break;
                        }
                   }catch(Exception exception){
                       System.out.println("OCORREU UM ERR0:");
                       exception.printStackTrace();
                   }
            }catch(Exception numException){
                JOptionPane.showMessageDialog(null,
                 "Erro: Verifique o código", "Codigo inválido",JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                 "Operação Cancelada",
                 "Cancelado",JOptionPane.INFORMATION_MESSAGE);
                
        }

    }
    public static void newAutor(){
        
        JTextField nome = new JTextField();
        Object[] message = {
            "Nome do Autor:",nome
        };
        int option = JOptionPane.showConfirmDialog(null,message,
                     "Novo Autor", JOptionPane.OK_CANCEL_OPTION);
         if (option == JOptionPane.OK_OPTION) {
            try{
                Control cadAutor = new Control();
                switch (cadAutor.initNautor(nome.getText())) {
                    case 0:
                    JOptionPane.showMessageDialog(null,
                    "Já existe um autor com este nome.",
                    "Autor Existente!",JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 1:
                    JOptionPane.showMessageDialog(null,
                    "Autor cadastrado com sucesso!",
                    "Temos um novo autor!",JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 2:
                    JOptionPane.showMessageDialog(null,
                    "Ocorreu um erro durante o cadastro do autor.",
                    "Erro!",JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }catch(Exception autorException){
                System.out.println("OCORREU UM ERRO:");
                autorException.printStackTrace();
            }

         }
                    
    }
//Atualizar
    public static void update(){
        Object[] initUpdateOptions = { "Atualizar Autor", "Atualizar Livro", "Cancelar" };
        int resposta = JOptionPane.showOptionDialog(null, "O que gostaria de atualizar?",
                    "Estante: Atualizar", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, initUpdateOptions, initUpdateOptions[0]);
        switch (resposta) {
            case 0:
                updateAutor();
                break;
            case 1:
                updateLivro();
                break;
            case 2:
            JOptionPane.showMessageDialog(null,
            "Operação Cancelada!",
            "Cancelado.",JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }
    public static void updateAutor(){
        JTextField codAutor = new JTextField();
        JTextField nome = new JTextField();
        Object[] message = {
            "Novo nome:",nome,
            "Código do Autor:", codAutor,
            "Consulte o código no menu de busca"
        };
        int option = JOptionPane.showConfirmDialog(null,message,
                     "Novo Autor", JOptionPane.OK_CANCEL_OPTION);          
        try{
        Integer codAutorConverter = Integer.parseInt(codAutor.getText());
            try{
                if (option == JOptionPane.OK_OPTION) {
                    Control attAutor = new Control();
                    switch (attAutor.initAtualizarAutor(codAutorConverter,nome.getText())) {
                        case 0:
                        JOptionPane.showMessageDialog(null,
                        "Autor não encontrado, vefique o código do autor.",
                        "Autor não cadastrado!",JOptionPane.ERROR_MESSAGE);
                            break;
                        case 1:
                        JOptionPane.showMessageDialog(null,
                        "Já existe um outro autor com este nome.",
                        "Autor já registrado!",JOptionPane.ERROR_MESSAGE);
                            break;
                        case 2:
                        JOptionPane.showMessageDialog(null,
                        "Autor atualizado com sucesso!",
                        "Autor atualizado!",JOptionPane.INFORMATION_MESSAGE);
                            break;
                        case 3:
                        JOptionPane.showMessageDialog(null,
                        "Ocorreu um erro durante a atualização do autor.",
                        "Erro!",JOptionPane.ERROR_MESSAGE);
                            break;
                        default:
                            break;
                    }
                }
            }catch(Exception exception){
                exception.printStackTrace();
            }
        }catch(Exception numException){
            JOptionPane.showMessageDialog(null,
                "Verifique o código do autor e tente novamente",
                "Código Autor Inválido",JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void updateLivro(){
        JTextField codAutor = new JTextField();
        JTextField titulo = new JTextField();
        JTextField codLivro = new JTextField();
        Object[] message = {
            "Novo titulo:",titulo,
            "Novo Autor (código):",codAutor,
            "Codigo Livro:",codLivro,
            "Consulte o código do livro no menu de busca"
        };
        int option = JOptionPane.showConfirmDialog(null,message,
                     "Novo Autor", JOptionPane.OK_CANCEL_OPTION);
        try{
            
               Integer codAutorConverter=Integer.parseInt(codAutor.getText());
            try{
                Integer codLivroConverter = Integer.parseInt(codLivro.getText());
                try{
                    if (option == JOptionPane.OK_OPTION) {
                        Control attLivro = new Control();
                        switch (attLivro.initAtualizarLivro(codLivroConverter,codAutorConverter,titulo.getText())) {
                            case 0:
                            JOptionPane.showMessageDialog(null,
                            "Verifique o código do Livro e tente novamente",
                            "Livro não encontrado!",JOptionPane.ERROR_MESSAGE);
                                break;
                            case 1:
                            JOptionPane.showMessageDialog(null,
                            "Um livro com este nome já está registrado",
                            "Livro já existente",JOptionPane.ERROR_MESSAGE);
                                break;
                            case 2:
                            JOptionPane.showMessageDialog(null,
                            "Verifique o código do autor e tente novamente",
                            "Autor não encontrado",JOptionPane.ERROR_MESSAGE);
                                break;
                            case 3:
                            JOptionPane.showMessageDialog(null,
                            "Livro atualizado com sucesso!",
                            "Livro atualizado!",JOptionPane.INFORMATION_MESSAGE);
                                break;
                            case 4:
                            JOptionPane.showMessageDialog(null,
                            "Algo deu errado na atualização do livro",
                            "ERRO!",JOptionPane.ERROR_MESSAGE);
                                break;
                        }
                    }
                }catch(Exception exception){
                    System.out.println("Ocorreu um Erro");
                    exception.printStackTrace();
                }
            }catch(Exception numExceptionAutor){
                JOptionPane.showMessageDialog(null,
                    "Verifique o código do Livro e tente novamente",
                    "Código Livro Inválido",JOptionPane.ERROR_MESSAGE);
            }
        }catch(Exception numExceptionLivro){
            JOptionPane.showMessageDialog(null,
                "Verifique o código do Autor e tente novamente",
                "Código Autor Inválido",JOptionPane.ERROR_MESSAGE);
        }
    }
//Remover
    public static void remove(){

        Object[] initRemoveOptions = { "Remover Autor", "Remover Livro", "Cancelar" };
        int resposta = JOptionPane.showOptionDialog(null, "O que gostaria de atualizar?",
                    "Estante: Atualizar", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, initRemoveOptions, initRemoveOptions[0]);
        switch (resposta) {
            case 0:
                removeAutor();
                break;
            case 1:
                removeLivro();
                break;
            case 2:
            JOptionPane.showMessageDialog(null,
            "Operação Cancelada!",
            "Cancelado.",JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }
    public static void removeAutor(){
        JTextField codAutor = new JTextField();
        Object[] message = {
            "Digite o código do Autor a ser removido:",
            "Código do Autor:", codAutor,
            "Consulte o código no menu de busca"
        };
        int option = JOptionPane.showConfirmDialog(null,message,
                     "Remover Autor", JOptionPane.OK_CANCEL_OPTION);
        try{
            Integer codAutorConverter = Integer.parseInt(codAutor.getText());
        Object[] confirmar = {
            "Você tem certeza?",
            "O que será feito não poderá ser desfeito",
            "Remover o Autor ? ", codAutorConverter}; //Colocar a Busca Aqui
        int confirm = JOptionPane.showConfirmDialog(null,confirmar,
                    "Confirme", JOptionPane.WARNING_MESSAGE);
        try{ 
            if (option == JOptionPane.OK_OPTION) {
                Control control = new Control();
                switch (control.initDeletarAutor(codAutorConverter)) {
                    case 0:
                    JOptionPane.showMessageDialog(null,
                    "Autor não encontrado",
                    "Estante: Autor Inexistente",JOptionPane.ERROR_MESSAGE);
                        break;
                    case 1:
                    JOptionPane.showMessageDialog(null,
                        "Autor removido com sucesso!",
                        "Estante: Autor Removido!",JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 2:
                    JOptionPane.showMessageDialog(null,
                    "Não foi possivel remover o Autor",
                    "Estante: Algo de errado!",JOptionPane.ERROR_MESSAGE);
                        break;
                }
                
            }
        }catch(Exception exception){
            System.out.println("OCORREU UM ERRO");
            exception.printStackTrace();
        }
                  
    }catch(Exception numException){
        JOptionPane.showMessageDialog(null,
                "Verifique o código do autor e tente novamente",
                "Código Autor Inválido",JOptionPane.OK_CANCEL_OPTION);
            
    }
    }
    public static void removeLivro(){
        JTextField codLivro = new JTextField();
        Object[] message = {
            "Digite o código do Livro a ser removido:",
            "Código do Livro:", codLivro,
            "Consulte o código no menu de busca"
        };
        int option = JOptionPane.showConfirmDialog(null,message,
                     "Remover Livro", JOptionPane.OK_CANCEL_OPTION);
        try{
            Integer codLivroConverter = Integer.parseInt(codLivro.getText());
        Object[] confirmar = {
            "Você tem certeza?",
            "O que será feito não poderá ser desfeito",
            "Remover o Livro ? ", codLivroConverter}; //Colocar a Busca Aqui
        int confirm = JOptionPane.showConfirmDialog(null,confirmar,
                    "Estante: Confirme", JOptionPane.WARNING_MESSAGE);
        try{ 
            if (option == JOptionPane.OK_OPTION) {
                Control control = new Control();
                switch (control.initDeletarLivro(codLivroConverter)) {
                    case 0:
                    JOptionPane.showMessageDialog(null,
                    "Livro não encontrado",
                    "Estante: Livro Inexistente",JOptionPane.ERROR_MESSAGE);
                        break;
                    case 1:
                    JOptionPane.showMessageDialog(null,
                        "Livro removido com sucesso!",
                        "Estante: Livro Removido!",JOptionPane.INFORMATION_MESSAGE);
                        break;
                    case 2:
                    JOptionPane.showMessageDialog(null,
                    "Não foi possivel remover o Livro",
                    "Estante: Algo de errado!",JOptionPane.ERROR_MESSAGE);
                        break;
                }
                
            }
        }catch(Exception exception){
            System.out.println("OCORREU UM ERRO");
            exception.printStackTrace();
        }
                  
    }catch(Exception numException){
        JOptionPane.showMessageDialog(null,
                "Verifique o código do autor e tente novamente",
                "Código Autor Inválido",JOptionPane.OK_CANCEL_OPTION);
            
    }
    }
//Listar
    public static void list(){
        Object[] initListOptions = { "Listar Autores","Listar Livros", "Buscar em Autor",
                "Buscar em Livro","Buscar Autor e seus Livros","UltimosRegistros", "Sair" };
        int resposta = JOptionPane.showOptionDialog(null, "O que gostaria de Buscar?",
                    "Estante: Buscar", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, initListOptions, initListOptions[0]);
        switch (resposta) {
            case 0:
                listAutor();
                break;
            case 1:
                listLivro();
                break;
            case 2:
                buscaEmAutor();
                break;
            case 3:
                buscaEmLivro();
                break;
            case 4:
                listarAutorLivro();
                break;
            case 5:
                ultimosRegistros();
                break;
            case 6:
                break;
        }
    }
    public static void listAutor(){
        Object[] message = {
            "Deseja Listar os Autores?"
        };
        int option = JOptionPane.showConfirmDialog(null,message,
                     "Estante:listar Autores", JOptionPane.OK_CANCEL_OPTION);
        try{
            if (option == JOptionPane.OK_OPTION) {
                Control listar = new Control();
                Object[] mostrar ={
                    listar.initListAutor()
                };
                JOptionPane.showMessageDialog(null,
                        mostrar,"Estante: Autores Listados!",
                        JOptionPane.INFORMATION_MESSAGE);
            }
                
        }catch(Exception exception){
            System.out.println("Ocorreu um erro:");
            exception.printStackTrace();
        }
    }
    public static void listLivro(){
        Object[] message = {
            "Deseja Listar os Livros?"
        };
        int option = JOptionPane.showConfirmDialog(null,message,
                     "Estante:Listar Livros", JOptionPane.OK_CANCEL_OPTION);
        try{
            if (option == JOptionPane.OK_OPTION) {
                Control listar = new Control();
                Object[] mostrar ={
                    listar.initListLivro()
                };
                JOptionPane.showMessageDialog(null,
                        mostrar,"Estante: Livros Listados!",
                        JOptionPane.INFORMATION_MESSAGE);
            }
                
        }catch(Exception exception){
            System.out.println("Ocorreu um erro:");
            exception.printStackTrace();
        }
    }
    public static void buscaEmAutor(){
        Object[] initListOptions = { "Busca por ID","Busca por nome" };
        int resposta = JOptionPane.showOptionDialog(null, "Tipo de Busca:",
                    "Estante: Buscar em Autor", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, initListOptions, initListOptions[0]);
                switch (resposta) {
                    case 0:
                        buscaAutor();
                        break;
                    case 1:
                        buscaAutorNome();
                        break;
                }
            }
    public static void buscaAutor(){
        JTextField codAutor = new JTextField();
        Object [] message = {
            "Digite o ID do Autor:", codAutor,
            "Consulte o código no menu de busca"
        };
        int option = JOptionPane.showConfirmDialog(null,message,
                     "Buscar Autor", JOptionPane.OK_CANCEL_OPTION);
        try{
            Integer codAutorConverter = Integer.parseInt(codAutor.getText());
            try{
                if (option == JOptionPane.OK_OPTION) {
                    Control busca = new Control();
                    Object[] mostrar ={
                        "Nome  Autor : ",busca.initBuscarAutor(codAutorConverter).getNome(),
                        "Código Autor : ",busca.initBuscarAutor(codAutorConverter).getCodAutor()
                    };
                    JOptionPane.showMessageDialog(null,
                            mostrar,"Estante: Livros Listados!",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(Exception exception){
                System.out.println("Ocorreu um erro");
                exception.printStackTrace();
            }
        }catch(Exception numException){
            JOptionPane.showMessageDialog(null,
                "Verifique o código do Autor e tente novamente",
                "Código Autor Inválido",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    public static void buscaAutorNome(){
        JTextField nome = new JTextField();
        Object [] message = {
            "Digite o nome do Autor:", nome,
            "O Nome deve ser digitado corretamete"
        };
        int option = JOptionPane.showConfirmDialog(null,message,
                     "Buscar Autor", JOptionPane.OK_CANCEL_OPTION);
            try{
                if (option == JOptionPane.OK_OPTION) {

                    String nomeConverter =nome.getText();

                    System.out.println(nomeConverter);

                    Control busca = new Control();

                    Object[] mostrar ={
                       "Titulo do Livro: ", busca.initBuscarAutorByName(nomeConverter).getNome(),
                       "Código Autor: ", busca.initBuscarAutorByName(nomeConverter).getCodAutor()
                    };
                    JOptionPane.showMessageDialog(null,
                            mostrar,"Estante: Autor Encontrado!",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(Exception exception){
                JOptionPane.showMessageDialog(null,
                            "Autor não Encontrado","Estante: Autor inexistente",
                            JOptionPane.INFORMATION_MESSAGE);
            }
    }
    public static void buscaEmLivro(){
        Object[] initListOptions = { "Busca por ID","Busca por titulo" };
        int resposta = JOptionPane.showOptionDialog(null, "Tipo de Busca:",
                    "Estante: Buscar em Autor", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, initListOptions, initListOptions[0]);
                switch (resposta) {
                    case 0:
                        buscaLivro();
                        break;
                    case 1:
                        buscaLivroNome();
                        break;
                }
    }
    public static void buscaLivro(){
        JTextField codLivro = new JTextField();
        Object [] message = {
            "Digite o ID do Livro:", codLivro,
            "Consulte o código no menu de busca"
        };
        int option = JOptionPane.showConfirmDialog(null,message,
                     "Buscar Livro", JOptionPane.OK_CANCEL_OPTION);
        try{
            Integer codLivroConverter = Integer.parseInt(codLivro.getText());
            try{
                if (option == JOptionPane.OK_OPTION) {
                    Control busca = new Control();
                    Object[] mostrar ={
                       "Titulo do Livro: ", busca.initBuscarLivroByID(codLivroConverter).getTitulo(),
                       "Código Livro: ", busca.initBuscarLivroByID(codLivroConverter).getCodLivro(),
                       "Código Autor: ", busca.initBuscarLivroByID(codLivroConverter).getCodAutor()
                    };
                    JOptionPane.showMessageDialog(null,
                            mostrar,"Estante: Livros Listados!",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(Exception exception){
                System.out.println("Ocorreu um erro");
                exception.printStackTrace();
            }
        }catch(Exception numException){
            JOptionPane.showMessageDialog(null,
                "Verifique o código do Autor e tente novamente",
                "Código Autor Inválido",JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void buscaLivroNome(){
        JTextField titulo = new JTextField();
        Object [] message = {
            "Digite o Titulo do Livro:", titulo,
            "O titulo deve ser digitado corretamete"
        };
        int option = JOptionPane.showConfirmDialog(null,message,
                     "Buscar Livro", JOptionPane.OK_CANCEL_OPTION);
            try{
                if (option == JOptionPane.OK_OPTION) {

                    String tituloConverter =titulo.getText();

                    System.out.println(tituloConverter);

                    Control busca = new Control();

                    Object[] mostrar ={
                       "Titulo do Livro: ", busca.initBuscarLivro(tituloConverter).getTitulo(),
                       "Código Livro: ", busca.initBuscarLivro(tituloConverter).getCodLivro(),
                       "Código Autor: ", busca.initBuscarLivro(tituloConverter).getCodAutor()
                    };
                    JOptionPane.showMessageDialog(null,
                            mostrar,"Estante: Livro Encontrado!",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(Exception exception){
                JOptionPane.showMessageDialog(null,
                            "Livro não Encontrado","Estante: Livros inexistente",
                            JOptionPane.INFORMATION_MESSAGE);
            }
        }
    public static void listarAutorLivro(){
        JTextField codAutor = new JTextField();
        Object [] message = {
            "Digite o ID do Autor:", codAutor,
            "Consulte o código no menu de busca"
        };
        int option = JOptionPane.showConfirmDialog(null,message,
                     "Buscar Autor", JOptionPane.OK_CANCEL_OPTION);
        try{
            Integer codAutorConverter = Integer.parseInt(codAutor.getText());
            try{
                if (option == JOptionPane.OK_OPTION) {
                    Control listar = new Control();
                    Object[] mostrar ={
                        listar.initListarBeanAutorLivro(codAutorConverter)
                    };
                    JOptionPane.showMessageDialog(null,
                            mostrar,"Estante: Autor e Livros Listados!",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(Exception exception){
                System.out.println("Ocorreu um erro");
                exception.printStackTrace();
            }
        }catch(Exception numException){
            JOptionPane.showMessageDialog(null,
                "Verifique o código do Autor e tente novamente",
                "Código Autor Inválido",JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void ultimosRegistros(){
        Object[] conf = {
            "Deseja Listar os ultimos registros?"
        };
        int option = JOptionPane.showConfirmDialog(null,conf,
                     "Estante:Listar Livros", JOptionPane.OK_CANCEL_OPTION);
        try {
            if (option == JOptionPane.OK_OPTION) {
            Control control = new Control();
            Object [] message = {
                "Último código do autor Registrado :",control.initUltimoRegistroAutor(),
                "Último código do livro Registrado :",control.initUltimoRegistroLivro()
            };
            JOptionPane.showMessageDialog(null,
                            message,"Estante: Ultimos Registros!",
                            JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


    






