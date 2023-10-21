package com.luisbb.model.swing.componentes;

import com.luisbb.model.swing.cores.CorNeutraClaraP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BotaoOperacaoCalculadora extends JButton {
    private static final int TAMANHO_FONTE = 20;
    private static final int ESTILO_FONTE = Font.BOLD;

    public interface EventoClique {
        void aoClicar(MouseEvent e);
    }

    private void setupBotao(String texto) {
        setText(texto);
        setBorderPainted(false);
        setFocusPainted(false);

        setBackground(new CorNeutraClaraP());
        setForeground(Color.WHITE);
    }

    public BotaoOperacaoCalculadora(String texto, DisplayConta displayConta) {
        setupBotao(texto);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                displayConta.setTexto(textoAnterior -> textoAnterior + texto);
            }
        });

        setFont(new Font("Segoe UI", ESTILO_FONTE, TAMANHO_FONTE));

    }

    public BotaoOperacaoCalculadora(String texto, EventoClique eventoClique) {
        setupBotao(texto);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                eventoClique.aoClicar(e);
            }
        });

        setFont(new Font("Segoe UI", ESTILO_FONTE, TAMANHO_FONTE));

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        super.paintComponent(g2d);
        g2d.dispose();
    }
}
