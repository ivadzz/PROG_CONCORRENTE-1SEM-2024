package bancoPCD;

public class Loja {
    public Conta conta;

    public Loja(double saldoInicial) {
        this.conta = new Conta(saldoInicial); //CRIA A CONTA DA LOJA
    }

    public Conta getConta() {
        return conta;
    }

    public void pagarSalarios() {
    	//REALIZA O PAGAMENTO DO SALARIO
        System.out.println("\n\nPagamento dos funcionários efetuado");
    }
}