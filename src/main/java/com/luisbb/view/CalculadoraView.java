package com.luisbb.view;

import com.luisbb.model.swing.paginas.PaginaCalculadoraPadrao;

import javax.swing.*;

public class CalculadoraView {

    public static void setupCalculadora() {


        JFrame abaCalculadora = new JFrame();
        abaCalculadora.setSize(600, 535);
        abaCalculadora.setTitle("Calculadora");


        abaCalculadora.add(new PaginaCalculadoraPadrao());
        abaCalculadora.setVisible(true);
    }

}
