package com.luisbb.model;

import com.luisbb.model.calculos.operacoes.OperacaoMatematica;
import com.luisbb.utils.UtilsNumero;

import java.util.ArrayList;
import java.util.HashMap;

public class ExpressaoMatematica {
    private String expressao = "(50 + 5) * 2";

    public void setExpressao(String expressao) {
        this.expressao = expressao.replace(" ", "");
    }

    public String getExpressao() {
        return expressao;
    }

    public Double calcular() {
        return calculoPEMDAS(expressao);
    }

    public Double calculoPEMDAS(String expressao) {
        String novaExpressao = expressao.replace(" ", "");
        System.out.println("Calculando " + novaExpressao);
        Double res = null;

        while (novaExpressao.charAt(0) == '(' && novaExpressao.charAt(novaExpressao.length() - 1) == ')') {
            if (novaExpressao.length() == 2) novaExpressao = "";
            else novaExpressao = novaExpressao.substring(1, novaExpressao.length() - 1);
        }


        String[] parenteses = parseParenteses(novaExpressao);
        for (String parentese: parenteses
             ) {
            novaExpressao = novaExpressao.replace(parentese, arredondarSePossivel(calculoPEMDAS(parentese)));
        }

        novaExpressao = fazerOperacoes(novaExpressao, OperacaoMatematica::getOperacaoMD);
        novaExpressao = fazerOperacoes(novaExpressao, OperacaoMatematica::getOperacaoAS);

        try {
            res = Double.parseDouble(novaExpressao);
        } catch (NumberFormatException e) {
            System.out.println("expressao incompleta: " + novaExpressao);
        }

        return res;
    }

    public interface adquirirOperacao {
        OperacaoMatematica adquirirOperacao(String operador);
    }
    public String fazerOperacoes(String expressao, adquirirOperacao adquirirOperacao) {
        String novaExpressao = expressao;

        // Resolver operacoes em loop
        StringBuilder expressaoAtual = new StringBuilder();
        OperacaoMatematica operacaoMatematicaAtual = null;
        boolean calcular = false;
        boolean resetar = false;
        for (int i = 0; i < novaExpressao.length(); i++) {
            char charAtual = novaExpressao.charAt(i);
            boolean charAtualENumero = UtilsNumero.eNumero(charAtual);

            // Adquire operacao matematica se nao checa se o char atual é numero
            if (operacaoMatematicaAtual == null) {
                operacaoMatematicaAtual = adquirirOperacao.adquirirOperacao(String.valueOf(charAtual));
                if (operacaoMatematicaAtual == null && !UtilsNumero.eNumero(charAtual)) {
                    resetar = true;
                }
            } else if (!charAtualENumero || i == novaExpressao.length() - 1) {
                // Se nao for numero e ja tiver uma operacao selecionada ou for ultimo caractere, calcular
                calcular = true;
                if (charAtualENumero) {
                    expressaoAtual.append(charAtual);
                }
            }

            if (!calcular) {
                expressaoAtual.append(charAtual);
            } else {
                double resultadoOperacaoAtual = operacaoMatematicaAtual.calcular(expressaoAtual.toString());
                String resultadoOperacaoAtualString = arredondarSePossivel(resultadoOperacaoAtual);
                String expressaoReplace = novaExpressao.replace(expressaoAtual, resultadoOperacaoAtualString);
                System.out.println("Novo calculo realizado:");
                System.out.println("Calculando expressao atual -> " + expressaoAtual + " RESULTADO -> " + resultadoOperacaoAtual);
                System.out.println("Expressao antiga -> " + novaExpressao + " Expressao calculada -> " + expressaoReplace);
                novaExpressao = expressaoReplace;
                resetar = true;
                i = -1;
            }

            if (resetar) {
                // reset
                expressaoAtual = new StringBuilder();
                operacaoMatematicaAtual = null;
                calcular = false;
                resetar = false;
            }
        }
        System.out.println("Entrada -> " + expressao + " // Saida -> " + novaExpressao);
        return novaExpressao;
    }

    public String arredondarSePossivel(double valor) {
        return valor % 1 == 0 ? String.valueOf((int) valor) : String.valueOf(valor);
    }

    public String[] parseParenteses(String expressao) {
        int level = 0;
        StringBuilder parentesesAtual = new StringBuilder();
        ArrayList<String> parenteses = new ArrayList<>();

        for (int i = 0; i < expressao.length(); i++) {
            char charAtual = expressao.charAt(i);
            if (charAtual == '(') {
                level ++;

            }
            if (level > 0) parentesesAtual.append(charAtual);
            if (charAtual == ')') {
                level --;
                if (level == 0) {
                    parenteses.add(parentesesAtual.toString());
                    parentesesAtual = new StringBuilder();
                };
            }
        }
        return parenteses.toArray(new String[0]);
    }

    public String getExpressaoComResultado() {
        Double resultado = calcular();
        return expressao + "=" + (resultado != null ? resultado : "?") ;
    }
}
