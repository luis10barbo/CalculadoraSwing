package com.luisbb.view;

import com.luisbb.model.componentes.BotaoOperacaoCalculadora;
import com.luisbb.model.componentes.DisplayConta;
import com.luisbb.model.cores.CorNeutra;
import com.luisbb.model.paginas.PaginaCalculadoraPadrao;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CalculadoraView {

    public static void setupCalculadora() {


        JFrame abaCalculadora = new JFrame();
        abaCalculadora.setSize(600, 535);
        abaCalculadora.setTitle("Calculadora");


        abaCalculadora.add(new PaginaCalculadoraPadrao());
        abaCalculadora.setVisible(true);
    }

}
