package Util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;



public class FilesName  {
	
	public static String INICIO = "inicio";
	public static String DULCES_TORTAS = "dulces";
	
	public static String[] getFilesNamesIndex(HttpServletRequest request, String section) {
		
		String[] filesName = null;
		String path = "";
		
		if(section.equalsIgnoreCase(INICIO)){
			path = "/home/spookie/jvm/apache-tomcat-7.0.27/domains/designyourcakes.com/ROOT/images/galeria_inicio";
		} else if(section.equalsIgnoreCase(DULCES_TORTAS)){
			path = "/home/spookie/jvm/apache-tomcat-7.0.27/domains/designyourcakes.com/ROOT/images/galeria_dulces";
		}
		//String path = "C:\\Program Files (x86)\\Apache Software Foundation\\Tomcat 7.0\\webapps\\armaTuTorta\\images\\galeria_inicio";
		//String path = "C:\\Program Files\\apache-tomcat-7.0.12\\webapps\\armaTuTorta\\images\\galeria_inicio";
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		if (listOfFiles.length > 0){
			filesName = new String[listOfFiles.length];
			for (int i = 0; i < listOfFiles.length; i++) {
			      if (listOfFiles[i].isFile()) {
			    	  filesName[i] = listOfFiles[i].getName();
		      } 
			}
		}
	    return filesName;
	}
	
	/**
	 * Función que elimina acentos y caracteres especiales de
	 * una cadena de texto.
	 * @param input
	 * @return cadena de texto limpia de acentos y caracteres especiales.
	 */
	public static String remove(String input) {
	    // Cadena de caracteres original a sustituir.
	    String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
	    // Cadena de caracteres ASCII que reemplazarán los originales.
	    String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
	    String output = input;
	    for (int i=0; i<original.length(); i++) {
	        // Reemplazamos los caracteres especiales.
	        output = output.replace(original.charAt(i), ascii.charAt(i));
	    }//for i

	    return output;
	}
}
