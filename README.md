# Sistema Bancário Simulado

Este é um sistema bancário simulado em Java, que inclui classes para contas bancárias, funcionários de lojas, clientes e um sistema de transferência de fundos entre contas.
As alterações feitas recentemente foram para adequar o modelo de organização solicitado pelo professor, separando devidamente as classes em arquivos unicos, inicializados pela `main.java`

## Requisitos de funcionamento da aplicação:
Como indicado na ativiade, foi utilizado o JAVA 17, para construção do programa;
A IDE utilizada foi a Eclipse IDE for Java Developers - 2024-03
## Funcionalidades

### Conta

A classe `Conta` representa uma conta bancária com métodos para depositar, sacar e exibir o saldo. Ele também usa um mecanismo de bloqueio para garantir a segurança das operações concorrentes.
O mecanismo utilizado na classe Conta para garantir a segurança das operações concorrentes é o uso de um bloqueio (lock) fornecido pela interface Lock do pacote java.util.concurrent.locks e implementado pela classe ReentrantLock.

![image](https://github.com/ryvvaS/SystemBank/assets/63801754/7deab87c-d5dd-4f3d-9bcf-c67823b32b0e)



### Banco

A classe `Banco` fornece um método estático `transferir` para transferir fundos entre duas contas.
A classe Banco fornece um método estático chamado transferir para transferir fundos entre duas contas. Vamos examinar como esse método é implementado:
Este método recebe três parâmetros:

1 origem: A conta de onde os fundos serão retirados.

2 destino: A conta para onde os fundos serão transferidos.

3 valor: O valor a ser transferido entre as contas.


![image](https://github.com/ryvvaS/SystemBank/assets/63801754/e0e897f4-4dc8-4250-8056-c8bc29054101)



### Loja

A classe `Loja` representa uma loja com um saldo inicial e métodos para exibir saldo final e pagar funcionários. Cada loja tem dois funcionários associados.
Funcionamento:

1 Inicialização: Ao ser criada, recebe um nome e um saldo inicial, além de duas instâncias de funcionário associadas.

2 Exibição de Saldo: Oferece um método para exibir o saldo final da loja.

3 Pagamento de Funcionários: Possui um método para pagar os salários dos funcionários, desde que haja saldo suficiente na conta da loja.

4 Associação com Funcionários: Mantém referências para dois funcionários associados, permitindo que o pagamento dos salários seja realizado.


![image](https://github.com/ryvvaS/SystemBank/assets/63801754/9656f68c-7f2c-4aed-a419-7fbc7166d6fe)



### Funcionário

A classe `Funcionario` representa um funcionário de uma loja com uma conta de salário e uma conta de investimento. Ele pode receber seu salário e fazer um investimento com uma parte do salário.

Funcionamento:

1 Inicialização: Quando uma instância de Loja é criada, ela recebe um nome, um saldo inicial e duas instâncias de Funcionario como parâmetros. A loja também inicializa sua própria conta bancária e um objeto Banco para operações bancárias.

2 Método exibirSaldoFinal(): Este método exibe o saldo final da conta bancária da loja.

3 Método pagarFuncionario(): Este método é responsável por pagar os funcionários da loja. Primeiro, ele verifica se o saldo da loja é suficiente para pagar os salários dos funcionários (que é definido como uma constante salarioFuncionario). Se houver saldo suficiente, o método utiliza o método estático transferir da classe Banco para transferir o salário de cada funcionário da conta da loja para suas contas de salário individuais. Em seguida, chama o método MakeInvestimentoSalario() de cada funcionário para investir uma parte de seu salário em sua conta de investimento.

4 Método toString(): Este método retorna uma representação em string da loja.

Portanto, a classe Loja permite a gestão do saldo da loja, exibição do saldo final e pagamento de salários aos seus funcionários.


![image](https://github.com/ryvvaS/SystemBank/assets/63801754/0b3a63ef-a65c-4fee-ae41-e95529afdee6)



### Cliente

A classe `Cliente` representa um cliente que pode fazer compras em duas lojas diferentes. Ele seleciona aleatoriamente uma loja para fazer uma compra e, se tiver saldo suficiente, realiza a transação. Este cliente é executado em uma thread separada.

Funcionamento:

1 Inicialização: Ao criar um cliente, são fornecidos seu nome, saldo inicial e referências para duas lojas.

2 Execução: Quando o cliente é executado em uma thread, ele verifica repetidamente se seu saldo é maior que zero para continuar as compras.

3 Seleção de Loja e Compra: O cliente seleciona aleatoriamente uma das duas lojas disponíveis e verifica se tem saldo suficiente para a compra atual.

4 Transação e Pagamento de Funcionário: Se houver saldo suficiente, o cliente realiza a compra transferindo o valor da sua conta para a conta da loja selecionada e solicita que a loja pague seus funcionários.

5 Atualização e Espera: Após cada compra, o cliente atualiza o índice de compra e aguarda um curto período de tempo antes de verificar novamente o saldo.


![image](https://github.com/ryvvaS/SystemBank/assets/63801754/845a782f-1c11-4dfb-9222-5431213208cd)



### Main

A classe `main` é a classe principal que inicializa todas as lojas, funcionários e clientes, cria threads para funcionários e clientes e gerencia suas execuções. Ele também exibe os saldos finais de todas as contas e clientes após a conclusão da simulação.

Funcionamento:

1 Inicialização: A classe main cria instâncias de funcionários, lojas e clientes com saldos iniciais e associações apropriadas.

2 Criação de Threads: Threads são criadas para cada cliente e funcionário, permitindo que eles executem suas atividades de forma concorrente.

3 Execução: As threads são iniciadas e executam suas operações. Os clientes fazem compras em lojas alternadamente, enquanto os funcionários recebem salários e realizam investimentos.

4 Finalização e Exibição: Após a conclusão das operações de todas as threads, os saldos finais de todas as contas e clientes são exibidos, fornecendo uma visão geral do estado final do sistema bancário simulado.

![image](https://github.com/ryvvaS/SystemBank/assets/63801754/5f6a913a-6c1c-47e5-9c20-735524e35775)



## Executando o Sistema

Para executar o sistema, basta executar a classe `main`. Ele criará e executará as threads para clientes e funcionários, realizará transações entre contas e exibirá os saldos finais.
