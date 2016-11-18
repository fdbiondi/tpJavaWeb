package entidades;

public class Personaje {
	
	public static int limiteDefensa = 20;
	public static int limiteEvasion = 80;
	
	//Attributes
	private int id;
	private String nombre;
	private int vida;
	private int energia;
	private int defensa;
	private int evasion;
	private int puntosDisponibles;

	// Constructores
	public Personaje(String nombre) {
		super();
		this.nombre = nombre;
		this.vida = 0;
		this.energia = 0;
		this.defensa = 0;
		this.evasion = 0;
		this.puntosDisponibles = 200;
	}

	// Getters
	public int getId() { return this.id; }

	public String getNombre() { return nombre; }

	public int getVida() { return vida; }

	public int getEnergia() { return energia; }

	public int getDefensa() { return defensa; }

	public int getEvasion() { return evasion; }

	public int getPuntosDisponibles() { return puntosDisponibles; }

	public int getPuntosTotales() {
		return this.vida + this.energia + this.defensa + this.evasion + this.puntosDisponibles;
	}

	public String toString() { return this.nombre; }

	// Setters
	public void setId(int id) { this.id = id; }

	public void setVida(int vida) { this.vida = vida; }
	
	public void setEnergia(int energia) { this.energia = energia; }

	public void setDefensa(int defensa) { this.defensa = defensa; }

	public void setEvasion(int evasion) { this.evasion = evasion; }

	public void setPuntosDisponibles(int puntosDisponibles) { this.puntosDisponibles = puntosDisponibles; }
	
	//Acciones
	 public void atacar(int puntos) {
		 this.energia -= puntos;
	 }
	 
	 public boolean intentarEvadir(int puntosAtaque) {
		 int umbralEvasion = (int)(Math.random() * 100);
		 if (getEvasion() < umbralEvasion) {
			 vida -= puntosAtaque;
			 return false;
		 }
		 return true;
	 }
	 
	 public void defender(int energia, int vida){
		 int energiaARecuperar = energia * getDefensa() / 100;
		 int vidaARecuperar = vida * getDefensa() / 250;
		 
		 this.energia += energiaARecuperar;
		 this.vida += vidaARecuperar;
	 }
}
