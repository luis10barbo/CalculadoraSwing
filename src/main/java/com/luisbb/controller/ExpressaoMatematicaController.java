package com.luisbb.controller;

import com.luisbb.model.ExpressaoMatematica;
import com.luisbb.model.calculos.operacoes.OperacaoMatematica;
import com.luisbb.utils.UtilsNumero;

import java.util.ArrayList;

import static com.luisbb.utils.UtilsNumero.arredondarSePossivel;

public class ExpressaoMatematicaController {
    private ExpressaoMatematica expressaoMatematica;

    public ExpressaoMatematicaController() {
        expressaoMatematica = new ExpressaoMatematica();
    }

    public ExpressaoMatematicaController(ExpressaoMatematica expressaoMatematica) {
        this.expressaoMatematica = expressaoMatematica;
    }

    /**
     * Resetar expressao matematica (funcionalidade do botao CLEAR)
     */
    public void resetar() {
        expressaoMatematica = new ExpressaoMatematica();
    }

    /**
     * Calcular expressao matematica
     */
    public void calcular() {
        expressaoMatematica.resetarOperacoesCalculo();
        expressaoMatematica.setResultado(calculoPEMDAS(expressaoMatematica));
    }

    /**
     * Calculo da expressao via padrao PEMDAS (Parenteses, Exponencial, Multiplicacao, Divisao, Adicao e Soma)
     * @param expressao
     * @return Double caso sucesso, null se nao tiver sucedido a operacao
     */
    private Double calculoPEMDAS(ExpressaoMatematica expressao) {
        // CALCULO DA EXPRESSAO VIA PADRAO PEMDAS
        String novaExpressao = expressao.getExpressao().replace(" ", "");
        Double res = null;

        // Retirar parenteses que envolvem operacao
        while (novaExpressao.charAt(0) == '(' && novaExpressao.charAt(novaExpressao.length() - 1) == ')') {
            if (novaExpressao.length() == 2) novaExpressao = "";
            else novaExpressao = novaExpressao.substring(1, novaExpressao.length() - 1);
        }

        // Resolver expressoes dentro de parenteses
        String[] parenteses = parseParenteses(novaExpressao);
        for (String expressaoParentese: parenteses
        ) {
            novaExpressao = novaExpressao.replace(expressaoParentese, arredondarSePossivel(calculoPEMDAS(new ExpressaoMatematica(expressaoParentese))));
        }

        // Resolver Multiplicacoes e Divisoes
        novaExpressao = resolverExpressao(novaExpressao, OperacaoMatematica::getOperacaoMD);

        // Resolver Adições e Subtrações
        novaExpressao = resolverExpressao(novaExpressao, OperacaoMatematica::getOperacaoAS);

        // Tentar parse em expressao "resolvida", caso não consiga, ignorar exceção
        try {
            res = Double.parseDouble(novaExpressao);
        } catch (NumberFormatException e) {
            System.out.println("expressao incompleta: " + novaExpressao);
        }

        return res;
    }

    public interface runnableAdquirirOperacao {
        OperacaoMatematica adquirirOperacao(String operador);
    }

    /**
     * Resolver expressão dada via operações adquiridas pelo runnable, caso não esteja nela, ignorar
     * @param expressao
     * @param runnableAdquirirOperacao runnable para adquirir operações
     * @return Expressão resolvida
     */
    private String resolverExpressao(String expressao, runnableAdquirirOperacao runnableAdquirirOperacao) {
        String novaExpressao = expressao;

        // Resolver operacoes em loop
        StringBuilder expressaoAtual = new StringBuilder();
        OperacaoMatematica operacaoMatematicaAtual = null;
        boolean calcular = false;
        boolean resetar = false;
        for (int i = 0; i < novaExpressao.length(); i++) {
            // Parse absurdo por caractere da expressão
            char charAtual = novaExpressao.charAt(i);
            boolean charAtualENumero = UtilsNumero.eNumero(charAtual);

            // Adquire operacao matematica se nao checa se o char atual é numero
            if (operacaoMatematicaAtual == null) {
                operacaoMatematicaAtual = runnableAdquirirOperacao.adquirirOperacao(String.valueOf(charAtual));
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
                String resultadoOperacaoAtual = arredondarSePossivel(operacaoMatematicaAtual.calcular(expressaoAtual.toString()));
                String expressaoResolvida = novaExpressao.replace(expressaoAtual, resultadoOperacaoAtual);
                expressaoMatematica.appendOperacaoCalculo(expressaoResolvida);

                novaExpressao = expressaoResolvida;
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
        return novaExpressao;
    }

    /**
     * Adquirir parenteses da expressao
     * @param expressao expressao para procurar parenteses
     * @return Array de Parenteses da operação
     */
    public String[] parseParenteses(String expressao) {
        // Profundidade representa quantos parenteses estao abertos no caractere atual
        int profundidade = 0;

        StringBuilder parentesesAtual = new StringBuilder();
        ArrayList<String> parenteses = new ArrayList<>();
        for (int i = 0; i < expressao.length(); i++) {
            char charAtual = expressao.charAt(i);
            if (charAtual == '(') {
                // Aumentar profundidade ao abrir parentese
                profundidade ++;
            }
            if (profundidade > 0) parentesesAtual.append(charAtual);
            if (charAtual == ')') {
                // Diminuir profundidade ao fechar parentese
                profundidade --;
                if (profundidade == 0) {
                    // Caso tenham fechado todos parenteses, adicionar expressao a lista parenteses
                    parenteses.add(parentesesAtual.toString());
                    parentesesAtual = new StringBuilder();
                };
            }
        }
        // Transformar todos parenteses em array
        return parenteses.toArray(new String[0]);
    }

    public String getExpressao() {
        return expressaoMatematica.getExpressao();
    }

    public String getExpressaoComResultado() {
        calcular();
        return expressaoMatematica.getExpressaoComResultado();
    }

    public void setExpressao(String expressao) {
        expressaoMatematica.setExpressao(expressao);
    }
}
