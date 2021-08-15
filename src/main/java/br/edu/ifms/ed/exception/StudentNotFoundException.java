package br.edu.ifms.ed.exception;

import br.edu.ifms.ed.constant.Textual;

public class StudentNotFoundException extends Exception {

    public StudentNotFoundException() {
        super(Textual.ALUNO_NAO_ENCONTRADO);
    }

}
