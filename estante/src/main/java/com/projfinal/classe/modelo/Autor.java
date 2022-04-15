package com.projfinal.classe.modelo;

public class Autor {
    private Integer codAutor;
    private String nome;

    public Autor() {
    }

    public Autor(Integer codAutor, String nome) {
        this.codAutor = codAutor;
        this.nome = nome;
    }

    public Integer getCodAutor() {
        return this.codAutor;
    }

    public void setCodAutor(Integer codAutor) {
        this.codAutor = codAutor;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "\n{" +
            " codAutor='" + getCodAutor() + "'\n" +
            ", nome='" + getNome() + "'" +
            "}\n"+"-----------------------";
    }
}
