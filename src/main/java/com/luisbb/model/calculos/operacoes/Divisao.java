package com.luisbb.model.calculos.operacoes;

import com.luisbb.model.calculos.ArgumentosCalculo;

public class Divisao implements OperacaoMatematica {
    @Override
    public double calcular(String expressao) {
        ArgumentosCalculo argumentosCalculo = parseArgumentos(expressao);
        return argumentosCalculo.a / argumentosCalculo.b;
    }

    @Override
    public String operador() {
        return "/";
    }
}
