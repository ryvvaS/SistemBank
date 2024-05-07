package atividadeIndividual01;

class Loja {
    Conta conta;
  
    private Funcionario funcionario1;
    private Funcionario funcionario2;
    private String name;

    private final double salarioFuncionario = 1400;

    public Loja(String name, double saldoInicial, Funcionario funcionario1, Funcionario funcionario2) {
        this.name = name;
        conta = new Conta(saldoInicial, name);

        this.funcionario1 = funcionario1;
        this.funcionario2 = funcionario2;
    }

    public double getSaldo() {
        return conta.getSaldo();
    }

    public void exibirSaldoFinal() {
        System.out.println("----------------------------------------------------");
        System.out.println(name + ":");
        System.out.println("Saldo final da " + name + ": R$" + getSaldo() + "\n");
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
