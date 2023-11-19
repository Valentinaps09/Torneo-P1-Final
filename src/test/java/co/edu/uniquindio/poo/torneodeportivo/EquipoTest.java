package co.edu.uniquindio.poo.torneodeportivo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

public class EquipoTest {
    //Prueba inclusion de genero
    private static final Logger LOG = Logger.getLogger(EquipoTest.class.getName());

    @Test
    public void registrarJugadorTest(){
        LOG.info("Inicio prueba del correcto almacenamiento de un jugador en un equipo");

        var representante = new Persona("Javier", "celis", "Jcelis@gmail.com","3008905673");

        var equipo = new Equipo("Beraca", representante);

        var jugador = new Jugador("Carlos", "Rodriguez", "CarlosR18@gmail.com", "3178906542", LocalDate.of(2033, 12, 3), GeneroJugador.MASCULINO);

        var torneo= new Torneo("El torneo del barrio", LocalDate.of(2033, 12, 3), LocalDate.of(2033, 12, 1), LocalDate.of(2033, 12, 2), (byte)0,(byte) 0, 0, null, GeneroTorneo.MASCULINO);

        equipo.registrarJugador(jugador,torneo);
        assertTrue(equipo.jugadores().contains(jugador));

        LOG.info("Fin prueba del correcto almacenamiento de un jugador dentro de un equipo");  
    }
    
    @Test
    //Registra un un juez en el torneo 
    public void registrarJugadorTestGeneroDiferente(){
        LOG.info("Inicio prueba  registrar jugador con genero diferente al torneo"); 

        Persona representante = new Persona("Camilo","Salazar","CamiS@gmail.com","311879975");        
        var equipo = new Equipo("Cafe y futbol", representante);
        var jugador = new Jugador("Valeria", "Rojas", "Valeria@gmail.com","13283785", LocalDate.of(2002, 8, 15), GeneroJugador.FEMENINO);
        Torneo torneo = new Torneo("PonyFutbol", LocalDate.of(2033, 12, 3), LocalDate.of(2033, 12, 1), LocalDate.of(2033, 12, 2), (byte)0,(byte) 0, 0, TipoTorneo.LOCAL, GeneroTorneo.MASCULINO);

        equipo.registrarJugador(jugador, torneo);
        assertFalse(equipo.jugadores().contains(jugador));
        
}

}