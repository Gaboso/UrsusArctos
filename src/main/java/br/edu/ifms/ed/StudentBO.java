package br.edu.ifms.ed;

import br.edu.ifms.ed.constant.Textual;
import br.edu.ifms.ed.model.Student;
import br.edu.ifms.ed.ui.JItemUI;
import br.edu.ifms.ed.ui.UAIcon;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.util.Objects;
import java.util.Stack;

/**
 * Classe com as operações realizadas com o aluno
 */
public class StudentBO {

    private static final Logger LOGGER = Logger.getLogger(StudentBO.class);

    private static Stack<Student> students = new Stack<>();

    private JFrame frame;

    public StudentBO(JFrame frame) {
        this.frame = frame;
    }

    public void addGrade() {
        int size;
        int counter;
        String id;

        if (students.empty()) {
            JItemUI.showErrorMesssge(frame, Textual.SEM_ALUNOS_CADASTRADOS, Textual.CADASTRO_DE_NOTAS);
        } else {
            counter = 0;
            id = getStudentID();
            if (id != null) {
                size = students.size() - 1;
                while (size != -1) {
                    if (students.get(size).getId().equals(id)) {
                        addGrade(students.get(size));
                        counter++;
                    }
                    size--;
                }
                if (counter == 0) {
                    JItemUI.showWarnMesssge(frame, Textual.ALUNO_NAO_ENCONTRADO, Textual.CADASTRO_DE_NOTAS);
                }
            }
        }
    }

    private Student addGrade(Student student) {
        boolean isOK = false;
        ImageIcon icon = UAIcon.CADASTRAR_NOTA;

        do {
            String grade = (String) JOptionPane.showInputDialog(frame, Textual.INSIRA_NOTA, Textual.CADASTRO_DE_NOTAS,
                    JOptionPane.PLAIN_MESSAGE, icon, null, null);
            if (grade != null) {
                try {
                    grade = grade.replaceAll(",", ".");
                    student.setGrade(Double.parseDouble(grade));
                    isOK = true;
                    JItemUI.showInfoMesssge(frame, Textual.NOTA_CADASTRADA, Textual.CADASTRO_DE_NOTAS, icon);
                } catch (Exception e) {
                    LOGGER.error(e.getMessage(), e);
                    JItemUI.showErrorMesssge(frame, Textual.NOTA_INVALIDA, Textual.CADASTRO_DE_NOTAS);
                }
            } else {
                isOK = true;
                student.setGrade(null);
            }
        } while (!isOK);


        return student;
    }

    private String getStudentID() {
        String id = null;
        boolean isOK = false;

        while (!isOK) {
            id = (String) JOptionPane.showInputDialog(frame, Textual.DIGITE_CODIGO_ALUNO, Textual.CODIGO_DO_ALUNO,
                    JOptionPane.PLAIN_MESSAGE, UAIcon.CADASTRAR_ALUNO, null, null);
            if (id != null && id.isEmpty()) {
                isOK = false;
                JItemUI.showErrorMesssge(frame, Textual.CODIGO_INVALIDO, Textual.CODIGO_DO_ALUNO);
            } else {
                isOK = true;
            }
        }

        return id;
    }

    public void addStudent() {
        Student student = new Student();
        student.setId(getStudentID());

        if (student.getId() != null) {
            boolean erro = false;
            int aux = students.size() - 1;
            while (aux != -1) {
                if (students.get(aux).getId().equals(student.getId())) {
                    erro = true;
                    break;
                }
                aux--;
            }

            if (erro) {
                JItemUI.showErrorMesssge(frame, Textual.CODIGO_JA_EXISTENTE, Textual.CADASTRO_DO_ALUNO);
            } else {
                // Atribui o valor null, pois somente o student foi cadastrado e a nota ainda não.
                student.setGrade(null);
                students.push(student);
                JItemUI.showInfoMesssge(frame, Textual.ALUNO_CADASTRADO, Textual.CADASTRO_DO_ALUNO, UAIcon.CADASTRAR_ALUNO);
            }
        }
    }

    public void calculateGradeAverage() {
        int size;
        int counter;
        double average;

        if (students.empty()) {
            JItemUI.showErrorMesssge(frame, Textual.SEM_ALUNOS_CADASTRADOS, Textual.CALCULAR_MEDIA);
        } else {
            size = students.size() - 1;
            counter = 0;
            average = 0;
            while (size != -1) {
                if (students.get(size).getGrade() != null) {
                    counter++;
                    average += students.get(size).getGrade();
                }
                size--;
            }

            ImageIcon icon = UAIcon.CALCULAR_MEDIA;

            if (counter != 0) {
                average /= counter;
                if (counter == 1) {
                    JItemUI.showInfoMesssge(frame, Textual.MEDIA_ALUNO + average, Textual.CALCULAR_MEDIA, icon);
                } else {
                    JItemUI.showInfoMesssge(frame, Textual.MEDIA_ALUNOS + average, Textual.CALCULAR_MEDIA, icon);
                }
            } else {
                JItemUI.showErrorMesssge(frame, Textual.SEM_NOTAS_CADASTRADAS, Textual.CALCULAR_MEDIA);
            }
        }
    }

    public void showAll() {
        int size;

        if (students.empty()) {
            JItemUI.showErrorMesssge(frame, Textual.SEM_ALUNOS_CADASTRADOS, Textual.EXIBIR_ALUNOS);
        } else {
            StringBuilder report = new StringBuilder();
            size = students.size() - 1;

            while (size != -1) {
                report.append(Textual.CODIGO).append(students.get(size).getId()).append(" ");
                if (students.get(size).getGrade() != null) {
                    report.append(Textual.NOTA).append(students.get(size).getGrade()).append("\r\n");
                } else {
                    report.append(Textual.SEM_NOTA);
                }
                size--;
            }

            JItemUI.showInfoMesssge(frame, report.toString(), Textual.EXIBIR_ALUNOS, UAIcon.EXIBIR_ALUNO);
        }
    }

    public void searchStudent() {
        int size;
        int counter;
        String id;

        if (students.empty()) {
            JItemUI.showErrorMesssge(frame, Textual.SEM_ALUNOS_CADASTRADOS, Textual.CONSULTA_DE_ALUNOS);
        } else {
            size = students.size() - 1;
            id = getStudentID();
            if (id != null) {
                counter = 0;

                ImageIcon icon = UAIcon.PROCURAR_ALUNO;

                while (size != -1) {
                    if (students.get(size).getId().equals(id)) {
                        counter++;
                        JItemUI.showInfoMesssge(frame, Textual.O_ALUNO + students.get(size).getId() + Textual.ESTA_CADASTRADO, Textual.CONSULTA_DE_ALUNOS, icon);
                    }
                    size--;
                }

                if (counter == 0) {
                    JItemUI.showErrorMesssge(frame, Textual.ALUNO_NAO_ENCONTRADO, Textual.CONSULTA_DE_ALUNOS);
                }
            }

        }
    }

    public void remove() {
        String id;

        if (students.empty())
            JItemUI.showErrorMesssge(frame, Textual.SEM_ALUNOS_CADASTRADOS, Textual.EXCLUIR_ALUNO);
        else {
            int size = students.size() - 1;
            id = getStudentID();

            if (id != null) {
                int counter = 0;
                // Verifica se o código foi cadastrado
                while (size != -1) {
                    if (id.equals(students.get(size).getId())) {
                        counter++;
                    }
                    size--;
                }

                if (counter > 0) {
                    while (!Objects.equals(students.peek().getId(), id) || students.peek().getId().equals(id)) {
                        if (students.peek().getId().equals(id)) {
                            students.pop();
                            break;
                        }
                        students.pop();
                    }

                    JItemUI.showInfoMesssge(frame, Textual.ALUNO_EXCLUIDO, Textual.EXCLUIR_ALUNO, UAIcon.EXCLUIR_ALUNO);
                } else {
                    JItemUI.showWarnMesssge(frame, Textual.ALUNO_NAO_ENCONTRADO, Textual.EXCLUIR_ALUNO);
                }
            }
        }
    }

}