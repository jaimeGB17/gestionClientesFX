package application.controller;


import application.CRUD.CRUD;
import application.domain.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.*;
import java.util.ArrayList;

public class controller {
    @FXML
    private ToggleGroup clientePremium;

    @FXML
    private TextField contrasennaTxt;
    @FXML
    private TextField descuentoTxt;
    @FXML
    private TextField correoTxt;
    @FXML
    private RadioButton premiumBtn;
    @FXML
    private RadioButton nopremiumBtn;

    CRUD crud = new CRUD();
    ArrayList<Usuario> usuarios = new ArrayList<>();

    /*
    En este metodo, ocurre cuando le pulsamos al botón de añadir usuario.
    Primero,comprobamos si hay algo escrito en los campos, después
    recogemos los datos de cada uno de los campos de textos y del radioButton.
    Comprobamos si el usuario se añadio correctamente.
     */
    @FXML
    void onAnnadirUsuarioBtn(ActionEvent event) {
        boolean comprobado = comprobarCampos();
        if (!comprobado) return;
        Usuario usuario = new Usuario(
                correoTxt.getText(),
                contrasennaTxt.getText(),
                Double.parseDouble(descuentoTxt.getText()),
                Boolean.parseBoolean(clientePremium.getSelectedToggle().getUserData().toString()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Comprobando usuario.");
        if (crud.annadirUsuario(usuarios, usuario)) {
            alert.setContentText("Usuario insertado con éxito.");
        } else {
            alert.setContentText("Error al insertar usuario.");
        }
        alert.showAndWait();
        onLimpiarBtn(event);
    }

    /*
    En este metodo buscamos un usuario en función de su correo
     */
    @FXML
    void onBuscarUsuarioBtn(ActionEvent event) {
        boolean comprobarCampoCorreo = comprobarCampoCorreo();
        if (!comprobarCampoCorreo) return;

        Usuario usuarioBuscado = crud.buscarUsuario(usuarios, correoTxt.getText());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Comprobando Usuario.");
        if (usuarioBuscado == null) {
            alert.setContentText("Usuario no encontrado.");
        } else {
            alert.setContentText("Usuario encontrado.");
            contrasennaTxt.setText(usuarioBuscado.getContrasenna());
            descuentoTxt.setText(usuarioBuscado.getDescuento().toString());
            if (usuarioBuscado.isPremium()) {
                clientePremium.selectToggle(premiumBtn);
            } else {
                clientePremium.selectToggle(nopremiumBtn);
            }
        }
        alert.showAndWait();

    }

    /*
    este boton muestra en un alert el ingreso total.
     */
    @FXML
    void onIngresoTotalBtn(ActionEvent event) {
        double ingresoTotal = crud.totalIngreso(usuarios);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ingreso total.");
        alert.setContentText("El ingreso total: " + ingresoTotal);
        alert.showAndWait();
    }

    /*
    Este botón limpia los campos.
     */
    @FXML
    void onLimpiarBtn(ActionEvent event) {
        correoTxt.setText("");
        contrasennaTxt.setText("");
        descuentoTxt.setText("");
        clientePremium.getSelectedToggle().setSelected(false);
    }

    /*
    Este botón cierra la aplicación.
     */
    @FXML
    void onSalirBtn(ActionEvent event) {
        int opcion = JOptionPane.showConfirmDialog(null,
                "¿Está seguro de que desea salir?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0); // CERRAR APLICACIÓN
        }
    }

    /*
    Comprobación de cada campo.
     */
    boolean comprobarCampos() {
        boolean camposComprobados = true;
        if (correoTxt.getLength() == 0) {
            camposComprobados = false;
        }
        if (contrasennaTxt.getLength() == 0) {
            camposComprobados = false;
        }
        if (descuentoTxt.getLength() == 0) {
            camposComprobados = false;
        }
        if (!clientePremium.getSelectedToggle().isSelected()) {
            camposComprobados = false;
        }
        if (!camposComprobados) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setContentText("Error al insertar usuario.");
            alert.showAndWait();
        }
        return camposComprobados;
    }

    boolean comprobarCampoCorreo() {
        boolean campoCorreo = true;
        if (correoTxt.getLength() == 0) {
            campoCorreo = false;
        }
        if (!campoCorreo) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Advertencia");
            alert.setContentText("Escriba un correo para poder buscar un usuario.");
            alert.showAndWait();
        }
        return campoCorreo;
    }
}