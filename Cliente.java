class Cliente implements Runnable {
    private Conta conta;
    private Loja loja1;
    private Loja loja2;
    private String name;
    private static final double[] VALORES_COMPRA = {100, 200};
    private int indexValorCompra = 0;
    private final Object lock = new Object();

    public Cliente(String name, double saldoInicial, Loja loja1, Loja loja2) {
        this.name = name;
        conta = new Conta(saldoInicial, name);
        this.loja1 = loja1;
        this.loja2 = loja2;
    }

    @Override
    public void run() {
        // Implementação da execução do cliente
        while (true) {
            double saldoCliente = conta.getSaldo();
            if (saldoCliente <= 0) break;

            double valorCompra = VALORES_COMPRA[indexValorCompra];
            Loja loja = indexValorCompra % 2 == 0 ? loja1 : loja2; // Alterna entre lojas
            String nomeLoja = indexValorCompra % 2 == 0 ? "Loja 1" : "Loja 2";

            synchronized (lock) {
                if (saldoCliente >= valorCompra) {
                    Banco.transferir(conta, loja.conta, valorCompra);
                    System.out.println(name + " comprou R$" + valorCompra + " na " + nomeLoja);
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
        System.out.println("Saldo do " + name + " : R$" + getSaldo() + "\n");
    }

    @Override
    public String toString() {
        return "Cliente";
    }
}
