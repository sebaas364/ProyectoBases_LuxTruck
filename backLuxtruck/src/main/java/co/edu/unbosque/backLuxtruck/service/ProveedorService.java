package co.edu.unbosque.backLuxtruck.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.backLuxtruck.dto.ProveedorDTO;
import co.edu.unbosque.backLuxtruck.model.PedidoMaterial;
import co.edu.unbosque.backLuxtruck.model.Proveedor;
import co.edu.unbosque.backLuxtruck.repository.PedidoMaterialRepository;
import co.edu.unbosque.backLuxtruck.repository.ProveedorRepository;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepo;

    @Autowired
    private PedidoMaterialRepository pedidoMaterialRepo;

    @Autowired
    private ModelMapper modelMapper;


    public int create(ProveedorDTO dto) {
        Optional<Proveedor> found = proveedorRepo.findByNIT(dto.getNIT());
        if (found.isEmpty()) {
            Proveedor entity = modelMapper.map(dto, Proveedor.class);
            entity.setIdEmpresa(null);
            proveedorRepo.save(entity);
            return 0;
        }
        return 1;
    }


    public List<ProveedorDTO> getAll() {
        List<ProveedorDTO> dtoList = new ArrayList<>();
        for (Proveedor p : proveedorRepo.findAll()) {
            dtoList.add(modelMapper.map(p, ProveedorDTO.class));
        }
        return dtoList;
    }

    public int update(int idEmpresa, ProveedorDTO dto) {
        Optional<Proveedor> found = proveedorRepo.findById(idEmpresa);
        if (found.isPresent()) {
            Proveedor proveedor = found.get();
            // Campos de Empresa
            proveedor.setNIT(dto.getNIT());
            proveedor.setNombre(dto.getNombre());
            proveedor.setTelefono(dto.getTelefono());
            proveedor.setCorreo(dto.getCorreo());
            // Campos propios de Proveedor
            proveedor.setCalificacion(dto.getCalificacion());
            proveedor.setTipoProveedor(dto.getTipoProveedor());
            proveedorRepo.save(proveedor);
            return 0;
        }
        return 1;
    }

    public int delete(int idEmpresa) {
        Optional<Proveedor> found = proveedorRepo.findById(idEmpresa);
        if (found.isPresent()) {
            proveedorRepo.deleteById(idEmpresa);
            return 0;
        }
        return 1;
    }

    public int addPedidoToProveedor(int idEmpresa, int idPedido) {
        Optional<Proveedor> proveedorOpt = proveedorRepo.findById(idEmpresa);
        Optional<PedidoMaterial> pedidoOpt = pedidoMaterialRepo.findById(idPedido);
        if (proveedorOpt.isPresent() && pedidoOpt.isPresent()) {
            PedidoMaterial pedido = pedidoOpt.get();
            pedido.setProveedor(proveedorOpt.get());
            pedidoMaterialRepo.save(pedido);
            return 0;
        }
        return 1;
    }
}
