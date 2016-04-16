package br.edu.ifms.ed;

import br.edu.ifms.ed.model.Aluno;
import br.edu.ifms.ed.util.AtributosVisuais;

import javax.swing.*;
import java.util.Objects;
import java.util.Stack;

public class Operacoes implements AtributosVisuais {

    private static Stack<Aluno> pilha = new Stack<>();

    /**
     * Método para fazer o cadastro da nota do aluno
     */
    public void cadastroNota() {
        int tamanho, contador;
        String codigo;

        if (pilha.empty()) pilhaVazia(cadastroNotas);
        else {
            contador = 0;
            codigo = pegaCodigoAluno();
            tamanho = pilha.size() - 1;
            while (tamanho != -1) {
                if (pilha.get(tamanho).getCodigoAluno().equals(codigo)) {
                    cadastrarNota(pilha.get(tamanho));
                    contador++;
                }
                tamanho--;
            }
            if (contador == 0) {
                JOptionPane.showMessageDialog(null, "Aluno não encontrado", cadastroNotas, JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Método para receber as notas do usuário
     *
     * @param aluno - Objeto de aluno
     * @return Retorna a nota do aluno
     */
    private Aluno cadastrarNota(Aluno aluno) {
        double numeroConvertido = 0;
        boolean ok = false;
        ImageIcon icone = new ImageIcon(CadastroAluno.class.getResource(caminhoImagem + "cadastrarNota.png"));

        do {
            String codigo = (String) JOptionPane.showInputDialog(null,
                    "Digite uma nota para o aluno: ", cadastroNotas, JOptionPane.PLAIN_MESSAGE, icone, null, null);
            try {
                codigo = codigo.replaceAll(",", ".");
                numeroConvertido = Double.parseDouble(codigo);
                ok = true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Não foi possível inserir a nota ao aluno", cadastroNotas, JOptionPane.ERROR_MESSAGE);
            }
        } while (!ok);

        JOptionPane.showMessageDialog(null, "Nota cadastrada", cadastroNotas, JOptionPane.INFORMATION_MESSAGE, icone);

        aluno.setNota(numeroConvertido);
        return aluno;
    }

    /**
     * Método para receber do usuário o código do aluno
     *
     * @return código do aluno
     */
    private String pegaCodigoAluno() {
        String codigo;

        ImageIcon icone = new ImageIcon(CadastroAluno.class.getResource(caminhoImagem + "cadastrarAluno.png"));
        do {
            codigo = (String) JOptionPane.showInputDialog(null,
                    "Digite um código para aluno: ", codigoAluno, JOptionPane.PLAIN_MESSAGE, icone, null, null);
            if (codigo == null || codigo.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "O código do aluno não pode ser em branco ou nulo", codigoAluno, JOptionPane.ERROR_MESSAGE);
            }
        } while (codigo == null || codigo.isEmpty());

        return codigo;
    }

    /**
     * Método para cadastrar os alunos
     *
     * @param aluno - Objeto de aluno
     * @return retorna o código do aluno igual a uma matricula
     */
    private Aluno cadastrarAluno(Aluno aluno) {
        String codigo = pegaCodigoAluno();
        aluno.setCodigoAluno(codigo);
        return aluno;
    }

    /**
     * Método para fazer o cadastro do aluno
     */
    public void cadastrandoAluno() {
        Aluno aluno = new Aluno();
        cadastrarAluno(aluno);

        boolean erro = false;
        int aux = pilha.size() - 1;
        while (aux != -1) {
            if (pilha.get(aux).getCodigoAluno().equals(aluno.getCodigoAluno())) {
                erro = true;
                break;
            }
            aux--;
        }

        if (erro) {
            JOptionPane.showMessageDialog(null, "Já possui um aluno cadastrado com esse código", cadastroAluno, JOptionPane.ERROR_MESSAGE);
        } else {
            // Atribui o valor null pois somente o aluno foi cadastrado e a nota ainda não.
            aluno.setNota(null);
            pilha.push(aluno);

            ImageIcon icone = new ImageIcon(CadastroAluno.class.getResource(caminhoImagem + "cadastrarAluno.png"));
            JOptionPane.showMessageDialog(null, "Aluno cadastrado", cadastroAluno, JOptionPane.INFORMATION_MESSAGE, icone);
        }
    }

    /**
     * Método para fazer o calculo da media dos alunos
     */
    public void calcularMedia() {
        int tamanho, cont;
        double media;

        if (pilha.empty()) pilhaVazia("Calcular media");
        else {
            tamanho = pilha.size() - 1;
            cont = 0;
            media = 0;
            while (tamanho != -1) {
                if (pilha.get(tamanho).getNota() != null) {
                    cont++;
                    media += pilha.get(tamanho).getNota();
                }
                tamanho--;
            }

            ImageIcon icone = new ImageIcon(CadastroAluno.class.getResource(caminhoImagem + "calcularMedia.png"));

            if (cont != 0) {
                media /= cont;
                if (cont == 1) {
                    JOptionPane.showMessageDialog(null, "Media do aluno: "
                            + media, calcularMedia, JOptionPane.INFORMATION_MESSAGE, icone);
                } else {
                    JOptionPane.showMessageDialog(null, "Media dos alunos: "
                            + media, calcularMedia, JOptionPane.INFORMATION_MESSAGE, icone);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Não existem notas cadastradas!", calcularMedia, JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Método para exibir o aluno
     */
    public void exibirAlunos() {
        int tamanho;

        if (pilha.empty()) pilhaVazia("Exibir alunos");
        else {
            String texto = "";
            tamanho = pilha.size() - 1;
            while (tamanho != -1) {
                texto += ("Código: " + pilha.get(tamanho).getCodigoAluno() + " ");
                if (pilha.get(tamanho).getNota() != null) texto += ("Nota: " + pilha.get(tamanho).getNota() + "\r\n");
                else texto += "Nota não cadastrada!\r\n";
                tamanho--;
            }

            ImageIcon icone = new ImageIcon(CadastroAluno.class.getResource(caminhoImagem + "exibirAlunos.png"));

            JOptionPane.showMessageDialog(null, texto, "Exibir alunos", JOptionPane.INFORMATION_MESSAGE, icone);
        }
    }

    public void consultaAluno() {
        int tamanho, contador;
        String codigo;

        if (pilha.empty()) pilhaVazia("Consulta de alunos");
        else {
            tamanho = pilha.size() - 1;
            codigo = pegaCodigoAluno();
            contador = 0;

            ImageIcon icone = new ImageIcon(CadastroAluno.class.getResource(caminhoImagem + "procurarAluno.png"));

            while (tamanho != -1) {
                if (pilha.get(tamanho).getCodigoAluno().equals(codigo)) {
                    contador++;
                    JOptionPane.showMessageDialog(null, "O aluno " + pilha.get(tamanho).getCodigoAluno()
                            + " esta cadastrado", "Consulta de alunos", JOptionPane.INFORMATION_MESSAGE, icone);
                }
                tamanho--;
            }
            if (contador == 0) {
                JOptionPane.showMessageDialog(null, "Este aluno não foi cadastrado", "Consulta de alunos", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    /**
     * Método para excluir os alunos
     */
    public void excluirAluno() {
        String codigo;

        if (pilha.empty()) pilhaVazia(excluirAluno);
        else {
            int tamanho = pilha.size() - 1;
            // Código do aluno que o usuário quer remover
            codigo = pegaCodigoAluno();

            int cont = 0;
            // Verifica se o código foi cadastrado
            while (tamanho != -1) {
                if (codigo.equals(pilha.get(tamanho).getCodigoAluno())) cont++;
                tamanho--;
            }
            ImageIcon icone = new ImageIcon(CadastroAluno.class.getResource(caminhoImagem + "excluirAlunos.png"));
            if (cont > 0) {
                while (!Objects.equals(pilha.peek().getCodigoAluno(), codigo)
                        || pilha.peek().getCodigoAluno().equals(codigo)) {
                    if (pilha.peek().getCodigoAluno().equals(codigo)) {
                        pilha.pop();
                        break;
                    }
                    pilha.pop();
                }
                JOptionPane.showMessageDialog(null, "Aluno excluído", excluirAluno, JOptionPane.INFORMATION_MESSAGE, icone);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Aluno informado não foi cadastrado", excluirAluno, JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**
     * Método para mostrar a mensagem de pilha vazia
     *
     * @param titulo - Título que ira aparecer na tela
     */
    private void pilhaVazia(String titulo) {
        JOptionPane.showMessageDialog(null, "Não existem alunos cadastrados!", titulo, JOptionPane.ERROR_MESSAGE);
    }
}