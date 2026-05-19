package co.edu.unbosque.backLuxtruck.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.backLuxtruck.dto.VentaDTO;
import co.edu.unbosque.backLuxtruck.model.Producto;
import co.edu.unbosque.backLuxtruck.model.ProductoVenta;
import co.edu.unbosque.backLuxtruck.model.Vendedor;
import co.edu.unbosque.backLuxtruck.model.Venta;
import co.edu.unbosque.backLuxtruck.repository.ProductoRepository;
import co.edu.unbosque.backLuxtruck.repository.ProductoVentaRepository;
import co.edu.unbosque.backLuxtruck.repository.VendedorRepository;
import co.edu.unbosque.backLuxtruck.repository.VentaRepository;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepo;

    @Autowired
    private VendedorRepository vendedorRepo;

    @Autowired
    private ProductoRepository productoRepo;

    @Autowired
    private ProductoVentaRepository productoVentaRepo;

    @Autowired
    private ModelMapper modelMapper;

    public int create(VentaDTO dto) {
        if (dto != null) {
            Venta entity = modelMapper.map(dto, Venta.class);
            entity.setIdVenta(null);

            if (dto.getFecha() != null) {
                entity.setFecha(new Date(dto.getFecha().getTime()));
            }
            ventaRepo.save(entity);
            return 0;
        }
        return 1;
    }

    public List<VentaDTO> getAll() {
        List<VentaDTO> dtoList = new ArrayList<>();

        for (Venta v : ventaRepo.findAll()) {
            VentaDTO dto = modelMapper.map(v, VentaDTO.class);

            if (v.getFecha() != null) {
                dto.setFecha(new Date(v.getFecha().getTime()));
            }

            dtoList.add(dto);
        }

        return dtoList;
    }

    public int update(int idVenta, VentaDTO dto) {
        Optional<Venta> found = ventaRepo.findById(idVenta);

        if (found.isPresent()) {
            Venta venta = found.get();

            if (dto.getFecha() != null) {
                venta.setFecha(new Date(dto.getFecha().getTime()));
            }

            venta.setMetodoPago(dto.getMetodoPago());

            ventaRepo.save(venta);
            return 0;
        }

        return 1;
    }

    public int delete(int idVenta) {
        Optional<Venta> found = ventaRepo.findById(idVenta);

        if (found.isPresent()) {
            ventaRepo.deleteById(idVenta);
            return 0;
        }

        return 1;
    }

    public int addVendedorToVenta(int idVenta, int idVendedor) {
        Optional<Venta> ventaOpt = ventaRepo.findById(idVenta);
        Optional<Vendedor> vendedorOpt = vendedorRepo.findById(idVendedor);

        if (ventaOpt.isPresent() && vendedorOpt.isPresent()) {
            Venta venta = ventaOpt.get();
            venta.setVendedor(vendedorOpt.get());
            ventaRepo.save(venta);
            return 0;
        }

        return 1;
    }

    public int addProductoToVenta(int idVenta, int idProducto, int cantidad) {
        Optional<Venta> ventaOpt = ventaRepo.findById(idVenta);
        Optional<Producto> productoOpt = productoRepo.findById(idProducto);

        if (ventaOpt.isPresent() && productoOpt.isPresent()) {
            ProductoVenta relacion = new ProductoVenta(ventaOpt.get(), productoOpt.get(), cantidad);
            productoVentaRepo.save(relacion);
            return 0;
        }

        return 1;
    }
}