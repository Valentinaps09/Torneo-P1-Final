package co.edu.uniquindio.poo.torneodeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

public class TorneoTest {
    private static final Logger LOG = Logger.getLogger(EquipoTest.class.getName());

    @Test
    public void registrarJugadorTest(){
        LOG.info("Inicio prueba del correcto almacenamiento de un jugador en un equipo desde torneo");

        var representante = new Persona("Juan", "Castaño", "Jc@gmail.com","3008905673");

        var equipo = new Equipo("Beraca", representante);

        var jugador = new Jugador("Juan", "Dominguez", "JDR18@gmail.com", "3158906542", LocalDate.of(2021, 12, 3), GeneroJugador.MASCULINO);

        var torneo= new Torneo("El torneo del barrio", LocalDate.of(2033, 12, 3), LocalDate.of(2033, 12, 1), LocalDate.of(2033, 12, 2), (byte)0,(byte) 0, 0, null, GeneroTorneo.MIXTO);

        equipo.registrarJugador(jugador,torneo);
        assertTrue(equipo.jugadores().contains(jugador));

        LOG.info("Fin prueba del correcto almacenamiento de un jugador en un equipo desde torneo");  
    }

    @Test
    public void registrarJugadorTestGeneroDiferente(){
        LOG.info("Inicio prueba  registrar jugador con genero diferente al torneo"); 

        Persona representante = new Persona("Juan", "Castaño", "Jc@gmail.com","3008905673");        
        var equipo = new Equipo("Beraca", representante);
        var jugador = new Jugador("Juan", "Dominguez", "JDR18@gmail.com", "3158906542", LocalDate.of(2021, 12, 3), GeneroJugador.MASCULINO);
        Torneo torneo = new Torneo("El torneo del barrio", LocalDate.of(2033, 12, 3), LocalDate.of(2033, 12, 1), LocalDate.of(2033, 12, 2), (byte)0,(byte) 0, 0, null, GeneroTorneo.FEMENINO);

        equipo.registrarJugador(jugador, torneo);
        assertFalse(equipo.jugadores().contains(jugador));
        
        LOG.info("Fin prueba del correcto almacenamiento del genero de un jugador en un equipo desde torneo");  
}
    @Test
    public void registrarJuezTorneo(){
        LOG.info("Inicio prueba registrar un juez en el torneo");
        
        Torneo torneo = new Torneo("Copa Trigreros", LocalDate.of(2025, 12,27), LocalDate.of(2025, 10, 1), LocalDate.of(2025, 11, 2), (byte)0,(byte) 0, 0, null, GeneroTorneo.MIXTO);
        var juez = new Juez("Aquiles", "Castro", "Arrj@gmail.com", "3134567893", "27183919");
        
        torneo.registrarJuez(juez);
        assertTrue(torneo.getJuecesParticipantes().contains(juez));
        LOG.info("Fin prueba reegistrar a un juez en el torneo");
    }

    @Test
    public void agendarEnfretamientoTest() {

    Torneo torneo = new Torneo("Torneo de Prueba",
        LocalDate.of(2033, 12, 8),LocalDate.of(2033, 12, 1), LocalDate.of(2033, 12, 2),(byte) 2, (byte) 18, 100, TipoTorneo.LOCAL, GeneroTorneo.MIXTO);

    var representante1 = new Persona("Juan", "Castaño", "Jc@gmail.com", "3008905673");
    var equipoLocal = new Equipo("Beraca", representante1);

    var representante2 = new Persona("Armando", "Casas", "ARc@gmail.com", "3238205771");
    var equipoVisitante = new Equipo("Cafe y futbol", representante2);

    var juez = new Juez("Ramiro", "Castaño", "Rc@gmail.com", "321721927", "125821");

    // Crear un objeto Enfrentamiento con un estado inicial
    Enfrentamiento enfrentamiento = new Enfrentamiento("Enfrentamiento1", LocalDateTime.now().plusDays(1),"Lugar1", EstadoEnfretamiento.PENDIENTE, equipoLocal, equipoVisitante, juez);

    // Agendar el enfrentamiento en el torneo
    torneo.agendarEnfrentamiento(enfrentamiento);
    assertTrue(torneo.getEnfrentamientosAgendados().contains(enfrentamiento));
    }

    
        LOG.info("Fin de prueba  número de participantes negativo...");
    }

    @Test
        public void testSetPuntosEquipoLocal() {
            Resultado resultado = new Resultado(0, 0);
            resultado.setPuntosEquipoLocal(2);
            assertEquals(2, resultado.getPuntosEquipoLocal());
        }
    
    @Test
        public void testSetPuntosEquipoVisitante() {
            Resultado resultado = new Resultado(0, 0);
            resultado.setPuntosEquipoVisitante(4);
            assertEquals(4, resultado.getPuntosEquipoVisitante());
        }

    
    @Test
    public void getEnfrentamientosJuez(){

        Torneo torneo = new Torneo("Torneo de Prueba",
                 LocalDate.of(2033, 12, 8),LocalDate.of(2033, 12, 1), LocalDate.of(2033, 12, 2),(byte) 2, (byte) 18, 100, TipoTorneo.LOCAL, GeneroTorneo.MIXTO);

        var representante1 = new Persona("Juan", "Castaño", "Jc@gmail.com", "3008905673");
        var equipoLocal = new Equipo("Beraca", representante1);

        var representante2 = new Persona("Armando", "Casas", "ARc@gmail.com", "3238205771");
        var equipoVisitante = new Equipo("Cafe y futbol", representante2);
        
        var juez1 = new Juez("Ramiro", "Castaño", "Rc@gmail.com", "321721927", "125821");
        var juez2 = new Juez("Carlos", "Gimenez", "CG12@gmail.com", "316523996", "119274");

        Enfrentamiento enfrentamiento1 = new Enfrentamiento("Enfrentamiento1", LocalDateTime.now().plusDays(1),"Lugar1", EstadoEnfretamiento.PENDIENTE, equipoLocal, equipoVisitante, juez1);
        Enfrentamiento enfrentamiento2 = new Enfrentamiento("Enfrentamiento2", LocalDateTime.now().plusDays(1),"Lugar2", EstadoEnfretamiento.PENDIENTE, equipoLocal, equipoVisitante, juez2);

        torneo.agendarEnfrentamiento(enfrentamiento1);
        torneo.agendarEnfrentamiento(enfrentamiento2);


        Collection<Enfrentamiento> enfrentamientosEquipoLocal = torneo.getEnfrentamientos();

        // Verificar que haya dos enfrentamientos para "Equipo A"
        assertEquals(2, enfrentamientosEquipoLocal.size());
    }

    @Test
    public void getEnfrentamientosEquipo_DebeDevolverEnfrentamientosDelEquipo() {

        Torneo torneo = new Torneo("Torneo de Prueba",
                 LocalDate.of(2033, 12, 8),LocalDate.of(2033, 12, 1), LocalDate.of(2033, 12, 2),(byte) 2, (byte) 18, 100, TipoTorneo.LOCAL, GeneroTorneo.MIXTO);

        var representante1 = new Persona("Juan", "Castaño", "Jc@gmail.com", "3008905673");
        var equipoLocal = new Equipo("Beraca", representante1);

        var representante2 = new Persona("Armando", "Casas", "ARc@gmail.com", "3238205771");
        var equipoVisitante = new Equipo("Cafe y futbol", representante2);
        
        var juez1 = new Juez("Ramiro", "Castaño", "Rc@gmail.com", "321721927", "125821");
        var juez2 = new Juez("Carlos", "Gimenez", "CG12@gmail.com", "316523996", "119274");

        Enfrentamiento enfrentamiento1 = new Enfrentamiento("Enfrentamiento1", LocalDateTime.now().plusDays(1),"Lugar1", EstadoEnfretamiento.PENDIENTE, equipoLocal, equipoVisitante, juez1);
        Enfrentamiento enfrentamiento2 = new Enfrentamiento("Enfrentamiento2", LocalDateTime.now().plusDays(1),"Lugar2", EstadoEnfretamiento.PENDIENTE, equipoLocal, equipoVisitante, juez2);
        
        torneo.agendarEnfrentamiento(enfrentamiento1);
        torneo.agendarEnfrentamiento(enfrentamiento2);
        //El error se encontraba en el metodo agendar enfrentamiento por que no agregaba los enfrentamientos a la lista de emfrentamiento
        Collection<Enfrentamiento> enfrentamientosEquipoLocal = torneo.getEnfrentamientosEquipo("Cafe y futbol");

    
        assertTrue(enfrentamientosEquipoLocal.contains(enfrentamiento1)); 
        assertTrue(enfrentamientosEquipoLocal.contains(enfrentamiento2));
    }

    @Test
    public void estadisticasEquipos(){

        Enfrentamiento enfrentamiento1 = new Enfrentamiento("Enfrentamiento1", LocalDateTime.now().plusDays(1),"Lugar1", EstadoEnfretamiento.PENDIENTE, equipoLocal, equipoVisitante, juez1);
        Enfrentamiento enfrentamiento2 = new Enfrentamiento("Enfrentamiento2", LocalDateTime.now().plusDays(1),"Lugar2", EstadoEnfretamiento.PENDIENTE, equipoLocal, equipoVisitante, juez2);

         enfrentamiento1.setResultado(new Resultado(0, 3));
        enfrentamiento2.setResultado(new Resultado(4,3));  

        torneo.agendarEnfrentamiento(enfrentamiento1);
        torneo.agendarEnfrentamiento(enfrentamiento2);
    
        Map<Equipo, EstadisticasEquipo> estadisticas=torneo.obtenerEstadisticasEquipos();

        EstadisticasEquipo estadisticasEquipoLocal =estadisticas.get(equipoLocal);
        assertEquals(1, estadisticasEquipoLocal.getVictorias());
        assertEquals(1, estadisticasEquipoLocal.getDerrotas());
        assertEquals(0, estadisticasEquipoLocal.getEmpates());

        // Verificar las estadísticas para el EquipoVisitante
        EstadisticasEquipo estadisticasEquipoVisitante = estadisticas.get(equipoVisitante);
        assertEquals(1, estadisticasEquipoVisitante.getVictorias());
        assertEquals(1, estadisticasEquipoVisitante.getDerrotas());
        assertEquals(0, estadisticasEquipoVisitante.getEmpates());
        
    }
        
}



    


