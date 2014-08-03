package Util;

import java.io.File;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;



public class FilesName  {
	
	private static HashMap<String, String[]> fileNamesCupcakes = new HashMap<String, String[]>();
	private static HashMap<String, String[]> fileNamesCakes = new HashMap<String, String[]>();
	private static HashMap<String, String[]> fileNamesSpecialsCakes = new HashMap<String, String[]>();
	private static String CUPCAKES = "cupcakes";
	private static String CAKES = "cakes";
	private static String SPECIALS = "specialsCakes";
	
	public static void cleanCupcakesCache(){
		fileNamesCupcakes.clear();
		System.out.println("Cupcakes's cache was clear");
	}
	
	public static void cleanCakesCache(){
		fileNamesCakes.clear();
		System.out.println("Cakes's cache was clear");
	}
	
	public static void cleanSpecialsCakesCache(){
		fileNamesSpecialsCakes.clear();
		System.out.println("Specials Cakes's cache was clear");
	}

	public static String[] getFilesNamesIndex(HttpServletRequest request) {
		
		String[] filesName;
		if (fileNamesCupcakes.get(CUPCAKES) == null){
			String path = request.getSession().getServletContext().getRealPath("/");
			path = path + "images\\galeria_inicio";
			
			File folder = new File(path);
			File[] listOfFiles = folder.listFiles();
			filesName = new String[listOfFiles.length]; ;
			for (int i = 0; i < listOfFiles.length; i++) {
			      if (listOfFiles[i].isFile()) {
			    	  filesName[i] = listOfFiles[i].getName();
			      } 
		    }
			fileNamesCupcakes.put(CUPCAKES, filesName);
		}else{
			filesName = (String[]) fileNamesCupcakes.get(CUPCAKES);
		}
	    return filesName;

	}
	
	public static String[] getFilesNamesCakes(HttpServletRequest request) {
		
		String[] filesName;
		if (fileNamesCakes.get(CAKES) == null){
			String path = request.getSession().getServletContext().getRealPath("/");
			path = path + "images\\galeria_tortas";
			
			File folder = new File(path);
			File[] listOfFiles = folder.listFiles();
			filesName = new String[listOfFiles.length]; ;
			for (int i = 0; i < listOfFiles.length; i++) {
			      if (listOfFiles[i].isFile()) {
			    	  filesName[i] = listOfFiles[i].getName();
			      } 
		    }
			fileNamesCakes.put(CAKES, filesName);
		}else{
			filesName = (String[]) fileNamesCakes.get(CAKES);
		}
	    return filesName;

	}
		
	
	public static String[] getFilesNamesSpecialsCakes(HttpServletRequest request) {
		
		String[] filesName;
		if (fileNamesSpecialsCakes.get(SPECIALS) == null){
			String path = request.getSession().getServletContext().getRealPath("/");
			path = path + "images\\galeria_ocasionesEsp";
			
			File folder = new File(path);
			File[] listOfFiles = folder.listFiles();
			filesName = new String[listOfFiles.length]; ;
			for (int i = 0; i < listOfFiles.length; i++) {
			      if (listOfFiles[i].isFile()) {
			    	  filesName[i] = listOfFiles[i].getName();
			      } 
		    }
			fileNamesSpecialsCakes.put(SPECIALS, filesName);
		}else{
			filesName = (String[]) fileNamesSpecialsCakes.get(SPECIALS);
		}
	    return filesName;

	}
		
	
}
