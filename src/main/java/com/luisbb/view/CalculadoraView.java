package com.luisbb.view;

import com.luisbb.model.swing.paginas.PaginaCalculadoraPadrao;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CalculadoraView {

    public static void setupCalculadora() {


        JFrame abaCalculadora = new JFrame();
        abaCalculadora.setSize(600, 535);
        abaCalculadora.setTitle("Calculadora");
        abaCalculadora.setFocusable(true);

        PaginaCalculadoraPadrao paginaCalculadora = new PaginaCalculadoraPadrao();
        abaCalculadora.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                System.out.println(e);
                paginaCalculadora.botoesCalculadora.pressionarBotao(String.valueOf(e.getKeyChar()));

            }
        });
        abaCalculadora.add(paginaCalculadora);
        abaCalculadora.setVisible(true);
    }

}
