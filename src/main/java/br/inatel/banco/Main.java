package br.inatel.banco;

import br.inatel.banco.controller.ManipulaConta;
import br.inatel.banco.services.ContaCorrenteService;
import br.inatel.banco.services.ContaPoupancaService;

public class Main {

    public static void main(String[] args) {
        System.out.println("-----------------------Inatel Bank-----------------------");

        ManipulaConta Conta = new ManipulaConta();

        Conta.menuInicial();
    }
}
