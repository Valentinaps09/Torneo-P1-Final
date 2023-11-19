/**
 * Clase que agrupa los datos de un Torneo
 * @author Área de programación UQ
 * @since 2023-08
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */
package co.edu.uniquindio.poo.torneodeportivo;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static co.edu.uniquindio.poo.util.AssertionUtil.ASSERTION;

public class Torneo {
    private final String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaInicioInscripciones;
    private LocalDate fechaCierreInscripciones;
    private final byte numeroParticipantes;
    private final byte limiteEdad;
    private final int valorInscripcion;
    private final TipoTorneo tipoTorneo;
    private final Collection<Equipo> equipos;
    protected final GeneroTorneo generoTorneo;
    private final Collection<Juez> juecesParticipantes;
    private Collection<Enfrentamiento>enfrentamientos;
    private Collection<Enfrentamiento> enfrentamientosAgendados;
    private Collection<Enfrentamiento> enfrentamientosFinalizados;


public Torneo(String nombre, LocalDate fechaInicio,
        LocalDate fechaInicioInscripciones,
        LocalDate fechaCierreInscripciones, byte numeroParticipantes,
        byte limiteEdad, int valorInscripcion,TipoTorneo tipoTorneo,GeneroTorneo generoTorneo) {

        ASSERTION.assertion( nombre != null , "El nombre es requerido");
        ASSERTION.assertion( numeroParticipantes >= 0,"El numero de participantes no puede ser negativo");
        ASSERTION.assertion( limiteEdad >= 0,"El limite de edad no puede ser negativo");
        ASSERTION.assertion (valorInscripcion >= 0,"El valor de la inscripción no puede ser negativo");
        
        this.nombre = nombre;   

        setFechaInicioInscripciones(fechaInicioInscripciones);
        setFechaCierreInscripciones(fechaCierreInscripciones); 
        setFechaInicio(fechaInicio);
        this.numeroParticipantes = numeroParticipantes;
        this.limiteEdad = limiteEdad;
        this.valorInscripcion = valorInscripcion;
        this.tipoTorneo = tipoTorneo;
        this.equipos = new LinkedList<>();
        this.generoTorneo = generoTorneo;
        this.juecesParticipantes = new LinkedList<>();
        this.enfrentamientos = new LinkedList<>();
        this.enfrentamientosAgendados =new LinkedList<>();
        this.enfrentamientosFinalizados= new LinkedList<>();


    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaInicioInscripciones() {
        return fechaInicioInscripciones;
    }

    public LocalDate getFechaCierreInscripciones() {
        return fechaCierreInscripciones;
    }

    public byte getNumeroParticipantes() {
        return numeroParticipantes;
    }

    public byte getLimiteEdad() {
        return limiteEdad;
    }

    public int getValorInscripcion() {
        return valorInscripcion;
    }

    public TipoTorneo getTipoTorneo() {
        return tipoTorneo;
    }

    public GeneroTorneo getGeneroTorneo(){
        return generoTorneo;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        ASSERTION.assertion( fechaInicio != null , "La fecha de inicio es requerida");
        ASSERTION.assertion( ( fechaInicioInscripciones == null || fechaInicio.isAfter(fechaInicioInscripciones) ) &&
                ( fechaCierreInscripciones == null || fechaInicio.isAfter(fechaCierreInscripciones) ),"La fecha de inicio no es válida" );
        this.fechaInicio = fechaInicio;
    }

    public void setFechaInicioInscripciones(LocalDate fechaInicioInscripciones) {
        ASSERTION.assertion( fechaInicioInscripciones != null , "La fecha de inicio de inscripciones es requerida");
        this.fechaInicioInscripciones = fechaInicioInscripciones;
    }


    public void setFechaCierreInscripciones(LocalDate fechaCierreInscripciones) {
        ASSERTION.assertion( fechaCierreInscripciones != null , "La fecha de cierre es requerida");
        ASSERTION.assertion( fechaCierreInscripciones.isAfter(fechaInicioInscripciones),"La fecha de cierre de inscripciones debe ser posterior a la fecha de inicio de inscripciones" );
        this.fechaCierreInscripciones = fechaCierreInscripciones;
    }
    
    /**
     * Permite registrar un equipo en el torneo
     * @param equipo Equipo a ser registrado
     * @throws Se genera un error si ya existe un equipo registrado con el mismo nombre, o en caso de que las inscripciones del torneo no esten abiertas.
     */
    public void registrarEquipo(Equipo equipo) {
        validarEquipoExiste(equipo); 

        validarInscripciopnesAbiertas(); 

        equipos.add(equipo);
    }

    /**
     * Valida que las inscripciones del torneo esten abiertas, en caso de no estarlo genera un assertion error.
     */
    private void validarInscripciopnesAbiertas() {
        boolean inscripcionAbierta = fechaInicioInscripciones.isBefore(LocalDate.now()) && fechaCierreInscripciones.isAfter(LocalDate.now());
        ASSERTION.assertion( inscripcionAbierta,"Las inscripciones no están abiertas");
    }

    /**
     * Valida que no exista ya un equipo registrado con el mismo nombre, en caso de haberlo genera un assertion error.
     */
    private void validarEquipoExiste(Equipo equipo) {
        boolean existeEquipo = buscarEquipoPorNombre(equipo.nombreEquipo()).isPresent();
        ASSERTION.assertion( !existeEquipo,"El equipo ya esta registrado");
    }

    /**
     * Permite obtener una copia no modificable de la lista de los equipos registrados.
     * @return Collection<Equipo> no modificable de los equipos registrados en el torneo.
     */
    public Collection<Equipo> getEquipos() {
        return Collections.unmodifiableCollection(equipos);
    }
    
    /**
     * Permite buscar un equipo por su nomnbre entre los equipos registrados en el torneo
     * @param nombre Nombre del equipo que se está buscando
     * @return Un Optional<Equipo> con el equipo cuyo nombre sea igual al nombre buscado, o un Optional vacio en caso de no encontrar un equipo con nombre igual al dado.
     */
    public Optional<Equipo> buscarEquipoPorNombre(String nombre){
        Predicate<Equipo> condicion = equipo->equipo.nombreEquipo().equals(nombre);
        return equipos.stream().filter(condicion).findAny();
    }

    /**
     * Permite registrar un jugador en el equipo siempre y cuando este dentro de las fechas validas de registro, 
     * no exista ya un jugador registrado con el mismo nombre y apellido y el jugador no exceda el limite de edad del torneo.
     *  
     * @param nombre Nombre del equipo en que se desea registrar el jugador
     * @param jugador Jugador que se desea registrar.
     */
    public void registrarJugador(String nombre, Jugador jugador,Torneo torneo) {
        var equipo = buscarEquipoPorNombre(nombre);
        equipo.ifPresent( (e)->registrarJugador(e, jugador,torneo) );
    }

    /**
     * Permite registrar un jugador en el equipo siempre y cuando este dentro de las fechas validas de registro, 
     * no exista ya un jugador registrado con el mismo nombre y apellido y el jugador no exceda el limite de edad del torneo.
     * 
     * @param equipo Equipo en el que se desea registrar el jugador.
     * @param jugador Jugador que se desea registrar.
     */
    public void registrarJugador(Equipo equipo, Jugador jugador,Torneo torneo) {
        ASSERTION.assertion( !LocalDate.now().isAfter(fechaCierreInscripciones) , "No se pueden registrar jugadores después del a fecha de cierre de inscripciones");
        validarLimiteEdadJugador(jugador); 
        validarJugadorExiste(jugador);
        if(validarGeneroJugador(jugador,torneo))
        equipo.registrarJugador(jugador,torneo);
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
     * Permite buscar un jugador basado en su nombre y apellido en todos los equipos registrados en el torneo.
     * @param jugador Jugador que se desea buscar
     * @return Optional con el jugador encontrado o un optional vacío en caso de no haber encontrado un jugador con el nombre y apellido del jugador buscado.
     */
    public Optional<Jugador> buscarJugador(Jugador jugador){
        return equipos.stream()
            .map(equipo->equipo.buscarJugador(jugador))
            .filter(Optional::isPresent)
            .map(Optional::get)
            .findAny();
    }

    /**
     * Valida que no exista ya un jugador registrado con el mismo nombre y apellido, en caso de haberlo genera un assertion error.
     */
    private void validarJugadorExiste(Jugador jugador) {
        boolean existeJugador = buscarJugador(jugador).isPresent();
        ASSERTION.assertion( !existeJugador,"El jugador ya esta registrado");
    }

    /**
     * Valida que no exista se puedan registrar jugadores que al momento del inicio del torneo excedan el limite de edad.
     */
    private void validarLimiteEdadJugador(Jugador jugador) {
        var edadAlInicioTorneo = jugador.calcularEdad(fechaInicio);
        ASSERTION.assertion( limiteEdad == 0 || limiteEdad >= edadAlInicioTorneo , "No se pueden registrar jugadores que excedan el limite de edad del torneo"); 
    }

    public Collection<Juez> getJuecesParticipantes() {
        return Collections.unmodifiableCollection(juecesParticipantes);
    }
    //Este método agrega un juez a la lista jueces participantes
    public void registrarJuez(Juez juez) {
        juecesParticipantes.add(juez);
    }
    
    public Collection<Enfrentamiento> getEnfrentamientos() {
        return Collections.unmodifiableCollection(enfrentamientos);
    }

    public Collection<Enfrentamiento> getEnfrentamientosAgendados() {
        return Collections.unmodifiableCollection(enfrentamientosAgendados);
    }

    public Collection<Enfrentamiento> getEnfrentamientosFinalizados() {
        return Collections.unmodifiableCollection(enfrentamientosFinalizados);
    }

    //Este método agrega un enfrentamiento a la lista de enfrentamientos agendados del torneo
    
    public void agendarEnfrentamiento(Enfrentamiento enfrentamiento) {
        if(enfrentamientos.contains(enfrentamiento)){
                enfrentamientosAgendados.add(enfrentamiento);
        }else{
                enfrentamientos.add(enfrentamiento);
                enfrentamientosAgendados.add(enfrentamiento);
            }
        }
    

    //Este método registra el resultado de un enfrentamiento. Verifica si el enfrentamiento está en la lista de enfrentamientos agendados, establece el resultado y mueve el enfrentamiento a la lista de enfrentamientos finalizados. Si el enfrentamiento no está agendado, muestra un mensaje por consola.

    public void registrarResultado(Enfrentamiento enfrentamiento, Resultado resultado) {

        if (enfrentamientosAgendados.contains(enfrentamiento)) {
            enfrentamiento.setResultado(resultado);
            enfrentamientosAgendados.remove(enfrentamiento);
            enfrentamientosFinalizados.add(enfrentamiento);

        } else {
            System.out.println("El enfrentamiento no está agendado para registrar resultados.");
        }
    }

    //Este método devuelve una colección de enfrentamientos en los que participa un equipo con el nombre especificado.

    public Collection<Enfrentamiento> getEnfrentamientosEquipo(String nombreEquipo) {
        Predicate<Enfrentamiento> condicion = enfrentamiento ->
                enfrentamiento.getEquipoLocal().nombreEquipo().equals(nombreEquipo) ||
                enfrentamiento.getEquipoVisitante().nombreEquipo().equals(nombreEquipo);
        return enfrentamientos.stream().filter(condicion).collect(Collectors.toList());
    }

    //Este método devuelve una colección de enfrentamientos en los que participa un juez con el número de licencia especificado.

    public Collection<Enfrentamiento> getEnfrentamientosJuez(String numeroLicencia) {
        Predicate<Enfrentamiento> condicion = enfrentamiento ->
                enfrentamiento.getJuecesParticipantes().stream()
                        .anyMatch(juez -> juez.getLicencia().equals(numeroLicencia));
        return enfrentamientos.stream().filter(condicion).collect(Collectors.toList());
    }

    //Este método devuelve un mapa que relaciona cada equipo con sus estadísticas (victorias, empates y derrotas) en base a los enfrentamientos registrados. Itera sobre los enfrentamientos y actualiza las estadísticas de los equipos involucrados en esos enfrentamientos.
    public Map<Equipo, EstadisticasEquipo> obtenerEstadisticasEquipos() {
        Map<Equipo, EstadisticasEquipo> estadisticasEquipos = new HashMap<>();
    
        for (Equipo equipo : equipos) {
            EstadisticasEquipo estadisticas = new EstadisticasEquipo();
            estadisticasEquipos.put(equipo, estadisticas);
        }
    
        for (Enfrentamiento enfrentamiento : enfrentamientos) {
            Resultado resultado = enfrentamiento.getResultado();
    
            // Solo procesar enfrentamientos con resultado y equipos no nulos
            if (resultado != null && enfrentamiento.getEquipoLocal() != null && enfrentamiento.getEquipoVisitante() != null) {
                EstadisticasEquipo local = estadisticasEquipos.get(enfrentamiento.getEquipoLocal());
                EstadisticasEquipo visitante = estadisticasEquipos.get(enfrentamiento.getEquipoVisitante());
    
                if (resultado.getPuntosEquipoLocal() > resultado.getPuntosEquipoVisitante()) {
                    local.incrementarVictorias();
                    visitante.incrementarDerrotas();
                } else if (resultado.getPuntosEquipoLocal() < resultado.getPuntosEquipoVisitante()) {
                    local.incrementarDerrotas();
                    visitante.incrementarVictorias();
                } else {
                    local.incrementarEmpates();
                    visitante.incrementarEmpates();
                }
            }
        }
    
        return estadisticasEquipos;
    }
}

