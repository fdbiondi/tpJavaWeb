package datos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import utils.*;

public class FactoryConexion {

	private String configPath = "com/package/props/config.properties";
	private String dbDriver="com.mysql.jdbc.Driver";
	private String host="localhost";
	private String port="3306";
	private String user = "root";
	private String pass = "1454710050";
	private String db = "tpjava";
	private String dbType="mysql";
	
	private Connection conn;
	private int cantConn=0;
	
	private FactoryConexion() throws ApplicationException{
		try {
			Class.forName(dbDriver);
		} catch (ClassNotFoundException e) {
			throw new ApplicationException("Error del Driver JDBC",e);
		}
	}
	
	private static FactoryConexion instancia;
	
	public static FactoryConexion getInstancia() throws ApplicationException{
		if (instancia==null){
			instancia = new FactoryConexion();
			//instancia.setAttributes();
		}
		return instancia;
	}
	
	private void setAttributes() throws ApplicationException {
		File configFile = new File(configPath); 
		try {
		    FileReader reader = new FileReader(configFile);
		    Properties props = new Properties();
		    props.load(reader);
		    
		    user = props.getProperty("user");
		    pass = props.getProperty("password");
		    db = props.getProperty("db_name");
		    
		    reader.close();
		} catch (FileNotFoundException e) {
			throw new ApplicationException("No se encontr� el archivo de configuraci�n",e);
		} catch (IOException e) {
			throw new ApplicationException("Error al abrir el archivo de configuraci�n",e);
		}	
	}
	
	public Connection getConn(){
		try {
			if(conn==null || conn.isClosed()){
				conn = DriverManager.getConnection(
						"jdbc:"+dbType+"://"+host+":"+port+"/"+
						db+"?user="+user+"&password="+pass);
				cantConn++;
			}
		} catch (SQLException e) {
			new ApplicationException("Error al conectar a la DB",e);

		}
		return conn;
	}
	
	public void releaseConn() throws ApplicationException{
		try {
			cantConn--;
			if(cantConn==0){
				conn.close();
			}
		} catch (SQLException e) {
			throw new ApplicationException("Error al cerrar conexion",e);
		}
		
	}
}
