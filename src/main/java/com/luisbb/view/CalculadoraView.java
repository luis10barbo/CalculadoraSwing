package com.luisbb.view;

import com.luisbb.view.swing.eventos.EventoEscreverCalculadora;
import com.luisbb.view.swing.paginas.PaginaCalculadoraPadrao;

import javax.swing.*;

public class CalculadoraView {

    public static void setupCalculadora() {


        JFrame abaCalculadora = new JFrame();
        abaCalculadora.setSize(600, 535);
        abaCalculadora.setTitle("Calculadora");
        abaCalculadora.setFocusable(true);

        PaginaCalculadoraPadrao paginaCalculadora = new PaginaCalculadoraPadrao();
        abaCalculadora.addKeyListener(new EventoEscreverCalculadora(paginaCalculadora.botoesCalculadora));
        abaCalculadora.add(paginaCalculadora);
        abaCalculadora.setVisible(true);
    }

}
