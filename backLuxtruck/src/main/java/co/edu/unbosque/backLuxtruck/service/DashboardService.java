package co.edu.unbosque.backLuxtruck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.backLuxtruck.dto.DashboardDTO;
import co.edu.unbosque.backLuxtruck.model.Inventario;
import co.edu.unbosque.backLuxtruck.model.Maquina;
import co.edu.unbosque.backLuxtruck.repository.InventarioRepository;
import co.edu.unbosque.backLuxtruck.repository.MaquinaRepository;
import co.edu.unbosque.backLuxtruck.repository.VentaRepository;

@Service
public class DashboardService {

	@Autowired
	private MaquinaRepository maquinaRepo;
	@Autowired
	private InventarioRepository inventarioRepo;
	@Autowired
	private VentaRepository ventaRepo;
	@Autowired


    public DashboardDTO getResumen() {

        DashboardDTO dto = new DashboardDTO();

        long libres = maquinaRepo.countByEstadoMaquinaEstado("Operativa");
        long total = ((List<Maquina>) maquinaRepo.findAll()).size();

        Long stock = inventarioRepo.sumCantidadProducto();
        Long ventas = ventaRepo.sumVentasDelMes();

        dto.setProductosEnStock(stock != null ? stock : 0);
        dto.setVentasDelMes(ventas != null ? ventas : 0);
        dto.setMaquinasLibres(libres);
        dto.setMaquinasTotales(total);

        long stockBajoCount = 0;
        for (Inventario inv : inventarioRepo.findAll()) {
            if (inv.getCantidadProducto() < inv.getStockMinimo()) {
                stockBajoCount++;
            }
        }

        dto.setStockBajo(stockBajoCount);
        return dto;
    }
}
