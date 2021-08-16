package br.edu.ifms.ed.ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UAFontTest {

    @Test
    @DisplayName("Validate primary font")
    void primaryFont() {
        Assertions.assertEquals("Arial", UAFont.PRIMARY_FONT.getFontName());
        Assertions.assertEquals(12, UAFont.PRIMARY_FONT.getSize());
        Assertions.assertTrue(UAFont.PRIMARY_FONT.isPlain());
    }

    @Test
    @DisplayName("Validate secondary font")
    void secondaryFont() {
        Assertions.assertEquals("Arial Bold", UAFont.SECONDARY_FONT.getFontName());
        Assertions.assertEquals(13, UAFont.SECONDARY_FONT.getSize());
        Assertions.assertTrue(UAFont.SECONDARY_FONT.isBold());
    }

}