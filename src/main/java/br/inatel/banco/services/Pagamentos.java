package br.inatel.banco.services;

public class Pagamentos {
    private double valor;
    private String nome;
    private String operacao;

    public Pagamentos(double valor, String nome, String operacao) {
        this.valor = valor;
        this.nome = nome;
        this.operacao = operacao;
    }

    public double getValor() {
        return valor;
    }

    public String getNome() {
        return nome;
    }

    public String getOperacao() {
        return operacao;
    }
}
