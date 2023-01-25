package br.inatel.banco.interfaces;

public interface IContaService
{
    public boolean pagarConta(double valor, String nome);
    public boolean depositoConta(double valor, String nome);
    public double consultaSaldo();
    public void consultaExtrato();
}
