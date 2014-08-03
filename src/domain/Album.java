package domain;

import java.util.Formatter;

public class Album {
	
	private long id;
	private String name;
	private String image;
	private int isActive;
	private int isNew;
	private String directory;
	
	/**
	 * 
	 * @param id
	 */
	public void setId(long id) {        
		this.id = id;
		this.setDirectory(id);
	}
	
	/**
	 * 
	 * @return
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param isActive
	 */
	public void setActive(int isActive) {
		this.isActive = isActive;
	}

	/**
	 * 
	 * @return
	 */
	public int isActive() {
		return isActive;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}

	public void setNew(int isNew) {
		this.isNew = isNew;
	}

	public int isNew() {
		return isNew;
	}	

	public String getDirectory(){
         
         return directory;
	}
	
	public static String getDirectory(long id){
		Formatter fmt = new Formatter();
        fmt.format("%08d",id);
        
        return fmt.toString();
	}

	public void setDirectory(long id) {
		this.directory = getDirectory(id);
	}
}
