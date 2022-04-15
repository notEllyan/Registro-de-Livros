package com.projfinal.classe.modelo;

public class BeanAutorLivro {
    private String titulo;
    private Integer codLivro;
    private String nome;
    private Integer codAutor;

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getCodLivro() {
        return this.codLivro;
    }

    public void setCodLivro(Integer codLivro) {
        this.codLivro = codLivro;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getCodAutor() {
        return this.codAutor;
    }

    public void setCodAutor(Integer codAutor) {
        this.codAutor = codAutor;
    }

    @Override
    public String toString() {
        return "\n{" +
            " titulo='" + getTitulo() + "'" +
            ", codLivro='" + getCodLivro() + "'" +
            ", nome='" + getNome() + "'" +
            ", codAutor='" + getCodAutor() + "'" +
            "}\n"+"-----------------------\n";
    }

}
