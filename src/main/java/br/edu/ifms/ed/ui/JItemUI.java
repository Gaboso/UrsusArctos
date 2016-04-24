package br.edu.ifms.ed.ui;


import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Classe com itens que são muito utilizados na interface gráfica
 */
public class JItemUI {

    private JItemUI() {
    }

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
     * @param text        - Texto a ser exibido no botão
     * @param colorButton - Cor do botao
     * @param imageName   - Nome da image a ser utilizada como icone do botão
     * @param colorText   -  Cor do texto a ser exibido no botão
     * @param tooltip     - Texto a ser exibido quando o mouse estiver sobre o botão
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

    public static void showInfoMesssge(JFrame frame, String message, String title, ImageIcon icon) {
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.INFORMATION_MESSAGE, icon);
    }

    public static void showErrorMesssge(JFrame frame, String message, String title) {
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.ERROR_MESSAGE, Icone.ERROR);
    }

    public static void showWarnMesssge(JFrame frame, String message, String title) {
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.WARNING_MESSAGE, Icone.WARN);
    }

}
