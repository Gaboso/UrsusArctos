package br.edu.ifms.ed;

import br.edu.ifms.ed.constant.Textual;
import br.edu.ifms.ed.exception.DuplicateStudentException;
import br.edu.ifms.ed.exception.InvalidGradeException;
import br.edu.ifms.ed.exception.NoGradeException;
import br.edu.ifms.ed.exception.NoStudentException;
import br.edu.ifms.ed.exception.StudentNotFoundException;
import br.edu.ifms.ed.model.Student;

import java.util.Objects;
import java.util.Stack;

public class StudentStack {

    private static final Stack<Student> students = new Stack<>();

    public void addGrade(String studentID, String grade) throws NoStudentException, StudentNotFoundException, InvalidGradeException {
        if (students.empty()) {
            throw new NoStudentException();
        }

        int counter = 0;
        if (studentID != null) {
            int currentPosition = students.size() - 1;
            while (currentPosition != -1) {
                Student currentStudent = students.get(currentPosition);
                if (currentStudent.getId().equals(studentID)) {
                    addGradeToStudent(currentStudent, grade);
                    counter++;
                }
                currentPosition--;
            }

            if (counter == 0) {
                throw new StudentNotFoundException();
            }
        }
    }

    private void addGradeToStudent(Student student, String grade) throws InvalidGradeException {
        if (grade != null) {
            try {
                grade = grade.replace(",", ".");
                student.setGrade(Double.parseDouble(grade));
            } catch (Exception e) {
                throw new InvalidGradeException();
            }
        }
    }

    public void addStudent(String studentID) throws DuplicateStudentException {
        Student student = new Student();
        student.setId(studentID);

        boolean alreadyExists = false;
        int currentPosition = students.size() - 1;
        while (currentPosition != -1) {
            String currentID = students.get(currentPosition).getId();
            if (currentID.equals(studentID)) {
                alreadyExists = true;
                break;
            }
            currentPosition--;
        }

        if (alreadyExists) {
            throw new DuplicateStudentException();
        }

        student.setGrade(null);
        students.push(student);
    }

    public double calculateGradeAverage() throws NoStudentException, NoGradeException {
        if (students.empty()) {
            throw new NoStudentException();
        }

        int currentPosition = students.size() - 1;
        int counter = 0;
        double average = 0;
        while (currentPosition != -1) {
            Student currentStudent = students.get(currentPosition);
            if (currentStudent.getGrade() != null) {
                counter++;
                average += currentStudent.getGrade();
            }
            currentPosition--;
        }

        if (counter == 0) {
            throw new NoGradeException();
        }

        average /= counter;
        return average;
    }

    public String getAllStudents() throws NoStudentException {
        if (students.empty()) {
            throw new NoStudentException();
        }

        StringBuilder report = new StringBuilder();
        int currentPosition = students.size() - 1;

        while (currentPosition != -1) {
            Student currentStudent = students.get(currentPosition);
            report.append(Textual.CODIGO)
                  .append(currentStudent.getId())
                  .append(" ");

            if (currentStudent.getGrade() != null) {
                report.append(Textual.NOTA)
                      .append(currentStudent.getGrade())
                      .append("\r\n");
            } else {
                report.append(Textual.SEM_NOTA);
            }
            currentPosition--;
        }

        return report.toString();
    }

    public boolean isPresent(String studentID) throws NoStudentException {
        if (students.empty()) {
            throw new NoStudentException();
        }

        int currentPosition = students.size() - 1;
        boolean hasStudent = false;

        while (currentPosition != -1) {
            String currentID = students.get(currentPosition).getId();
            if (currentID.equals(studentID)) {
                hasStudent = true;
                break;
            }
            currentPosition--;
        }

        return hasStudent;
    }

    public void remove(String studentID) throws NoStudentException, StudentNotFoundException {
        if (students.empty()) {
            throw new NoStudentException();
        }

        int currentPosition = students.size() - 1;

        int counter = 0;
        // Verifica se o código foi cadastrado
        while (currentPosition != -1) {
            String currentID = students.get(currentPosition).getId();
            if (studentID.equals(currentID)) {
                counter++;
            }
            currentPosition--;
        }

        if (counter == 0) {
            throw new StudentNotFoundException();
        }

        while (!Objects.equals(students.peek().getId(), studentID) || students.peek().getId().equals(studentID)) {
            if (students.peek().getId().equals(studentID)) {
                students.pop();
                break;
            }
            students.pop();
        }
    }

}
