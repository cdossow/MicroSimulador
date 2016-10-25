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
	
	static int MicrosPorRuta = 12;
	static double MicrosMaxSpeed = 0.00016;
	static double Timer=0.1; //(segundos)
	

	
	public static void main(String[] args) {
		KmlReader KmlReader = new KmlReader();
		ImportToDB db;
		//Kml kml = loadXMLFile("resurce/Bus 7 Troncal (REGRESO).kml");
		List<Path> MklFiles = new ArrayList<Path>();
		List<Ruta> Rutas = new ArrayList<Ruta>();
		List<Linea> Lineas = new ArrayList<Linea>();
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
    	if(ruta.nombre.contains("Bus 1 "))
    		linea1.rutas.add(ruta);
    	if(ruta.nombre.contains("Bus 2 "))
    		linea2.rutas.add(ruta);
    	if(ruta.nombre.contains("Bus 3 "))
    		linea3.rutas.add(ruta);
    	if(ruta.nombre.contains("Bus 4 "))
    		linea4.rutas.add(ruta);
    	if(ruta.nombre.contains("Bus 5 "))
    		linea5.rutas.add(ruta);
    	if(ruta.nombre.contains("Bus 6 "))
    		linea6.rutas.add(ruta);
    	if(ruta.nombre.contains("Bus 7 "))
    		linea7.rutas.add(ruta);
    	if(ruta.nombre.contains("Bus 8 "))
    		linea8.rutas.add(ruta);
    	if(ruta.nombre.contains("Bus 9 "))
    		linea9.rutas.add(ruta);
    	if(ruta.nombre.contains("Bus 10 "))
    		linea10.rutas.add(ruta);
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
    	for (Linea linea : Lineas) {
    		for(int i = 0; i <= MicrosPorRuta; i++){
    			Micro micro = new Micro(0,0,0,0,0,0,0);
    			micro.setSpeed(MicrosMaxSpeed);
    			micro.setMaxSpeed(MicrosMaxSpeed);
    			micro.id=idm;
    			micro.patente="Micro"+idm;
    			micro.setEstado(0);
    			micro.setRuta(linea.getRutas().get(0));
    			micro.setTarget(Util.randInt(1, micro.ruta.points.length-2));
    			micro.setLat(micro.getRuta().getPoints()[micro.getTarget()-1].getLatitude());
    			micro.setLng(micro.getRuta().getPoints()[micro.getTarget()-1].getLongitude());
    			micro.waypoint=new SwingWaypoint(l+"A", micro.geoposition);
    			micro.rutaida=linea.getRutas().get(0);
    			micro.rutavuelta=linea.getRutas().get(1);
    			micro.setId_linea(linea.id);
    			linea.micro.add(micro);
    			map.micropuntos.add(micro.waypoint);
    			idm = idm +1;
    		}
    		for(int i = 0; i <= MicrosPorRuta; i++){
    			Micro micro = new Micro(0,0,0,0,0,0,0);
    			micro.setSpeed(MicrosMaxSpeed);
    			micro.setMaxSpeed(MicrosMaxSpeed);
    			micro.id=idm;
    			micro.patente="Micro"+idm;
    			micro.setEstado(1);
    			micro.setRuta(linea.getRutas().get(1));
    			micro.setTarget(Util.randInt(1, micro.ruta.points.length-2));
    			micro.setLat(micro.getRuta().getPoints()[micro.getTarget()-1].getLatitude());
    			micro.setLng(micro.getRuta().getPoints()[micro.getTarget()-1].getLongitude());
    			micro.waypoint=new SwingWaypoint(l+"A", micro.geoposition);
    			micro.rutaida=linea.getRutas().get(0);
    			micro.rutavuelta=linea.getRutas().get(1);
    			micro.setId_linea(linea.id);
    			linea.micro.add(micro);
    			map.micropuntos.add(micro.waypoint);
    			idm = idm +1;
    		}
    		l=l+1;
    	}
 
    	
    	for (Linea linea : Lineas) {
    		try {
				MicroXmlParser.generate(linea);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	
		
		map.main(args);
		while(true){
	    	for (Linea linea : Lineas) {
	    		for (Micro  micro : linea.micro) {
	    			micro.update();
	    			//System.out.println(micro1.getLat()+"-"+micro1.getLng()+" Target:"+micro1.getTarget());
	    		}
	    	}
			map.refresh();
			try {
				Thread.sleep((long)(Timer*1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Micro micro1=Lineas.get(0).micro.get(0);
			//System.out.println(micro1.getLat()+"-"+micro1.getLng()+" Target:"+micro1.getTarget());
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
}
