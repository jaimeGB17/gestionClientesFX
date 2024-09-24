package application.CRUD;

import application.domain.Usuario;

import java.util.ArrayList;

public class CRUD {
    // Estas dos variables son fijas para evitar modificarlas y son los dos tipos de tarifas según si el usuario es fijo o no.
    public static final double TARIFAPREMIUM = 35.5;
    public static final double TARIFANOPREMIUM = 20.5;

    /*
    En esta función, añadimos un usuario a la lista de usuarios.
    Para ello, primero comprobamos si existe en la lista.
    Si existe, devolvemos false y si no existe, se realiza otra comprobación en caso de que el usuario tenga el mismo
    correo que uno ya existente, fallará al insertar.
    Si al final, pasa las dos condiciones, se insertará dicho usuario
     */
    public boolean annadirUsuario(ArrayList<Usuario> usuarios, Usuario usuario) {
        if (usuarios.contains(usuario)) return false;
        if (usuarios.stream().anyMatch(user ->
                user.getIdentificador().equalsIgnoreCase(usuario.getIdentificador()))) return false;
        usuarios.add(usuario);
        return true;
    }

    /*
    En este codigo buscaremos un usuario dentro del listado de usuarios de la aplicación en funcion de su correo de gmail.
    Para ello, recorremos el array con stream y buscamos mediante filter que lo que hace es filtrar según la condición dada.
    En este caso, si el identificador del usuario es igual (tanto en mayúsculas como en minúsculas) nos devolvera dicho usuario.
    Si no lo encontramos, devolvemos null.
    */
    public Usuario buscarUsuario(ArrayList<Usuario> usuarios, String gmail) {
        return usuarios.stream()
                .filter(usuario -> usuario.getIdentificador().equalsIgnoreCase(gmail))
                .findFirst()
                .orElse(null);
    }

    /*
    En este metodo, devolvemos el total de ingreso que tenemos en función de si un usuario es premiun o no y del descuento de cada usuario.
    Para ello, recorremos la lista de usuarios, mapeamos a double (ya que las tarifas son doubles).
    Dentro del map, realizamos una comprobación (if) si el usuario es premium o no.
    En caso afirmativo, a la tarifa de premium se le descuenta el decuento de dicho usuario.
    En caso negativo, a la tarifa de no premium se le descuenta el decuento de dicho usuario.
    Por ultimo, como nos interesa el total con todos los usuarios, lo sumamos todo.
     */
    public Double totalIngreso(ArrayList<Usuario> usuarios) {
        return usuarios.stream()
                .mapToDouble(usuario ->
                        usuario.isPremium() ? TARIFAPREMIUM - usuario.getDescuento() : TARIFANOPREMIUM - usuario.getDescuento()
                ).sum();
    }
}
