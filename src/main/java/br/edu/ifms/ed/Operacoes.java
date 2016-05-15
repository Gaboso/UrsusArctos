package br.edu.ifms.ed;

import br.edu.ifms.ed.constant.Textual;
import br.edu.ifms.ed.model.Aluno;
import br.edu.ifms.ed.ui.Icone;
import br.edu.ifms.ed.ui.JItemUI;

import javax.swing.*;
import java.util.Objects;
import java.util.Stack;

public class Operacoes {

    private static Stack<Aluno> pilha = new Stack<>();

    /**
     * Método para fazer o cadastro da nota do aluno
     */
    public void cadastroNota(JFrame frame) {
        int tamanho;
        int contador;
        String codigo;

        if (pilha.empty())
            JItemUI.showErrorMesssge(frame, Textual.SEM_ALUNOS_CADASTRADOS, Textual.CADASTRO_DE_NOTAS);
        else {
            contador = 0;
            codigo = pegaCodigoAluno(frame);
            if (codigo != null) {
                tamanho = pilha.size() - 1;
                while (tamanho != -1) {
                    if (pilha.get(tamanho).getCodigo().equals(codigo)) {
                        cadastrarNota(pilha.get(tamanho), frame);
                        contador++;
                    }
                    tamanho--;
                }
                if (contador == 0) {
                    JItemUI.showWarnMesssge(frame, Textual.ALUNO_NAO_ENCONTRADO, Textual.CADASTRO_DE_NOTAS);
                }
            }
        }
    }

    /**
     * Método para receber as notas do usuário
     *
     * @param aluno - Objeto de aluno
     * @return Retorna a nota do aluno
     */
    private Aluno cadastrarNota(Aluno aluno, JFrame frame) {
        boolean ok = false;
        ImageIcon icone = Icone.CADASTRAR_NOTA;

        do {
            String codigo = (String) JOptionPane.showInputDialog(frame, Textual.INSIRA_NOTA, Textual.CADASTRO_DE_NOTAS,
                    JOptionPane.PLAIN_MESSAGE, icone, null, null);
            if (codigo != null) {
                try {
                    codigo = codigo.replaceAll(",", ".");
                    double numeroConvertido = Double.parseDouble(codigo);
                    ok = true;
                    JItemUI.showInfoMesssge(frame, Textual.NOTA_CADASTRADA, Textual.CADASTRO_DE_NOTAS, icone);
                    aluno.setNota(numeroConvertido);
                } catch (Exception e) {
                    JItemUI.showErrorMesssge(frame, Textual.NOTA_INVALIDA, Textual.CADASTRO_DE_NOTAS);
                }
            } else {
                ok = true;
                aluno.setNota(null);
            }
        } while (!ok);


        return aluno;
    }

    /**
     * Método para receber do usuário o código do aluno
     *
     * @return código do aluno
     */
    private String pegaCodigoAluno(JFrame frame) {
        String codigo = null;
        boolean valido = false;

        while (!valido) {
            codigo = (String) JOptionPane.showInputDialog(frame, Textual.DIGITE_CODIGO_ALUNO, Textual.CODIGO_DO_ALUNO,
                    JOptionPane.PLAIN_MESSAGE, Icone.CADASTRAR_ALUNO, null, null);
            if (codigo != null && codigo.isEmpty()) {
                valido = false;
                JItemUI.showErrorMesssge(frame, Textual.CODIGO_INVALIDO, Textual.CODIGO_DO_ALUNO);
            } else {
                valido = true;
            }
        }

        return codigo;
    }

    /**
     * Método para cadastrar os alunos
     *
     * @param aluno - Objeto de aluno
     * @return retorna o código do aluno igual a uma matricula
     */
    private Aluno cadastrarAluno(Aluno aluno, JFrame frame) {
        String codigo = pegaCodigoAluno(frame);
        aluno.setCodigo(codigo);
        return aluno;
    }

    /**
     * Método para fazer o cadastro do aluno
     */
    public void cadastrandoAluno(JFrame frame) {
        Aluno aluno = new Aluno();
        aluno = cadastrarAluno(aluno, frame);

        if (aluno.getCodigo() != null) {
            boolean erro = false;
            int aux = pilha.size() - 1;
            while (aux != -1) {
                if (pilha.get(aux).getCodigo().equals(aluno.getCodigo())) {
                    erro = true;
                    break;
                }
                aux--;
            }

            if (erro) {
                JItemUI.showErrorMesssge(frame, Textual.CODIGO_JA_EXISTENTE, Textual.CADASTRO_DO_ALUNO);
            } else {
                // Atribui o valor null pois somente o aluno foi cadastrado e a nota ainda não.
                aluno.setNota(null);
                pilha.push(aluno);
                JItemUI.showInfoMesssge(frame, Textual.ALUNO_CADASTRADO, Textual.CADASTRO_DO_ALUNO, Icone.CADASTRAR_ALUNO);
            }
        }
    }

    /**
     * Método para fazer o calculo da media dos alunos
     */
    public void calcularMedia(JFrame frame) {
        int tamanho;
        int cont;
        double media;

        if (pilha.empty())
            JItemUI.showErrorMesssge(frame, Textual.SEM_ALUNOS_CADASTRADOS, Textual.CALCULAR_MEDIA);
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

            ImageIcon icone = Icone.CALCULAR_MEDIA;

            if (cont != 0) {
                media /= cont;
                if (cont == 1) {
                    JItemUI.showInfoMesssge(frame, Textual.MEDIA_ALUNO + media, Textual.CALCULAR_MEDIA, icone);
                } else {
                    JItemUI.showInfoMesssge(frame, Textual.MEDIA_ALUNOS + media, Textual.CALCULAR_MEDIA, icone);
                }
            } else {
                JItemUI.showErrorMesssge(frame, Textual.SEM_NOTAS_CADASTRADAS, Textual.CALCULAR_MEDIA);
            }
        }
    }

    /**
     * Método para exibir o aluno
     */
    public void exibirAlunos(JFrame frame) {
        int tamanho;

        if (pilha.empty())
            JItemUI.showErrorMesssge(frame, Textual.SEM_ALUNOS_CADASTRADOS, Textual.EXIBIR_ALUNOS);
        else {
            String texto = "";
            tamanho = pilha.size() - 1;
            while (tamanho != -1) {
                texto += (Textual.CODIGO + pilha.get(tamanho).getCodigo() + " ");
                if (pilha.get(tamanho).getNota() != null)
                    texto += (Textual.NOTA + pilha.get(tamanho).getNota() + "\r\n");
                else
                    texto += Textual.SEM_NOTA;
                tamanho--;
            }

            JItemUI.showInfoMesssge(frame, texto, Textual.EXIBIR_ALUNOS, Icone.EXIBIR_ALUNO);
        }
    }

    public void consultaAluno(JFrame frame) {
        int tamanho;
        int contador;
        String codigo;

        if (pilha.empty())
            JItemUI.showErrorMesssge(frame, Textual.SEM_ALUNOS_CADASTRADOS, Textual.CONSULTA_DE_ALUNOS);
        else {
            tamanho = pilha.size() - 1;
            codigo = pegaCodigoAluno(frame);
            if (codigo != null) {
                contador = 0;

                ImageIcon icone = Icone.PROCURAR_ALUNO;

                while (tamanho != -1) {
                    if (pilha.get(tamanho).getCodigo().equals(codigo)) {
                        contador++;
                        String texto = Textual.O_ALUNO + pilha.get(tamanho).getCodigo() + Textual.ESTA_CADASTRADO;
                        JItemUI.showInfoMesssge(frame, texto, Textual.CONSULTA_DE_ALUNOS, icone);
                    }
                    tamanho--;
                }
                if (contador == 0) {
                    JItemUI.showErrorMesssge(frame, Textual.ALUNO_NAO_ENCONTRADO, Textual.CONSULTA_DE_ALUNOS);
                }
            }

        }
    }

    /**
     * Método para excluir os alunos
     */
    public void excluirAluno(JFrame frame) {
        String codigo;

        if (pilha.empty())
            JItemUI.showErrorMesssge(frame, Textual.SEM_ALUNOS_CADASTRADOS, Textual.EXCLUIR_ALUNO);
        else {
            int tamanho = pilha.size() - 1;
            // Código do aluno que o usuário quer remover
            codigo = pegaCodigoAluno(frame);

            if (codigo != null) {
                int cont = 0;
                // Verifica se o código foi cadastrado
                while (tamanho != -1) {
                    if (codigo.equals(pilha.get(tamanho).getCodigo()))
                        cont++;
                    tamanho--;
                }

                if (cont > 0) {
                    while (!Objects.equals(pilha.peek().getCodigo(), codigo)
                            || pilha.peek().getCodigo().equals(codigo)) {
                        if (pilha.peek().getCodigo().equals(codigo)) {
                            pilha.pop();
                            break;
                        }
                        pilha.pop();
                    }
                    JItemUI.showInfoMesssge(frame, Textual.ALUNO_EXCLUIDO, Textual.EXCLUIR_ALUNO, Icone.EXCLUIR_ALUNO);
                } else {
                    JItemUI.showWarnMesssge(frame, Textual.ALUNO_NAO_ENCONTRADO, Textual.EXCLUIR_ALUNO);
                }
            }
        }
    }
}