package bancoPCD;

public class Banco {
    public static void main(String[] args) throws InterruptedException {
        //inicia as lojas
        Loja[] lojas = new Loja[2]; 
        for (int l = 0; l < lojas.length; l++) {
            lojas[l] = new Loja(0); 
        }

        // "" "" funcionarios
        Funcionario[] funcionarios = new Funcionario[4]; 
        for (int k = 0; k < funcionarios.length; k++) {
            Conta salarioLoja = lojas[k / 2].getConta(); 
            Conta contaInvestimento = new Conta(0); 
            funcionarios[k] = new Funcionario(salarioLoja, contaInvestimento);
            funcionarios[k].start();
        }
        // "" "" contas
        Conta[] contasClientes = new Conta[5]; 
        for (int j = 0; j < contasClientes.length; j++) {
        //cada conta coemça com 1000
            contasClientes[j] = new Conta(1000); 
        }
        
        // inicia os clientes
        Cliente[] clientes = new Cliente[5]; 
        for (int i = 0; i < clientes.length; i++) {
            clientes[i] = new Cliente(contasClientes[i], lojas);
            clientes[i].start();
        }

        //coloca as threads pra rodar
        for (Cliente cliente : clientes) {
            try {
                cliente.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // PAG das lojas
        for (Loja loja : lojas) {
            loja.pagarSalarios(); 
        }

        System.out.println("\n\n");
        System.out.println("SAÍDA:");
        for (int index = 0; index < contasClientes.length; index++) {
            System.out.println("---------------------------------");
            System.out.println("O cliente " + (index + 1) + " restou --> R$" + contasClientes[index].getSaldo());
            System.out.println("---------------------------------");
            Thread.sleep(1000); 

        }
        for (int index = 0; index < lojas.length; index++) {
            System.out.println("\n\n---------------------------------");
            System.out.println("Loja " + (index + 1) + " faturou --> R$" + lojas[index].getConta().getSaldo());
            System.out.println("---------------------------------");
            Thread.sleep(1000); 
        }
        for (int h = 0; h < funcionarios.length; h++) {
            System.out.println("\n===========================================================================\nO funcionário " + (h + 1) + ", da loja " + (h / 2 + 1) + ", obteve de Salário --> R$" + (funcionarios[h].getSalario().getSaldo()/2+1400) + ", e investiu--> R$" + (funcionarios[h].getSalario().getSaldo()/2+1400)*0.2);
        }
    }
}