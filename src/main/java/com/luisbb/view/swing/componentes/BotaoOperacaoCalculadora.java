package com.luisbb.view.swing.componentes;

import com.luisbb.view.swing.cores.CorNeutraClaraP;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BotaoOperacaoCalculadora extends JButton {
    private static final int TAMANHO_FONTE = 20;
    private static final int ESTILO_FONTE = Font.BOLD;
    private EventoClique eventoClique;
    private DisplayConta displayConta;
    private String texto;

    public interface EventoClique {
        void aoClicar(MouseEvent e);
    }

    public BotaoOperacaoCalculadora(String texto, DisplayConta displayConta) {
        this.texto = texto;
        this.displayConta = displayConta;

        setupBotao();
    }

    public BotaoOperacaoCalculadora(String texto, EventoClique eventoClique) {
        this.texto = texto;
        this.eventoClique = eventoClique;

        setupBotao();
    }

    private void setupBotao() {
        setText(texto);
        setBorderPainted(false);
        setFocusPainted(false);

        setBackground(new CorNeutraClaraP());
        setForeground(Color.WHITE);
        setFont(new Font("Segoe UI", ESTILO_FONTE, TAMANHO_FONTE));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                fazerClique(e);
            }
        });
    }

    public void fazerClique(@Nullable MouseEvent e) {
        if (displayConta != null) {
            displayConta.setTexto(textoAnterior -> textoAnterior + texto);
        } else if (eventoClique != null) {
            eventoClique.aoClicar(e);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        super.paintComponent(g2d);
        g2d.dispose();
    }
}
