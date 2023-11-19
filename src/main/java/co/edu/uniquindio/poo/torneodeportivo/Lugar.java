package co.edu.uniquindio.poo.torneodeportivo;

public class Lugar {
    private final String nombreLugar;
    private final String ubicacion;

    public Lugar (String nombreLugar,String ubicacion){
        this.nombreLugar=nombreLugar;
        this.ubicacion = ubicacion;
    }

    public String getNombreLugar(){
        return nombreLugar;
    }

    public String getUbicacion(){
        return ubicacion;
    }

}
