package Main;


public class Ruta {
	int id;
	int id_linea;
	String nombre;
	//double lng[];
	//double lat[];
	GeoPoint points[];
	



	public Ruta(int id, int id_linea, String nombre, GeoPoint[] points) {
		super();
		this.id = id;
		this.id_linea = id_linea;
		this.nombre = nombre;
		this.points = points;
	}

	
	

	public GeoPoint[] getPoints() {
		return points;
	}




	public void setPoints(GeoPoint[] points) {
		this.points = points;
	}




	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getId_linea() {
		return id_linea;
	}


	public void setId_linea(int id_linea) {
		this.id_linea = id_linea;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
