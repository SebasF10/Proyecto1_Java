/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1_java.Vista;


import com.mycompany.proyecto1_java.Controlador.PagoControlador;
import java.util.Scanner;

public class MenuPago {

    public static void mostrar(Scanner sc) {

        int opcion;

        do {
            System.out.println("---------------------------------");
            System.out.println("          MENU PAGOS");
            System.out.println("1. Registrar pago");
            System.out.println("2. Listar pagos");
            System.out.println("3. Buscar pago");
            System.out.println("4. Ver historial de pagos");
            System.out.println("5. Consultar saldo pendiente");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opcion: ");
            System.out.println("---------------------------------");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    PagoControlador.registrarPago(sc);
                    break;

                case 2:
                    PagoControlador.listarPagos();
                    break;

                case 3:
                    PagoControlador.buscarPago(sc);
                    break;
                case 4:
                    PagoControlador.verHistorialPagos(sc);
                    break;

                case 5:
                    PagoControlador.consultarSaldoPendiente(sc);
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