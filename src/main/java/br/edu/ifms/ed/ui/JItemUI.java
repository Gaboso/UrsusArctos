package br.edu.ifms.ed.ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Classe com itens que são muito utilizados na interface gráfica
 */
public class JItemUI {

    /**
     * Construtor privado
     */
    private JItemUI() {
    }

    /**
     * Método para construir uma borda com título
     *
     * @param title - título que deve aparecer junto com a borda
     * @return retorna a borda com o título e formatação
     */
    public static TitledBorder titledBorder(String title) {
        LineBorder border = new LineBorder(Cor.SECONDARY_COLOR, 1, true);
        TitledBorder titledBorder = new TitledBorder(border, title);
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setTitleFont(Fonte.SECONDARY_FONT);
        return titledBorder;
    }

    /**
     * Método para criar JButtons
     *
     * @param text        - texto a ser exibido no botão
     * @param colorButton - cor do botão
     * @param imageName   - nome da imagem a ser utilizada como ícone do botão
     * @param colorText   - cor do texto a ser exibido no botão
     * @param tooltip     - texto a ser exibido quando o mouse estiver sobre o botão
     * @return JButton
     */
    public static JButton button(String text, Color colorButton, String imageName, Color colorText, String tooltip) {
        JButton jButton = new JButton(text);

        jButton.setBackground(colorButton);
        jButton.setForeground(colorText);
        jButton.setIcon(imageIcon(imageName));
        jButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jButton.setToolTipText(tooltip);
        jButton.setFont(Fonte.PRIMARY_FONT);

        return jButton;
    }

    /**
     * Método para pegar uma imagem
     *
     * @param imageName - Nome da imagem
     * @return retorna uma imagem
     */
    public static ImageIcon imageIcon(String imageName) {
        return new ImageIcon(JItemUI.class.getResource("/img/" + imageName + ".png"));
    }

    /**
     * Método para mostrar uma mensagem do tipo informativo
     *
     * @param frame   - frame pai da mensagem que será exibida
     * @param message - mensagem a ser exibida
     * @param title   - título da mensagem
     * @param icon    - ícone que será exibido com a mensagem
     */
    public static void showInfoMesssge(JFrame frame, String message, String title, ImageIcon icon) {
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.INFORMATION_MESSAGE, icon);
    }

    /**
     * Método para mostrar uma mensagem do tipo erro
     *
     * @param frame   - frame pai da mensagem que será exibida
     * @param message - mensagem a ser exibida
     * @param title   - título da mensagem
     */
    public static void showErrorMesssge(JFrame frame, String message, String title) {
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.ERROR_MESSAGE, Icone.ERROR);
    }

    /**
     * Método para mostrar uma mensagem do warning
     *
     * @param frame   - frame pai da mensagem que será exibida
     * @param message - mensagem a ser exibida
     * @param title   - título da mensagem
     */
    public static void showWarnMesssge(JFrame frame, String message, String title) {
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.WARNING_MESSAGE, Icone.WARN);
    }

}