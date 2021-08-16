package br.edu.ifms.ed;

import br.edu.ifms.ed.StudentStack;
import br.edu.ifms.ed.constant.Textual;
import br.edu.ifms.ed.exception.DuplicateStudentException;
import br.edu.ifms.ed.exception.InvalidGradeException;
import br.edu.ifms.ed.exception.NoGradeException;
import br.edu.ifms.ed.exception.NoStudentException;
import br.edu.ifms.ed.exception.StudentNotFoundException;
import br.edu.ifms.ed.model.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StudentStackTest {

    private StudentStack stack;

    @BeforeEach
    public void init() {
        stack = new StudentStack();
    }

    @AfterEach
    void tearDown() {
        stack = null;
    }

    void assertRemove(String studentIdToRemove, int stackSize) throws DuplicateStudentException {
        stack.addStudent("3");
        stack.addStudent("2");
        stack.addStudent("1");
        Assertions.assertDoesNotThrow(() -> stack.remove(studentIdToRemove));
        Assertions.assertEquals(stackSize, stack.getStudents().size());
    }

    void assertAddGrade(String studentId, String grade) throws Exception {
        stack.addStudent("3");
        stack.addStudent("2");
        stack.addStudent("1");
        Assertions.assertDoesNotThrow(() -> stack.addGrade(studentId, grade));

        for (Student student : stack.getStudents()) {
            if (student.getId().equals(studentId)) {
                Assertions.assertEquals(Double.parseDouble(grade), student.getGrade());
            } else {
                Assertions.assertNull(student.getGrade());
            }
        }
    }

    @Test
    @DisplayName("Empty stack")
    void isEmptyTrue() {
        boolean isEmpty = stack.isEmpty();
        Assertions.assertTrue(isEmpty);
    }

    @Test
    @DisplayName("Not Empty stack")
    void isEmptyFalse() throws DuplicateStudentException {
        stack.addStudent("1");
        boolean isEmpty = stack.isEmpty();
        Assertions.assertFalse(isEmpty);
    }

    @Test
    @DisplayName("isPresent without students should throw Exception")
    void isPresentWithoutStudents() {
        Assertions.assertThrows(NoStudentException.class, () -> stack.isPresent("1"));
    }

    @Test
    @DisplayName("Student is not present")
    void isNotPresent() throws Exception {
        stack.addStudent("2");
        boolean isPresent = stack.isPresent("1");
        Assertions.assertFalse(isPresent);
    }

    @Test
    @DisplayName("Student is present")
    void isPresent() throws Exception {
        stack.addStudent("3");
        boolean isPresent = stack.isPresent("3");
        Assertions.assertTrue(isPresent);
    }

    @Test
    @DisplayName("remove without students should throw Exception")
    void removeWithoutStudents() {
        Assertions.assertThrows(NoStudentException.class, () -> stack.remove("1"));
    }

    @Test
    @DisplayName("Remove with student not present")
    void RemoveStudentIsNotPresent() throws Exception {
        stack.addStudent("2");
        Assertions.assertThrows(StudentNotFoundException.class, () -> stack.remove("1"));
    }

    @Test
    @DisplayName("Remove with student on top")
    void removeOnTop() throws Exception {
        assertRemove("1", 2);
    }

    @Test
    @DisplayName("Remove with student on middle")
    void removeOnMiddle() throws Exception {
        assertRemove("2", 1);
    }

    @Test
    @DisplayName("Remove with student on bottom")
    void removeOnBottom() throws Exception {
        assertRemove("3", 0);
    }

    @Test
    @DisplayName("Add first student to stack")
    void addFirstStudent() throws Exception {
        stack.addStudent("50");
        Assertions.assertEquals(1, stack.getStudents().size());
    }

    @Test
    @DisplayName("Add more than one student to stack")
    void addMoreStudents() throws Exception {
        stack.addStudent("100");
        stack.addStudent("200");
        Assertions.assertEquals(2, stack.getStudents().size());
    }

    @Test
    @DisplayName("Add duplicate students to stack")
    void addDuplicateStudents() throws Exception {
        stack.addStudent("100");

        Assertions.assertThrows(DuplicateStudentException.class, () -> stack.addStudent("100"));
    }

    @Test
    @DisplayName("Add grade without students")
    void addGradeWithoutStudents() {
        Assertions.assertThrows(NoStudentException.class, () -> stack.addGrade("1", "10"));
    }

    @Test
    @DisplayName("Add grade to student no present in stack")
    void addGradeToStudentNotPresent() throws Exception {
        stack.addStudent("1");
        Assertions.assertThrows(StudentNotFoundException.class, () -> stack.addGrade("2", "10"));
    }

    @Test
    @DisplayName("Add invalid grade to student")
    void addGradeInvalid() throws Exception {
        stack.addStudent("1");
        Assertions.assertThrows(InvalidGradeException.class, () -> stack.addGrade("1", "a.1"));
    }

    @Test
    @DisplayName("Add grade to student on top of stack")
    void addGradeToStudentOnTop() throws Exception {
        assertAddGrade("1", "9.5");
    }

    @Test
    @DisplayName("Add grade to student on middle of stack")
    void addGradeToStudentOnMiddle() throws Exception {
        assertAddGrade("2", "7");
    }

    @Test
    @DisplayName("Add grade to student on bottom of stack")
    void addGradeToStudentOnBottom() throws Exception {
        assertAddGrade("3", "5");
    }

    @Test
    @DisplayName("Calculate grade average without students in stack")
    void calculateGradeAverageWithoutStudents() {
        Assertions.assertThrows(NoStudentException.class, () -> stack.calculateGradeAverage());
    }

    @Test
    @DisplayName("Calculate grade average without grades in stack")
    void calculateGradeAverageWithoutGrade() throws Exception {
        stack.addStudent("1");
        Assertions.assertThrows(NoGradeException.class, () -> stack.calculateGradeAverage());
    }

    @Test
    @DisplayName("Calculate grade average with only one student")
    void calculateGradeAverageOnlyOneStudent() throws Exception {
        stack.addStudent("1");
        stack.addGrade("1", "10");
        double average = stack.calculateGradeAverage();
        Assertions.assertEquals(10, average);
    }

    @Test
    @DisplayName("Calculate grade average with more than one student")
    void calculateGradeAverage() throws Exception {
        stack.addStudent("1");
        stack.addGrade("1", "10");
        stack.addStudent("2");
        stack.addGrade("2", "8");
        double average = stack.calculateGradeAverage();
        Assertions.assertEquals(9, average);
    }

    @Test
    @DisplayName("Get all without students in stack")
    void getAllWithoutStudents() {
        Assertions.assertThrows(NoStudentException.class, () -> stack.getAllStudents());
    }

    @Test
    @DisplayName("Get all students without grades in stack")
    void getAllWithoutGrades() throws Exception {
        stack.addStudent("3");
        stack.addStudent("2");
        stack.addStudent("1");

        String allStudents = stack.getAllStudents();
        Assertions.assertNotNull(allStudents);
        Assertions.assertTrue(allStudents.contains(Textual.SEM_NOTA));
        Assertions.assertFalse(allStudents.contains(Textual.NOTA));
    }

    @Test
    @DisplayName("Get all students without grades in stack")
    void getAllWithGrades() throws Exception {
        stack.addStudent("3");
        stack.addStudent("2");
        stack.addStudent("1");
        stack.addGrade("3", "9");
        stack.addGrade("2", "6");
        stack.addGrade("1", "7.5");

        String allStudents = stack.getAllStudents();
        Assertions.assertNotNull(allStudents);
        Assertions.assertFalse(allStudents.contains(Textual.SEM_NOTA));
        Assertions.assertTrue(allStudents.contains(Textual.NOTA));
    }

}