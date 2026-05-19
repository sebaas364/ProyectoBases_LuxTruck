package co.edu.unbosque.backLuxtruck.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.backLuxtruck.dto.ClienteEmpresaDTO;
import co.edu.unbosque.backLuxtruck.model.ClienteEmpresa;
import co.edu.unbosque.backLuxtruck.model.Vendedor;
import co.edu.unbosque.backLuxtruck.repository.ClienteEmpresaRepository;
import co.edu.unbosque.backLuxtruck.repository.VendedorRepository;

@Service
public class ClienteEmpresaService {

    @Autowired
    private ClienteEmpresaRepository clienteEmpresaRepo;

    @Autowired
    private VendedorRepository vendedorRepo;

    @Autowired
    private ModelMapper modelMapper;

    public int create(ClienteEmpresaDTO dto) {
        Optional<ClienteEmpresa> found = clienteEmpresaRepo.findByNIT(dto.getNIT());
        if (found.isEmpty()) {
            ClienteEmpresa entity = modelMapper.map(dto, ClienteEmpresa.class);
            entity.setIdEmpresa(null);
            clienteEmpresaRepo.save(entity);
            return 0;
        }
        return 1;
    }

    public List<ClienteEmpresaDTO> getAll() {
        List<ClienteEmpresaDTO> dtoList = new ArrayList<>();
        for (ClienteEmpresa c : clienteEmpresaRepo.findAll()) {
            dtoList.add(modelMapper.map(c, ClienteEmpresaDTO.class));
        }
        return dtoList;
    }

    public int update(int idEmpresa, ClienteEmpresaDTO dto) {
        Optional<ClienteEmpresa> found = clienteEmpresaRepo.findById(idEmpresa);
        if (found.isPresent()) {
            ClienteEmpresa cliente = found.get();
            // Campos heredados de Empresa
            cliente.setNIT(dto.getNIT());
            cliente.setNombre(dto.getNombre());
            cliente.setTelefono(dto.getTelefono());
            cliente.setCorreo(dto.getCorreo());
            clienteEmpresaRepo.save(cliente);
            return 0;
        }
        return 1;
    }

    public int delete(int idEmpresa) {
        Optional<ClienteEmpresa> found = clienteEmpresaRepo.findById(idEmpresa);
        if (found.isPresent()) {
            clienteEmpresaRepo.deleteById(idEmpresa);
            return 0;
        }
        return 1;
    }

    public int addVendedorToClienteEmpresa(int idEmpresa, int idVendedor) {
        Optional<ClienteEmpresa> clienteOpt = clienteEmpresaRepo.findById(idEmpresa);
        Optional<Vendedor> vendedorOpt = vendedorRepo.findById(idVendedor);
        if (clienteOpt.isPresent() && vendedorOpt.isPresent()) {
            ClienteEmpresa cliente = clienteOpt.get();
            cliente.setVendedor(vendedorOpt.get());
            clienteEmpresaRepo.save(cliente);
            return 0;
        }
        return 1;
    }
}
