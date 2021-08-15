package br.edu.ifms.ed.exception;

import br.edu.ifms.ed.constant.Textual;

public class DuplicateStudentException extends Exception {

    public DuplicateStudentException() {
        super(Textual.CODIGO_JA_EXISTENTE);
    }

}
