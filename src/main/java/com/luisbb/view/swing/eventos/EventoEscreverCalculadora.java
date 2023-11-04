package com.luisbb.view.swing.eventos;

import com.luisbb.view.swing.componentes.PainelBotoesCalculadora;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.event.KeyEvent.*;

public class EventoEscreverCalculadora implements KeyListener {
    private final PainelBotoesCalculadora painelBotoesCalculadora;

    public EventoEscreverCalculadora(PainelBotoesCalculadora painelBotoesCalculadora) {
        this.painelBotoesCalculadora = painelBotoesCalculadora;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char charPressionado = e.getKeyChar();
        String outroPressionado = null;

        switch (e.getKeyChar())  {
            case VK_BACK_SPACE:
            case VK_DELETE:
                outroPressionado = "DEL";
                break;
            case VK_ENTER:
                outroPressionado = "=";
                break;
        }

        painelBotoesCalculadora.pressionarBotao(outroPressionado != null ? outroPressionado : String.valueOf(charPressionado));

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
