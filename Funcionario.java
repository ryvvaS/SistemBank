package atividadeIndividual01;

class Funcionario implements Runnable {
    Conta contaSalario;
    private Conta contaInvestimento;
    private String name;

    public Funcionario(String name, double saldoSalario, double saldoInvestimento) {
        this.name = name;
        contaSalario = new Conta(saldoSalario, name);
        contaInvestimento = new Conta(saldoInvestimento, name);
    }

    public void MakeInvestimentoSalario() {
        double valorInvestimento = contaSalario.getSaldo() * 0.2;
        Banco.transferir(contaSalario, contaInvestimento, valorInvestimento);
        System.out.println("Salário de R$" + contaSalario.getSaldo() + " recebido por " + name + ". Valor investido: R$" + valorInvestimento);
    }

    @Override
    public void run() {
        // Implementação da execução do funcionário
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
        System.out.println("Saldo da conta de salário: R$" + getSaldoSalario() + "\n");
    }

    @Override
    public String toString() {
        return "Funcionário";
    }
}
