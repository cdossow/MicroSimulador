import java.lang.Math;

public class Micro {
	int id;
	int id_linea;
	int estado; //0 ida, 1 vuelta, 2 fuera de recorrido
	int id_ruta_ida;
	int id_ruta_vuelta;
	Ruta rutaida;
	Ruta rutavuelta;
	double lng;
	double lat;
	double speed;
	int target;
	Ruta ruta;
	
	
	public Micro(int id, int id_linea, int estado, int id_ruta_ida, int id_ruta_vuelta, double lng, double lat) {
		super();
		this.id = id;
		this.id_linea = id_linea;
		this.estado = estado;
		this.id_ruta_ida = id_ruta_ida;
		this.id_ruta_vuelta = id_ruta_vuelta;
		this.lng = lng;
		this.lat = lat;
	}

	public Ruta getRuta() {
		return ruta;
	}

	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
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

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getId_ruta_ida() {
		return id_ruta_ida;
	}

	public void setId_ruta_ida(int id_ruta_ida) {
		this.id_ruta_ida = id_ruta_ida;
	}

	public int getId_ruta_vuelta() {
		return id_ruta_vuelta;
	}

	public void setId_ruta_vuelta(int id_ruta_vuelta) {
		this.id_ruta_vuelta = id_ruta_vuelta;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}
	public void update(){
		
		double desLng= (Math.abs(lng-ruta.getPoints()[target].getLongitude())/Math.abs(lng-ruta.getPoints()[target].getLongitude()+lat-ruta.getPoints()[target].getLatitude()))*speed;
		double desLat= (Math.abs(lat-ruta.getPoints()[target].getLatitude())/Math.abs(lng-ruta.getPoints()[target].getLongitude()+lat-ruta.getPoints()[target].getLatitude()))*speed;
		if(Double.isNaN(desLat))
			desLat=0;
		if(Double.isNaN(desLng))
			desLng=0;
		if(lat>ruta.getPoints()[target].getLatitude())
			desLat=desLat*-1;
		if(lng>ruta.getPoints()[target].getLongitude())
			desLng=desLng*-1;
		//System.out.println(desLat);
		//System.out.println(desLng);
		lat=lat+desLat;
		lng=lng+desLng;
		//lat=Util.truncate(lat,5);
		//lng=Util.truncate(lng,5);
		if(Util.distanciaCoord(lat, lng, ruta.getPoints()[target].getLatitude(), ruta.getPoints()[target].getLongitude())<speed*2){
			target=target+1;
		}
		System.out.println(Util.distanciaCoord(lat, lng, ruta.getPoints()[target].getLatitude(), ruta.getPoints()[target].getLongitude()));
		//System.out.println(ruta.getPoints()[target].getLatitude()+"-"+ruta.getPoints()[target].getLongitude());
	}
}
