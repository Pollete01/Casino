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
public class Menu {
    
   
    public static void ini() {
        /*Aqui llamo a la funcion ejecutarOpcion y el parametro de dentro llama 
        a la funcion menu que hace ver que her marcado si 1 o 2*/
        
        ejecutarOpcion(menu());
    }
    
    public static int menu() {
        Scanner sc= new Scanner(System.in);
        
        /*Aqui pregunto a que quieres entrar,1 es ruleta y 2 es loteria*/
        System.out.println("Bienvenido al Casino de Victoria FP, ¿a que desea jugar?");
        System.out.println("1.Ruleta");
        System.out.println("2.Loteria");
        int juego = sc.nextInt();
        
        /*Aqui hace que si nosotros ponemos un numero menor a 1 o mayor que 2 
        lo vuelva a preguntar hasta que popnga el 1 o el 2*/
        while (juego <1 || juego >2){
            System.out.println("Bienvenido al Casino de Victoria FP, ¿a que desea jugar?");
        System.out.println("1.Ruleta");
        System.out.println("2.Loteria");
        juego = sc.nextInt();
        }
       return juego;
    }
    
    public static void ejecutarOpcion(int respuesta) {
        /*Si lo que marqué en el menu es 1 me lleva a la ruleta y si es 2
        a la loteria*/
        if (respuesta == 1){
                Ruleta.ini();
            }else if (respuesta == 2){
                Loteria.ini();
            } 
    }

}
