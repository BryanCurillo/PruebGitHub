/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Contacto;
import View.ViewContactos;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bryan
 */
public class ControllerContactos {

    ViewContactos viewContactos;
    Contacto contacto;
    Validaciones mivalidacion = new Validaciones();
    String Resultado = "";

    public static ArrayList<Contacto> listContactos = new ArrayList<Contacto>();

    public ControllerContactos(ViewContactos viewContactos, Contacto contacto) {
        this.viewContactos = viewContactos;
        this.contacto = contacto;
        viewContactos.setVisible(true);
        viewContactos.setLocationRelativeTo(null);
        viewContactos.setTitle("Contactos");
        cargarLista();
        cargarContactos();
    }

    public void iniciarControles() {
        viewContactos.getBtnAgregar().addActionListener(l -> Registrar());
        viewContactos.getBtnValidar().addActionListener(l -> evaluar());
        viewContactos.getBtnCancelar().addActionListener(l -> limpiar());
        viewContactos.getBtnEliminar().addActionListener(l -> eliminar());
        viewContactos.getBtnLampiar().addActionListener(l -> viewContactos.getTxaValidacion().setText(""));

    }

    public void cargarLista() {
        Contacto c1 = new Contacto("Laura", "laurav@gmail.com", "+123456", "140");
        Contacto c2 = new Contacto("Lucy", "lucy@gmail.com", "+123456", "12346");
        Contacto c3 = new Contacto("laurav123", " laurav123@skype.com ", "+12357681", "124");
        Contacto c4 = new Contacto("Laura", " laurav123@skype.com ", "+12357681", "14");
        Contacto c5 = new Contacto("Lucy", "lucy@gmail.com", "+123456", "12346");

        listContactos.add(c1);
        listContactos.add(c2);
        listContactos.add(c3);
        listContactos.add(c4);
        listContactos.add(c5);
    }

    public void Registrar() {

        if (validar()) {
            String usu = viewContactos.getTxtUsuario().getText(),
                    pass = viewContactos.getTxtPassword().getText(),
                    correo = viewContactos.getTxtCorreo().getText(),
                    telf = viewContactos.getTxtTelefono().getText();

            Contacto cont = new Contacto();
            cont.setCorreo(correo);
            cont.setPassword(pass);
            cont.setTelefono(telf);
            cont.setUsuario(usu);

            listContactos.add(cont);
            cargarContactos();
            limpiar();
        }

    }

    public void eliminar() {
        int id = viewContactos.getTblContactos().getSelectedRow();
        if (id > -1) {
            listContactos.remove(id);
            cargarContactos();
        } else {
            JOptionPane.showMessageDialog(viewContactos, "Seleccione un contacto");
        }

    }

    public void cargarContactos() {
        DefaultTableModel model = (DefaultTableModel) viewContactos.getTblContactos().getModel();
        model.setRowCount(0);
        listContactos.stream()
                //                                .sorted((x, y) -> x.getUsuario().compareToIgnoreCase(y.getUsuario()))
                .forEach(c -> {

                    Object[] fila = {c.getUsuario(), c.getPassword(), c.getCorreo(), c.getTelefono()};
                    model.addRow(fila);
                    viewContactos.getTblContactos().repaint();
                });
    }

    public void evaluar() {
        viewContactos.getTxaValidacion().setText("");
//        int cont = 0;
        String coincidencias = "",
                mensaje = "",
                campos = "";
        for (int i = 0; i < listContactos.size(); i++) {
//            coincidencias = listContactos.get(i).getUsuario();

            for (int j = i + 1; j < listContactos.size(); j++) {
                coincidencias = listContactos.get(i).getUsuario();

                int cont = 0;
                campos = "";

                if (i != j) {
                    System.out.println(listContactos.get(i).getUsuario() + " equals " + listContactos.get(j).getUsuario());
                    System.out.println(listContactos.get(i).getUsuario().equals(listContactos.get(j).getUsuario()));

                    if (listContactos.get(i).getUsuario().equals(listContactos.get(j).getUsuario())) {
                        campos = campos + ", usuario";
//                        System.out.println(listContactos.get(i).getUsuario() + " = " + listContactos.get(j).getUsuario());
                        cont++;
                    }
                    if (listContactos.get(i).getCorreo().equals(listContactos.get(j).getCorreo())) {
                        campos = campos + ", correo";
//                        System.out.println(listContactos.get(i).getCorreo() + " = " + listContactos.get(j).getCorreo());
                        cont++;
                    }
                    if (listContactos.get(i).getTelefono().equals(listContactos.get(j).getTelefono())) {
                        campos = campos + ", telefono";
//                        System.out.println(listContactos.get(i).getTelefono() + " = " + listContactos.get(j).getTelefono());
                        cont++;
                    }
                    if (listContactos.get(i).getPassword().equals(listContactos.get(j).getPassword())) {
                        campos = campos + ", password";
//                        System.out.println(listContactos.get(i).getPassword() + " = " + listContactos.get(j).getPassword());
                        cont++;
                    }
                    if (cont > 0) {
                        coincidencias = coincidencias + ", " + listContactos.get(j).getUsuario();
                        System.out.println("c= " + coincidencias);
                    }
                    switch (cont) {
                        case 1:
                            mensaje = mensaje + "Entre el contacto " + coincidencias + " hay cierta similitud por que se repite en el campo" + campos + "\n";
                            break;
                        case 2:
                            mensaje = mensaje + "Entre el contacto " + coincidencias + " hay cierta igualdad por que el" + campos + " son iguales." + "\n";
                            break;
                        case 3:
                            mensaje = mensaje + "Entre el contacto " + coincidencias + " hay cierta igualdad por que el" + campos + " son iguales." + "\n";

                            break;
                        case 4:
                            mensaje = mensaje + "El contacto " + coincidencias + " estan duplicados por que son iguales en todos los campos." + "\n";
                            break;
                        default:
                    }
//                    coincidencias = "";

                }
            }
        }
        viewContactos.getTxaValidacion().setText(mensaje);

    }

    public void limpiar() {
        viewContactos.getTxtCorreo().setText("");
        viewContactos.getTxtPassword().setText("");
        viewContactos.getTxtTelefono().setText("");
        viewContactos.getTxtUsuario().setText("");
    }

    public boolean validar() {
        boolean ban = true;

        if (viewContactos.getTxtUsuario().getText().isEmpty()) {
            JOptionPane.showMessageDialog(viewContactos, "Ingrese el nombre de usuario");
            ban = false;
        }
        if (viewContactos.getTxtPassword().getText().isEmpty()) {
            JOptionPane.showMessageDialog(viewContactos, "Ingrese la contraseña");
            ban = false;
        }

        if (!viewContactos.getTxtCorreo().getText().isEmpty()) {
            if (!mivalidacion.validarCorreo(viewContactos.getTxtCorreo().getText())) {
                JOptionPane.showMessageDialog(viewContactos, "Correo invalido");
                ban = false;
            }
        } else {
            JOptionPane.showMessageDialog(viewContactos, "Ingrese el correo");
            ban = false;
        }

        if (viewContactos.getTxtTelefono().getText().isEmpty()) {
            JOptionPane.showMessageDialog(viewContactos, "Ingrese el telefono");
            ban = false;
        }

        return ban;
    }

}
