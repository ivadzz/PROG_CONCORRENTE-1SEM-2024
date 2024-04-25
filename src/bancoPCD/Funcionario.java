package bancoPCD;

public class Funcionario extends Thread {
    private static int contaFunc = 0; //CONTADOR DE FUNCIONÁRIOS

    private int funcNumero;
    private Conta salario; 
    private Conta investimento;

    public Funcionario(Conta salario, Conta investimento) {
        this.funcNumero = ++contaFunc; //AUMENTA O CONTADOR DE FUNCIONÁRIOS
        this.salario = salario; //=SALARIO
        this.investimento = investimento; 
    }
    
    public Conta getSalario() {
        return salario;
    }

    public Conta getInvestimento() {
        return investimento;
    }
}