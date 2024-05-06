package Sistema_Hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HotelSimulation {
    public static void main(String[] args) {
        // Criação de 10 quartos no hotel
        List<Quarto> quartos = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            quartos.add(new Quarto(i)); // Cada quarto tem capacidade para até 4 hóspedes
        }

        // Inicia o recepcionista para gerenciar as reservas
        Recepcionista recepcionista = new Recepcionista(quartos);
        recepcionista.start();

        // Inicia 10 camareiras para limpeza dos quartos
        List<Camareira> camareiras = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Camareira camareira = new Camareira(quartos, "Camareira " + i);
            camareiras.add(camareira);
            camareira.start(); // Inicia as camareiras como threads
        }

        // Inicia 50 hóspedes com tamanhos de grupos variados
        List<Hospede> hospedes = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i <= 50; i++) {
            int tamanhoGrupo = random.nextInt(6) + 1; // Tamanho do grupo entre 1 e 6
            Hospede hospede = new Hospede(recepcionista, "Hospede " + i, tamanhoGrupo);
            hospedes.add(hospede);
            hospede.start(); // Inicia as threads dos hóspedes
        }

        // O sistema deve rodar por um tempo para observar a interação
        try {
            Thread.sleep(30000); // Roda por 30 segundos para simular a atividade
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restabelece a interrupção se interrompida
        }

        System.out.println("Fim da simulação."); // Mensagem indicando o fim da simulação
    }
}
