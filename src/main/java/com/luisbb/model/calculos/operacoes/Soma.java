package com.luisbb.model.calculos.operacoes;

import com.luisbb.model.calculos.ArgumentosCalculo;

public class Soma implements OperacaoMatematica {
    @Override
    public double calcular(String expressao) {
        ArgumentosCalculo argumentos = parseArgumentos(expressao);
        return argumentos.a + argumentos.b;
    }

    @Override
    public String operador() {
        return "+";
    }
}
