package br.edu.ifms.ed;

import br.edu.ifms.ed.constant.Textual;
import br.edu.ifms.ed.exception.DuplicateStudentException;
import br.edu.ifms.ed.exception.InvalidGradeException;
import br.edu.ifms.ed.exception.NoGradeException;
import br.edu.ifms.ed.exception.NoStudentException;
import br.edu.ifms.ed.exception.StudentNotFoundException;
import br.edu.ifms.ed.ui.JItemUI;
import br.edu.ifms.ed.ui.UAFont;
import br.edu.ifms.ed.ui.UAIcon;
import com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme;
import lombok.extern.slf4j.Slf4j;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

@Slf4j
public class CadastroAluno {

    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 42;
    private static final int FRAME_WIDTH = 340;
    private static final int FRAME_HEIGHT = 530;

    private static final Color PINK = Color.decode("#ff79c6");

    private final StudentStack stack;
    private JFrame mainFrame;

    private CadastroAluno() {
        stack = new StudentStack();
        initialize();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                FlatDraculaIJTheme.setup();
                CadastroAluno window = new CadastroAluno();
                window.mainFrame.setVisible(true);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        });
    }

    private void initialize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int horizontalStart = (int) Math.ceil((screenSize.width / 2D) - (FRAME_WIDTH / 2D));
        int verticalStart = (int) Math.ceil((screenSize.height / 2D) - (FRAME_HEIGHT / 2D));

        mainFrame = new JFrame();
        mainFrame.setResizable(false);
        mainFrame.getContentPane().setFont(UAFont.PRIMARY_FONT);
        mainFrame.setIconImage(UAIcon.LOGO.getImage());
        mainFrame.setTitle(Textual.TITLE_VERSION);
        mainFrame.setBounds(horizontalStart, verticalStart, FRAME_WIDTH, FRAME_HEIGHT);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.getContentPane().setLayout(null);

        JLabel title = new JLabel(Textual.TITLE);
        title.setFont(new Font("tahoma", Font.BOLD, 22));
        title.setBounds(0, 11, 337, 54);
        title.setIcon(UAIcon.LOGO);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(PINK);
        mainFrame.getContentPane().add(title);

        JPanel panelAdd = new JPanel();
        panelAdd.setBounds(48, 66, 230, 146);
        panelAdd.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        panelAdd.setFont(new Font("consolas", Font.PLAIN, 15));
        panelAdd.setBorder(JItemUI.titledBorder(Textual.CADASTRAR));
        mainFrame.getContentPane().add(panelAdd);
        panelAdd.setLayout(null);

        JButton buttonAddStudent = JItemUI.button(Textual.CADASTRAR_ALUNO, "user-detail", Textual.CADASTRAR_ALUNO_TOOLTIP);
        buttonAddStudent.setBounds(15, 30, BUTTON_WIDTH, BUTTON_HEIGHT);
        panelAdd.add(buttonAddStudent);
        buttonAddStudent.addMouseListener(onAddStudent());

        JButton buttonAddGrade = JItemUI.button(Textual.CADASTRAR_NOTA, "pencil", Textual.CADASTRAR_NOTA_TOOLTIP);
        buttonAddGrade.setBounds(15, 82, BUTTON_WIDTH, BUTTON_HEIGHT);
        panelAdd.add(buttonAddGrade);
        buttonAddGrade.addMouseListener(onAddGrade());

        JPanel panelSearch = new JPanel();
        panelSearch.setBounds(48, 223, 230, 146);
        panelSearch.setBorder(JItemUI.titledBorder(Textual.CONSULTA));
        mainFrame.getContentPane().add(panelSearch);
        panelSearch.setLayout(null);

        JButton buttonSearchStudent = JItemUI.button(Textual.CONSULTAR_ALUNO, "search", Textual.CONSULTAR_ALUNO_TOOLTIP);
        buttonSearchStudent.setBounds(15, 30, BUTTON_WIDTH, BUTTON_HEIGHT);
        panelSearch.add(buttonSearchStudent);
        buttonSearchStudent.addMouseListener(onIsPresent());

        JButton buttonShowStudent = JItemUI.button(Textual.EXIBIR_ALUNOS, "student", Textual.EXIBIR_ALUNOS_TOOLTIP);
        buttonShowStudent.setBounds(15, 82, BUTTON_WIDTH, BUTTON_HEIGHT);
        panelSearch.add(buttonShowStudent);
        buttonShowStudent.addMouseListener(onGetAll());

        JButton buttonCalculateGradeAverage = JItemUI.button(Textual.CALCULAR_MEDIA, "calculator", Textual.CALCULAR_MEDIA_TOOLTIP);
        buttonCalculateGradeAverage.setBounds(64, 390, BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonCalculateGradeAverage.addMouseListener(onAverage());
        mainFrame.getContentPane().add(buttonCalculateGradeAverage);

        JButton buttonRemoveStudent = JItemUI.button(Textual.EXCLUIR_ALUNO, "trash", Textual.EXCLUIR_ALUNO_TOOLTIP);
        buttonRemoveStudent.setBounds(64, 438, BUTTON_WIDTH, BUTTON_HEIGHT);
        buttonRemoveStudent.addMouseListener(onRemove());
        mainFrame.getContentPane().add(buttonRemoveStudent);
    }

    private MouseAdapter onAverage() {
        return new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    double average = stack.calculateGradeAverage();
                    JItemUI.showInfoMesssge(mainFrame, Textual.MEDIA_ALUNOS + average, Textual.CALCULAR_MEDIA, UAIcon.CALC_AVERAGE);
                } catch (NoStudentException noStudentException) {
                    JItemUI.showErrorMesssge(mainFrame, Textual.SEM_ALUNOS_CADASTRADOS, Textual.CALCULAR_MEDIA);
                } catch (NoGradeException noGradeException) {
                    JItemUI.showErrorMesssge(mainFrame, Textual.SEM_NOTAS_CADASTRADAS, Textual.CALCULAR_MEDIA);
                }
            }
        };
    }

    private MouseAdapter onRemove() {
        return new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                String studentID = getStudentIDSearch();
                if (studentID != null) {
                    try {
                        stack.remove(studentID);
                        JItemUI.showInfoMesssge(mainFrame, Textual.ALUNO_EXCLUIDO, Textual.EXCLUIR_ALUNO, UAIcon.REMOVE_STUDENT);
                    } catch (NoStudentException noStudentException) {
                        JItemUI.showErrorMesssge(mainFrame, Textual.SEM_ALUNOS_CADASTRADOS, Textual.EXCLUIR_ALUNO);
                    } catch (StudentNotFoundException studentNotFoundException) {
                        JItemUI.showWarnMesssge(mainFrame, Textual.ALUNO_NAO_ENCONTRADO, Textual.EXCLUIR_ALUNO);
                    }
                }
            }
        };
    }

    private MouseAdapter onAddStudent() {
        return new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                String studentID = getStudentIDCreate();
                if (studentID != null) {
                    try {
                        stack.addStudent(studentID);
                        JItemUI.showInfoMesssge(mainFrame, Textual.ALUNO_CADASTRADO, Textual.CADASTRO_DO_ALUNO, UAIcon.ADD_STUDENT);
                    } catch (DuplicateStudentException duplicateStudentException) {
                        JItemUI.showErrorMesssge(mainFrame, Textual.CODIGO_JA_EXISTENTE, Textual.CADASTRO_DO_ALUNO);
                    }
                }
            }
        };
    }

    private MouseAdapter onAddGrade() {
        return new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                String studentID = getStudentIDSearch();
                if (studentID == null) {
                    return;
                }

                String grade = (String) JOptionPane.showInputDialog(
                    mainFrame, Textual.INSIRA_NOTA, Textual.CADASTRO_DE_NOTAS,
                    JOptionPane.PLAIN_MESSAGE, UAIcon.ADD_GRADE, null, null
                );
                if (grade == null) {
                    return;
                }

                try {
                    stack.addGrade(studentID, grade);
                    JItemUI.showInfoMesssge(mainFrame, Textual.NOTA_CADASTRADA, Textual.CADASTRO_DE_NOTAS, UAIcon.ADD_GRADE);
                } catch (NoStudentException noStudentException) {
                    JItemUI.showErrorMesssge(mainFrame, Textual.SEM_ALUNOS_CADASTRADOS, Textual.CADASTRO_DE_NOTAS);
                } catch (StudentNotFoundException studentNotFoundException) {
                    JItemUI.showWarnMesssge(mainFrame, Textual.ALUNO_NAO_ENCONTRADO, Textual.CADASTRO_DE_NOTAS);
                } catch (InvalidGradeException invalidGradeException) {
                    JItemUI.showErrorMesssge(mainFrame, Textual.NOTA_INVALIDA, Textual.CADASTRO_DE_NOTAS);
                }
            }
        };
    }

    private MouseAdapter onIsPresent() {
        return new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                String studentID = getStudentIDSearch();
                if (studentID != null) {
                    try {
                        boolean isPresent = stack.isPresent(studentID);
                        JItemUI.showInfoMesssge(
                            mainFrame,
                            Textual.O_ALUNO + studentID + (isPresent ? Textual.ESTA_CADASTRADO : Textual.NAO_ESTA_CADASTRADO),
                            Textual.CONSULTA_DE_ALUNOS,
                            UAIcon.SEARCH_STUDENT
                        );
                    } catch (NoStudentException noStudentException) {
                        JItemUI.showErrorMesssge(mainFrame, Textual.SEM_ALUNOS_CADASTRADOS, Textual.CONSULTA_DE_ALUNOS);
                    }
                }
            }
        };
    }

    private MouseAdapter onGetAll() {
        return new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    String allStudents = stack.getAllStudents();
                    JItemUI.showInfoMesssge(mainFrame, allStudents, Textual.EXIBIR_ALUNOS, UAIcon.SHOW_STUDENT);
                } catch (NoStudentException noStudentException) {
                    JItemUI.showErrorMesssge(mainFrame, Textual.SEM_ALUNOS_CADASTRADOS, Textual.EXIBIR_ALUNOS);
                }
            }
        };
    }

    private String getStudentIDSearch() {
        return getStudentID(false);
    }

    private String getStudentIDCreate() {
        return getStudentID(true);
    }

    private String getStudentID(boolean isCreate) {
        if (!isCreate && stack.isEmpty()) {
            JItemUI.showErrorMesssge(mainFrame, Textual.SEM_ALUNOS_CADASTRADOS, Textual.TITLE);
            return null;
        }

        String id = null;
        boolean isOK = false;

        while (!isOK) {
            id = (String) JOptionPane.showInputDialog(
                mainFrame, Textual.DIGITE_CODIGO_ALUNO, Textual.CODIGO_DO_ALUNO,
                JOptionPane.PLAIN_MESSAGE, UAIcon.ADD_STUDENT, null, null
            );

            if (id != null && id.trim().isEmpty()) {
                isOK = false;
                JItemUI.showErrorMesssge(mainFrame, Textual.CODIGO_INVALIDO, Textual.CODIGO_DO_ALUNO);
            } else {
                isOK = true;
            }
        }

        return id;
    }

}
