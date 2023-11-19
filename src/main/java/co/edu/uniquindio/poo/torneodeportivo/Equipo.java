/**
 * Registro que agrupa los datos de un Equipo
 * @author Área de programación UQ
 * @since 2023-09
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo.torneodeportivo;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;
import java.util.function.Predicate;

import static co.edu.uniquindio.poo.util.AssertionUtil.ASSERTION;

public record Equipo(String nombreEquipo,Persona representante,Collection<Jugador> jugadores) {
    
    public Equipo{
        ASSERTION.assertion( nombreEquipo != null && !nombreEquipo.isBlank() , "El nombre es requerido");
        ASSERTION.assertion( representante != null , "El representante es requerido");

    }

    public Equipo(String nombre,Persona representante){
        this(nombre,representante,new LinkedList<>());
    }

    /**
     * Permite registrar un jugador en un equipo siempre y cuando no exista ya un jugador registrado en el equipo con el mismo nombre y apellido
     * @param jugador Jugador que se desea registrar.
     */
    public void registrarJugador(Jugador jugador,Torneo torneo) {
        
        validarJugadorExiste(jugador);
        if(validarGeneroJugador(jugador,torneo))
        jugadores.add(jugador);
    }

    public boolean validarGeneroJugador(Jugador jugador,Torneo torneo) {

    switch (torneo.getGeneroTorneo()) {
        case MIXTO:
            return true; // No hay restricciones de género en torneos mixtos
        case MASCULINO:
            return jugador.getGeneroJugador() == GeneroJugador.MASCULINO;
        case FEMENINO:
            return jugador.getGeneroJugador() == GeneroJugador.FEMENINO;
        default:
            throw new IllegalStateException("Tipo de género de torneo no válido");
    }

    }
    
    /**
     * Permimte buscar un jugador en el equipo basado en su nombre y apellido.
     * @param jugador Jugador que se desea buscar
     * @return Optional con el jugador que coincida con el nombre y apellido del jugador buscado, 
     * o Optinal vacío en caso de no encontrar un jugador en el equipo con dicho nombre y apellido.
     */
    public Optional<Jugador> buscarJugador(Jugador jugador){
        Predicate<Jugador> nombreIgual = j->j.getNombre().equals(jugador.getNombre());
        Predicate<Jugador> apellidoIgual = j->j.getApellido().equals(jugador.getApellido());
        return jugadores.stream().filter(nombreIgual.and(apellidoIgual)).findAny();
    }

    /**
     * Valida que no exista ya un jugador registrado con el mismo nombre y apellido, en caso de haberlo genera un assertion error.
     */
    private void validarJugadorExiste(Jugador jugador) {
        boolean existeJugador = buscarJugador(jugador).isPresent();
        ASSERTION.assertion( !existeJugador,"El jugador ya esta registrado");
    }

}
