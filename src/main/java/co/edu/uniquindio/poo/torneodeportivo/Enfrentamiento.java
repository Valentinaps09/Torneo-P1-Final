package co.edu.uniquindio.poo.torneodeportivo;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class Enfrentamiento {

    private final String nombre;
    private final LocalDateTime fechaHora;
    private final String lugar;
    private EstadoEnfretamiento estadoEnfretamiento;
    private Equipo equipoLocal;
    private Equipo equipoVisitante;
    private Collection<Juez>juecesParticipantes;
    private Resultado resultado;
    
    
    public Enfrentamiento(String nombre,LocalDateTime fechaHora,String lugar,EstadoEnfretamiento estadoEnfretamiento,Equipo equipoLocal, Equipo equipoVisitante, Juez juez){
        this.nombre=nombre;
        this.fechaHora = fechaHora;
        this.lugar = lugar;
        this.estadoEnfretamiento = EstadoEnfretamiento.PENDIENTE;
        this.equipoLocal =equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.juecesParticipantes = new LinkedList<>();
        this.resultado = null;

    }

    public String getNombre() {
        return nombre;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public String getLugar() {
        return lugar;
    }

    public EstadoEnfretamiento getEstadoEnfretamiento(){
        return estadoEnfretamiento;
    }

    public Equipo getEquipoLocal() {
        return equipoLocal;
    }

    public Equipo getEquipoVisitante() {
        return equipoVisitante;
    }
    public void setEstadoEnfrentamiento(EstadoEnfretamiento estadoEnfrentamiento) {
        this.estadoEnfretamiento = estadoEnfrentamiento;
    }

    public Collection<Juez> getJuecesParticipantes() {
        return Collections.unmodifiableCollection(juecesParticipantes);
    }

    public Resultado getResultado() {
        return resultado;
    }

    public void setResultado(Resultado resultado) {
        this.resultado = resultado;
        this.estadoEnfretamiento = EstadoEnfretamiento.FINALIZADO; 
    }

 }
    
