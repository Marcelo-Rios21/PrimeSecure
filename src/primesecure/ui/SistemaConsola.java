package primesecure.ui;

import java.util.Scanner;
import primesecure.concurrent.PrimesThread;
import primesecure.core.GestorCodigos;

public class SistemaConsola {
    private final GestorCodigos gestor;
    private final Scanner input;
    
    public SistemaConsola() {
        this.gestor = new GestorCodigos();
        this.input = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opcion: ");
            switch (opcion) {
                case 1 -> agregarMensaje();
                case 2 -> eliminarMensajePorCodigo();
                case 3 -> buscarMensajePorCodigo();
                case 4 -> mostrarTodosLosCodigos();
                case 5 -> mostrarCantidadTotal();
                case 6 -> ejecutarGeneradorConcurrente();
                case 7 -> exportarListaCSV();
                case 8 -> inportarCodigosCSV();
                case 0 -> System.out.println("Saliendo... Hasta pronto!");
                default -> System.out.println("Opcion invalida. Intente nuevamente.");
            }
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("---- PRIMESECURE ----");
        System.out.println("1. Agregar mensaje.");
        System.out.println("2. Eliminar mensaje por codigo primo.");
        System.out.println("3. Buscar mensaje por codigo primo.");
        System.out.println("4. Mostrar todos los codigos.");
        System.out.println("5. Mostrar cantidad total.");
        System.out.println("6. Generar numeros primos.");
        System.out.println("7. Exportar lista a archivo CSV.");
        System.out.println("8. Cargar codigos desde archivo.");
        System.out.println("0. Salir.");
    }

    private void agregarMensaje() {
        System.out.println("Ingrese el mensaje");
        String mensaje = input.nextLine();

        boolean exito = gestor.agregarCodigo(mensaje);
        if (exito) {
            System.out.println("Codigo agregado correctamente.");
        } else {
            System.out.println("No se pudo agregar el codigo.");
        }
    }

    private void eliminarMensajePorCodigo() {
        int codigo = leerEntero("Ingrese el codigo primo a eliminar: ");
        if (gestor.eliminarCodigo(codigo)) {
            System.out.println("Mensaje eliminado correctamente.");
        } else {
            System.out.println("No se encontro un mensaje con ese codigo.");
        }
    }

    private void buscarMensajePorCodigo() {
        int codigo = leerEntero("Ingrese el codigo primo a buscar: ");
        var resultado = gestor.buscarPorCodigo(codigo);
        if (resultado != null) {
            System.out.println("Mensaje encontrado: " + resultado);
        } else {
            System.out.println("No se encontro un mensaje con ese codigo.");
        }
    }

    private void mostrarTodosLosCodigos() {
        gestor.mostrarCodigos();
    }

    private void mostrarCantidadTotal() {
        System.out.println("Total de mensajes: " + gestor.getCantidadCodigos());
    }

    private int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.println(mensaje);
                return Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada invalida. Intente nuevamente.");
            }
        }
    }

    private void ejecutarGeneradorConcurrente() {
        System.out.println(" --- Generador ---");
        int cantidadHilos = leerEntero("Cuantos hilos desea ejecutar?");
        int ciclosPorHilo = leerEntero("Cuantos ciclos debe ejecutar cada hilo?");

        Thread[] hilos = new Thread[cantidadHilos];

        for (int i = 0; i < cantidadHilos; i++) {
            PrimesThread tarea = new PrimesThread(ciclosPorHilo, gestor.getListaCompartida());
            hilos[i] = new Thread(tarea, "Hilo-" + (i+1));
            hilos[i].start();
        }

        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                System.out.println("Hilo interrumpido: " + hilo.getName());
            }
        }

        System.out.println("Total de primos generados: " + gestor.getListaCompartida().getPrimesCount());
    }

    private void exportarListaCSV() {
        System.out.println("Ingrese el nombre del archivo a guardar: ");
        String nombreArchivo = input.nextLine();

        gestor.guardarCodigosEncriptados(nombreArchivo);
    }

    private void inportarCodigosCSV() {
        System.out.println("Nombre del archivo para cargar: ");
        String archivo = input.nextLine();
        gestor.cargarCodigosDesdeCSV(archivo);
    }
}
