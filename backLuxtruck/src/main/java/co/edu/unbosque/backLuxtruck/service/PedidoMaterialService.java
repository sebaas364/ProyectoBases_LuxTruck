package co.edu.unbosque.backLuxtruck.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.backLuxtruck.dto.PedidoMaterialDTO;
import co.edu.unbosque.backLuxtruck.model.Contener;
import co.edu.unbosque.backLuxtruck.model.Material;
import co.edu.unbosque.backLuxtruck.model.PedidoMaterial;
import co.edu.unbosque.backLuxtruck.model.Proveedor;
import co.edu.unbosque.backLuxtruck.repository.ContenerRepository;
import co.edu.unbosque.backLuxtruck.repository.MaterialRepository;
import co.edu.unbosque.backLuxtruck.repository.PedidoMaterialRepository;
import co.edu.unbosque.backLuxtruck.repository.ProveedorRepository;

@Service
public class PedidoMaterialService {

    @Autowired
    private PedidoMaterialRepository pedidoMaterialRepo;

    @Autowired
    private ProveedorRepository proveedorRepo;

    @Autowired
    private MaterialRepository materialRepo;

    @Autowired
    private ContenerRepository contenerRepo;

    @Autowired
    private ModelMapper modelMapper;

    // ----------------------------------------------------
    // CREATE
    // ----------------------------------------------------
    public int create(PedidoMaterialDTO dto) {
        Optional<PedidoMaterial> found = pedidoMaterialRepo.findById(dto.getIdPedido());
        if (found.isEmpty()) {
            PedidoMaterial entity = modelMapper.map(dto, PedidoMaterial.class);
            pedidoMaterialRepo.save(entity);
            return 0;
        }
        return 1;
    }

    public List<PedidoMaterialDTO> getAll() {
        List<PedidoMaterialDTO> dtoList = new ArrayList<>();
        for (PedidoMaterial p : pedidoMaterialRepo.findAll()) {
            dtoList.add(modelMapper.map(p, PedidoMaterialDTO.class));
        }
        return dtoList;
    }


    public int update(int idPedido, PedidoMaterialDTO dto) {
        Optional<PedidoMaterial> found = pedidoMaterialRepo.findById(idPedido);
        if (found.isPresent()) {
            PedidoMaterial pedido = found.get();
            pedido.setCantidadMaterial(dto.getCantidadMaterial());
            pedido.setFechaPedido(new Date(dto.getFechaPedido().getTime()) );
            pedido.setFechaEntrega(new Date(dto.getFechaEntrega().getTime()));
            pedidoMaterialRepo.save(pedido);
            return 0;
        }
        return 1;
    }

    public int delete(int idPedido) {
        Optional<PedidoMaterial> found = pedidoMaterialRepo.findById(idPedido);
        if (found.isPresent()) {
            pedidoMaterialRepo.deleteById(idPedido);
            return 0;
        }
        return 1;
    }

    public int addProveedorToPedido(int idPedido, int idProveedor) {
        Optional<PedidoMaterial> pedidoOpt = pedidoMaterialRepo.findById(idPedido);
        Optional<Proveedor> proveedorOpt = proveedorRepo.findById(idProveedor);
        if (pedidoOpt.isPresent() && proveedorOpt.isPresent()) {
            PedidoMaterial pedido = pedidoOpt.get();
            pedido.setProveedor(proveedorOpt.get());
            pedidoMaterialRepo.save(pedido);
            return 0;
        }
        return 1;
    }

    public int addMaterialToPedido(int idPedido, int idMaterial) {
        Optional<PedidoMaterial> pedidoOpt = pedidoMaterialRepo.findById(idPedido);
        Optional<Material> materialOpt = materialRepo.findById(idMaterial);
        if (pedidoOpt.isPresent() && materialOpt.isPresent()) {
            Contener relacion = new Contener(pedidoOpt.get(), materialOpt.get());
            contenerRepo.save(relacion);
            return 0;
        }
        return 1;
    }
}
