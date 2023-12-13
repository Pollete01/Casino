/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Casino;

import java.util.Scanner;
import java.util.Arrays;

/**
 *
 * @author Pablo Cortes
 */
public class Loteria {

    public static void ini() {
        int tickets, numeros, boletosManual, random, boletos[][], boletoGanador[];
        double comprobarGanado;

        tickets = tickets();
        numeros = numeros();
        random = random();
        boletos = boletos(numeros, tickets);
        boletoGanador = boletoGanador();
        comprobarGanado = comprobarGanado(boletoGanador, boletos, tickets);
        balance(tickets, comprobarGanado);
    }

    public static int tickets() {
        Scanner sc = new Scanner(System.in);

        /*Le pregunto cuantos tickets quiere comprar*/
        System.out.println("¿Cuantos tickets quieres?, vale 5 euros cada ticket");
        int tickets = sc.nextInt();

        /*Hago un filtro para que no pueda pedir menos de 0 tickets, incluyendo el 0*/
        while (tickets < 1) {
            System.out.println("Tiene que pedir al menos 1,¿Cuantos tickets quieres?, vale 5 euros cada ticket");
            tickets = sc.nextInt();
        }

        return tickets;
    }

    public static int numeros() {
        Scanner sc = new Scanner(System.in);

        /*Le pregunto si quieres numeros aleatorios(1) o si quiere introducirlo
        manualmente(2) en los boletos que haya comprado*/
        System.out.println("¿Quieres numeros aleatorios en los boletos o manual?");
        System.out.println("1.Aleatorio");
        System.out.println("2.Manual");
        int respuesta = sc.nextInt();

        /*Hago un filtro para que solo pueda elegir una de las 2 opciones*/
        while (respuesta < 1 || respuesta > 2) {
            System.out.println("Elija una de las 2 opciones,¿Quieres numeros aleatorios en los boletos o manual?");
            System.out.println("1.Aleatorio");
            System.out.println("2.Manual");
            respuesta = sc.nextInt();
        }

        return respuesta;
    }

    public static int[][] boletos(int numeros, int tickets) {
        /*Hago que si elegió en la funcion de numeros la opcion de automatico 
        que es el 1, pues le lleve a la funcion boletosAutomatico, si no pues
        le lleva a la de manual*/
        int[][] boletos;
        if (numeros == 1) {
            boletos = boletosAutomatico(tickets);
        } else {
            boletos = boletosManual(tickets);
        }

        return boletos;
    }

    public static int[][] boletosAutomatico(int tickets) {
        Scanner sc = new Scanner(System.in);

        int[][] boleto = new int[tickets][5];
        int numeros;

        /*Aqui recorro el array creado para llenarlo de numeros random*/
        for (int i = 0; i < tickets; i++) {
            for (int j = 0; j < 5; j++) {
                numeros = random();
                boleto[i][j] = numeros;

            }
        }

        /*Hago que se muestre la array, poniendo que boleto es cada vez que se cambia de fila*/
        for (int i = 0; i < tickets; i++) {
            System.out.println("Boleto " + (i + 1) + ": " + Arrays.toString(boleto[i]));
        }
        return boleto;
    }

    public static int[][] boletosManual(int tickets) {
        Scanner sc = new Scanner(System.in);

        int[][] boleto = new int[tickets][5];
        int numeros;

        /*Por cada fila que se haga se va poniendo que boleto es, recorro el array
        recorro el array y le pregunto por cada columna que numero quiere*/
        for (int i = 0; i < tickets; i++) {
            System.out.println("Boleto " + (i + 1));

            for (int j = 0; j < 5; j++) {
                System.out.println("Dime los numeros que sea del 1 al 49");
                numeros = sc.nextInt();

                /*Hago un filtro para que solo pueda poner del 1 al 49*/
                while (numeros < 1 || numeros > 49) {
                    System.out.println("Numero incorrecto, pon del 1 al 49");
                    numeros = sc.nextInt();
                }

                boleto[i][j] = numeros;

            }
        }

        /*Hago que se muestre la array, poniendo que boleto es cada vez que se cambia de fila*/
        for (int i = 0; i < tickets; i++) {
            System.out.println("Boleto " + (i + 1) + ": " + Arrays.toString(boleto[i]));
        }
        return boleto;

    }

    public static int random() {
        /*Genera el numero random del 1 al 49*/
        int numeroRandom = (int) (Math.random() * 50 + 1);
        return numeroRandom;
    }

    public static int[] boletoGanador() {
        int[] boletoGanador = new int[5];

        /*Aqui creo un array que es el boleto ganador, lo recorro y llamo a la 
        funcion random para que ponga un numero random*/
        for (int i = 0; i < 5; i++) {
            int numeros = random();
            boletoGanador[i] = numeros;

        }

        /*Muestro el boleto ganador, pongo un sout arriba para que haya espacio 
        entre los boletos generados y el boleto ganador*/
        System.out.println();
        System.out.println("Boleto ganador " + Arrays.toString(boletoGanador));

        return boletoGanador;
    }

    public static double comprobarGanado(int boletoGanador[], int boletos[][], int tickets) {
        /*Cada acierto que haga asigno diferentes premios*/
        double premioPorAcierto1 = 50.0;
        double premioPorAcierto2 = 2200.0;
        double premioPorAcierto3 = 20000.0;
        double premioPorAcierto4 = 50000.0;
        double premioPorAcierto5 = 1000000.0;

        double totalAcumulado = 0.0;
        
        int aciertos;
        
        /*Creo una variable igual a 0 para calcular los aciertos de cada boleto,
        compruebo si el numero de la misma columna del boleto comprado y del boleto ganador
        es el mismo, si es el mismo sumo 1 a la variable aciertos*/
        for (int i = 0; i < tickets; i++) {
            aciertos = 0;

            for (int j = 0; j < 5; j++) {
                int numeroBoleto = boletos[i][j];
                int numeroGanador = boletoGanador[j];

                if (numeroBoleto == numeroGanador) {
                    aciertos++;
                }
            }

            double premioBoleto = 0.0;

            /*En cada vuelta del primer for, dependiendo de la variable aciertos,
            asigno el premio ganado de cada boleto en premioBoleto*/
            if (aciertos == 1) {
                premioBoleto = premioPorAcierto1;
            } else if (aciertos == 2) {
                premioBoleto = premioPorAcierto2;
            } else if (aciertos == 3) {
                premioBoleto = premioPorAcierto3;
            } else if (aciertos == 4) {
                premioBoleto = premioPorAcierto4;
            } else if (aciertos == 5) {
                premioBoleto = premioPorAcierto5;
            }

            /*Antes de terminar cada vuelta del primer for, guardo el premio del boleto en totalAcumulado
            y lo voy sumando en cada vuelta que haga para saber cuanto dinero he ganado en todos los boletos en total
            */
            totalAcumulado += premioBoleto;

            /*También muestro la cantidad de acierto de cada boleto y el premio de este, todo antes de cada vuelta del primer for
            porque si no se pierde con cada vuelta
            */
            System.out.println();
            System.out.println("En el boleto " + (i + 1) + " has acertado " + aciertos + " números en el mismo orden.");
            System.out.println("Premio para el boleto " + (i + 1) + ": " + premioBoleto + " euros.");

        }

        /*Muestro el dinero acumulado cuando ya haya acabado todo, pongo un sout arriba para que haya un espacio y se vea mejor*/
        System.out.println();
        System.out.println("Total acumulado: " + totalAcumulado + " euros.");

        return totalAcumulado;
    }

    public static void balance(int tickets, double comprobarGanado) {

        double ganancias;
        double dineroTickets = (tickets * 5.0);

        /*Resto lo que ha ganado con los boletos a los tickets que ha 
        comprado * 5 porque es lo que vale cada ticket y saber sus ganancias*/
        ganancias = comprobarGanado - dineroTickets;

        System.out.println("Dinero gastado en tickets: " + dineroTickets);
        System.out.println("Las ganancias totales son de: " + ganancias + " euros");

    }

}
