package br.edu.ifms.ed.model;

/**
 * Classe aluno com os atributos necessários para o cadastro
 */
public class Aluno {

    private String codigo;
    private Double nota;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

}