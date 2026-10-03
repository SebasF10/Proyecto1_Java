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
    
    // BUSCAR 
    public static void buscarPrestamo(Scanner sc) {

        System.out.print("\nID del préstamo: ");
        int id = sc.nextInt();

        try {

            Operaciones.setConnection(ConexionBD.MysConnection());

            String sql =
                    "SELECT * FROM prestamos WHERE id = ?";

            PreparedStatement ps =
                    Operaciones.getConnection().prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs =
                    Operaciones.consultar_BD(ps);

            if (rs.next()) {

                System.out.println("--------- PRESTAMO ENCONTRADO --------");

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

            } else {

                System.out.println("Prestamo no encontrado.");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    // VER DETALLE DEL PRESTAMO
    public static void verDetallePrestamo(Scanner sc) {

        System.out.print("ID del préstamo: ");
        int id = sc.nextInt();

        try {

            Operaciones.setConnection(ConexionBD.MysConnection());

            String sql =
                    "SELECT p.*, "
                    + "c.nombre AS nombre_cliente, "
                    + "e.nombre AS nombre_empleado "
                    + "FROM prestamos p "
                    + "INNER JOIN clientes c ON p.cliente_id = c.id "
                    + "INNER JOIN empleados e ON p.empleado_id = e.id "
                    + "WHERE p.id = ?";

            PreparedStatement ps =
                    Operaciones.getConnection().prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs =
                    Operaciones.consultar_BD(ps);

            if (rs.next()) {

                System.out.println("------- DETALLE DEL PRÉSTAMO -------");

                System.out.println("ID préstamo: "
                        + rs.getInt("id"));

                System.out.println("Cliente: "
                        + rs.getString("nombre_cliente"));

                System.out.println("Empleado: "
                        + rs.getString("nombre_empleado"));

                System.out.println("Monto: $"
                        + rs.getDouble("monto"));

                System.out.println("Interés: "
                        + rs.getDouble("interes") + "%");

                System.out.println("Número de cuotas: "
                        + rs.getInt("cuotas"));

                System.out.println("Monto total: $"
                        + rs.getDouble("monto_total"));

                System.out.println("Valor de cuota: $"
                        + rs.getDouble("valor_cuota"));

                System.out.println("Saldo pendiente: $"
                        + rs.getDouble("saldo_pendiente"));

                System.out.println("Fecha inicio: "
                        + rs.getDate("fecha_inicio"));

                System.out.println("Fecha vencimiento: "
                        + rs.getDate("fecha_vencimiento"));

                System.out.println("Estado: "
                        + rs.getString("estado"));

            } else {

                System.out.println("Prestamo no encontrado.");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    // CAMBIAR ESTADO
    public static void cambiarEstado(Scanner sc) {

        System.out.print("ID del préstamo: ");
        int id = sc.nextInt();

        sc.nextLine();

        System.out.print("Nuevo estado: ");
        String estado = sc.nextLine();

        try {

            Operaciones.setConnection(ConexionBD.MysConnection());

            String sql =
                    "UPDATE prestamos SET estado = ? WHERE id = ?";

            PreparedStatement ps =
                    Operaciones.getConnection().prepareStatement(sql);

            ps.setString(1, estado);
            ps.setInt(2, id);

            int filas =
                    Operaciones.insertar_actualizar_borrar_BD(ps);

            if (filas > 0) {
                System.out.println("Estado actualizado.");
            } else {
                System.out.println("No se encontró el préstamo.");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    // PRÉSTAMOS ACTIVOS
    public static void consultarPrestamosActivos() {

        try {

            Operaciones.setConnection(ConexionBD.MysConnection());

            String sql =
                    "SELECT * FROM prestamos WHERE estado = 'ACTIVO'";

            PreparedStatement ps =
                    Operaciones.getConnection().prepareStatement(sql);

            ResultSet rs =
                    Operaciones.consultar_BD(ps);

            System.out.println("--------- PRÉSTAMOS ACTIVOS ---------");

            while (rs.next()) {

                System.out.println("----------------------------");

                System.out.println("ID: "
                        + rs.getInt("id"));

                System.out.println("Cliente ID: "
                        + rs.getInt("cliente_id"));

                System.out.println("Monto total: $"
                        + rs.getDouble("monto_total"));

                System.out.println("Saldo pendiente: $"
                        + rs.getDouble("saldo_pendiente"));

                System.out.println("Estado: "
                        + rs.getString("estado"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    // PRÉSTAMOS VENCIDOS
    public static void consultarPrestamosVencidos() {

        try {

            Operaciones.setConnection(ConexionBD.MysConnection());

            String sql =
                    "SELECT * FROM prestamos "
                    + "WHERE fecha_vencimiento < CURDATE() "
                    + "AND saldo_pendiente > 0";

            PreparedStatement ps =
                    Operaciones.getConnection().prepareStatement(sql);

            ResultSet rs =
                    Operaciones.consultar_BD(ps);

            System.out.println("------- PRÉSTAMOS VENCIDOS ---------");

            while (rs.next()) {

                System.out.println("----------------------------");

                System.out.println("ID: "
                        + rs.getInt("id"));

                System.out.println("Cliente ID: "
                        + rs.getInt("cliente_id"));

                System.out.println("Monto total: $"
                        + rs.getDouble("monto_total"));

                System.out.println("Saldo pendiente: $"
                        + rs.getDouble("saldo_pendiente"));

                System.out.println("Fecha vencimiento: "
                        + rs.getDate("fecha_vencimiento"));

                System.out.println("Estado: "
                        + rs.getString("estado"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
