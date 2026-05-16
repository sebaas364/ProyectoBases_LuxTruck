package co.edu.unbosque.backLuxtruck.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.backLuxtruck.dto.ClientePersonaDTO;
import co.edu.unbosque.backLuxtruck.model.ClientePersona;
import co.edu.unbosque.backLuxtruck.model.Vendedor;
import co.edu.unbosque.backLuxtruck.repository.ClientePersonaRepository;
import co.edu.unbosque.backLuxtruck.repository.VendedorRepository;

@Service
public class ClientePersonaService {

    @Autowired
    private ClientePersonaRepository clientePersonaRepo;

    @Autowired
    private VendedorRepository vendedorRepo;

    @Autowired
    private ModelMapper modelMapper;


    public int create(ClientePersonaDTO dto) {
        Optional<ClientePersona> found = clientePersonaRepo.findById(dto.getIdPersona());
        if (found.isEmpty()) {
            ClientePersona entity = modelMapper.map(dto, ClientePersona.class);
            clientePersonaRepo.save(entity);
            return 0;
        }
        return 1;
    }


    public List<ClientePersonaDTO> getAll() {
        List<ClientePersonaDTO> dtoList = new ArrayList<>();
        for (ClientePersona c : clientePersonaRepo.findAll()) {
            dtoList.add(modelMapper.map(c, ClientePersonaDTO.class));
        }
        return dtoList;
    }


    public int update(int idPersona, ClientePersonaDTO dto) {
        Optional<ClientePersona> found = clientePersonaRepo.findById(idPersona);
        if (found.isPresent()) {
            ClientePersona cliente = found.get();
            // Campos heredados de Persona
            cliente.setNumeroDocumento(dto.getNumeroDocumento());
            cliente.setTipoDocumento(dto.getTipoDocumento());
            cliente.setPrimerNombre(dto.getPrimerNombre());
            cliente.setSegundoNombre(dto.getSegundoNombre());
            cliente.setPrimerApellido(dto.getPrimerApellido());
            cliente.setSegundoApellido(dto.getSegundoApellido());
            cliente.setTelefono(dto.getTelefono());
            cliente.setCorreo(dto.getCorreo());
            clientePersonaRepo.save(cliente);
            return 0;
        }
        return 1;
    }

    public int delete(int idPersona) {
        Optional<ClientePersona> found = clientePersonaRepo.findById(idPersona);
        if (found.isPresent()) {
            clientePersonaRepo.deleteById(idPersona);
            return 0;
        }
        return 1;
    }

    public int addVendedorToClientePersona(int idPersona, int idVendedor) {
        Optional<ClientePersona> clienteOpt = clientePersonaRepo.findById(idPersona);
        Optional<Vendedor> vendedorOpt = vendedorRepo.findById(idVendedor);
        if (clienteOpt.isPresent() && vendedorOpt.isPresent()) {
            ClientePersona cliente = clienteOpt.get();
            cliente.setVendedor(vendedorOpt.get());
            clientePersonaRepo.save(cliente);
            return 0;
        }
        return 1;
    }
}
