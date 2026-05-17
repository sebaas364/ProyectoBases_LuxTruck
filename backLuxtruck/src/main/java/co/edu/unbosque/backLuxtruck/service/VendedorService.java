package co.edu.unbosque.backLuxtruck.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.backLuxtruck.dto.VendedorDTO;
import co.edu.unbosque.backLuxtruck.model.EstadoTrabajador;
import co.edu.unbosque.backLuxtruck.model.Vendedor;
import co.edu.unbosque.backLuxtruck.model.Venta;
import co.edu.unbosque.backLuxtruck.model.ZonaVenta;
import co.edu.unbosque.backLuxtruck.repository.EstadoTrabajadorRepository;
import co.edu.unbosque.backLuxtruck.repository.VendedorRepository;
import co.edu.unbosque.backLuxtruck.repository.VentaRepository;
import co.edu.unbosque.backLuxtruck.repository.ZonaVentaRepository;
import co.edu.unbosque.backLuxtruck.security.SecurityConfig;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepo;

    @Autowired
    private EstadoTrabajadorRepository estadoTrabajadorRepo;

    @Autowired
    private VentaRepository ventaRepo;

    @Autowired
    private ZonaVentaRepository zonaVentaRepo;
    
    @Autowired
    private SecurityConfig sec;

    @Autowired
    private ModelMapper modelMapper;

    public int create(VendedorDTO dto) {
        Optional<Vendedor> found = vendedorRepo.findById(dto.getIdPersona());

        if (found.isEmpty()) {
            Vendedor entity = modelMapper.map(dto, Vendedor.class);

            if (dto.getFechaIngreso() != null) {
                entity.setFechaIngreso(new Date(dto.getFechaIngreso().getTime()));
            }
            entity.setContrasenia(sec.hashingToSHA256(dto.getContrasenia()));
            vendedorRepo.save(entity);
            return 0;
        }

        return 1;
    }

    public List<VendedorDTO> getAll() {
        List<VendedorDTO> dtoList = new ArrayList<>();

        for (Vendedor v : vendedorRepo.findAll()) {
            VendedorDTO dto = modelMapper.map(v, VendedorDTO.class);

            if (v.getFechaIngreso() != null) {
                dto.setFechaIngreso(new java.util.Date(v.getFechaIngreso().getTime()));
            }

            dtoList.add(dto);
        }

        return dtoList;
    }

    public int update(int idPersona, VendedorDTO dto) {
        Optional<Vendedor> found = vendedorRepo.findById(idPersona);

        if (found.isPresent()) {
            Vendedor vendedor = found.get();

            vendedor.setNumeroDocumento(dto.getNumeroDocumento());
            vendedor.setTipoDocumento(dto.getTipoDocumento());
            vendedor.setPrimerNombre(dto.getPrimerNombre());
            vendedor.setSegundoNombre(dto.getSegundoNombre());
            vendedor.setPrimerApellido(dto.getPrimerApellido());
            vendedor.setSegundoApellido(dto.getSegundoApellido());
            vendedor.setTelefono(dto.getTelefono());
            vendedor.setCorreo(dto.getCorreo());

            if (dto.getFechaIngreso() != null) {
                vendedor.setFechaIngreso(new java.sql.Date(dto.getFechaIngreso().getTime()));
            }

            vendedor.setSalario(dto.getSalario());
            
            if (dto.getContrasenia() != null && !dto.getContrasenia().isBlank()) {
                vendedor.setContrasenia(sec.hashingToSHA256(dto.getContrasenia()));
            }
            
            vendedor.setComision(dto.getComision());

            vendedorRepo.save(vendedor);
            return 0;
        }

        return 1;
    }

    public int delete(int idPersona) {
        Optional<Vendedor> found = vendedorRepo.findById(idPersona);

        if (found.isPresent()) {
            vendedorRepo.deleteById(idPersona);
            return 0;
        }

        return 1;
    }

    public int addEstadoToVendedor(int idPersona, int idEstado) {
        Optional<Vendedor> vendedorOpt = vendedorRepo.findById(idPersona);
        Optional<EstadoTrabajador> estadoOpt = estadoTrabajadorRepo.findById(idEstado);

        if (vendedorOpt.isPresent() && estadoOpt.isPresent()) {
            Vendedor vendedor = vendedorOpt.get();
            vendedor.setEstadoTrabajador(estadoOpt.get());
            vendedorRepo.save(vendedor);
            return 0;
        }

        return 1;
    }

    public int addVentaToVendedor(int idVendedor, int idVenta) {
        Optional<Vendedor> vendedorOpt = vendedorRepo.findById(idVendedor);
        Optional<Venta> ventaOpt = ventaRepo.findById(idVenta);

        if (vendedorOpt.isPresent() && ventaOpt.isPresent()) {
            Venta venta = ventaOpt.get();
            venta.setVendedor(vendedorOpt.get());
            ventaRepo.save(venta);
            return 0;
        }

        return 1;
    }

    public int addZonaToVendedor(int idVendedor, int idZona) {
        Optional<Vendedor> vendedorOpt = vendedorRepo.findById(idVendedor);
        Optional<ZonaVenta> zonaOpt = zonaVentaRepo.findById(idZona);

        if (vendedorOpt.isPresent() && zonaOpt.isPresent()) {
            ZonaVenta zona = zonaOpt.get();
            zona.setVendedor(vendedorOpt.get());
            zonaVentaRepo.save(zona);
            return 0;
        }

        return 1;
    }
}