package com.luisbb.model.swing.paginas;

import com.luisbb.model.swing.componentes.BotaoOperacaoCalculadora;
import com.luisbb.model.swing.componentes.DisplayConta;
import com.luisbb.model.swing.cores.CorNeutra;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PaginaCalculadoraPadrao extends JPanel {
    static final int ESPACO_PEQUENO = 3;
    private final DisplayConta displayConta = new DisplayConta();

    public PaginaCalculadoraPadrao() {
        setLayout(new GridLayout(0,1));
        setBackground(new CorNeutra());

        setBorder(new EmptyBorder(ESPACO_PEQUENO, ESPACO_PEQUENO, ESPACO_PEQUENO, ESPACO_PEQUENO));
        add(displayConta);
        add(criarBotoesCalculadora());

    }

    public JPanel criarBotoesCalculadora() {
        JPanel frameBotoesCalculadora = new JPanel();
        frameBotoesCalculadora.setOpaque(false);

        GridLayout layout = new GridLayout(0, 4);
        layout.setHgap(ESPACO_PEQUENO);
        layout.setVgap(ESPACO_PEQUENO);

        frameBotoesCalculadora.setLayout(layout);

//        for (int i = 0; i < 5; i++) {
//            JButton botaoNumeroUm = new BotaoOperacaoCalculadora("1", e -> {
//            });
//            frameBotoesCalculadora.add(botaoNumeroUm);
//        }
        frameBotoesCalculadora.add(new BotaoOperacaoCalculadora("(", displayConta));
        frameBotoesCalculadora.add(new BotaoOperacaoCalculadora(")", displayConta));
        frameBotoesCalculadora.add(new BotaoOperacaoCalculadora("C", e -> {displayConta.limpar();}));
        frameBotoesCalculadora.add(new BotaoOperacaoCalculadora("DEL", e -> {
            displayConta.apagarCaractere();
        }));

        frameBotoesCalculadora.add(new BotaoOperacaoCalculadora("1/x", e -> {}));
        frameBotoesCalculadora.add(new BotaoOperacaoCalculadora("x^2", e -> {}));
        frameBotoesCalculadora.add(new BotaoOperacaoCalculadora("x^(1/2)", e -> {}));
        frameBotoesCalculadora.add(new BotaoOperacaoCalculadora("/", displayConta));

        frameBotoesCalculadora.add(new BotaoOperacaoCalculadora("7", displayConta));
        frameBotoesCalculadora.add(new BotaoOperacaoCalculadora("8", displayConta));
        frameBotoesCalculadora.add(new BotaoOperacaoCalculadora("9", displayConta));
        frameBotoesCalculadora.add(new BotaoOperacaoCalculadora("*", displayConta));

        frameBotoesCalculadora.add(new BotaoOperacaoCalculadora("4", displayConta));
        frameBotoesCalculadora.add(new BotaoOperacaoCalculadora("5", displayConta));
        frameBotoesCalculadora.add(new BotaoOperacaoCalculadora("6", displayConta));
        frameBotoesCalculadora.add(new BotaoOperacaoCalculadora("-", displayConta));

        frameBotoesCalculadora.add(new BotaoOperacaoCalculadora("1", displayConta));
        frameBotoesCalculadora.add(new BotaoOperacaoCalculadora("2", displayConta));
        frameBotoesCalculadora.add(new BotaoOperacaoCalculadora("3", displayConta));
        frameBotoesCalculadora.add(new BotaoOperacaoCalculadora("+", displayConta));

        frameBotoesCalculadora.add(new BotaoOperacaoCalculadora("%", displayConta));
        frameBotoesCalculadora.add(new BotaoOperacaoCalculadora("0", displayConta));
        frameBotoesCalculadora.add(new BotaoOperacaoCalculadora(".", displayConta));
        frameBotoesCalculadora.add(new BotaoOperacaoCalculadora("=", e -> {
            displayConta.gerarCalculo();
        }));

        return frameBotoesCalculadora;
    }
}
