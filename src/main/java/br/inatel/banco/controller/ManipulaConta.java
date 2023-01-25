package br.inatel.banco.controller;

import br.inatel.banco.services.ContaBaseService;
import br.inatel.banco.services.ContaCorrenteService;
import br.inatel.banco.services.ContaPoupancaService;
import jdk.swing.interop.SwingInterOpUtils;

import java.sql.SQLOutput;
import java.util.Scanner;

public class ManipulaConta {
    ContaCorrenteService ContaCorrente;
    ContaPoupancaService ContaPoupanca;

    public void menuInicial(){
        System.out.println("Selecione seu tipo de conta: \n");
        System.out.println(" (1) -> Conta Corrente");
        System.out.println(" (2) -> Conta Poupança");
        System.out.print("\nEscolha uma opção: ");

        Scanner teclado = new Scanner(System.in);

        short op = teclado.nextShort();

        teclado.nextLine();

        System.out.print("Entre com a agência: ");
        String agencia = teclado.nextLine();

        System.out.print("Entre com o número da conta: ");
        String numero = teclado.nextLine();

        System.out.println("-------------------------------");

        switch (op){
            case 1:
                ContaCorrente = new ContaCorrenteService(agencia, numero);
                short opcaoPagamento = 0;

                while(opcaoPagamento != 3) {
                    System.out.println("Conta Corrente:");
                    System.out.println(" (1) -> Pagar com o Crédito");
                    System.out.println(" (2) -> Pagar fatura do Crédito");
                    System.out.println(" (3) -> Próximas Operações");
                    System.out.print("\nEscolha uma opção: ");

                    opcaoPagamento = teclado.nextShort();

                    switch (opcaoPagamento) {
                        case 1:
                            System.out.println("Entre com o valor da conta que deseja pagar com o crédito: ");
                            double valor = teclado.nextDouble();

                            boolean resposta = ContaCorrente.pagarCredito(valor);

                            if (resposta == false)
                                System.out.println("Falha, valor incoerente");
                            else
                                System.out.println("Pagamento de conta realizado com sucesso, valor de R$" + ContaCorrente.consultaCredito());
                            break;
                        case 2:
                            if (ContaCorrente.consultaCredito() < 1000) {
                                double valorPagamento = 0;
                                while(valorPagamento <= 0)
                                {
                                    System.out.println("Insira o valor do pagamento: ");
                                    valorPagamento = teclado.nextDouble();
                                }
                                teclado.nextLine();

                                System.out.println(this.pagamentoFaturaCredito(ContaCorrente, valorPagamento));
                            }
                            else{
                                System.out.println("Não há pendências de créditos");
                            }
                            break;
                        case 3:
                            this.menuConta(ContaCorrente);
                            break;
                    }
                }
                break;

            case 2:
                ContaPoupanca = new ContaPoupancaService(agencia, numero);
                this.menuConta(ContaPoupanca);
                break;
        }

    }

    public void menuConta(ContaBaseService Conta){
        short op = 0;
        while(op != 3) {
            System.out.println("Conta:");
            System.out.println(" (1) -> Depositar dinheiro em conta");
            System.out.println(" (2) -> Pagar conta");
            System.out.println(" (3) -> Sair");

            Scanner teclado = new Scanner(System.in);

            op = teclado.nextShort();

            teclado.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Entre com o nome do depósito: ");
                    String nomeDeposito = teclado.nextLine();

                    System.out.print("Entre com o valor do depósito: ");
                    double valorDeposito = teclado.nextDouble();

                    teclado.nextLine();

                    System.out.println("\n" + this.depositarValor(Conta, valorDeposito, nomeDeposito) + "\n");
                    break;

                case 2:
                    System.out.print("Entre com o nome do pagamento: ");
                    String nomePagamento = teclado.nextLine();

                    System.out.print("Entre com o valor do pagamento: ");
                    double valorPagamento = teclado.nextDouble();

                    teclado.nextLine();

                    System.out.println("\n" + this.pagamentoConta(Conta, valorPagamento, nomePagamento) + "\n");
                    break;

                case 3:
                    System.out.println("Fim da operação");
                    break;
            }
        }
    }

    public String depositarValor(ContaBaseService Conta, double valor, String nome){
        boolean response = Conta.depositoConta(valor, nome);

        if (!response){
            return "Falha ao realizar depósito";
        }

        return "Depósito realizado com sucesso";
    }

    public String pagamentoConta(ContaBaseService Conta, double valor, String nome){
        boolean response = Conta.pagarConta(valor, nome);

        if (!response){
            return "Falha ao realizar pagamento";
        }

        return "Pagamento realizado com sucesso";
    }

    public String pagamentoFaturaCredito(ContaCorrenteService contaCorrente, double valorPagamento){

        double response = contaCorrente.pagarFaturaCredito(valorPagamento);

        if (response == -1)
            return "Pagamento da fatura de crédito realizda com sucesso";
        return "Falha no pagamento da fatura de crédito, valor de R$" + response;
    }
}
