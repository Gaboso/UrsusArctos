package br.edu.ifms.ed;

import br.edu.ifms.ed.util.AtributosVisuais;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadastroAluno implements AtributosVisuais {

    private JFrame telaPrincipal;

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
     * Create the application.
     */
    private CadastroAluno() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        telaPrincipal = new JFrame();
        telaPrincipal.getContentPane().setBackground(brancoUm);
        telaPrincipal.setResizable(false);
        telaPrincipal.setBackground(brancoDois);
        telaPrincipal.getContentPane().setFont(consolasPlainQuize);
        telaPrincipal.getContentPane().setMaximumSize(new Dimension(400, 400));
        telaPrincipal.setIconImage(Toolkit.getDefaultToolkit().getImage(CadastroAluno.class.getResource(caminhoLogo)));
        telaPrincipal.setTitle("Cadastro de alunos v1.3");
        telaPrincipal.setBounds(100, 100, 339, 530);
        telaPrincipal.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        JButton botaoCalcularMedia = new JButton("Calcular media");
        botaoCalcularMedia.setBackground(verdeAmarelado);
        botaoCalcularMedia.setForeground(brancoUm);
        botaoCalcularMedia.setBounds(61, 390, 208, 41);
        botaoCalcularMedia.setIcon(new ImageIcon(CadastroAluno.class.getResource(caminhoImagem + "calcularMedia.png")));
        botaoCalcularMedia.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botaoCalcularMedia.setFont(tahomaNegritoTreze);
        botaoCalcularMedia.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Operacoes operacoes = new Operacoes();
                operacoes.calcularMedia();
            }
        });
        telaPrincipal.getContentPane().setLayout(null);
        botaoCalcularMedia.setToolTipText("Clique  aqui para calcular a media");
        telaPrincipal.getContentPane().add(botaoCalcularMedia);

        JButton botaoExcluirAluno = new JButton("Excluir Aluno");
        botaoExcluirAluno.setBackground(vermelho);
        botaoExcluirAluno.setForeground(brancoUm);
        botaoExcluirAluno.setBounds(61, 438, 208, 42);
        botaoExcluirAluno.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botaoExcluirAluno.setIcon(new ImageIcon(CadastroAluno.class.getResource(caminhoImagem + "excluirAlunos.png")));
        botaoExcluirAluno.setFont(tahomaNegritoTreze);
        botaoExcluirAluno.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Operacoes operacoes = new Operacoes();
                operacoes.excluirAluno();
            }
        });
        botaoExcluirAluno.setToolTipText("Clique aqui excluir um aluno");
        telaPrincipal.getContentPane().add(botaoExcluirAluno);

        JLabel textoCadastroDeAlunos = new JLabel("Cadastro de alunos");
        textoCadastroDeAlunos.setFont(tahomaNegritoVinteDois);
        textoCadastroDeAlunos.setBounds(0, 11, 337, 54);
        textoCadastroDeAlunos.setIcon(new ImageIcon(CadastroAluno.class.getResource(caminhoLogo)));
        textoCadastroDeAlunos.setToolTipText("Cadastro de alunos através de pilha");
        textoCadastroDeAlunos.setHorizontalAlignment(SwingConstants.CENTER);
        textoCadastroDeAlunos.setForeground(verdeEscuro);
        telaPrincipal.getContentPane().add(textoCadastroDeAlunos);

        JPanel painelInserir = new JPanel();
        painelInserir.setBackground(brancoUm);
        painelInserir.setBounds(51, 66, 230, 145);
        painelInserir.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        painelInserir.setFont(consolasPlainQuize);
        painelInserir.setForeground(preto);
        painelInserir.setBorder(new TitledBorder(borda, "Cadastrar", TitledBorder.LEADING, TitledBorder.TOP, null, preto));
        telaPrincipal.getContentPane().add(painelInserir);
        painelInserir.setLayout(null);

        JButton botaoCadastrarAluno = new JButton("Cadastrar Aluno");
        botaoCadastrarAluno.setBackground(roxo);
        botaoCadastrarAluno.setForeground(brancoUm);
        botaoCadastrarAluno.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botaoCadastrarAluno.setBounds(10, 30, 208, 41);
        painelInserir.add(botaoCadastrarAluno);
        botaoCadastrarAluno.setIcon(new ImageIcon(CadastroAluno.class.getResource(caminhoImagem + "cadastrarAluno.png")));
        botaoCadastrarAluno.setFont(tahomaNegritoTreze);
        botaoCadastrarAluno.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Operacoes operacoes = new Operacoes();
                operacoes.cadastrandoAluno();
            }
        });
        botaoCadastrarAluno.setToolTipText("Clique aqui cadastrar um aluno");

        JButton botaoCadastrarNota = new JButton("Cadastrar Nota");
        botaoCadastrarNota.setBackground(azul);
        botaoCadastrarNota.setForeground(brancoUm);
        botaoCadastrarNota.setIcon(new ImageIcon(CadastroAluno.class.getResource(caminhoImagem + "cadastrarNota.png")));
        botaoCadastrarNota.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botaoCadastrarNota.setBounds(10, 82, 208, 41);
        painelInserir.add(botaoCadastrarNota);
        botaoCadastrarNota.setFont(tahomaNegritoTreze);
        botaoCadastrarNota.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Operacoes operacoes = new Operacoes();
                operacoes.cadastroNota();
            }
        });
        botaoCadastrarNota.setToolTipText("Clique aqui para inserir uma nota");

        JPanel painelConsulta = new JPanel();
        painelConsulta.setBackground(new Color(255, 250, 250));
        painelConsulta.setBounds(51, 223, 230, 146);
        painelConsulta.setBorder(new TitledBorder(borda, "Consulta",
                TitledBorder.LEADING, TitledBorder.TOP, null, preto));
        telaPrincipal.getContentPane().add(painelConsulta);
        painelConsulta.setLayout(null);

        JButton botaoConsultarAluno = new JButton("Consultar Aluno");
        botaoConsultarAluno.setBackground(laranja);
        botaoConsultarAluno.setBounds(14, 30, 204, 41);
        painelConsulta.add(botaoConsultarAluno);
        botaoConsultarAluno.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botaoConsultarAluno.setIcon(new ImageIcon(CadastroAluno.class.getResource(caminhoImagem + "procurarAluno.png")));
        botaoConsultarAluno.setFont(tahomaNegritoTreze);
        botaoConsultarAluno.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Operacoes operacoes = new Operacoes();
                operacoes.consultaAluno();
            }
        });
        botaoConsultarAluno.setToolTipText("Clique aqui para consultar por um aluno");

        JButton botaoExibirAlunos = new JButton("Exibir Alunos");
        botaoExibirAlunos.setBackground(amarelo);
        botaoExibirAlunos.setBounds(14, 82, 204, 41);
        painelConsulta.add(botaoExibirAlunos);
        botaoExibirAlunos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botaoExibirAlunos.setIcon(new ImageIcon(CadastroAluno.class.getResource(caminhoImagem + "exibirAlunos.png")));
        botaoExibirAlunos.setFont(tahomaNegritoTreze);
        botaoExibirAlunos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                Operacoes operacoes = new Operacoes();
                operacoes.exibirAlunos();
            }
        });
        botaoExibirAlunos.setToolTipText("Clique aqui para exibir os alunos");
    }
}