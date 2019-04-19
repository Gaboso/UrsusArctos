package br.edu.ifms.ed;

import br.edu.ifms.ed.constant.Textual;
import br.edu.ifms.ed.ui.JItemUI;
import br.edu.ifms.ed.ui.UAColor;
import br.edu.ifms.ed.ui.UAFont;
import br.edu.ifms.ed.ui.UAIcon;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadastroAluno {

    private JFrame mainFrame;
    private static final Logger LOGGER = Logger.getLogger(CadastroAluno.class);

    private CadastroAluno() {
        initialize();
    }

    /**
     * Run application
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CadastroAluno window = new CadastroAluno();
                window.mainFrame.setVisible(true);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        });
    }

    /**
     * Initializes the contents in frame
     */
    private void initialize() {
        mainFrame = new JFrame();
        mainFrame.getContentPane().setBackground(UAColor.PRIMARY_COLOR);
        mainFrame.setResizable(false);
        mainFrame.getContentPane().setFont(UAFont.PRIMARY_FONT);
        mainFrame.getContentPane().setMaximumSize(new Dimension(400, 400));
        mainFrame.setIconImage(UAIcon.LOGO.getImage());
        mainFrame.setTitle(Textual.TITLE_VERSION);
        mainFrame.setBounds(100, 100, 339, 530);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.getContentPane().setLayout(null);

        JButton buttonCalculateGradeAverage = JItemUI.button(Textual.CALCULAR_MEDIA, UAColor.BLUE_GREEN, "calculator",
                UAColor.BLACK, Textual.CALCULAR_MEDIA_TOOLTIP);
        buttonCalculateGradeAverage.setBounds(61, 390, 208, 41);
        buttonCalculateGradeAverage.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                StudentBO studentBO = new StudentBO(mainFrame);
                studentBO.calculateGradeAverage();
            }
        });
        mainFrame.getContentPane().add(buttonCalculateGradeAverage);

        JButton buttonRemoveStudent = JItemUI.button(Textual.EXCLUIR_ALUNO, UAColor.RED, "trash",
                UAColor.BLACK, Textual.EXCLUIR_ALUNO_TOOLTIP);
        buttonRemoveStudent.setBounds(61, 438, 208, 42);
        buttonRemoveStudent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                StudentBO studentBO = new StudentBO(mainFrame);
                studentBO.remove();
            }
        });
        mainFrame.getContentPane().add(buttonRemoveStudent);

        JLabel title = new JLabel(Textual.TITLE);
        title.setFont(new Font("tahoma", Font.BOLD, 22));
        title.setBounds(0, 11, 337, 54);
        title.setIcon(UAIcon.LOGO);
        title.setToolTipText(Textual.CADASTRAR_ATRAVES_DE_PILHA);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(UAColor.BROWN);
        mainFrame.getContentPane().add(title);

        JPanel panelAdd = new JPanel();
        panelAdd.setBackground(UAColor.PRIMARY_COLOR);
        panelAdd.setBounds(51, 66, 230, 145);
        panelAdd.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        panelAdd.setFont(new Font("consolas", Font.PLAIN, 15));
        panelAdd.setForeground(UAColor.BLACK);
        panelAdd.setBorder(JItemUI.titledBorder(Textual.CADASTRAR));
        mainFrame.getContentPane().add(panelAdd);
        panelAdd.setLayout(null);

        JButton buttonAddStudent = JItemUI.button(Textual.CADASTRAR_ALUNO, UAColor.PURPLE, "user-detail",
                UAColor.BLACK, Textual.CADASTRAR_ALUNO_TOOLTIP);
        buttonAddStudent.setBounds(10, 30, 208, 41);
        panelAdd.add(buttonAddStudent);
        buttonAddStudent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                StudentBO studentBO = new StudentBO(mainFrame);
                studentBO.addStudent();
            }
        });

        JButton buttonAddGrade = JItemUI.button(Textual.CADASTRAR_NOTA, UAColor.BLUE, "pencil",
                UAColor.BLACK, Textual.CADASTRAR_NOTA_TOOLTIP);
        buttonAddGrade.setBounds(10, 82, 208, 41);
        panelAdd.add(buttonAddGrade);
        buttonAddGrade.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                StudentBO studentBO = new StudentBO(mainFrame);
                studentBO.addGrade();
            }
        });

        JPanel panelSearch = new JPanel();
        panelSearch.setBackground(UAColor.PRIMARY_COLOR);
        panelSearch.setBounds(51, 223, 230, 146);
        panelSearch.setBorder(JItemUI.titledBorder(Textual.CONSULTA));
        mainFrame.getContentPane().add(panelSearch);
        panelSearch.setLayout(null);

        JButton buttonSearchStudent = JItemUI.button(Textual.CONSULTAR_ALUNO, UAColor.ORANGE, "search",
                UAColor.BLACK, Textual.CONSULTAR_ALUNO_TOOLTIP);
        buttonSearchStudent.setBounds(14, 30, 204, 41);
        panelSearch.add(buttonSearchStudent);
        buttonSearchStudent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                StudentBO studentBO = new StudentBO(mainFrame);
                studentBO.searchStudent();
            }
        });

        JButton buttonShowStudent = JItemUI.button(Textual.EXIBIR_ALUNOS, UAColor.YELLOW, "student",
                UAColor.BLACK, Textual.EXIBIR_ALUNOS_TOOLTIP);
        buttonShowStudent.setBounds(14, 82, 204, 41);
        panelSearch.add(buttonShowStudent);
        buttonShowStudent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                StudentBO studentBO = new StudentBO(mainFrame);
                studentBO.showAll();
            }
        });
    }

}