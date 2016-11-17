package Main;



import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import org.omg.CORBA.portable.InputStream;

import org.jxmapviewer.viewer.GeoPosition;

import mapcontrols.SwingWaypoint;



public class Main {
	
	static int MicrosPorRuta = 10;
	static double MicrosMaxSpeed = 0.00016; //60 km/h aprox
	static double Timer=0.2; //(segundos)
	

	
	public static void main(String[] args) {
		KmlReader KmlReader = new KmlReader();
		ImportToDB db;
		//Kml kml = loadXMLFile("resurce/Bus 7 Troncal (REGRESO).kml");
		List<Path> MklFiles = new ArrayList<Path>();
		List<Ruta> Rutas = new ArrayList<Ruta>();
		List<Linea> Lineas = new ArrayList<Linea>();
		List<Parada> Paradas = new ArrayList<Parada>();
		
		/*
		System.out.println("Loading .Kml files");
		URL url = Main.class.getResource("resource");
		
		if (url == null) {
			System.out.println("No folder");
		     // error - missing folder
		} else {
		    File dir=null;
			try {
				dir = new File(url.toURI());
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    for (File nextFile : dir.listFiles()) {
		        // Do something with nextFile
		    	MklFiles.add(nextFile.toPath());
		    }
		}
		*/
		
		System.out.println("Loading paradas.kml file");
		GeoPoint paradas[]=KmlReader.parseKml2("paradas/paradas.kml");
		
		for(int i = 0; i < paradas.length;i++){
			GeoPosition geoposition = new GeoPosition(paradas[i].latitude, paradas[i].longitude);
			Parada parada = new Parada(i,"Parada"+i,geoposition);
			parada.waypoint=new SwingWaypoint("P"+parada.getId(),parada.getGeoposition(),"Esta parada es la");
			Paradas.add(parada);
			
			//System.out.println(paradas[i].latitude+"-"+paradas[i].longitude);
		}
		
    	try {
    		System.out.println("Loading .Kml files");
			Files.walk(Paths.get("src/kml")).forEach(filePath -> {
			    if (Files.isRegularFile(filePath)) {
			    	MklFiles.add(filePath);
			    }
			});

		} catch (IOException e) {
			try {
				Files.walk(Paths.get("kml/")).forEach(filePath -> {
				    if (Files.isRegularFile(filePath)) {
				    	MklFiles.add(filePath);
				    }
				}
						);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
    	
		int id=0;
    	for (Path files : MklFiles) {
    		System.out.println(files.toString());
    		GeoPoint ruta[]=KmlReader.parseKml(files.toString());
    		String tempname = files.getFileName().toString();
    		tempname=tempname.replace(".kml","");
    	    Rutas.add(new Ruta(id,0,tempname,ruta));
    	    id++;
    	}
    	
    	
    	
    	Linea linea1 = new Linea(0,"Linea1","50.000.001-1");
    	Linea linea2 = new Linea(1,"Linea2","50.000.002-1");
    	Linea linea3 = new Linea(2,"Linea3","50.000.003-1");
    	Linea linea4 = new Linea(3,"Linea4","50.000.004-1");
    	Linea linea5 = new Linea(4,"Linea5","50.000.005-1");
    	Linea linea6 = new Linea(5,"Linea6","50.000.006-1");
    	Linea linea7 = new Linea(6,"Linea7","50.000.007-1");
    	Linea linea8 = new Linea(7,"Linea8","50.000.008-1");
    	Linea linea9 = new Linea(8,"Linea9","50.000.009-1");
    	Linea linea10 = new Linea(9,"Linea10","50.000.010-1");
    	
    	for (Ruta ruta : Rutas) {
    	if(ruta.nombre.contains("Bus 1 ")){
    		char letra = (char) (65 + (linea1.rutas.size()/2));
    		ruta.letra=letra;
    		linea1.rutas.add(ruta);}
    	if(ruta.nombre.contains("Bus 2 ")){
    		char letra = (char) (65 + (linea2.rutas.size()/2));
    		ruta.letra=letra;
    		linea2.rutas.add(ruta);}
    	if(ruta.nombre.contains("Bus 3 ")){
    		char letra = (char) (65 + (linea3.rutas.size()/2));
    		ruta.letra=letra;
    		linea3.rutas.add(ruta);}
    	if(ruta.nombre.contains("Bus 4 ")){
    		char letra = (char) (65 + (linea4.rutas.size()/2));
    		ruta.letra=letra;
    		linea4.rutas.add(ruta);}
    	if(ruta.nombre.contains("Bus 5 ")){
    		char letra = (char) (65 + (linea5.rutas.size()/2));
    		ruta.letra=letra;
    		linea5.rutas.add(ruta);}
    	if(ruta.nombre.contains("Bus 6 ")){
    		char letra = (char) (65 + (linea6.rutas.size()/2));
    		ruta.letra=letra;
    		linea6.rutas.add(ruta);}
    	if(ruta.nombre.contains("Bus 7 ")){
    		char letra = (char) (65 + (linea7.rutas.size()/2));
    		ruta.letra=letra;
    		linea7.rutas.add(ruta);}
    	if(ruta.nombre.contains("Bus 8 ")){
    		char letra = (char) (65 + (linea8.rutas.size()/2));
    		ruta.letra=letra;
    		linea8.rutas.add(ruta);}
    	if(ruta.nombre.contains("Bus 9 ")){
    		char letra = (char) (65 + (linea9.rutas.size()/2));
    		ruta.letra=letra;
    		linea9.rutas.add(ruta);}
    	if(ruta.nombre.contains("Bus 10 ")){
    		char letra = (char) (65 + (linea10.rutas.size()/2));
    		ruta.letra=letra;
    		linea10.rutas.add(ruta);}
    	//System.out.println(ruta.letra);
    	}
    	
    	Lineas.add(linea1);
    	Lineas.add(linea2);
    	Lineas.add(linea3);
    	Lineas.add(linea4);
    	Lineas.add(linea5);
    	Lineas.add(linea6);
    	Lineas.add(linea7);
    	Lineas.add(linea8);
    	Lineas.add(linea9);
    	Lineas.add(linea10);
    	
    	
    	Jmap map = new Jmap();
		int l=1;
		int idm =0;
		
		for (Parada  parada : Paradas) {
			parada.waypoint.SetToParada();
			map.micropuntos.add(parada.waypoint);
		}

    	for (Linea linea : Lineas) {
    		for(Ruta ruta : linea.rutas){
    			for(int i=0;i<MicrosPorRuta;i++){
        			PoblarLinea(linea,ruta,map,idm,Rutas);
        			idm = idm +1;
    			}
    		}
    	}
 
		map.main(args);
		

		
		while(true){
			long startTime = System.currentTimeMillis();
	    	for (Linea linea : Lineas) {
	    		for (Micro  micro : linea.micro) {
	    			micro.update(Paradas);
	    			//System.out.println(micro1.getLat()+"-"+micro1.getLng()+" Target:"+micro1.getTarget());
	    		}
	    	}
			map.refresh();
			
	    	for (Linea linea : Lineas) {
	    		try {
					MicroXmlParser.generate(linea);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
			long stopTime = System.currentTimeMillis();
		    long elapsedTime = stopTime - startTime;
		    //System.out.println(elapsedTime);
			try {
				long time =(long)((Timer*1000)-elapsedTime);
				if(time <0)
					time =0;
				Thread.sleep(time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Micro micro1=Lineas.get(0).micro.get(0);
			//System.out.println(micro1.getLat()+"-"+micro1.getLng()+" Target:"+micro1.getTarget());
			stopTime = System.currentTimeMillis();
		    elapsedTime = stopTime - startTime;
		    //System.out.println(elapsedTime);

		}
		
	    /*
    	db=new ImportToDB();
    	
    	try {
			db.CrearConexion();
			//db.exportarLineas(Lineas);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		*/
	}
	static void PoblarLinea(Linea linea, Ruta ruta,Jmap map, int idm, List<Ruta> Rutas){
		
		Micro micro = new Micro(0,0,0,0,0,0,0);
		micro.setSpeed(MicrosMaxSpeed);
		micro.setMaxSpeed(MicrosMaxSpeed);
		micro.id=idm;
		micro.patente="Micro"+idm;
		micro.setEstado(1);
		micro.setRuta(ruta);
		micro.setTarget(Util.randInt(1, micro.ruta.points.length-2));
		micro.setLat(micro.getRuta().getPoints()[micro.getTarget()-1].getLatitude());
		micro.setLng(micro.getRuta().getPoints()[micro.getTarget()-1].getLongitude());
		micro.waypoint=new SwingWaypoint(linea.id+1+""+ruta.letra, micro.geoposition,"Esta micro es de la");
		micro.setId_linea(linea.id);
		linea.micro.add(micro);
		map.micropuntos.add(micro.waypoint);
		idm = idm +1;
		
	    if(ruta.id%2==0){
	    	micro.rutaida=ruta;
	    	micro.rutavuelta=Rutas.get(ruta.id+1);
	    	//System.out.println("ida-"+ruta.id+"-"+linea.id);
	    }else{
	    	micro.rutaida=Rutas.get(ruta.id-1);
	    	micro.rutavuelta=ruta;
	    	//System.out.println("vuelta-"+ruta.id+"-"+linea.id);
	    }
		
	}
}
