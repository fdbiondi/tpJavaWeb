package entidades;

public class Partida {

	//Attributes
	private int turno;
	//Jugador1
	private Personaje jugador1;
	private int energia1;
	private int vida1;
	//Jugador2
	private Personaje jugador2;
	private int energia2;
	private int vida2;
	
	public String mensaje;
	
	//Constructor
	public Partida(Personaje jugador1, Personaje jugador2) {
		 this.jugador1 = jugador1;
		 this.jugador2 = jugador2;
		 
		 this.energia1 = jugador1.getEnergia();
		 this.vida1 = jugador1.getVida();
		 this.energia2 = jugador2.getEnergia();
		 this.vida2 = jugador2.getVida();
		 
		 turno = Math.random() < 0.5 ? jugador1.getId() : jugador2.getId();
		 
		 mensaje = "";
	}
	
	//Acciones
	public void atacar(int puntosAtaque) {
		boolean evasion = false;
		
		if(turno == jugador1.getId()){
			jugador1.atacar(puntosAtaque);
			evasion = jugador2.intentarEvadir(puntosAtaque);
		}
		else if(turno == jugador2.getId()){
			jugador2.atacar(puntosAtaque);
			evasion = jugador1.intentarEvadir(puntosAtaque);
		}
		
		if(evasion)
			mensaje = "Ataque evadido";
		else 
			mensaje = "Ataque realizado con exito";
		
		cambiarTurno();
	}
	
	public void defender() {
		if(turno == jugador1.getId())
			jugador1.defender(energia1, vida1);
		else if(turno == jugador2.getId())
			jugador2.defender(energia2, vida2);
		
		mensaje = "Defensa efectuada. Energia y vida recuperada.";
		
		cambiarTurno();
	}

	public void cambiarTurno() {
		if(turno == jugador1.getId())
			turno = jugador2.getId();
		else if(turno == jugador2.getId())
			turno = jugador1.getId();
	}

	//Getters
	public int getTurno() { return this.turno; }

	public Personaje getJugador1() { return jugador1; }

	public Personaje getJugador2() { return jugador2; }
	
	//Setters
	public void setJugador1(Personaje jugador1) { this.jugador1 = jugador1; }

	public void setJugador2(Personaje jugador2) { this.jugador2 = jugador2; }
}