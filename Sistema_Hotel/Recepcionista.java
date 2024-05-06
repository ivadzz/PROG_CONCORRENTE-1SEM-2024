package Sistema_Hotel;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Recepcionista extends Thread {
    private final List<Quarto> quartos;
    private final ReentrantLock lock;
    private final Condition temQuartoDisponivel;

    public Recepcionista(List<Quarto> quartos) {
        this.quartos = quartos;
        this.lock = new ReentrantLock();
        this.temQuartoDisponivel = lock.newCondition();
    }

    public Quarto reservarQuarto(int tamanhoGrupo) {
        lock.lock();
        try {
            while (!existeQuartoDisponivel(tamanhoGrupo)) {
                temQuartoDisponivel.await();
            }

            for (Quarto quarto : quartos) {
                if (quarto.temEspaco(tamanhoGrupo) && quarto.chaveNaRecepcao()) {
                    quarto.pegarChave();
                    return quarto;
                }
            }

            return null;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return null;
        } finally {
            lock.unlock();
        }
    }

    private boolean existeQuartoDisponivel(int tamanhoGrupo) {
        return quartos.stream().anyMatch(quarto -> quarto.temEspaco(tamanhoGrupo) && quarto.chaveNaRecepcao());
    }

    public void notificarQuartoDisponivel() {
        lock.lock();
        try {
            temQuartoDisponivel.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000); // Simular trabalho da recepção
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
