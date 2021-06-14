/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Pati
 */
//Aqui se aplica la herencia con la conenexion 
public class ConsultaProductos extends Conexion {

    public boolean registrar(Productos pro) {

        PreparedStatement pst = null;
        Connection con = getconexion();

        String sql = "INSERT INTO producto(codigo,nombre,precio, cantidad) VALUES(?,?,?,?)";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, pro.getCodigo());
            pst.setString(2, pro.getNombre());
            pst.setDouble(3, pro.getPrecio());
            pst.setInt(4, pro.getCantidad());
            pst.execute();

            return true;

        } catch (SQLException e) {
            System.err.println(e);
            return false;

        } finally {//la ocuparemos para cerra la conexxion
            try {
                con.close();//cerrando
            } catch (SQLException e) {
                System.err.println(e);

            }

        }
    }
    
    
        public boolean modificar(Productos pro) {

        PreparedStatement pst = null;
        Connection con = getconexion();

        String sql = "UPDATE producto SET codigo=?, nombre=?, precio=?, cantidad=?  WHERE id=?";

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, pro.getCodigo());
            pst.setString(2, pro.getNombre());
            pst.setDouble(3, pro.getPrecio());
            pst.setInt(4, pro.getCantidad());
            pst.setInt(5, pro.getId());
            pst.execute();

            return true;

        } catch (SQLException e) {
            System.err.println(e);
            return false;

        } finally {//la ocuparemos para cerra la conexxion
            try {
                con.close();//cerrando
            } catch (SQLException e) {
                System.err.println(e);

            }

        }
    }

         public boolean eliminar(Productos pro) {

        PreparedStatement pst = null;
        Connection con = getconexion();

        String sql = "DELETE FROM producto WHERE id=?";//comando para elimimar produtos

        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, pro.getId());
            pst.execute();

            return true;

        } catch (SQLException e) {
            System.err.println(e);
            return false;

        } finally {//la ocuparemos para cerra la conexxion
            try {
                con.close();//cerrando
            } catch (SQLException e) {
                System.err.println(e);

            }

        }
    }
         
         public boolean buscar(Productos pro) {
             
       
        PreparedStatement pst = null;
        ResultSet rs = null;
        Connection con = getconexion();
        
        

        String sql = "SELECT * FROM producto WHERE codigo=?";//comando para buscar

        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, pro.getCodigo());
            rs = pst.executeQuery();;// es el que se encarga de regresar el resultado
            
            if (rs.next()){ //se pasa los resultado 
                pro.setId(Integer.parseInt(rs.getString("id")));//Integer.parseInt Devuelve el valor entero representado por el argumento en decimal. 
                pro.setCodigo(rs.getString("codigo"));
                pro.setNombre(rs.getString("nombre"));
                pro.setPrecio(Double.parseDouble(rs.getString("precio")));//parseDouble devuelve un doble primitivo que contiene el valor de la cadena
                pro.setCantidad(Integer.parseInt(rs.getString("cantidad")));//Integer.parseInt Devuelve el valor entero representado por el argumento en decimal. 
                return true; 
                
            }
            return false;
// cerrar la conexion 
        } catch (SQLException e) {
            System.err.println(e);
            return false;

        } finally {//la ocuparemos para cerra la conexxion
            try {
                con.close();//cerrando
            } catch (SQLException e) {
                System.err.println(e);

            }

        }
    }

}


