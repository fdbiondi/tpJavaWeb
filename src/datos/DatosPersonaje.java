package datos;

import java.sql.*;
import java.util.ArrayList;
import entidades.Personaje;
import utils.ApplicationException;

public class DatosPersonaje {

	public DatosPersonaje() {
		
	}
	
	public Personaje traerPor(int id) {
		ResultSet rs=null;
		PreparedStatement stmt=null;
		Personaje personaje = null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("SELECT * FROM personajes WHERE id = ? LIMIT 1");
			
			stmt.setInt(1, id);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				personaje = new Personaje(rs.getString("nombre"));
				
				personaje.setId(id);
				personaje.setDefensa(rs.getInt("defensa"));
				personaje.setEnergia(rs.getInt("energia"));
				personaje.setEvasion(rs.getInt("evasion"));
				personaje.setVida(rs.getInt("vida"));
				personaje.setPuntosDisponibles(rs.getInt("puntosDisponibles"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (ApplicationException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return personaje;
	}
	
	public ArrayList<Personaje> traerTodos(){
		
		ArrayList<Personaje> personajes = new ArrayList<Personaje>();
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("SELECT * FROM personajes");
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Personaje personaje = new Personaje(rs.getString("nombre"));
				
				personaje.setId(rs.getInt("id"));
				personaje.setDefensa(rs.getInt("defensa"));
				personaje.setEnergia(rs.getInt("energia"));
				personaje.setEvasion(rs.getInt("evasion"));
				personaje.setVida(rs.getInt("vida"));
				personaje.setPuntosDisponibles(rs.getInt("puntosDisponibles"));
				
				personajes.add(personaje);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (ApplicationException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return personajes;
	}
	
	public int crear(Personaje personaje) {
		ResultSet rs=null;
		PreparedStatement stmt=null;
		int id = 0;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"INSERT INTO personajes(nombre, vida, energia, defensa, evasion, puntosDisponibles) "+
					"VALUES(?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
						
			stmt.setString(1, personaje.getNombre());
			stmt.setInt(2, personaje.getVida());
			stmt.setInt(3, personaje.getEnergia());
			stmt.setInt(4, personaje.getDefensa());
			stmt.setInt(5, personaje.getEvasion());
			stmt.setInt(6, personaje.getPuntosDisponibles());
			stmt.execute();
			
			rs=stmt.getGeneratedKeys();
			
			if(rs!=null && rs.next())
				id = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (ApplicationException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return id;
	}
	
	public void actualizar(Personaje personaje) {
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"UPDATE personajes " +
					"SET vida = ?, energia = ?, defensa = ?, evasion = ?, puntosDisponibles = ? "+
					"WHERE id = ?");
						
			stmt.setInt(1, personaje.getVida());
			stmt.setInt(2, personaje.getEnergia());
			stmt.setInt(3, personaje.getDefensa());
			stmt.setInt(4, personaje.getEvasion());
			stmt.setInt(5, personaje.getPuntosDisponibles());
			stmt.setInt(6, personaje.getId());
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt!=null) stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (ApplicationException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void eliminar(Personaje personaje) {
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"DELETE FROM personajes " +
					"WHERE id = ?");
			stmt.setInt(1, personaje.getId());
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt!=null) stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (ApplicationException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean existe(String nombre) {
		ResultSet rs=null;
		PreparedStatement stmt=null;
		boolean existe = false;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"SELECT nombre FROM personajes " +
					"WHERE nombre=?");
			stmt.setString(1, nombre);
			
			rs = stmt.executeQuery();
			
			while(rs.next()){ 
				existe = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (ApplicationException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return existe;
	}
	
	public static void setPuntosGanador(Personaje personaje) {
		ResultSet rs=null;
		PreparedStatement stmt=null;
		
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"UPDATE personajes SET puntosDisponibles=? " +
					"WHERE id=?");
			
			stmt.setInt(1, personaje.getPuntosDisponibles());
			stmt.setInt(2, personaje.getId());
			
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(stmt!=null) stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			} catch (ApplicationException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
