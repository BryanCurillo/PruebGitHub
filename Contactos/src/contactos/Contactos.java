/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package contactos;

import Controller.ControllerContactos;
import Model.Contacto;
import View.ViewContactos;

/**
 *
 * @author Bryan
 */
public class Contactos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ViewContactos viewContactos = new ViewContactos();
        Contacto contacto = new Contacto();
        Controller.ControllerContactos controlCont = new ControllerContactos(viewContactos, contacto);
        controlCont.iniciarControles();
    }
    
}
