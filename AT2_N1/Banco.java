public class Banco {
    public synchronized boolean transferir(Conta de, Conta para, double valor) {
        if (de.retirar(valor)) {
            para.depositar(valor);
            return true;
        }
        return false;
    }
}
