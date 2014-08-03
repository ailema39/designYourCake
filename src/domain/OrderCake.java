package domain;

public class OrderCake {
	
	private long id;
	private String forma;
	private String tamano;
	private String sabor;
	private String capas;
	private String[] relleno;
	private String cubierta;
	private String precio;
	private String nombreImagen;
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}

	public void setForma(String forma){
		this.forma = forma;
	}
	
	public String getForma(){
		return forma;
	}
	
	
	public void setTamano(String tamano){
		this.tamano = tamano;
	}
	
	public String getTamano(){
		return tamano;
	}
	
	public void setSabor(String sabor){
		this.sabor = sabor;
	}
	
	public String getSabor(){
		return sabor;
	}
	
	
	public void setCapas(String capas){
		this.capas = capas;
	}
	
	public String getCapas(){
		return capas;
	}
	
	public void setRelleno(String[] relleno){
		this.relleno = relleno;
	}
	
	public String[] getRelleno(){
		return relleno;
	}
	
	public void setCubiertas(String cubierta){
		this.cubierta = cubierta;
	}
	
	public String getCubiertas(){
		return cubierta;
	}
	
	public void setPrecio(String precio){
		this.precio = precio;
	}
	
	public String getPrecio(){
		return precio;
	}
	
	
	public void setNombreImagen(String nombreImg){
		this.nombreImagen = nombreImg;
	}
	
	public String getNombreImagen(){
		return nombreImagen;
	}
	
}
