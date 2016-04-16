package br.edu.ifms.ed.model;

/**
 * Classe aluno com os atributos necessários para o cadastro
 *
 * @author Gabriel Carvalho
 */
public class Aluno {

    private String codigoAluno;
    private Double nota;

    public String getCodigoAluno() {
        return codigoAluno;
    }

    public void setCodigoAluno(String codigoAluno) {
        this.codigoAluno = codigoAluno;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}
