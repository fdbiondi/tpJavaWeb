package negocio;

import java.util.ArrayList;

import datos.DatosPersonaje;
import entidades.Personaje;

public class ControladorPersonaje {
	
	private ArrayList<Personaje> personajes;
	
	private Personaje personaje;
	
	private DatosPersonaje datosPersonaje;

	public ControladorPersonaje() {
		this.personajes = new ArrayList<Personaje>();
		this.datosPersonaje = new DatosPersonaje();
	}

	public Personaje traerActual() {
		return this.personaje;
	}
	
	public boolean crear(String nombre) {
		
		if(!existe(nombre)) {
			this.personaje = new Personaje(nombre.trim());
			
			personaje.setId(this.datosPersonaje.crear(personaje));
			
			return true;
		}
		
		return false;
	}
	
	public void actualizar(Personaje personaje) {
		this.datosPersonaje.actualizar(personaje);
	}
	
	public void eliminar(Personaje personaje){
		this.datosPersonaje.eliminar(personaje);
	}
	
	public Personaje traerPor(int id) {
		return this.datosPersonaje.traerPor(id);
	}
	
	public ArrayList<Personaje> traerTodos(){
		personajes = this.datosPersonaje.traerTodos();
		return personajes;
	}
	
	public boolean existe(String nombre) {
		return this.datosPersonaje.existe(nombre.trim());
	}

}
