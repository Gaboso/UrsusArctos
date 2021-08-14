package br.edu.ifms.ed.exception;

import br.edu.ifms.ed.constant.Textual;

public class NoStudentException extends Exception {

    public NoStudentException() {
        super(Textual.SEM_ALUNOS_CADASTRADOS);
    }

}
