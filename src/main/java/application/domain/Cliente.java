package application.domain;

public abstract class Cliente {
    private String identificador; //gmail
    private String contrasenna;
    private Double descuento;

    public Cliente() {
    }

    public Cliente(String identificador, String contrasenna, Double descuento) {
        this.identificador = identificador;
        this.contrasenna = contrasenna;
        this.descuento = descuento;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }
}
