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
import java.util.Scanner;
/**
 *
 * @author Sebas
 */
public class PagoControlador {
    
    // REGISTRAR PAGO
    public static void registrarPago(Scanner sc) {

        System.out.println("------- REGISTRAR PAGO -------- ");

        System.out.print("ID del prestamo: ");
        int prestamoId = sc.nextInt();

        System.out.print("Monto del pago: ");
        double montoPago = sc.nextDouble();

        try {

            Operaciones.setConnection(ConexionBD.MysConnection());

            // Buscar saldo actual del préstamo
            String sqlConsulta =
                    "SELECT saldo_pendiente FROM prestamos WHERE id = ?";

            PreparedStatement psConsulta =
                    Operaciones.getConnection()
                            .prepareStatement(sqlConsulta);

            psConsulta.setInt(1, prestamoId);

            ResultSet rs =
                    Operaciones.consultar_BD(psConsulta);

            if (!rs.next()) {
                System.out.println("El prestamo no existe.");
                return;
            }

            double saldoActual =
                    rs.getDouble("saldo_pendiente");

            // Verificar que el pago no sea mayor al saldo
            if (montoPago <= 0) {
                System.out.println("El monto del pago debe ser mayor que 0.");
                return;
            }

            if (montoPago > saldoActual) {
                System.out.println("El pago no puede ser mayor al saldo pendiente.");
                return;
            }

            // Registrar el pago
            String sqlPago =
                    "INSERT INTO pagos "
                    + "(prestamo_id, fecha_pago, monto) "
                    + "VALUES (?, CURDATE(), ?)";

            PreparedStatement psPago =
                    Operaciones.getConnection()
                            .prepareStatement(sqlPago);

            psPago.setInt(1, prestamoId);
            psPago.setDouble(2, montoPago);

            int filas =
                    Operaciones.insertar_actualizar_borrar_BD(psPago);

            if (filas > 0) {

                // Calcular nuevo saldo
                double nuevoSaldo =
                        saldoActual - montoPago;

                // Actualizar saldo
                String sqlActualizar =
                        "UPDATE prestamos "
                        + "SET saldo_pendiente = ? "
                        + "WHERE id = ?";

                PreparedStatement psActualizar =
                        Operaciones.getConnection()
                                .prepareStatement(sqlActualizar);

                psActualizar.setDouble(1, nuevoSaldo);
                psActualizar.setInt(2, prestamoId);

                Operaciones.insertar_actualizar_borrar_BD(
                        psActualizar
                );

                // Si el saldo llega a 0, marcar como pagado
                if (nuevoSaldo == 0) {

                    String sqlEstado =
                            "UPDATE prestamos "
                            + "SET estado = 'PAGADO' "
                            + "WHERE id = ?";

                    PreparedStatement psEstado =
                            Operaciones.getConnection()
                                    .prepareStatement(sqlEstado);

                    psEstado.setInt(1, prestamoId);

                    Operaciones.insertar_actualizar_borrar_BD(
                            psEstado
                    );

                    System.out.println("Prestamo pagado completamente.");

                } else {

                    System.out.println("Pago registrado correctamente.");
                }

                System.out.println("Saldo pendiente: $"
                        + nuevoSaldo);

            } else {

                System.out.println("No se pudo registrar el pago.");
            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
    }
    
    // LISTAR PAGOS
    public static void listarPagos() {

        try {

            Operaciones.setConnection(ConexionBD.MysConnection());

            String sql = "SELECT * FROM pagos";

            PreparedStatement ps =
                    Operaciones.getConnection()
                            .prepareStatement(sql);

            ResultSet rs =
                    Operaciones.consultar_BD(ps);

            System.out.println("------ PAGOS -------");

            while (rs.next()) {

                System.out.println("----------------------------");

                System.out.println("ID pago: "
                        + rs.getInt("id"));

                System.out.println("ID prestamo: "
                        + rs.getInt("prestamo_id"));

                System.out.println("Fecha: "
                        + rs.getDate("fecha_pago"));

                System.out.println("Monto: $"
                        + rs.getDouble("monto"));
            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
    }


    // BUSCAR PAGO
    public static void buscarPago(Scanner sc) {

        System.out.print("ID del pago: ");
        int id = sc.nextInt();

        try {

            Operaciones.setConnection(ConexionBD.MysConnection());

            String sql =
                    "SELECT * FROM pagos WHERE id = ?";

            PreparedStatement ps =
                    Operaciones.getConnection()
                            .prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs =
                    Operaciones.consultar_BD(ps);

            if (rs.next()) {

                System.out.println("------ PAGO ENCONTRADO ------");

                System.out.println("ID pago: "
                        + rs.getInt("id"));

                System.out.println("ID prestamo: "
                        + rs.getInt("prestamo_id"));

                System.out.println("Fecha: "
                        + rs.getDate("fecha_pago"));

                System.out.println("Monto: $"
                        + rs.getDouble("monto"));

            } else {

                System.out.println("Pago no encontrado.");
            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
    }


    // HISTORIAL DE PAGOS
    public static void verHistorialPagos(Scanner sc) {

        System.out.print("ID del préstamo: ");
        int prestamoId = sc.nextInt();

        try {

            Operaciones.setConnection(ConexionBD.MysConnection());

            String sql =
                    "SELECT * FROM pagos "
                    + "WHERE prestamo_id = ? "
                    + "ORDER BY fecha_pago";

            PreparedStatement ps =
                    Operaciones.getConnection()
                            .prepareStatement(sql);

            ps.setInt(1, prestamoId);

            ResultSet rs =
                    Operaciones.consultar_BD(ps);

            System.out.println("------ HISTORIAL DE PAGOS ------");

            boolean tienePagos = false;

            while (rs.next()) {

                tienePagos = true;

                System.out.println("----------------------------");

                System.out.println("ID pago: "
                        + rs.getInt("id"));

                System.out.println("Fecha: "
                        + rs.getDate("fecha_pago"));

                System.out.println("Monto: $"
                        + rs.getDouble("monto"));
            }

            if (!tienePagos) {
                System.out.println("Este prestamo no tiene pagos registrados.");
            }

        } catch (SQLException ex) {

            System.out.println(ex.getMessage());
        }
    }


    // CONSULTAR SALDO PENDIENTE
    public static void consultarSaldoPendiente(Scanner sc) {

        System.out.print("ID del préstamo: ");
        int prestamoId = sc.nextInt();

        try {

            Operaciones.setConnection(ConexionBD.MysConnection());

            String sql =
                    "SELECT saldo_pendiente, estado "
                    + "FROM prestamos "
                    + "WHERE id = ?";

            PreparedStatement ps =
                    Operaciones.getConnection()
                            .prepareStatement(sql);

            ps.setInt(1, prestamoId);

            ResultSet rs =
                    Operaciones.consultar_BD(ps);

            if (rs.next()) {

                System.out.println("-------- SALDO PENDIENTE -------");

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
}
