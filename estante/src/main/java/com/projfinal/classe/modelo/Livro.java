package com.projfinal.classe.modelo;

public class Livro {
    private Integer codLivro;
    private String titulo;
    private Integer codAutor;

    public Livro() {
    }

    public Livro(Integer codLivro, String titulo, Integer codAutor) {
        this.codLivro = codLivro;
        this.titulo = titulo;
        this.codAutor = codAutor;
    }

    public Integer getCodLivro() {
        return this.codLivro;
    }

    public void setCodLivro(Integer codLivro) {
        this.codLivro = codLivro;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getCodAutor() {
        return this.codAutor;
    }

    public void setCodAutor(int i) {
        this.codAutor = i;
    }

    @Override
    public String toString() {
        return "\n{" +
            " codLivro='" + getCodLivro() + "'\n" +
            ", titulo='" + getTitulo() + "'\n" +
            ", codAutor='" + getCodAutor() + "'" +
            "}\n"+"-----------------------";
    }
    

}
