package co.edu.uniquindio.poo.torneodeportivo;

import java.time.LocalDateTime;

public enum EstadoEnfretamiento {

    PENDIENTE{
    @Override
        public boolean verificarEstado(Enfrentamiento enfrentamiento){
            return enfrentamiento.getFechaHora().isAfter(LocalDateTime.now());
    }
    },
    
    ENJUEGO{
    @Override
        public boolean verificarEstado(Enfrentamiento enfrentamiento){
            return enfrentamiento.getFechaHora().equals(LocalDateTime.now())||enfrentamiento.getFechaHora().equals(LocalDateTime.now().plusMinutes(60))||enfrentamiento.getFechaHora().equals(LocalDateTime.now().minusMinutes(60));
    }

    },
    FINALIZADO{
    @Override
        public boolean verificarEstado(Enfrentamiento enfrentamiento){
            return enfrentamiento.getFechaHora().isBefore(LocalDateTime.now());

        }

    },

    APLAZADO{
    @Override
        public boolean verificarEstado(Enfrentamiento enfrentamiento){ 
            return enfrentamiento.getFechaHora().equals(null);
        }

    };
    
    public abstract boolean verificarEstado(Enfrentamiento enfrentamiento);

}

