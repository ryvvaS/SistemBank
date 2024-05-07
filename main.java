package atividadeIndividual01;
public class main {
    public static void main(String[] args) {
        Funcionario funcionario1 = new Funcionario("funcionario1", 0, 0);
        Funcionario funcionario2 = new Funcionario("funcionario2", 0, 0);
        Loja loja1 = new Loja("Loja 1", 0, funcionario1, funcionario2);

        Funcionario funcionario3 = new Funcionario("funcionario3", 0, 0);
        Funcionario funcionario4 = new Funcionario("funcionario4", 0, 0);
        Loja loja2 = new Loja("Loja 2", 0, funcionario3, funcionario4);

        Cliente cliente1 = new Cliente("cliente1", 1000, loja1, loja2);
        Cliente cliente2 = new Cliente("cliente2", 1000, loja1, loja2);
        Cliente cliente3 = new Cliente("cliente3", 1000, loja1, loja2);
        Cliente cliente4 = new Cliente("cliente4", 1000, loja1, loja2);
        Cliente cliente5 = new Cliente("cliente5", 1000, loja1, loja2);

        Thread tCliente1 = new Thread(cliente1);
        Thread tCliente2 = new Thread(cliente2);
        Thread tCliente3 = new Thread(cliente3);
        Thread tCliente4 = new Thread(cliente4);
        Thread tCliente5 = new Thread(cliente5);

        Thread tFuncionario1 = new Thread(funcionario1);
        Thread tFuncionario2 = new Thread(funcionario2);
        Thread tFuncionario3 = new Thread(funcionario3);
        Thread tFuncionario4 = new Thread(funcionario4);

        // Iniciar threads
        tCliente1.start();
        tCliente2.start();
        tCliente3.start();
        tCliente4.start();
        tCliente5.start();

        tFuncionario1.start();
        tFuncionario2.start();
        tFuncionario3.start();
        tFuncionario4.start();

        try {
            tCliente1.join();
            tCliente2.join();
            tCliente3.join();
            tCliente4.join();
            tCliente5.join();

            tFuncionario1.join();
            tFuncionario2.join();
            tFuncionario3.join();
            tFuncionario4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Exibir saldos finais
        loja1.exibirSaldoFinal();
        loja2.exibirSaldoFinal();
        funcionario1.exibirSaldoFinal();
        funcionario2.exibirSaldoFinal();
        funcionario3.exibirSaldoFinal();
        funcionario4.exibirSaldoFinal();
        cliente1.exibirSaldoFinal();
        cliente2.exibirSaldoFinal();
        cliente3.exibirSaldoFinal();
        cliente4.exibirSaldoFinal();
        cliente5.exibirSaldoFinal();
    }
}
