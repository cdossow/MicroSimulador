package Main;
import java.lang.Math;
import java.util.List;
import java.util.Random;

import org.jxmapviewer.viewer.GeoPosition;

import mapcontrols.SwingWaypoint;

public class Micro {
	String patente;
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
	public double MaxSpeed;
	int target;
	Ruta ruta;
	int UltimaParada;
	public SwingWaypoint waypoint;
	public GeoPosition geoposition;
	public int desaceleracion;
	public int Parar;
	static int DesaceleracionFactor = 10;
	static int AceleracionFactor = 20;
	static int DesaceleracionPorTarget = 3;
	static int DesaceleracionAnteTarget = 2;
	
	public Micro(int id, int id_linea, int estado, int id_ruta_ida, int id_ruta_vuelta, double lng, double lat) {
		super();
		this.id = id;
		this.id_linea = id_linea;
		this.estado = estado;
		this.id_ruta_ida = id_ruta_ida;
		this.id_ruta_vuelta = id_ruta_vuelta;
		this.lng = lng;
		this.lat = lat;
		this.waypoint=null;
		this.geoposition = new GeoPosition(this.lat, this.lng);
		this.desaceleracion=0;
	}

	public Ruta getRutaida() {
		return rutaida;
	}

	public void setRutaida(Ruta rutaida) {
		this.rutaida = rutaida;
	}

	public Ruta getRutavuelta() {
		return rutavuelta;
	}

	public void setRutavuelta(Ruta rutavuelta) {
		this.rutavuelta = rutavuelta;
	}

	public double getMaxSpeed() {
		return MaxSpeed;
	}

	public void setMaxSpeed(double maxSpeed) {
		MaxSpeed = maxSpeed;
	}

	public SwingWaypoint getWaypoint() {
		return waypoint;
	}

	public void setWaypoint(SwingWaypoint waypoint) {
		this.waypoint = waypoint;
	}

	public GeoPosition getGeoposition() {
		return geoposition;
	}

	public void setGeoposition(GeoPosition geoposition) {
		this.geoposition = geoposition;
	}

	public int getDesaceleracion() {
		return desaceleracion;
	}

	public void setDesaceleracion(int desaceleracion) {
		this.desaceleracion = desaceleracion;
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
		this.geoposition = new GeoPosition(this.lat, this.lng);
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
		this.geoposition = new GeoPosition(this.lat, this.lng);
	}
	public void update(List<Parada> Paradas){
		
		
		if(this.Parar>0){
			Parar=Parar-1;
			speed = speed - (MaxSpeed / DesaceleracionFactor);
			if(speed<0){
				speed=0;
			}
		}
		else if(desaceleracion>0){
			desaceleracion=desaceleracion-1;
			speed = speed - (MaxSpeed / DesaceleracionFactor);
			if(speed<0){
				speed=0;
			}
		}
		
		double tarlng = ruta.getPoints()[target].getLongitude();
		double tarlat = ruta.getPoints()[target].getLatitude();
		
		
		if(Parar<1 &&Util.distanciaCoord(lat,lng,tarlat,tarlng)<speed*6&&Util.distanciaCoord(lat,lng,tarlat,tarlng)>=speed*3&&Util.randInt(1, 10)<4){
			desaceleracion=desaceleracion+DesaceleracionAnteTarget;
			if(speed==0)
				speed = (MaxSpeed / AceleracionFactor);
		}
		
		for(Parada parada: Paradas){
			if(Util.distanciaCoord(lat,lng,parada.getGeoposition().getLatitude(),parada.getGeoposition().getLongitude())<speed*6){
				if(parada.id!=this.UltimaParada){
					Random rnd = new Random();
					if(rnd.nextDouble()<0.25)
						Parar=30;
					UltimaParada=parada.id;
				}
			}
		}

		
		//double desLng= (Math.abs(lng-ruta.getPoints()[target].getLongitude())/Math.abs(lng-ruta.getPoints()[target].getLongitude()+lat-ruta.getPoints()[target].getLatitude()))*speed;
		//double desLat= (Math.abs(lat-ruta.getPoints()[target].getLatitude())/Math.abs(lng-ruta.getPoints()[target].getLongitude()+lat-ruta.getPoints()[target].getLatitude()))*speed;
		double desLng=(Util.distanciaCoord(lat,lng,lat,tarlng)/Util.distanciaCoord(lat,lng,tarlat,tarlng))*speed;
		double desLat=(Util.distanciaCoord(lat,lng,tarlat,lng)/Util.distanciaCoord(lat,lng,tarlat,tarlng))*speed;
		//System.out.println((Util.distanciaCoord(lat,lng,tarlat,lng)/Util.distanciaCoord(lat,lng,tarlat,tarlng))+" - "+Util.distanciaCoord(lat,lng,lat,tarlng)/Util.distanciaCoord(lat,lng,tarlat,tarlng));
		if(Double.isNaN(desLat))
			desLat=0;
		if(Double.isNaN(desLng))
			desLng=0;
		if(lat>tarlat)
			desLat=desLat*-1;
		if(lng>tarlng)
			desLng=desLng*-1;
		//desLat=Util.truncate(desLat,6);
		//desLng=Util.truncate(desLng,6);
		//System.out.println(desLat);
		//System.out.println(desLng);
		this.setLat(lat+desLat);
		this.setLng(lng+desLng);
		this.waypoint.setPosition(this.geoposition);
		
		if(desaceleracion==0){
			speed = speed + (MaxSpeed / AceleracionFactor);
			if(speed>MaxSpeed)
				speed=MaxSpeed;
		}


		if(Util.distanciaCoord(lat,lng,tarlat,tarlng)<=speed*2){
			target=target+1;
			
			desaceleracion=desaceleracion+DesaceleracionPorTarget;
			
			if(target>=ruta.getPoints().length){
				//System.out.println("Cambio");
				if(estado==0){
					estado=1;
					ruta=rutavuelta;
				}
				if(estado==1){
					estado=0;
					ruta=rutaida;
				}
				this.setLat(this.ruta.getPoints()[0].getLatitude());
				this.setLng(this.ruta.getPoints()[0].getLongitude());
				this.waypoint.setPosition(this.geoposition);
				target=1;
			}
		}
		
		
		
		//System.out.println(Util.distanciaCoord(lat, lng, ruta.getPoints()[target].getLatitude(), ruta.getPoints()[target].getLongitude()));
		//System.out.println(ruta.getPoints()[target].getLatitude()+"-"+ruta.getPoints()[target].getLongitude());
	}
}
