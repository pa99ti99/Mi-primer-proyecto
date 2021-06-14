/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import javax.swing.ButtonGroup;

import Modelo.ConsultaProductos;
import Modelo.Productos;
import Vista.FrmProductos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;



/*
            if (ingfrm.jrbMoto.isSelected() == true) {
                JOptionPane.showMessageDialog(null, "Seleccionaste Auto");
            } else if (ingfrm.jrbici.isSelected() == true) {
                JOptionPane.showMessageDialog(null, "Seleccionaste bici");
            } else if (ingfrm.jrbAuto.isSelected() == true) {
                JOptionPane.showMessageDialog(null, "Seleccionaste ");
            } else if (ingfrm.jrbVeh.isSelected() == true) {
                JOptionPane.showMessageDialog(null, "Seleccionaste veh");
            } else {
                System.out.println("Debe elegir una opción");
            }

          if (ingfrm.jrbMoto.isSelected() == false && ingfrm.jrbici.isSelected() == false && ingfrm.jrbAuto.isSelected() == false && ingfrm.jrbVeh.isSelected() == false) {
                JOptionPane.showMessageDialog(null, "No selecciono ninguna opción");
            }
*/
   

/**
 *
 * @author Pati
 */
public class ControladorProductos implements ActionListener {

    private Productos mod;// se declara la clase productos que se manejo en el modelo
    private ConsultaProductos modC;// se declara la clase consultaproductos que se manejo en el modelo
    private FrmProductos frm; // se declara la interfaz   que se manejo en la vista.

    public ControladorProductos(Productos mod, ConsultaProductos modC, FrmProductos frm) {// contructor que recibe el modelo y la vista 
// igualamos los valores que estamos recibiendo
        this.mod = mod;
        this.modC = modC;
        this.frm = frm;
        //se declaran los ActionListener para cada botones
        this.frm.btnguardar.addActionListener(this);
        this.frm.btnmodificar.addActionListener(this);
        this.frm.btnlimpiar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnbuscar.addActionListener(this);
    }

    public void iniciar() {// este metodo no recibe  solo la utilizaremos para iniciar la vista
        frm.setTitle("Productos");// se establece el titulo 
        frm.setLocationRelativeTo(null);// se establece la posicion del formulario
        frm.txtID.setVisible(false);// se establece la caja de texto para que no este visible

///Con esto ya podemos inicir la vista
    }
///  se crea el metodo que van escuchar los clic de los botones 

    @Override
    public void actionPerformed(ActionEvent e) {
        //haremosun if para dectecta que boton se preciono
        if (e.getSource() == frm.btnguardar) {// si se cumple esta condicion significa que el usurio presiono el boton guardar.
       
            mod.setCodigo(frm.jtxtcodigo.getText());//para ingresar lo datos en el campo jtextfield
            mod.setNombre(frm.jtxtnombre.getText());
            mod.setPrecio(Double.parseDouble(frm.jtxtprecio.getText()));
            mod.setCantidad(Integer.parseInt(frm.jtxtCantidad.getText()));

            if (modC.registrar(mod)) {
                
                
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                limpiar(); // se invoca el metodo limpiar cajas
            } else {
                JOptionPane.showMessageDialog(null, "Error al Guardado");
                limpiar(); // se invoca el metodo limpiar cajas
            }

        }
        if (e.getSource() == frm.btnmodificar) {// ahora detectamos el boton modificar 
            mod.setId(Integer.parseInt(frm.txtID.getText()));
            mod.setCodigo(frm.jtxtcodigo.getText());//para ingresar lo datos en el campo jtextfield
            mod.setNombre(frm.jtxtnombre.getText());
            mod.setPrecio(Double.parseDouble(frm.jtxtprecio.getText()));
            mod.setCantidad(Integer.parseInt(frm.jtxtCantidad.getText()));

            if (modC.modificar(mod)) {

                JOptionPane.showMessageDialog(null, "Registro Modificado");
                limpiar(); // se invoca el metodo limpiar cajas
            } else {
                JOptionPane.showMessageDialog(null, "Error al Modificar");
                limpiar(); // se invoca el metodo limpiar cajas
            }

        }

        if (e.getSource() == frm.btnEliminar) {// ahora detectamos el boton eliminar
            mod.setId(Integer.parseInt(frm.txtID.getText()));

            if (modC.eliminar(mod)) {

                JOptionPane.showMessageDialog(null, "Registro eliminado");
                limpiar(); // se invoca el metodo limpiar cajas
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar");
                limpiar(); // se invoca el metodo limpiar cajas
            }

        }
        if (e.getSource() == frm.btnbuscar) {// ahora detectamos el boton eliminar
            mod.setCodigo(frm.jtxtcodigo.getText());

            if (modC.buscar(mod)) {

                frm.txtID.setText(String.valueOf(mod.getId()));
                frm.jtxtcodigo.setText(mod.getCodigo());
                frm.jtxtnombre.setText(mod.getNombre());
                frm.jtxtprecio.setText(String.valueOf(mod.getPrecio()));
                frm.jtxtCantidad.setText(String.valueOf(mod.getCantidad()));

               } else {
                JOptionPane.showMessageDialog(null, "El registo no se ha encontrado");
                limpiar(); // se invoca el metodo limpiar cajas
            }

        }
        if (e.getSource() == frm.btnlimpiar) {
             limpiar(); // se invoca el metodo limpiar cajas
            
        }

    }

    public void limpiar() {// se establece el metodo para limpiar las cajas de texto.
        frm.txtID.setText(null);
        frm.jtxtcodigo.setText(null);
        frm.jtxtnombre.setText(null);
        frm.jtxtprecio.setText(null);
        frm.jtxtCantidad.setText(null);
    }
}
