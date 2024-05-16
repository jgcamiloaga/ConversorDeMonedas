import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MenuParaElegir {
    private final String monedasDisponibles = """
                Monedas Disponibles:
                USD - Dólar Estadounidense
                ARS - Peso Argentino
                BRL - Real Brasileño
                GBP - Libra Esterlina
                RUB - Rublo Ruso
                PEN - Sol Peruano
                EUR - Euro""";

    public void mostrarMenuPrincipal(){
        System.out.println("-----------------------------------------------------");
        System.out.println("A continuación digíte la opción que desea realizar: \n");
        String menuPrincipal = """
                1) Realizar una conversión de moneda
                2) Consultar el historial de conversiones
                3) Salir del programa""";
        System.out.println(menuPrincipal);
    }

    public int leerOpcionPrincipal(){
        Scanner lectura = new Scanner(System.in);
        System.out.println("\n-----------------------------------------------------");
        System.out.println("|              Elija una opción válida              |");
        System.out.println("-----------------------------------------------------");
        return lectura.nextInt();
    }

    public void mostrarMenuMonedas(String denominacion){
        System.out.println("-----------------------------------------------------");
        System.out.println("       Seleccione el tipo de moneda " + denominacion + "       ");
        System.out.println("-----------------------------------------------------");
        System.out.println(monedasDisponibles);
        System.out.println("-----------------------------------------------------");
    }

    public String leerOpcionMoneda(){
        Scanner lectura = new Scanner(System.in);
        String opcion = lectura.nextLine().toLowerCase();
        while (!monedasDisponibles.toLowerCase().contains(opcion)) {
            System.out.println("La opción digitada no está disponible");
            System.out.println("Elija una opción válida");
            System.out.println("-----------------------------------------------------");
            opcion = lectura.nextLine().toLowerCase();
        }
        return opcion.toUpperCase();
    }

    public Double leerCantidadACambiar(){
        System.out.println("-----------------------------------------------------");
        System.out.println("Por favor, seleccione la cantidad que desea convertir:");
        System.out.println("-----------------------------------------------------");
        Scanner lectura = new Scanner(System.in);
        double cantidad;
        while (!lectura.hasNextDouble()) {
            System.out.println("-----------------------------------------------------");
            System.out.println("La cantidad ingresada no es válida como número");
            System.out.println("Ingrese una cantidad válida");
            System.out.println("-----------------------------------------------------");
            lectura.nextLine();
        }
        cantidad = lectura.nextDouble();
        lectura.nextLine();
        return cantidad;
    }

    public Double cantidadObtenida(String monedaBase, Double cantidadCambiar, Double tasaDeConversion, String monedaFinal){
        Double resultado = cantidadCambiar * tasaDeConversion;
        System.out.println("-----------------------------------------------------");
        System.out.println("|                 Cambio realizado                  |");
        System.out.println("-----------------------------------------------------");
        System.out.println("De " + cantidadCambiar + " " + monedaBase + " a " + resultado + " " + monedaFinal);
        return resultado;
    }

    public void imprimirConversiones(ArrayList<NombreDeMoneda> listaDeConversiones) {
        if (listaDeConversiones.isEmpty()) {
            System.out.println("No se han realizado conversiones.");
        } else {
            System.out.println("-----------------------------------------------------");
            System.out.println("|            Historial de conversiones              |");
            System.out.println("-----------------------------------------------------");
            for (int i = 0; i < listaDeConversiones.size(); i++) {
                NombreDeMoneda moneda = listaDeConversiones.get(i);
                System.out.println("Conversión " + (i + 1) + ":");
                System.out.println("Moneda origen: " + moneda.getMonedaBase());
                System.out.println("Moneda objetivo: " + moneda.getMonedaObjetivo());
                System.out.println("Cantidad a cambiar: " + moneda.getCantidadACambiar());
                System.out.println("Cantidad obtenida: " + moneda.getCantidadEnMonedaObjetivo());
                System.out.println("Fecha y hora: " + formatDateTime(moneda.getTiempo()));
                System.out.println("-----------------------------------------------------");
            }
        }
    }

    private String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
}