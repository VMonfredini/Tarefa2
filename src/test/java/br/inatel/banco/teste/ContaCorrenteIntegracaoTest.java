package br.inatel.banco.teste;

import br.inatel.banco.controller.ManipulaConta;
import br.inatel.banco.services.ContaBaseService;
import br.inatel.banco.services.ContaCorrenteService;
import org.junit.Assert;
import org.junit.Test;

public class ContaCorrenteIntegracaoTest {
    ManipulaConta ManipulaContaFake;

    @Test
    public void depositarValorContaCorrenteTest_Ok(){
        ManipulaContaFake = new ManipulaConta();

        ContaBaseService cc = new ContaCorrenteService("1111", "123456789-0");

        String resultado = ManipulaContaFake.depositarValor(cc, 1500, "pix");
        Assert.assertEquals("Depósito realizado com sucesso", resultado);
    }

    @Test
    public void depositarValorContaCorrenteTest_Error(){
        ManipulaContaFake = new ManipulaConta();

        ContaBaseService cc = new ContaCorrenteService("1111", "123456789-0");

        String resultado = ManipulaContaFake.depositarValor(cc, -1500, "pix");
        Assert.assertEquals("Falha ao realizar depósito", resultado);
    }

    @Test
    public void pagamentoContaContaCorrenteTest_Ok(){
        ManipulaContaFake = new ManipulaConta();

        ContaBaseService cc = new ContaCorrenteService("1111", "123456789-0");

        ManipulaContaFake.depositarValor(cc, 1500, "pix");

        String resultado = ManipulaContaFake.pagamentoConta(cc, 99.90, "Internet");

        Assert.assertEquals("Pagamento realizado com sucesso", resultado);
    }

    @Test
    public void pagamentoContaContaCorrenteTest_Error(){
        ManipulaContaFake = new ManipulaConta();

        ContaBaseService cc = new ContaCorrenteService("1111", "123456789-0");

        ManipulaContaFake.depositarValor(cc, 15, "pix");

        String resultado = ManipulaContaFake.pagamentoConta(cc, 99.90, "Internet");

        Assert.assertEquals("Falha ao realizar pagamento", resultado);
    }

    @Test
    public void pagamentoFaturaCreditoTest_Ok(){
        ManipulaContaFake = new ManipulaConta();

        ContaCorrenteService cc = new ContaCorrenteService("1111", "123456789-0");

        cc.pagarCredito(50);
        cc.pagarCredito(50);
        cc.pagarCredito(50);

        Assert.assertEquals("Pagamento da fatura de crédito realizda com sucesso", ManipulaContaFake.pagamentoFaturaCredito(cc, 150));
    }

    @Test
    public void pagamentoFaturaCreditoTest_Error(){
        ManipulaContaFake = new ManipulaConta();

        ContaCorrenteService cc = new ContaCorrenteService("1111", "123456789-0");

        cc.pagarCredito(50);

        Assert.assertEquals("Falha no pagamento da fatura de crédito, valor de R$50.0", ManipulaContaFake.pagamentoFaturaCredito(cc, 35));
    }
}
