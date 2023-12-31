package com.luisbb.view.swing.componentes;

import com.luisbb.controller.ExpressaoMatematicaController;
import com.luisbb.model.ExpressaoMatematica;

import javax.swing.*;
import java.awt.*;

public class DisplayConta extends JPanel {
    private final ExpressaoMatematicaController expressaoMatematica = new ExpressaoMatematicaController();
    private final JLabel labelConta = new JLabel();
    private final JLabel labelHistorico = new JLabel();

    public DisplayConta() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        labelConta.setForeground(Color.WHITE);
        labelConta.setFont(new Font("Sergoe UI", Font.BOLD, 32));
        add(labelConta);

        labelHistorico.setForeground(Color.WHITE);
        labelConta.setFont(new Font("Sergoe UI", Font.BOLD, 32));
        add(labelHistorico);

        setOpaque(false);
        setTexto(expressaoMatematica.getExpressao(), false);
    }
    public interface TrocarTextoRunnable {
        String trocarTexto(String textoAnterior);
    }
    public void setTexto(TrocarTextoRunnable trocarTextoRunnable) {
        setTexto(trocarTextoRunnable.trocarTexto(expressaoMatematica.getExpressao()), true);
    }

    public void setTexto(String texto, boolean atualizarExpressao) {
        if (atualizarExpressao) this.expressaoMatematica.setExpressao(texto);
        if (texto.length() > 0) labelConta.setText(texto);
        else labelConta.setText("0");

    }

    public void limpar() {
        setTexto("", true);
    }

    public void apagarCaractere() {
        setTexto(textoAnterior -> {
            int len = textoAnterior.length();

            if (len < 1) return "";
            return textoAnterior.substring(0, len - 1);
        });
    }

    public void gerarCalculo() {
        setTexto(expressaoMatematica.getExpressaoComResultado(), false);

        String[] operacoesCalculo = expressaoMatematica.getOperacoesCalculo();
        StringBuilder texto = new StringBuilder();
        for (int i = 1; i < operacoesCalculo.length; i++) {
            if (i != 1) texto.append("<br>");
            texto.append(i).append(". ").append(operacoesCalculo[i]);
        }

        labelHistorico.setText("<html>" + texto);
    }
}
