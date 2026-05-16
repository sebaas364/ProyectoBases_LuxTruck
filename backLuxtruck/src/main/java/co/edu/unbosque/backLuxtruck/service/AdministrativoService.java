package co.edu.unbosque.backLuxtruck.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.backLuxtruck.dto.AdministrativoDTO;
import co.edu.unbosque.backLuxtruck.model.Administrativo;
import co.edu.unbosque.backLuxtruck.model.EstadoTrabajador;
import co.edu.unbosque.backLuxtruck.repository.AdministrativoRepository;
import co.edu.unbosque.backLuxtruck.repository.EstadoTrabajadorRepository;

@Service
public class AdministrativoService {

    @Autowired
    private AdministrativoRepository administrativoRepo;

    @Autowired
    private EstadoTrabajadorRepository estadoTrabajadorRepo;

    @Autowired
    private ModelMapper modelMapper;

    public int create(AdministrativoDTO dto) {
        Optional<Administrativo> found = administrativoRepo.findById(dto.getIdPersona());
        if (found.isEmpty()) {
            Administrativo entity = modelMapper.map(dto, Administrativo.class);
            administrativoRepo.save(entity);
            return 0;
        }
        return 1;
    }
    
    public List<AdministrativoDTO> getAll() {
        List<AdministrativoDTO> dtoList = new ArrayList<>();
        for (Administrativo a : administrativoRepo.findAll()) {
            dtoList.add(modelMapper.map(a, AdministrativoDTO.class));
        }
        return dtoList;
    }

    public int update(int idPersona, AdministrativoDTO dto) {
        Optional<Administrativo> found = administrativoRepo.findById(idPersona);
        if (found.isPresent()) {
            Administrativo administrativo = found.get();
            // Campos de Persona
            administrativo.setNumeroDocumento(dto.getNumeroDocumento());
            administrativo.setTipoDocumento(dto.getTipoDocumento());
            administrativo.setPrimerNombre(dto.getPrimerNombre());
            administrativo.setSegundoNombre(dto.getSegundoNombre());
            administrativo.setPrimerApellido(dto.getPrimerApellido());
            administrativo.setSegundoApellido(dto.getSegundoApellido());
            administrativo.setTelefono(dto.getTelefono());
            administrativo.setCorreo(dto.getCorreo());
            // Campos de Trabajador
            administrativo.setFechaIngreso((Date) dto.getFechaIngreso());
            administrativo.setSalario(dto.getSalario());
            administrativo.setContrasenia(dto.getContrasenia());
            administrativoRepo.save(administrativo);
            return 0;
        }
        return 1;
    }

    public int delete(int idPersona) {
        Optional<Administrativo> found = administrativoRepo.findById(idPersona);
        if (found.isPresent()) {
            administrativoRepo.deleteById(idPersona);
            return 0;
        }
        return 1;
    }

    public int addEstadoToAdministrativo(int idPersona, int idEstado) {
        Optional<Administrativo> adminOpt = administrativoRepo.findById(idPersona);
        Optional<EstadoTrabajador> estadoOpt = estadoTrabajadorRepo.findById(idEstado);
        if (adminOpt.isPresent() && estadoOpt.isPresent()) {
            Administrativo administrativo = adminOpt.get();
            administrativo.setEstadoTrabajador(estadoOpt.get());
            administrativoRepo.save(administrativo);
            return 0;
        }
        return 1;
    }
}
