package br.edu.ifms.ed.exception;

import br.edu.ifms.ed.constant.Textual;

public class NoGradeException extends Exception {

    public NoGradeException() {
        super(Textual.SEM_NOTAS_CADASTRADAS);
    }

}
