package com.luisbb.model.swing.bordas;

import javax.swing.border.Border;
import java.awt.*;

public class BordaArredondada implements Border {
    private final int raio;

    public BordaArredondada(int raio) {
        this.raio = raio;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width -1, height - 1, raio, raio);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(raio+1, raio+1, raio+1, raio+1);
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }
}
