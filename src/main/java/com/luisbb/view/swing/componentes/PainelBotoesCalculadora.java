package com.luisbb.view.swing.componentes;

import com.luisbb.view.swing.eventos.EventoEscreverCalculadora;
import com.luisbb.view.swing.paginas.PaginaCalculadoraPadrao;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PainelBotoesCalculadora extends JPanel {
    HashMap<String, BotaoOperacaoCalculadora> botoes = new HashMap<>();
    public PainelBotoesCalculadora(DisplayConta displayConta) {
        setOpaque(false);

        GridLayout layout = new GridLayout(0, 4);
        layout.setHgap(PaginaCalculadoraPadrao.ESPACO_PEQUENO);
        layout.setVgap(PaginaCalculadoraPadrao.ESPACO_PEQUENO);

        setLayout(layout);
        setupBotoes(displayConta);
        setFocusable(true);
        addKeyListener(new EventoEscreverCalculadora(this));
    }

    public void setupBotoes(DisplayConta displayConta) {
        adicionarBotao("(", displayConta);
        adicionarBotao(")", displayConta);
        adicionarBotao("C", e -> {
            displayConta.limpar();}, new String[] {"c"});
        adicionarBotao("DEL", e -> {
            displayConta.apagarCaractere();
        }, new String[]{"DEL"});

        adicionarBotao("", e -> {});
        adicionarBotao("", e -> {});
        adicionarBotao("^", displayConta);
        adicionarBotao("/", displayConta);

        adicionarBotao("7", displayConta);
        adicionarBotao("8", displayConta);
        adicionarBotao("9", displayConta);
        adicionarBotao("*", displayConta);

        adicionarBotao("4", displayConta);
        adicionarBotao("5", displayConta);
        adicionarBotao("6", displayConta);
        adicionarBotao("-", displayConta);

        adicionarBotao("1", displayConta);
        adicionarBotao("2", displayConta);
        adicionarBotao("3", displayConta);
        adicionarBotao("+", displayConta);

        adicionarBotao("%", displayConta);
        adicionarBotao("0", displayConta);
        adicionarBotao(".", displayConta);
        adicionarBotao("=", e -> {
            displayConta.gerarCalculo();
        });
    }

    private void adicionarBotao(String texto, DisplayConta displayConta) {
        BotaoOperacaoCalculadora botao = new BotaoOperacaoCalculadora(texto, displayConta);
        add(botao);
        botoes.put(texto, botao);
    }

    private void adicionarBotao(String texto, BotaoOperacaoCalculadora.EventoClique eventoClique) {
        BotaoOperacaoCalculadora botao = new BotaoOperacaoCalculadora(texto, eventoClique);
        add(botao);
        botoes.put(texto, botao);
    }

    private void adicionarBotao(String texto, BotaoOperacaoCalculadora.EventoClique eventoClique, String[] aliases) {
        BotaoOperacaoCalculadora botao = new BotaoOperacaoCalculadora(texto, eventoClique);
        add(botao);
        for (String alias: aliases) botoes.put(alias, botao);
    }

    public void pressionarBotao(String textoBotao) {
        BotaoOperacaoCalculadora botao = botoes.get(textoBotao);
        if (botao != null) {
            botao.fazerClique(null);
        }
    }
}
