package application.domain;

public class Anunciante extends Cliente {
    private int nAnuncios;

    public Anunciante() {
    }

    public Anunciante(String identificador, String contrasenna, Double descuento, int nAnuncios) {
        super(identificador, contrasenna, descuento);
        this.nAnuncios = nAnuncios;
    }

    public int getnAnuncios() {
        return nAnuncios;
    }

    public void setnAnuncios(int nAnuncios) {
        this.nAnuncios = nAnuncios;
    }
}
