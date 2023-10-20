package com.luisbb.model.calculos.operacoes;

import com.luisbb.model.calculos.ArgumentosCalculo;
import com.luisbb.utils.UtilsNumero;

public interface OperacaoMatematica {
    double calcular(String expressao);
    String operador();
    default ArgumentosCalculo parseArgumentos(String expressao) {
        String operador = operador();

        StringBuilder a = new StringBuilder();
        StringBuilder b = new StringBuilder();
        boolean primeiroArgumento = true;
        for (int i = 0; i < expressao.length(); i++) {
            String stringLenOperador = expressao.substring(i, i + operador.length());
            char charAtual = expressao.charAt(i);

            if (primeiroArgumento) {
                if (stringLenOperador.equals(operador)) {
                    primeiroArgumento = false;
                    continue;
                }


                a.append(charAtual);
                continue;
            }
            if (!UtilsNumero.eNumero(charAtual)) break;
            b.append(charAtual);
        }
        try {
            return new ArgumentosCalculo(Double.parseDouble(a.toString()), Double.parseDouble(b.toString()));
        } catch(NumberFormatException e) {
            System.out.println(a + "  " +  b);
            return null;
        }
    }


    static OperacaoMatematica getOperacaoMD(String operador) {
        switch (operador) {
            case "*": return new Multiplicacao();
            case "/": return new Divisao();
            default: return null;
        }
    }

    static OperacaoMatematica getOperacaoAS(String operador) {
        // ADICAO E SUBTRACAO
        switch (operador) {
            case "+": return new Soma();
            case "-": return new Subtracao();
            default: return null;
        }
    }
}
