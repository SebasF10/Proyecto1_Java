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

public class EmpleadoControlador {

    // REGISTRAR
    public static void registrarEmpleado(Scanner sc) {

        sc.nextLine();

        System.out.println("===== REGISTRAR EMPLEADO =====");

        System.out.print("Nombre: ");
        String nombre = sc.nextLine();

        System.out.print("Documento: ");
        String documento = sc.nextLine();

        System.out.print("Rol: ");
        String rol = sc.nextLine();

        System.out.print("Correo: ");
        String correo = sc.nextLine();

        System.out.print("Salario: ");
        double salario = sc.nextDouble();

        try {

            Operaciones.setConnection(
                    ConexionBD.MysConnection()
            );

            String sql = "INSERT INTO empleados "
                    + "(nombre, documento, rol, correo, salario) "
                    + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps =
                    Operaciones.getConnection().prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setString(2, documento);
            ps.setString(3, rol);
            ps.setString(4, correo);
            ps.setDouble(5, salario);

            int filas =
                    Operaciones.insertar_actualizar_borrar_BD(ps);

            if (filas > 0) {
                System.out.println("Empleado registrado.");
            } else {
                System.out.println("No se pudo registrar.");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // LISTAR
    public static void listarEmpleados() {

        try {

            Operaciones.setConnection(
                    ConexionBD.MysConnection()
            );

            String sql = "SELECT * FROM empleados";

            PreparedStatement ps =
                    Operaciones.getConnection().prepareStatement(sql);

            ResultSet rs =
                    Operaciones.consultar_BD(ps);

            System.out.println("---- EMPLEADOS ------");

            while (rs.next()) {

                System.out.println("------------------");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Documento: " + rs.getString("documento"));
                System.out.println("Rol: " + rs.getString("rol"));
                System.out.println("Correo: " + rs.getString("correo"));
                System.out.println("Salario: " + rs.getDouble("salario"));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    // BUSCAR
    public static void buscarEmpleado(Scanner sc) {

        System.out.print("ID del empleado: ");
        int id = sc.nextInt();

        try {

            Operaciones.setConnection(
                    ConexionBD.MysConnection()
            );

            String sql =
                    "SELECT * FROM empleados WHERE id = ?";

            PreparedStatement ps =
                    Operaciones.getConnection().prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs =
                    Operaciones.consultar_BD(ps);

            if (rs.next()) {

                System.out.println("Empleado encontrado:");

                System.out.println("ID: " +
                        rs.getInt("id"));

                System.out.println("Nombre: " +
                        rs.getString("nombre"));

                System.out.println("Documento: " +
                        rs.getString("documento"));

                System.out.println("Rol: " +
                        rs.getString("rol"));

                System.out.println("Correo: " +
                        rs.getString("correo"));

                System.out.println("Salario: " +
                        rs.getDouble("salario"));

            } else {
                System.out.println("Empleado no encontrado.");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
