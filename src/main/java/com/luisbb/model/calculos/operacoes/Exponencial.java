package com.luisbb.model.calculos.operacoes;

import com.luisbb.model.calculos.ArgumentosCalculo;

public class Exponencial implements OperacaoMatematica {
    @Override
    public double calcular(String expressao) {
        ArgumentosCalculo argumentosCalculo = parseArgumentos(expressao);
        return Math.pow(argumentosCalculo.a, argumentosCalculo.b);
    }

    @Override
    public String operador() {
        return "^";
    }
}
