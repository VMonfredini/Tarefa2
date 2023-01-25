package br.inatel.banco.services;

import br.inatel.banco.interfaces.IContaPoupancaService;

public class ContaPoupancaService extends ContaBaseService implements IContaPoupancaService
{
    private double rendimento = 0;

    public ContaPoupancaService(String agencia, String numeroConta) {
        super(agencia, numeroConta);
    }

    @Override
    public void adicionaRendimento(){
        this.rendimento = this.rendimento + this.consultaSaldo()*0.01;
    }

    @Override
    public double consultaRendimento(){
        return this.rendimento;
    }
}
