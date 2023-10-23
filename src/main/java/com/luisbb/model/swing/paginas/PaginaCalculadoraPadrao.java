package com.luisbb.model.swing.paginas;

import com.luisbb.model.swing.componentes.PainelBotoesCalculadora;
import com.luisbb.model.swing.componentes.DisplayConta;
import com.luisbb.model.swing.cores.CorNeutra;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PaginaCalculadoraPadrao extends JPanel {
    public static final int ESPACO_PEQUENO = 3;
    private final DisplayConta displayConta = new DisplayConta();
    public final PainelBotoesCalculadora botoesCalculadora = new PainelBotoesCalculadora(displayConta);

    public PaginaCalculadoraPadrao() {
        setLayout(new GridLayout(0,1));
        setBackground(new CorNeutra());

        setBorder(new EmptyBorder(ESPACO_PEQUENO, ESPACO_PEQUENO, ESPACO_PEQUENO, ESPACO_PEQUENO));
        add(displayConta);
        add(botoesCalculadora);

    }
}
