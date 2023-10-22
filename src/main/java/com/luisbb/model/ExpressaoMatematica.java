package com.luisbb.model;

import com.luisbb.model.calculos.operacoes.OperacaoMatematica;
import com.luisbb.utils.UtilsNumero;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class ExpressaoMatematica {
    private String expressao = "";
    private ArrayList<String> operacoesCalculo = new ArrayList<>();
    private Double resultado = null;

    public ExpressaoMatematica() {}

    public ExpressaoMatematica(String expressao) {
        this.expressao = expressao;
    }

    public void setExpressao(String expressao) {
        this.expressao = expressao.replace(" ", "");
    }

    public String getExpressao() {
        return expressao;
    }

    public String getExpressaoComResultado() {
        return expressao + "=" + (resultado != null ? resultado : "?") ;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }

    /**
     * @return Historico de operacoes para gerar resultado
     */
    public String[] getOperacoesCalculo() {
        return this.operacoesCalculo.toArray(new String[0]);
    }

    public void appendOperacaoCalculo(String operacao) {
        this.operacoesCalculo.add(operacao);
    }

    public void resetarOperacoesCalculo() {
        this.operacoesCalculo = new ArrayList<>();
    }
}
