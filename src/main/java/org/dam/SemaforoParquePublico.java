package org.dam;

import java.util.concurrent.Semaphore;

public class SemaforoParquePublico extends Thread {

    private static final Semaphore puertas = new Semaphore(3);

    private static int contador = 1;

    //Constructor de la clase:
    SemaforoParquePublico(String nombreHilo) {
        super(nombreHilo);
    }

    //Método run() de la clase Thread que se ejecuta al iniciar el hilo:
    @Override
    public void run() {
        try {
            //Intenta adquirir un permiso:
            puertas.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Entra el visitante: " + getName());
        System.out.println("Número de visitantes: " + contador++);
        //Libera el permiso:
        puertas.release();
        try {
            //Espera 100 milisegundos:
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        //Número de hilos:
        for (int i = 1; i <= 10; i++) {
            new SemaforoParquePublico("Visitante " + i).start();
        }
    }

}