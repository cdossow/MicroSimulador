import java.util.ArrayList;
import java.util.List;

public class Linea {
	int id;
	String nombre;
	List<Ruta> rutas;
	String rut;
	
	public Linea(int id, String nombre, String rut) {
		super();
		this.id = id;
		this.nombre = nombre;
		rutas = new ArrayList<Ruta>();
		this.rut = rut;
	}




	public Linea(int id, String nombre, String rut,  List<Ruta> rutas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.rutas = rutas;
		this.rut = rut;
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




	public List<Ruta> getRutas() {
		return rutas;
	}




	public void setRutas(List<Ruta> rutas) {
		this.rutas = rutas;
	}
	
	
}
