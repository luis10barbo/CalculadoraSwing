package com.luisbb.model;

public class ExpressaoMatematica {
    private String expressao = "(5 + 5)";

    public void setExpressao(String expressao) {
        this.expressao = expressao;
    }

    public String getExpressao() {
        return expressao;
    }

    public double calcular() {
        return 0.0;
    }

    public String getExpressaoComResultado() {
        return expressao + "=" + calcular();
    }
}
