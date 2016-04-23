package br.edu.ifms.ed;

import br.edu.ifms.ed.constant.Textual;
import br.edu.ifms.ed.ui.Cor;
import br.edu.ifms.ed.ui.Fonte;
import br.edu.ifms.ed.ui.Icone;
import br.edu.ifms.ed.ui.JItemUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadastroAluno {

    private JFrame telaPrincipal;

    /**
     * Create the application.
     */
    private CadastroAluno() {
        initialize();
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CadastroAluno window = new CadastroAluno();
                window.telaPrincipal.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        telaPrincipal = new JFrame();
        telaPrincipal.getContentPane().setBackground(Cor.PRIMARY_COLOR);
        telaPrincipal.setResizable(false);
        telaPrincipal.getContentPane().setFont(Fonte.PRIMARY_FONT);
        telaPrincipal.getContentPane().setMaximumSize(new Dimension(400, 400));
        telaPrincipal.setIconImage(Icone.LOGO.getImage());
        telaPrincipal.setTitle(Textual.TITLE_VERSION);
        telaPrincipal.setBounds(100, 100, 339, 530);
        telaPrincipal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        telaPrincipal.getContentPane().setLayout(null);

        JButton botaoCalcularMedia = JItemUI.button(Textual.CALCULAR_MEDIA, Cor.BLUE_GREEN, "calcularMedia",
                Cor.PRIMARY_COLOR, Textual.CALCULAR_MEDIA_TOOLTIP);
        botaoCalcularMedia.setBounds(61, 390, 208, 41);
        botaoCalcularMedia.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Operacoes operacoes = new Operacoes();
                operacoes.calcularMedia(telaPrincipal);
            }
        });
        telaPrincipal.getContentPane().add(botaoCalcularMedia);

        JButton botaoExcluirAluno = JItemUI.button(Textual.EXCLUIR_ALUNO, Cor.RED, "excluirAlunos",
                Cor.PRIMARY_COLOR, Textual.EXCLUIR_ALUNO_TOOLTIP);
        botaoExcluirAluno.setBounds(61, 438, 208, 42);
        botaoExcluirAluno.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Operacoes operacoes = new Operacoes();
                operacoes.excluirAluno(telaPrincipal);
            }
        });
        telaPrincipal.getContentPane().add(botaoExcluirAluno);

        JLabel textoCadastroDeAlunos = new JLabel(Textual.TITLE);
        textoCadastroDeAlunos.setFont(new Font("tahoma", Font.BOLD, 22));
        textoCadastroDeAlunos.setBounds(0, 11, 337, 54);
        textoCadastroDeAlunos.setIcon(Icone.LOGO);
        textoCadastroDeAlunos.setToolTipText(Textual.CADASTRAR_ATRAVES_DE_PILHA);
        textoCadastroDeAlunos.setHorizontalAlignment(SwingConstants.CENTER);
        textoCadastroDeAlunos.setForeground(Cor.GREEN_DARK);
        telaPrincipal.getContentPane().add(textoCadastroDeAlunos);

        JPanel painelInserir = new JPanel();
        painelInserir.setBackground(Cor.PRIMARY_COLOR);
        painelInserir.setBounds(51, 66, 230, 145);
        painelInserir.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        painelInserir.setFont(new Font("consolas", Font.PLAIN, 15));
        painelInserir.setForeground(Cor.BLACK);
        painelInserir.setBorder(JItemUI.titledBorder("Cadastrar"));
        telaPrincipal.getContentPane().add(painelInserir);
        painelInserir.setLayout(null);

        JButton botaoCadastrarAluno = JItemUI.button(Textual.CADASTRAR_ALUNO, Cor.PURPLE, "cadastrarAluno",
                Cor.PRIMARY_COLOR, Textual.CADASTRAR_ALUNO_TOOLTIP);
        botaoCadastrarAluno.setBounds(10, 30, 208, 41);
        painelInserir.add(botaoCadastrarAluno);
        botaoCadastrarAluno.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Operacoes operacoes = new Operacoes();
                operacoes.cadastrandoAluno(telaPrincipal);
            }
        });

        JButton botaoCadastrarNota = JItemUI.button(Textual.CADASTRAR_NOTA, Cor.BLUE, "cadastrarNota",
                Cor.PRIMARY_COLOR, Textual.CADASTRAR_NOTA_TOOLTIP);
        botaoCadastrarNota.setBounds(10, 82, 208, 41);
        painelInserir.add(botaoCadastrarNota);
        botaoCadastrarNota.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Operacoes operacoes = new Operacoes();
                operacoes.cadastroNota(telaPrincipal);
            }
        });

        JPanel painelConsulta = new JPanel();
        painelConsulta.setBackground(Cor.PRIMARY_COLOR);
        painelConsulta.setBounds(51, 223, 230, 146);
        painelConsulta.setBorder(JItemUI.titledBorder("Consulta"));
        telaPrincipal.getContentPane().add(painelConsulta);
        painelConsulta.setLayout(null);

        JButton botaoConsultarAluno = JItemUI.button(Textual.CONSULTAR_ALUNO, Cor.ORANGE, "procurarAluno",
                Cor.BLACK, Textual.CONSULTAR_ALUNO_TOOLTIP);
        botaoConsultarAluno.setBounds(14, 30, 204, 41);
        painelConsulta.add(botaoConsultarAluno);
        botaoConsultarAluno.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Operacoes operacoes = new Operacoes();
                operacoes.consultaAluno(telaPrincipal);
            }
        });

        JButton botaoExibirAlunos = JItemUI.button(Textual.EXIBIR_ALUNOS, Cor.YELLOW, "exibirAlunos",
                Cor.BLACK, Textual.EXIBIR_ALUNOS_TOOLTIP);
        botaoExibirAlunos.setBounds(14, 82, 204, 41);
        painelConsulta.add(botaoExibirAlunos);
        botaoExibirAlunos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Operacoes operacoes = new Operacoes();
                operacoes.exibirAlunos(telaPrincipal);
            }
        });
    }
}