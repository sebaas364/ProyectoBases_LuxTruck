package co.edu.unbosque.backLuxtruck.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.backLuxtruck.dto.ProductoDTO;
import co.edu.unbosque.backLuxtruck.model.Inventario;
import co.edu.unbosque.backLuxtruck.model.Producto;
import co.edu.unbosque.backLuxtruck.repository.InventarioRepository;
import co.edu.unbosque.backLuxtruck.repository.ProductoRepository;

@Service
public class ProductoService {

	@Autowired
	private ProductoRepository productoRepo;

	@Autowired
	private InventarioRepository inventarioRepo;

	@Autowired
	private ModelMapper modelMapper;

	public int create(ProductoDTO dto) {
		if(dto != null) {
			Producto entity = modelMapper.map(dto, Producto.class);
			entity.setIdProducto(null);
			productoRepo.save(entity);
			return 0;
		}
		return 1;
	}

	public List<ProductoDTO> getAll() {
		List<ProductoDTO> dtoList = new ArrayList<>();
		for (Producto p : productoRepo.findAll()) {
			dtoList.add(modelMapper.map(p, ProductoDTO.class));
		}
		return dtoList;
	}

	public int update(int idProducto, ProductoDTO dto) {
		Optional<Producto> found = productoRepo.findById(idProducto);
		if (found.isPresent()) {
			Producto producto = found.get();
			producto.setNombre(dto.getNombre());
			producto.setPrecioUnitario(dto.getPrecioUnitario());
			producto.setTipo(dto.getTipo());
			productoRepo.save(producto);
			return 0;
		}
		return 1;
	}

	public int delete(int idProducto) {
		Optional<Producto> found = productoRepo.findById(idProducto);
		if (found.isPresent()) {
			productoRepo.deleteById(idProducto);
			return 0;
		}
		return 1;
	}

	public int addInventarioToProducto(int idProducto, int stockMinimo, int cantidadProducto) {
		Optional<Producto> productoOpt = productoRepo.findById(idProducto);
		if (productoOpt.isPresent()) {
			// Solo si no tiene inventario aún
			if (!inventarioRepo.existsById(idProducto)) {
				Inventario inventario = new Inventario(productoOpt.get(), stockMinimo, cantidadProducto);
				inventarioRepo.save(inventario);
				return 0;
			}
			return 2; // Ya tiene inventario
		}
		return 1;
	}
}
