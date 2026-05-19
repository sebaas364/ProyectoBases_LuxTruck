package co.edu.unbosque.backLuxtruck.dto;

public class DashboardDTO {
	
    private long maquinasLibres;
    private long maquinasTotales;
    private long productosEnStock;
    private long ventasDelMes;
    private long stockBajo;
    
    public DashboardDTO() {
		// TODO Auto-generated constructor stub
	}

	public DashboardDTO(long maquinasLibres, long maquinasTotales, long productosEnStock,
			long ventasDelMes, long stockBajo) {
		super();
		this.maquinasLibres = maquinasLibres;
		this.maquinasTotales = maquinasTotales;
		this.productosEnStock = productosEnStock;
		this.ventasDelMes = ventasDelMes;
		this.stockBajo = stockBajo;
	}

	public long getMaquinasLibres() {
		return maquinasLibres;
	}

	public void setMaquinasLibres(long maquinasLibres) {
		this.maquinasLibres = maquinasLibres;
	}

	public long getMaquinasTotales() {
		return maquinasTotales;
	}

	public void setMaquinasTotales(long maquinasTotales) {
		this.maquinasTotales = maquinasTotales;
	}

	public long getProductosEnStock() {
		return productosEnStock;
	}

	public void setProductosEnStock(long productosEnStock) {
		this.productosEnStock = productosEnStock;
	}

	public long getVentasDelMes() {
		return ventasDelMes;
	}

	public void setVentasDelMes(long ventasDelMes) {
		this.ventasDelMes = ventasDelMes;
	}

	public long getStockBajo() {
		return stockBajo;
	}

	public void setStockBajo(long stockBajo) {
		this.stockBajo = stockBajo;
	}
    
    
	
}
