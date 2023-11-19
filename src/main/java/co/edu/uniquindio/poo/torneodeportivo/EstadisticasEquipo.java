package co.edu.uniquindio.poo.torneodeportivo;

public class EstadisticasEquipo {
    private int victorias;
    private int empates;
    private int derrotas;

    public EstadisticasEquipo() {
     
    }

    public int getVictorias() {
        return victorias;
    }

    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public void incrementarVictorias() {
        victorias++;
    }

    public void incrementarDerrotas() {
        derrotas++;
    }

    public void incrementarEmpates() {
        empates++;
    }

    
    
}
