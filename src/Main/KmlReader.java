package Main;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.LineString;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class KmlReader {


        //final Kml kml = Kml.unmarshal(new File("resurce/Bus 7 Troncal (IDA).kml"));
        public GeoPoint[] parseKml(String path) {
        	
        	
            final Kml kmlNew = Kml.unmarshal(new File(path)); 

            Feature feature = kmlNew.getFeature();
            //final Placemark placemark = (Placemark) kmlNew.getFeature(); 
            //Placemark placemark=null;
            List<Placemark> placemarklist = new ArrayList<Placemark>();
            Document document = (Document) feature;
            List<Feature> featureList = document.getFeature();
            for(Feature documentFeature : featureList) {
                if(documentFeature instanceof Placemark) {
                	placemarklist.add((Placemark) documentFeature);
                }
            }
            //Point point = (Point) placemarklist.get(0).getGeometry(); 
            LineString lineString = (LineString) placemarklist.get(0).getGeometry(); 
            List<Coordinate> coordinates = lineString.getCoordinates(); 
            
            GeoPoint Ruta[] = new GeoPoint[coordinates.size()];
            int id=0;

        	for (Coordinate coords : coordinates) {

        		double lat=coords.getLatitude();
        		double lng=coords.getLongitude();
        		
        		//lat=Util.truncate(lat,5);
        		//lng=Util.truncate(lng,5);
        		
        		GeoPoint point = new GeoPoint(lat,lng);
        		Ruta[id]=point;
        		id++;
        	}
        	
            return Ruta;
        } 
        
        public GeoPoint[] parseKml2(String path) {
        	
        	
            final Kml kmlNew = Kml.unmarshal(new File(path)); 

            Feature feature = kmlNew.getFeature();
            //final Placemark placemark = (Placemark) kmlNew.getFeature(); 
            //Placemark placemark=null;
            List<Placemark> placemarklist = new ArrayList<Placemark>();
            Document document = (Document) feature;
            List<Feature> featureList = document.getFeature();
            for(Feature documentFeature : featureList) {
                if(documentFeature instanceof Placemark) {
                	placemarklist.add((Placemark) documentFeature);
                }
            }
            //Point point = (Point) placemarklist.get(0).getGeometry(); 
            
            List<Coordinate> coordinates= new ArrayList<Coordinate>();
            for(Placemark placemark : placemarklist){
                Point point = (Point) placemark.getGeometry(); 
                coordinates.addAll(0, point.getCoordinates());
            }
            
            GeoPoint Ruta[] = new GeoPoint[coordinates.size()];
            int id=0;

        	for (Coordinate coords : coordinates) {

        		double lat=coords.getLatitude();
        		double lng=coords.getLongitude();
        		
        		//lat=Util.truncate(lat,5);
        		//lng=Util.truncate(lng,5);
        		
        		GeoPoint point2 = new GeoPoint(lat,lng);
        		Ruta[id]=point2;
        		id++;
        	}
        	
            return Ruta;
        } 
    }


