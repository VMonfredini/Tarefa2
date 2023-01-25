package br.inatel.banco.services;

import br.inatel.banco.interfaces.IContaCorrenteService;

import java.util.ArrayList;

public class ContaCorrenteService extends ContaBaseService implements IContaCorrenteService
{
    private double credito = 1000;

    private ArrayList<Pagamentos> extratoCredito = new ArrayList<Pagamentos>();

    public ContaCorrenteService(String agencia, String numeroConta) {
        super(agencia, numeroConta);
    }

    @Override
    public void adicionarCredito(double valor){
        this.credito = credito + valor;
    }

    @Override
    public boolean pagarCredito(double valor){
        if (valor <= 0 || valor > this.credito)
            return false;

        extratoCredito.add(new Pagamentos(-valor, "Crédito", "Conta"));

        this.credito = this.credito - valor;

        return true;
    }

    @Override
    public double consultaCredito(){
        return this.credito;
    }

    public double pagarFaturaCredito(double valorPagamento){
        double valor = 0;

        for (Pagamentos Pagamento: this.extratoCredito) {
            if(Pagamento.getNome() == "Crédito")
                valor = valor + Pagamento.getValor();
        }

        if (valorPagamento == valor){
            this.credito = this.credito + valorPagamento;
            return -1;
        }
        return valor;

    }
}
