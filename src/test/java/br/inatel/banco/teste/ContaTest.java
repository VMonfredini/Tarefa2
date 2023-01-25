package br.inatel.banco.teste;

import br.inatel.banco.controller.ManipulaConta;
import br.inatel.banco.services.ContaCorrenteService;
import br.inatel.banco.services.ContaPoupancaService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ContaTest {
    ContaCorrenteService ContaCorrente;
    ContaPoupancaService ContaPoupanca;

    @Before
    public void setup(){
        ContaCorrente = new ContaCorrenteService("1111", "1111111-1");
        ContaPoupanca = new ContaPoupancaService("2222", "2222222-2");
    }

    @Test
    public void contaCorrentePagarCreditoTest_Ok(){
        boolean verifica = ContaCorrente.pagarCredito(10);
        assertEquals(true, verifica);
    }

    @Test
    public void contaCorrentePagarCreditoTest_Error(){

        assertEquals(false, ContaCorrente.pagarCredito(-10));
    }

    @Test
    public void contaPoupancaAdicionaRendimentoTest_Ok(){
        ContaPoupanca.depositoConta(5600, "teste");
        ContaPoupanca.adicionaRendimento();
        assertEquals(56.0, ContaPoupanca.consultaRendimento(), 0);
    }

    @Test
    public void pagamentoFaturaCredito_Ok(){
        ContaCorrente.pagarCredito(57);
        ContaCorrente.pagarCredito(57);

        assertEquals(-1, ContaCorrente.pagarFaturaCredito(114), 0);
    }

    @Test
    public void pagamentoFaturaCredito_Error(){
        ContaCorrente.pagarCredito(57);
        ContaCorrente.pagarCredito(57);

        assertEquals(114, ContaCorrente.pagarFaturaCredito(57), 0);
    }
}

