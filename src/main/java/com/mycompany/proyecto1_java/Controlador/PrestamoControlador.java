/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1_java.Controlador;

import com.mycompany.proyecto1_java.Modelo.Persistencia.ConexionBD;
import com.mycompany.proyecto1_java.Modelo.Persistencia.Operaciones;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class PrestamoControlador {

    // CREAR PRÉSTAMO
    public static void crearPrestamo(Scanner sc) {

        sc.nextLine();

        System.out.println("-------- CREAR PRÉSTAMO --------");

        System.out.print("ID del cliente: ");
        int clienteId = sc.nextInt();

        System.out.print("ID del empleado: ");
        int empleadoId = sc.nextInt();

        System.out.print("Monto del préstamo: ");
        double monto = sc.nextDouble();

        System.out.print("Interés (%): ");
        double interes = sc.nextDouble();

        System.out.print("Número de cuotas: ");
        int cuotas = sc.nextInt();

        sc.nextLine();

        System.out.print("Fecha de inicio (AAAA-MM-DD): ");
        LocalDate fechaInicio = LocalDate.parse(sc.nextLine());

        System.out.print("Fecha de vencimiento (AAAA-MM-DD): ");
        LocalDate fechaVencimiento = LocalDate.parse(sc.nextLine());

        // Cálculo del préstamo
        
        double montoInteres = monto * interes / 100;
        double montoTotal = monto + montoInteres;
        double valorCuota = montoTotal / cuotas;
        double saldoPendiente = montoTotal;

        String estado = "PENDIENTE";

        try {

            Operaciones.setConnection(ConexionBD.MysConnection());

            String sql = "INSERT INTO prestamos "
                    + "(cliente_id, empleado_id, monto, interes, cuotas, "
                    + "fecha_inicio, estado, monto_total, valor_cuota, "
                    + "saldo_pendiente, fecha_vencimiento) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps =
                    Operaciones.getConnection().prepareStatement(sql);

            ps.setInt(1, clienteId);
            ps.setInt(2, empleadoId);
            ps.setDouble(3, monto);
            ps.setDouble(4, interes);
            ps.setInt(5, cuotas);
            ps.setDate(6, java.sql.Date.valueOf(fechaInicio));
            ps.setString(7, estado);
            ps.setDouble(8, montoTotal);
            ps.setDouble(9, valorCuota);
            ps.setDouble(10, saldoPendiente);
            ps.setDate(11, java.sql.Date.valueOf(fechaVencimiento));

            int filas =
                    Operaciones.insertar_actualizar_borrar_BD(ps);

            if (filas > 0) {
                System.out.println("Préstamo creado correctamente.");
                System.out.println("Monto total: $" + montoTotal);
                System.out.println("Valor de cada cuota: $" + valorCuota);
                System.out.println("Saldo pendiente: $" + saldoPendiente);
                System.out.println("Estado: " + estado);
            } else {
                System.out.println("No se pudo crear el préstamo.");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    // LISTAR PRÉSTAMOS
    public static void listarPrestamos() {

        try {

            Operaciones.setConnection(ConexionBD.MysConnection());

            String sql = "SELECT * FROM prestamos";

            PreparedStatement ps =
                    Operaciones.getConnection().prepareStatement(sql);

            ResultSet rs =
                    Operaciones.consultar_BD(ps);

            System.out.println("--------- PRÉSTAMOS ----------");

            while (rs.next()) {

                System.out.println("----------------------------");

                System.out.println("ID: "
                        + rs.getInt("id"));

                System.out.println("Cliente ID: "
                        + rs.getInt("cliente_id"));

                System.out.println("Empleado ID: "
                        + rs.getInt("empleado_id"));

                System.out.println("Monto: $"
                        + rs.getDouble("monto"));

                System.out.println("Interés: "
                        + rs.getDouble("interes") + "%");

                System.out.println("Cuotas: "
                        + rs.getInt("cuotas"));

                System.out.println("Fecha inicio: "
                        + rs.getDate("fecha_inicio"));

                System.out.println("Fecha vencimiento: "
                        + rs.getDate("fecha_vencimiento"));

                System.out.println("Monto total: $"
                        + rs.getDouble("monto_total"));

                System.out.println("Valor cuota: $"
                        + rs.getDouble("valor_cuota"));

                System.out.println("Saldo pendiente: $"
                        + rs.getDouble("saldo_pendiente"));

                System.out.println("Estado: "
                        + rs.getString("estado"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //Buscar
    
    public static void buscarPrestamo(Scanner sc) {

        System.out.print("ID del Cliente: ");
        int id = sc.nextInt();

        try {

            Operaciones.setConnection(
                    ConexionBD.MysConnection()
            );

            String sql =
                    "SELECT * FROM clientes WHERE id = ?";

            PreparedStatement ps =
                    Operaciones.getConnection().prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs =
                    Operaciones.consultar_BD(ps);

            if (rs.next()) {
                
                System.out.println("--------------------------------");
                
                System.out.println("Empleado encontrado:");

                System.out.println("ID: " +
                        rs.getInt("id"));

                System.out.println("Nombre: " +
                        rs.getString("nombre"));

                System.out.println("Documento: " +
                        rs.getString("documento"));

                System.out.println("Correo: " +
                        rs.getString("correo"));

            } else {
                System.out.println("Cliente no encontrado.");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
