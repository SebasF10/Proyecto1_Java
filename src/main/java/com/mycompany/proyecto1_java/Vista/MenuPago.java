/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1_java.Vista;


import java.util.Scanner;

public class MenuPago {

    public static void mostrar(Scanner sc) {

        int opcion;

        do {
            System.out.println("================================");
            System.out.println("          MENU PAGOS");
            System.out.println("================================");
            System.out.println("1. Registrar pago");
            System.out.println("2. Listar pagos");
            System.out.println("3. Buscar pago");
            System.out.println("4. Ver historial de pagos");
            System.out.println("5. Consultar saldo pendiente");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opcion: ");
            System.out.println("================================");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Registrar pago");
                    break;

                case 2:
                    System.out.println("Listar pagos");
                    break;

                case 3:
                    System.out.println("Buscar pago");
                    break;

                case 4:
                    System.out.println("Ver historial de pagos");
                    break;

                case 5:
                    System.out.println("Consultar saldo pendiente");
                    break;

                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opcion no válida.");
            }

        } while (opcion != 0);
    }
}