package br.edu.ifms.ed.ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

class JItemUITest {


    @Test
    @DisplayName("Validate titled border")
    void titledBorder() {
        TitledBorder titledBorder = JItemUI.titledBorder("Test Border");
        Assertions.assertEquals(UAFont.SECONDARY_FONT, titledBorder.getTitleFont());
        Assertions.assertEquals(TitledBorder.TOP, titledBorder.getTitlePosition());
        Assertions.assertEquals(TitledBorder.CENTER, titledBorder.getTitleJustification());
        Assertions.assertEquals("Test Border", titledBorder.getTitle());
        Assertions.assertEquals(Color.GRAY, ((LineBorder) titledBorder.getBorder()).getLineColor());
    }

    @Test
    @DisplayName("Validate button")
    void button() {
        JButton button = JItemUI.button("Add Grade", "test", "add grade to student");
        Assertions.assertEquals(UAFont.PRIMARY_FONT, button.getFont());
        Assertions.assertEquals(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR), button.getCursor());
        Assertions.assertEquals("Add Grade", button.getText());
        Assertions.assertEquals("add grade to student", button.getToolTipText());
    }

}