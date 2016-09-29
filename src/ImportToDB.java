
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.Statement;

public class ImportToDB {

    static String strUsuario = "root";
    static String strPassword = "1234";
    static String strBaseDatos ="test";
    static String strHost = "localhost";
    /*
    static String strUsuario = "sgonzalez";
    static String strPassword = "q11IpXNDIku05l5I";
    static String strBaseDatos ="sgonzalez";
    static String strHost = "w3.inf.uct.cl";
    */
    
    public Connection connection =null;
     
    //Constructor, le llegan los datos con los que se conectara al DBMS
    public void CrearConexion(String usr,String pw, String bd)
    {
        strUsuario = usr;
        strPassword = pw;
        strBaseDatos =bd;
        try
        {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");      
        } catch ( ClassNotFoundException e )
        {
            System.out.println("ERROR: Error al cargar la clase del Driver");       
        }
    }
     
    //Constructor, le llegan los datos con los que se conectara al DBMS
    // a dif. del otro constructor le llega tbn el host (servidor)
    public void CrearConexion() throws SQLException
    {
    	this.connection=getConnection();
    }
     
    public Connection getConnection() throws SQLException 
    {
        //a continuacion vamos a formar la cadena de conexion, pero...
        //OJO aca debes poner el nombre de la instancia de SQL Server que vas a usar
        String url = "jdbc:mysql://"+strHost+"/"+strBaseDatos;
        return DriverManager.getConnection(url,strUsuario,strPassword);
    }
    
    public void exportarLineas(List<Linea> lineas) throws SQLException
    {
    	int rutaid = 0;
    	Statement st = (Statement) connection.createStatement(); 
	    for (Linea linea : lineas) {
	    	st.executeUpdate("INSERT INTO COMPANY " + "VALUES ('"+linea.id+"', '"+linea.nombre+"', '"+linea.rut+"')");
	    	for (Ruta ruta : linea.rutas) {
	    		System.out.println("Cargando Ruta "+ruta.nombre);
	    		st.executeUpdate("INSERT INTO ROUTE " + "VALUES ('"+rutaid+"', '"+linea.id+"', '"+ruta.nombre+"')");
	        	for (GeoPoint punto : ruta.points) {
	        		st.executeUpdate("INSERT INTO POINT " + "VALUES (DEFAULT, '"+rutaid+"', '"+punto.latitude+"', '"+punto.longitude+"')");
	        	}
	        	rutaid++;
        	}
        	
    	}
    }
     
 
    //Cierra objeto Resultset
    public static void cerrar(ResultSet rs)
    {   
        try
        {
            rs.close();
        } 
        catch(Exception ex)
        {}
    }
     
    //Cierra objeto Statement
    public static void cerrar(Statement st)
    {
        try
        {
            st.close();
        } 
        catch(Exception ex)
        {}
    }
     
    //Cierra objeto Connection
    public static void cerrar(Connection con)
    {   
        try
        {
            con.close();
        } 
        catch(Exception ex)
        {}
    }
}
