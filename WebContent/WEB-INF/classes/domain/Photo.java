package domain;

public class Photo {

	private long id;
	private long albumId;
	private String name;
	private String image;
	private int isActive;
	private int isNew;
	
	/**
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
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

	public void setAlbumId(long albumId) {
		this.albumId = albumId;
	}

	public long getAlbumId() {
		return albumId;
	}	
}
