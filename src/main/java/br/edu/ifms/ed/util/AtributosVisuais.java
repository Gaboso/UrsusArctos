package br.edu.ifms.ed.util;

import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Classe que contem os atributos e textos usados para formatar as telas
 */
public interface AtributosVisuais {

    // Mensagens
    String cadastroAluno = "Cadastro do aluno";
    String cadastroNotas = "Cadastro de notas";
    String codigoAluno = "Código do aluno";
    String calcularMedia = "Calcular media";
    String excluirAluno = "Excluir Alunos";

    // Caminhos
    String caminhoImagem = "/img/";
    String caminhoLogo = caminhoImagem + "logo.png";

    // Fontes
    Font tahomaNegritoTreze = new Font("Tahoma", Font.BOLD, 13);
    Font tahomaNegritoVinteDois = new Font("Tahoma", Font.BOLD, 22);
    Font consolasPlainQuize = new Font("Consolas", Font.PLAIN, 15);

    // Cores
    Color preto = new Color(0, 0, 0);
    Color verdeEscuro = new Color(27, 94, 32);
    Color verdeClaro = new Color(76, 175, 80);
    Color brancoUm = new Color(250, 250, 250);
    Color brancoDois = new Color(245, 245, 245);
    Color verdeAmarelado = new Color(0, 137, 123);
    Color amarelo = new Color(255, 235, 59);
    Color laranja = new Color(255, 183, 77);
    Color vermelho = new Color(213, 0, 0);
    Color roxo = new Color(103, 58, 183);
    Color azul = new Color(3, 169, 244);

    // Bordas
    LineBorder borda = new LineBorder(verdeClaro, 1, true);
}
