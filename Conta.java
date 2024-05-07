package atividadeIndividual01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Conta {
    private double saldo;
    private final Lock lock = new ReentrantLock();
    String name;

    public Conta(double saldo, String name) {
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
