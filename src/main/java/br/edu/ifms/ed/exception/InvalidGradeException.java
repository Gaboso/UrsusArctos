package br.edu.ifms.ed.exception;

import br.edu.ifms.ed.constant.Textual;

public class InvalidGradeException extends Exception {

    public InvalidGradeException() {
        super(Textual.NOTA_INVALIDA);
    }

}
