package bancoPCD;

public class Cliente extends Thread {
    public static int clienteContador = 0;

    public int clienteNumero;
    public Conta conta;
    public Loja[] lojas;

    public Cliente(Conta conta, Loja[] lojas) {
        this.lojas = lojas; 
        this.conta = conta; 
        this.clienteNumero = ++clienteContador; 
    }

    @Override
    public void run() { //BOTA PRA RODAR
        while (conta.getSaldo() > 0) { //RODA ENQUANTO SALDO>0
            double compra = Math.random() < 0.5 ? 100 : 200; //ALEATORIO ENTRE 100 E 200
            int lojaCompra = (int) (Math.random() * lojas.length); //ALEATORIZA A LOJA
            Loja loja = lojas[lojaCompra];
            synchronized (loja.getConta()) {
                if (conta.receber(compra)) {
                    loja.getConta().investir(compra); //EFETUA A COMPRA
                        try {
                            Thread.sleep(500);//DA UMA PAUSA PARA APARECER PRIMEIRO OS FUNCIONARIOS NO CONSOLE
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    System.out.printf("\n---------------------------------\nCompra realizada na loja %d\nPelo cliente %d\nNo valor de = R$ %.0f\n", lojaCompra+1,clienteNumero, compra);

                } else {
                    break; // PARA O LOOOOP
                }
            }
        }
    }
}