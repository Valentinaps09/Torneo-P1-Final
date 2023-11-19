package co.edu.uniquindio.poo.torneodeportivo;

public class Ubicacion {
    private final String nombreUbicacion;
    private final String direccion;

    public Ubicacion(String nombreUbicacion, String direccion){
        this.nombreUbicacion = nombreUbicacion;
        this.direccion = direccion;
    }  

    public String getNombreUbicacion(){
        return nombreUbicacion;
    }

    public String getDireccion(){
        return direccion;
    }
    
}
