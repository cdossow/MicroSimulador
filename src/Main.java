import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import org.omg.CORBA.portable.InputStream;

import de.micromata.opengis.kml.v_2_2_0.Placemark;


public class Main {
	public static void main(String[] args) {
		KmlReader KmlReader = new KmlReader();
		ImportToDB db;
		//Kml kml = loadXMLFile("resurce/Bus 7 Troncal (REGRESO).kml");
		List<Path> MklFiles = new ArrayList<Path>();
		List<Ruta> Rutas = new ArrayList<Ruta>();
		List<Linea> Lineas = new ArrayList<Linea>();
		
    	try {
			Files.walk(Paths.get("resurce")).forEach(filePath -> {
			    if (Files.isRegularFile(filePath)) {
			    	MklFiles.add(filePath);
			    }
			});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int id=0;
    	for (Path files : MklFiles) {
    		System.out.println(files.toString());
    		GeoPoint ruta[]=KmlReader.parseKml(files.toString());
    	    Rutas.add(new Ruta(id,0,files.getFileName().toString(),ruta));
    	    id++;
    	}
    	
    	Linea linea1 = new Linea(0,"Linea1","50.000.000-1");
    	linea1.rutas.add(Rutas.get(0));
    	linea1.rutas.add(Rutas.get(1));
    	Lineas.add(linea1);
	
    	db=new ImportToDB();
    	
    	try {
			db.CrearConexion();
			db.exportarLineas(Lineas);
		} catch (SQLException e) {
			e.printStackTrace();
		}

        /*

		//double rt1lo[]={0,10,20,20,50,0};
		//double rt1la[]={0,10,10,-10,30,0};
		//Ruta ruta1 = new Ruta(0,0,"linea1ida",rt1lo,rt1la);
		Micro micro1 = new Micro(0,0,0,0,0,0,0);
		micro1.setSpeed(0.0001);
		micro1.setTarget(0);
		micro1.setRuta(Rutas.get(0));
		micro1.setLat(micro1.getRuta().getPoints()[0].getLatitude());
		micro1.setLng(micro1.getRuta().getPoints()[0].getLongitude());
		
		while(micro1.getTarget()<micro1.getRuta().getPoints().length){
			micro1.update();
			System.out.println(micro1.getLat()+"-"+micro1.getLng()+" Target:"+micro1.getTarget());
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		*/
	}
}
