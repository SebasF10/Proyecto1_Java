/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1_java.Vista;

/**
 *
 * @author Sebas
 */

import com.mycompany.proyecto1_java.Controlador.PrestamoControlador;
import java.util.Scanner;

public class MenuPrestamo {

    public static void mostrar(Scanner sc) {

        int opcion;

        do {
            System.out.println("--------------------------------");
            System.out.println("        MENU PRESTAMOS");
            System.out.println("1. Crear prestamo");
            System.out.println("2. Listar prestamos");
            System.out.println("3. Buscar prestamo");
            System.out.println("4. Ver detalle del prestamo");
            System.out.println("5. Cambiar estado");
            System.out.println("6. Consultar prestamos activos");
            System.out.println("7. Consultar prestamos vencidos");
            System.out.println("0. Volver");
            System.out.print("Seleccione una opcion: ");
            System.out.println("--------------------------------");

            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    PrestamoControlador.crearPrestamo(sc);
                    break;

                case 2:
                    PrestamoControlador.listarPrestamos();
                    break;

                case 3:
                    System.out.println("Buscar prestamo");
                    break;

                case 4:
                    System.out.println("Ver detalle del prestamo");
                    break;

                case 5:
                    System.out.println("Cambiar estado");
                    break;

                case 6:
                    System.out.println("Consultar prestamos activos");
                    break;

                case 7:
                    System.out.println("Consultar prestamos vencidos");
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