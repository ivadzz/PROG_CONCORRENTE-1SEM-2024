package Sistema_Hotel;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Hospede extends Thread {
    private final Recepcionista recepcionista;
    private final int tamanhoGrupo;

    public Hospede(Recepcionista recepcionista, String nome, int tamanhoGrupo) {
        super(nome);
        this.recepcionista = recepcionista;
        this.tamanhoGrupo = tamanhoGrupo;
    }

    @Override
    public void run() {
        boolean conseguiuQuarto = false;
        int tentativas = 0;
        Random random = new Random();

        while (!conseguiuQuarto && tentativas < 2) {
            Quarto quarto = recepcionista.reservarQuarto(tamanhoGrupo);
            tentativas++;

            if (quarto != null) {
                System.out.println(getName() + " reservou o quarto " + quarto.getNumero());
                quarto.entrar(tamanhoGrupo);

                // Simular a estadia
                try {
                    TimeUnit.SECONDS.sleep(random.nextInt(5) + 2); // Hospede dorme por um tempo
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                quarto.sair(tamanhoGrupo); // Sair do quarto
                recepcionista.notificarQuartoDisponivel(); // Notifica que um quarto está disponível
                conseguiuQuarto = true;
            } else {
                System.out.println(getName() + " não conseguiu um quarto. Tentará novamente mais tarde.");
                try {
                    TimeUnit.SECONDS.sleep(random.nextInt(3) + 1); // Espera antes de tentar novamente
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        if (!conseguiuQuarto) {
            System.out.println(getName() + " não conseguiu um quarto após duas tentativas e fez uma reclamação.");
        }
    }
}
