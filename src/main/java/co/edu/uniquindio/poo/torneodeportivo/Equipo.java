/**
 * Registro que agrupa los datos de un Equipo
 * @author Área de programación UQ
 * @since 2023-09
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo.torneodeportivo;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

import static co.edu.uniquindio.poo.util.AssertionUtil.ASSERTION;

public record Equipo(String nombre,Persona representante,Collection<Jugador> jugadores) {
    public Equipo{
        ASSERTION.assertion( nombre != null && !nombre.isBlank() , "El nombre es requerido");
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
        validarGeneroJugador(jugador, torneo);
        validarJugadorExiste(jugador);
        jugadores.add(jugador);
    }

   public static void validarGeneroJugador(Jugador jugador,Torneo torneo){    
    GeneroJugador generoJugador = jugador.getGeneroJugador();
    GeneroTorneo generoTorneo = torneo.getGeneroTorneo();

    Map<GeneroTorneo, Set<GeneroJugador>> validaciones = new EnumMap<>(GeneroTorneo.class);       
    validaciones.put(GeneroTorneo.MASCULINO, Collections.singleton(GeneroJugador.MASCULINO));
    validaciones.put(GeneroTorneo.FEMENINO, Collections.singleton(GeneroJugador.FEMENINO));
    validaciones.put(GeneroTorneo.MIXTO, EnumSet.allOf(GeneroJugador.class));

    if (!validaciones.get(generoTorneo).contains(generoJugador)) {
        throw new IllegalArgumentException("El género del jugador no coincide con el género del torneo.");
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

    public void registrarJugador(Jugador jugador) {
    }
}
