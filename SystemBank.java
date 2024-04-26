import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Conta {
    private double saldo;
    private final Lock lock = new ReentrantLock();
     String name; 

    public Conta(double saldo, String name ) {
        this.saldo = saldo;
        this.name = name;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        lock.lock();
        try {
            saldo += valor;
        } finally {
            lock.unlock();
        }
    }

    public void sacar(double valor) {
        lock.lock();
        try {
            saldo -= valor;
        } finally {
            lock.unlock();
        }
    }

    public void exibirSaldoFinal() {
        System.out.println("Saldo final: R$" + saldo);
    }
}

class Banco {
    public static void transferir(Conta origem, Conta destino, double valor) {
        origem.sacar(valor);
        destino.depositar(valor);
        System.out.println("Transferência de R$" + valor + " realizada de " + origem.name + " para " + destino.name);
    }
}

class Loja {
    Conta conta;
    private Banco banco;
    private Funcionario funcionario1;
    private Funcionario funcionario2;
    private String name;

    private final double salarioFuncionario = 1400;

    public Loja(String name, double saldoInicial, Funcionario funcionario1, Funcionario funcionario2) {
        this.name = name ;
        conta = new Conta(saldoInicial, name);
        banco = new Banco();
        this.funcionario1 = funcionario1;
        this.funcionario2 = funcionario2;
    }

    public double getSaldo() {
        return conta.getSaldo();
    }

    public void exibirSaldoFinal() {
        System.out.println("----------------------------------------------------");
        System.out.println(name + ":");
        System.out.println("Saldo final da "+ name +": R$" + getSaldo()+"\n");
    }

    public void pagarFuncionario() {
        double saldoLoja = conta.getSaldo();
        if (saldoLoja >= salarioFuncionario) {
            Banco.transferir(conta, funcionario1.contaSalario, salarioFuncionario);
            Banco.transferir(conta, funcionario2.contaSalario, salarioFuncionario);
            funcionario1.MakeInvestimentoSalario();
            funcionario2.MakeInvestimentoSalario();
            System.out.println("Salário de R$" + salarioFuncionario + " pago para funcionários da loja " + this);
        }
    }

    @Override
    public String toString() {
        return "Loja";
    }
}

class Funcionario implements Runnable {
    Conta contaSalario;
    private Conta contaInvestimento;
    private String name;
    public Funcionario( String name, double saldoSalario, double saldoInvestimento) {
        this.name = name;
        contaSalario = new Conta(saldoSalario, name);
        contaInvestimento = new Conta(saldoInvestimento, name);
    }

    public void MakeInvestimentoSalario() {
        double valorInvestimento = contaSalario.getSaldo() * 0.2;
        Banco.transferir(contaSalario, contaInvestimento, valorInvestimento);
        System.out.println("Salário de R$" + contaSalario.getSaldo() + " recebido por "+name+". Valor investido: R$" + valorInvestimento);
    }

    @Override
    public void run() {
        
    }

    public double getSaldoInvestimento() {
        return contaInvestimento.getSaldo();
    }

    public double getSaldoSalario() {
        return contaSalario.getSaldo();
    }

    public void exibirSaldoFinal() {
        System.out.println("----------------------------------------------------");
        System.out.println(name + ":");
        System.out.println("Saldo da conta de investimento: R$" + getSaldoInvestimento());
        System.out.println("Saldo da conta de salário: R$" + getSaldoSalario()+"\n");
    }

    @Override
    public String toString() {
        return "Funcionário";
    }
}

class Cliente implements Runnable {
    private Conta conta;
    private Loja loja1;
    private Loja loja2;
    private String name;
    private static final double[] VALORES_COMPRA = {100, 200};
    private int indexValorCompra = 0;
    private final Object lock = new Object();

    public Cliente(String name , double saldoInicial, Loja loja1, Loja loja2) {
        this.name = name;
        conta = new Conta(saldoInicial, name);
        this.loja1 = loja1;
        this.loja2 = loja2;
    }

    @Override
    public void run() {
        while (true) {
            double saldoCliente = conta.getSaldo();
            if (saldoCliente <= 0) break;

            double valorCompra = VALORES_COMPRA[indexValorCompra];
            Loja loja = indexValorCompra % 2 == 0 ? loja1 : loja2; // Alterna entre lojas
            String nomeLoja = indexValorCompra % 2 == 0 ? "Loja 1" : "Loja 2";

            synchronized (lock) {
                if (saldoCliente >= valorCompra) {
                    Banco.transferir(conta, loja.conta, valorCompra);
                    System.out.println(name + " comprou R$" + valorCompra + " na " + nomeLoja );
                    loja.pagarFuncionario();
                }
            }

            indexValorCompra = (indexValorCompra + 1) % VALORES_COMPRA.length;

            try {
                Thread.sleep(100); // Dorme por um curto período de tempo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public double getSaldo() {
        return conta.getSaldo();
    }

    public void exibirSaldoFinal() {
        System.out.println("Saldo do " + name +" : R$" + getSaldo()+"\n");
    }

    @Override
    public String toString() {
        return "Cliente";
    }
}

public class SystemBank {
    public static void main(String[] args) {
        Funcionario funcionario1 = new Funcionario("funcionario1",0, 0);
        Funcionario funcionario2 = new Funcionario("funcionario2",0, 0);
        Loja loja1 = new Loja("Loja 1",0, funcionario1, funcionario2);

        Funcionario funcionario3 = new Funcionario("funcionario3",0, 0);
        Funcionario funcionario4 = new Funcionario("funcionario4",0, 0);
        Loja loja2 = new Loja("Loja 2",0, funcionario3, funcionario4);

        Cliente cliente1 = new Cliente("cliente1",1000, loja1, loja2);
        Cliente cliente2 = new Cliente("cliente2",1000, loja1, loja2);
        Cliente cliente3 = new Cliente("cliente3",1000, loja1, loja2);
        Cliente cliente4 = new Cliente("cliente4",1000, loja1, loja2);
        Cliente cliente5 = new Cliente("cliente5",1000, loja1, loja2);

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
