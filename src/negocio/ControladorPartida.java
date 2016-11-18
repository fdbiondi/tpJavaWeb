package negocio;

import java.util.ArrayList;
import entidades.Partida;
import entidades.Personaje;
import datos.DatosPersonaje;

public class ControladorPartida {
	
	private Partida partida;
	
	private static int puntosGanador = 10;
	
	public ControladorPartida(Personaje personaje1, Personaje personaje2) {	
		this.partida = new Partida(personaje1, personaje2);
	}
	
	public Partida getPartida() {
		return this.partida;
	}
	
	public int getTurno() {
		return this.partida.getTurno();
	}
	
	public void atacar(int puntosAtaque) {
		this.partida.mensaje ="";
		this.partida.atacar(puntosAtaque);
	}
	
	public void defender(){
		this.partida.mensaje ="";
	    this.partida.defender();
	}
	
	public boolean validaFinPartida() {
		Personaje jugador1 = this.partida.getJugador1();
		Personaje jugador2 = this.partida.getJugador2();
		
		if(jugador1.getVida() <= 0)
		{
			jugador2.setPuntosDisponibles(jugador2.getPuntosDisponibles() + puntosGanador);
			
			DatosPersonaje.setPuntosGanador(jugador2);
			
			this.partida.mensaje = "Ha ganado el jugador "+jugador2.getNombre();
			
			return true;
		}
		
		if(jugador2.getVida() <= 0) 
		{
			jugador1.setPuntosDisponibles(jugador1.getPuntosDisponibles() + puntosGanador);
			
			DatosPersonaje.setPuntosGanador(jugador1);
			
			this.partida.mensaje = "Ha ganado el jugador "+jugador1.getNombre();
			
			return true;
		}
		
		return false;
	}
	
	public boolean validarEnergia(int energia){
		
		if(this.partida.getTurno() == this.partida.getJugador1().getId())
		{
			if(this.partida.getJugador1().getEnergia() < energia){
				this.partida.mensaje = "No tiene suficientes puntos de energia disponible";
				return false;
			}
		}
		if(this.partida.getTurno() == this.partida.getJugador2().getId())
		{
			if(this.partida.getJugador2().getEnergia() < energia){
				this.partida.mensaje = "No tiene suficientes puntos de energia disponible";
				return false;
			}
		}
			
		return true;
	}
}
