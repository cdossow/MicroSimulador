package Main;

import org.jxmapviewer.viewer.GeoPosition;

import mapcontrols.SwingWaypoint;

public class Parada {
	int id;
	String nombre;
	public GeoPosition geoposition;
	public SwingWaypoint waypoint;
	
	
	public Parada(int id, String nombre, GeoPosition geoposition) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.geoposition = geoposition;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public GeoPosition getGeoposition() {
		return geoposition;
	}



	public void setGeoposition(GeoPosition geoposition) {
		this.geoposition = geoposition;
	}
	
	
	
}
