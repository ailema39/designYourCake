package domain;

public class OrderCupcake {
	
	private long id;
	private String tamano;
	private String cantidad;
	private String sabor;
	private String cubierta;
	private String[] color;
	private String decoracion;
	private String precio;
	private String cantDocenas;
	private String textoCalcomania;
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}

	public void setTamano(String tamano){
		this.tamano = tamano;
	}
	
	public String getTamano(){
		return tamano;
	}
	
	public void setCantidad(String cantidad){
		this.cantidad = cantidad;
	}
	
	public String getCantidad(){
		return cantidad;
	}
	
	public void setSabor(String sabor){
		this.sabor = sabor;
	}
	
	public String getSabor(){
		return sabor;
	}
	
	public void setCubiertas(String cubierta){
		this.cubierta = cubierta;
	}
	
	public String getCubiertas(){
		return cubierta;
	}
	
	
	public void setColor(String[] color){
		this.color = color;
	}
	
	public String[] getColor(){
		return color;
	}
	
	public void setDecoracion(String decoracion){
		this.decoracion = decoracion;
	}
	
	public String getDecoracion(){
		return decoracion;
	}
	
	
	public void setPrecio(String precio){
		this.precio = precio;
	}
	
	public String getPrecio(){
		return precio;
	}
	
	public void setCalcomania(String textoCalcomania){
		this.textoCalcomania = textoCalcomania;
	}
	
	public String getCalcomania(){
		return textoCalcomania;
	}
	
	public void setCantidadDocenas(String cantDocenas){
		this.cantDocenas = cantDocenas;
	}
	
	public String getCantidadDocenas(){
		return cantDocenas;
	}
	
}
