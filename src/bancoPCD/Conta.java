package bancoPCD;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

public class Conta {
    private double saldo; 
    private final Lock lock;

    public Conta(double saldoInicial) { 
        this.saldo = saldoInicial; 
        this.lock = new ReentrantLock(); //BLOQUEIA
    }

    public double getSaldo() { //PEGA O SALDO
        return saldo;
    }

    public void investir(double valor) { //FAZ A OPERAÇÃO DE IVESTIR
        lock.lock(); 
        try {
            saldo += valor; //ALTERA O SALDO
        } finally {
            lock.unlock(); //DESBLOQUEIA
        }
    }

    public boolean receber(double valor) { 
        lock.lock(); // BLOQUEIA
        try {
            if (saldo >= valor) { 
                saldo -= valor; 
                return true; //COMPRA = VERDADEIRO
            }
            return false; //COMPRA = FALSO
        } finally {
            lock.unlock(); 
        }
    }
}