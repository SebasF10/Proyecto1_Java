/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1_java.Vista;


import java.util.Scanner;

public class MenuReporte {

    public static void mostrar(Scanner sc) {

        int opcion;

        do {
            System.out.println("================================");
            System.out.println("         MENU REPORTES");
            System.out.println("================================");
            System.out.println("1. Prestamos activos");
            System.out.println("2. Prestamos vencidos");
            System.out.println("3. Clientes morosos");
            System.out.println("4. Prestamos pagados");
            System.out.println("5. Total prestado");
            System.out.println("6. Total recaudado");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opcion: ");
            System.out.println("================================");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Prestamos activos");
                    break;

                case 2:
                    System.out.println("Prestamos vencidos");
                    break;

                case 3:
                    System.out.println("Clientes morosos");
                    break;

                case 4:
                    System.out.println("Prestamos pagados");
                    break;

                case 5:
                    System.out.println("Total prestado");
                    break;

                case 6:
                    System.out.println("Total recaudado");
                    break;

                case 0:
                    System.out.println("Volviendo al menu principal...");
                    break;

                default:
                    System.out.println("Opcion no válida.");
            }

        } while (opcion != 0);
    }
}