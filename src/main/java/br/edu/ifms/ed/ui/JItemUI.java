package br.edu.ifms.ed.ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class JItemUI {

    private JItemUI() {
    }

    public static TitledBorder titledBorder(String title) {
        LineBorder border = new LineBorder(UAColor.SECONDARY_COLOR, 1, true);
        TitledBorder titledBorder = new TitledBorder(border, title);
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        titledBorder.setTitlePosition(TitledBorder.TOP);
        titledBorder.setTitleFont(UAFont.SECONDARY_FONT);
        return titledBorder;
    }

    public static JButton button(String text, Color colorButton, String imageName, String tooltip) {
        JButton jButton = new JButton(text);

        jButton.setBackground(colorButton);
        jButton.setIcon(getImage(imageName));
        jButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jButton.setToolTipText(tooltip);
        jButton.setFont(UAFont.PRIMARY_FONT);

        return jButton;
    }

    public static ImageIcon getImage(String imageName) {
        return new ImageIcon(JItemUI.class.getResource("/img/" + imageName + ".png"));
    }

    public static void showInfoMesssge(JFrame frame, String message, String title, ImageIcon icon) {
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.INFORMATION_MESSAGE, icon);
    }

    public static void showErrorMesssge(JFrame frame, String message, String title) {
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.ERROR_MESSAGE, UAIcon.ERROR);
    }

    public static void showWarnMesssge(JFrame frame, String message, String title) {
        JOptionPane.showMessageDialog(frame, message, title, JOptionPane.WARNING_MESSAGE, UAIcon.WARN);
    }

}
