class Banco {
    public static void transferir(Conta origem, Conta destino, double valor) {
        origem.sacar(valor);
        destino.depositar(valor);
        System.out.println("Transferência de R$" + valor + " realizada de " + origem.name + " para " + destino.name);
    }
}