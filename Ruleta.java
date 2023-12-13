/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Casino;


import java.util.Scanner;

/**
 *
 * @author Pablo Cortes
 */
public class Ruleta {

    public static void ini() {
        boolean continuar = true;
        int dineroInicial, tipoApuesta, irJuego, apuesta, random, ganadoPerdido,dineroActual;

        dineroInicial = dineroInicial();
        dineroActual = dineroInicial;

        while (continuar) {
            tipoApuesta = tipoApuesta();
            irJuego = irJuego(tipoApuesta);
            apuesta = apuesta(dineroInicial);
            random = random();
            ganadoPerdido = ganadoPerdido(tipoApuesta, random, irJuego);
            dineroInicial = actualizarDinero(dineroInicial, apuesta, ganadoPerdido, tipoApuesta);
            mostrar(dineroInicial, dineroActual);
            continuar = continuar(dineroInicial);
        }

    }

    public static int dineroInicial() {
        Scanner sc = new Scanner(System.in);

        /*Aqui nos pregunta cuanto dinero vamos a meter incialmente*/
        System.out.println("Cuanto dinero vas a meter en juego");
        int cantidad = sc.nextInt();

        /*Aqui hago un filtro para que no pueda meter 0 o menos euros en dinero inicial*/
        while (cantidad <= 0) {
            System.out.println("Tienes que meter un saldo positivo mayor que 0,¿Cuanto dineros vas a meter en juego");
            cantidad = sc.nextInt();
        }

        return cantidad;
    }

    public static int tipoApuesta() {
        Scanner sc = new Scanner(System.in);

        /*Aqui solo recoge el dato de que apuesta queremos*/
        System.out.println("¿Que tipo de apuesta quieres?");
        System.out.println("1.Colores");
        System.out.println("2.Par o Impar");
        System.out.println("3.Numeros");
        System.out.println("4.Filas");
        int juego = sc.nextInt();

        /*Aqui pongo un filtro para que tenga que solo elegir una de esas 4 opciones*/
        while (juego < 1 || juego > 4) {
            System.out.println("Elige una de las 4 opciones,¿Que tipo de apuesta quieres?");
            System.out.println("1.Colores");
            System.out.println("2.Par o Impar");
            System.out.println("3.Numeros");
            System.out.println("4.Filas");
            juego = sc.nextInt();
        }

        return juego;
    }

    public static int irJuego(int tipoApuesta) {
        Scanner sc = new Scanner(System.in);

        /*Aqui compara que elegimos en tipoApuesta, te pregunta las opciones 
        que hay dentro del tipo de apuesta que elegiste y recoge el dato.
        Los while que hay es para poner un filtro que solo pueda elegir entre las opciones que pido*/
        if (tipoApuesta == 1) {
            System.out.println("¿A que color quieres apostar?");
            System.out.println("1.Rojo");
            System.out.println("2.Negro");
            int color = sc.nextInt();

            while (color < 1 || color > 2) {
                System.out.println("Elige una de las 2,¿A que color quieres apostar?");
                System.out.println("1.Rojo");
                System.out.println("2.Negro");
                color = sc.nextInt();
            }
            return color;

        } else if (tipoApuesta == 2) {
            System.out.println("¿A qué quieres apostar a Par o Impar?");
            System.out.println("1.Par");
            System.out.println("2.Impar");
            int parImp = sc.nextInt();

            while (parImp < 1 || parImp > 2) {
                System.out.println("Elige una de las 2,¿A qué quieres apostar a Par o Impar?");
                System.out.println("1.Par");
                System.out.println("2.Impar");
                parImp = sc.nextInt();
            }
            return parImp;

        } else if (tipoApuesta == 3) {
            System.out.println("¿A que numero quieres apostar entre el 0 y 36?");
            int numero = sc.nextInt();

            while (numero < 0 || numero > 36) {
                System.out.println("Tiene que ser un numero entre 0 y 36,¿A que numero quieres apostar entre el 0 y 36?");
                numero = sc.nextInt();
            }
            return numero;

        } else if (tipoApuesta == 4) {
            System.out.println("¿A que fila quieres apostar?");
            System.out.println("1.Fila 1");
            System.out.println("2.Fila 2");
            System.out.println("3.Fila 3");
            int fila = sc.nextInt();

            while (fila < 1 || fila > 3) {
                System.out.println("Elige una de las 3 opciones,¿A que fila quieres apostar?");
                System.out.println("1.Fila 1");
                System.out.println("2.Fila 2");
                System.out.println("3.Fila 3");
                fila = sc.nextInt();
            }
            return fila;
        }

        return tipoApuesta;
    }

    public static int apuesta(int dineroInicial) {
        Scanner sc = new Scanner(System.in);

        /*Recoge el dato de cuanto dinero vas a apostar en la ronda*/
        System.out.println("Cuanto dinero vas a apostar");
        int apuesta = sc.nextInt();
        System.out.println();

        /*Pongo un filtro para que no puedas apostar más que el dineroInicial ni menos que 0*/
        while (apuesta > dineroInicial || apuesta <= 0) {
            System.out.println("Dime de nuevo cuanto dinero vas a apostar");
            apuesta = sc.nextInt();
            System.out.println();
        }
        return apuesta;
    }

    public static int random() {
        /*Genera el numero random*/
        int numeroRandom = (int) (Math.random() * 37);
        return numeroRandom;
    }

    public static int ganadoPerdido(int tipoApuesta, int random, int irJuego) {
        /*En esta funcion recoge el valor que tiene la funcion de cada apuesta 
          y la guarda en variable res*/

        int res = 0;
        if (tipoApuesta == 1) {
            res = color(random, irJuego, tipoApuesta);
        } else if (tipoApuesta == 2) {
            res = parImpar(random, irJuego, tipoApuesta);
        } else if (tipoApuesta == 3) {
            res = numeros(random, irJuego);
        } else if (tipoApuesta == 4) {
            res = filas(random, irJuego);
        }
        return res;
    }

    public static int color(int random, int irJuego, int tipoApuesta) {
        int[] rojo = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
        int[] negro = {2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35};
        int res = 3;
        int com = 0;
        System.out.println("El numero random es: " + random);

        /*Aqui comprueba que el numero random esta en el array rojo, 
        si lo esta devuelve valor 1 en la variable res*/
        for (int i = 0; i < rojo.length; i++) {
            if (random == rojo[i]) {
                System.out.println("El número es rojo");
                res = 1;
            }
        }

        /*Aqui comprueba que el numero random esta en el array negro, 
        si lo esta devuelve valor 2 en la variable res*/
        for (int i = 0; i < negro.length; i++) {
            if (random == negro[i]) {
                System.out.println("El numero es negro");
                res = 2;
            }
        }

        /*Si la opcion que elegimos en la funcion irJuego es la misma que el 
        valor que devuelve la variable res, es que hemos ganado,
        si no es la misma hemos perdido.
        Cuando hemos ganado recoge en la variable com el valor 1, cuando hemos 
        perdido recoge el valor 2.
        Esta funcion color() devuelve el valor que recoge la variable com.*/
        if (irJuego == res) {
            com = 1;
        } else {
            com = 2;
        }

        return com;

    }

    public static int parImpar(int random, int irJuego, int tipoApuesta) {
        int[] par = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36};
        int[] impar = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 3, 37};

        int res = 3;
        int com = 0;
        System.out.println("El numero random es: " + random);

        /*Aqui comprueba que el numero random esta en el array par, 
        si lo esta devuelve valor 1 en la variable res*/
        for (int i = 0; i < par.length; i++) {
            if (random == par[i]) {
                System.out.println("El número es par");
                res = 1;
            }
        }

        /*Aqui comprueba que el numero random esta en el array impar, 
        si lo esta devuelve valor 2 en la variable res*/
        for (int i = 0; i < impar.length; i++) {
            if (random == impar[i]) {
                System.out.println("El numero es impar");
                res = 2;
            }
        }

        /*Si la opcion que elegimos en la funcion irJuego es la misma que el 
        valor que devuelve la variable res, es que hemos ganado,
        si no es la misma hemos perdido.
        Cuando hemos ganado recoge en la variable com el valor 1, cuando hemos 
        perdido recoge el valor 2.
        Esta funcion parImpar() devuelve el valor que recoge la variable com.*/
        if (irJuego == res) {
            com = 1;
        } else {
            com = 2;
        }

        return com;
    }

    public static int numeros(int random, int irJuego) {
        System.out.println("El numero random es: " + random);

        int com = 0;

        /*Si la opcion que elegimos en la funcion irJuego es la misma que el 
        valor que devuelve la funcion random(), es que hemos ganado,
        si no es la misma hemos perdido.
        Cuando hemos ganado recoge en la variable com el valor 1, cuando hemos 
        perdido recoge el valor 2.*/
        if (irJuego == random) {
            System.out.println("Felicidades has ganado");
            com = 1;
        } else {
            System.out.println("Lo siento no has tenido suerte");
            com = 2;
        }

        return com;

    }

    public static int filas(int random, int irJuego) {
        System.out.println("El numero random es: " + random);
        int[] primera = {1, 4, 7, 10, 13, 16, 19, 22, 25, 28, 31, 34};
        int[] segunda = {2, 5, 8, 11, 14, 17, 20, 23, 26, 29, 32, 35};
        int[] tercera = {3, 6, 9, 12, 15, 18, 21, 24, 27, 30, 33, 36};

        int res = 0;

        /*Aqui me devuelve en la variable res el valor 1 si está en la primera fila,
        el valor 2 si está en la segunda fila, el valor 3 si esta en la tercera fila
        */
        for (int i = 0; i < primera.length; i++) {
            if (random == primera[i]) {
                System.out.println("El número está en la primera fila");
                res = 1;
            }
        }

        for (int i = 0; i < segunda.length; i++) {
            if (random == segunda[i]) {
                System.out.println("El número está en la segunda fila");
                res = 2;
            }
        }

        for (int i = 0; i < tercera.length; i++) {
            if (random == tercera[i]) {
                System.out.println("El número está en la tercera fila");
                res = 3;
            }
        }

        int com = 0;
        
        /*Si la opcion que elegimos en la funcion irJuego es la misma que el 
        valor que devuelve la variable res, es que hemos ganado,
        si no es la misma hemos perdido.
        Cuando hemos ganado recoge en la variable com el valor 1, cuando hemos 
        perdido recoge el valor 2.
        Esta funcion parImpar() devuelve el valor que recoge la variable com.*/
        if (irJuego == res) {
            com = 1;
        } else {
            com = 2;
        }

        return com;

    }

    public static int actualizarDinero(int dineroActual, int apuesta, int ganadoPerdido, int tipoApuesta) {
        /*Si el tipoApuesta que hemos elegido es 1 o 2, es decir, Color o ParImpar,
        compara si la función ganadoPerdido() devuelve valor 1,
        si es así hace que el dinero apostado se multiplique por 2 y lo sume al dinero inicial, 
        si no devuelve valor 1 es que ha perdido y resta el dinero apostado al dinero incial.
        
        Si el tipoApuesta es 3, es decir, Numeros, compara si la función ganadoPerdido() devuelve valor 1,
        si es así hace que el dinero apostado se multiplique por 36 y lo sume al dinero inicial, 
        si no devuelve valor 1 es que ha perdido y resta el dinero apostado al dinero incial.*/

        if (tipoApuesta == 1 || tipoApuesta == 2) {
            if (ganadoPerdido == 1) {
                dineroActual = dineroActual - apuesta;
                dineroActual += apuesta * 2;
                return dineroActual;
            } else {
                dineroActual = dineroActual - apuesta;
                return dineroActual;
            }

        } else if (tipoApuesta == 3) {
            if (ganadoPerdido == 1) {
                dineroActual = dineroActual - apuesta;
                dineroActual += apuesta * 36;
                return dineroActual;
            } else {
                dineroActual = dineroActual - apuesta;
                return dineroActual;
            }
        } else if (tipoApuesta == 4) {
            if (ganadoPerdido == 1) {
                dineroActual = dineroActual - apuesta;
                dineroActual += apuesta * 3;
                return dineroActual;
            } else {
                dineroActual = dineroActual - apuesta;
                return dineroActual;
            }
        }

        return dineroActual;

    }
    
    public static void mostrar(int dineroInicial,int dineroActual) {
        int ganancias= 0;
        int diferencia=0;
        
        /*Aqui ponemos el dinero inicial y el actual que tenemos,pongo los sout
        en blanco para que se visualize mejor en la terminal*/
        System.out.println();
        System.out.println("Tu dinero inicial es de: " + dineroActual);
        System.out.println("Tu dinero actual es de: " + dineroInicial);
        
        /*Aqui hacemos el calculo de las ganacias o perdidas que hemos tenido*/
        diferencia = dineroActual - dineroInicial;
        ganancias = ganancias - diferencia;
        System.out.println("Las ganancias que tienes son de: "+ganancias);
        System.out.println();
    }

    public static boolean continuar(int dineroInicial) {
        /*Aqui hace que si el dineroInicial es menos que cero devuelve falso y
        hace que en la función ini() se pare el bucle porque hemos hecho que si 
        devuelve false aqui, la condicion del bucle de la funcion ini() tambien lo sea*/

        if (dineroInicial <= 0) {
            System.out.println("Te has quedado sin blanca, vuelve cuando tengas dinero ");
            return false;
        } else {

            Scanner sc = new Scanner(System.in);
            System.out.println("¿Desea continuar?");
            System.out.println("1.Si");
            System.out.println("2.No");
            int respuesta = sc.nextInt();

            while (respuesta < 1 || respuesta > 2) {
                System.out.println("Elija una de las 2 opciones,¿Desea continuar?");
                System.out.println("1.Si");
                System.out.println("2.No");
                respuesta = sc.nextInt();
            }

            /*Si la respuesta tiene valor 1 devuelve true y continuas en el bucle de la ruleta,
        si no es valor 1 devuelve false y te sale del bucle.*/
            if (respuesta == 1) {
                return true;
            } else {
                return false;
            }
        }
    }

}
