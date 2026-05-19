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
import co.edu.unbosque.backLuxtruck.security.SecurityConfig;

@Service
public class AdministrativoService {

    @Autowired
    private AdministrativoRepository administrativoRepo;

    @Autowired
    private EstadoTrabajadorRepository estadoTrabajadorRepo;

    @Autowired
    private ModelMapper modelMapper;
    
    private SecurityConfig sec;
    
    public AdministrativoService() {
    	sec = new SecurityConfig();
    }

    public int create(AdministrativoDTO dto) {
        Optional<Administrativo> found = administrativoRepo.findById(dto.getIdPersona());

        if (found.isEmpty()) {
            Administrativo entity = modelMapper.map(dto, Administrativo.class);
            entity.setIdPersona(null);
            if (dto.getFechaIngreso() != null) {
                entity.setFechaIngreso(new Date(dto.getFechaIngreso().getTime()));
            }
            entity.setContrasenia(sec.hashingToSHA256(dto.getContrasenia()));

            administrativoRepo.save(entity);
            return 0;
        }

        return 1;
    }

    public List<AdministrativoDTO> getAll() {
        List<AdministrativoDTO> dtoList = new ArrayList<>();

        for (Administrativo a : administrativoRepo.findAll()) {
            AdministrativoDTO dto = modelMapper.map(a, AdministrativoDTO.class);

            if (a.getFechaIngreso() != null) {
                dto.setFechaIngreso(new Date(a.getFechaIngreso().getTime()));
            }

            dtoList.add(dto);
        }

        return dtoList;
    }

    public int update(int idPersona, AdministrativoDTO dto) {
        Optional<Administrativo> found = administrativoRepo.findById(idPersona);

        if (found.isPresent()) {
            Administrativo administrativo = found.get();

            
            administrativo.setNumeroDocumento(dto.getNumeroDocumento());
            administrativo.setTipoDocumento(dto.getTipoDocumento());
            administrativo.setPrimerNombre(dto.getPrimerNombre());
            administrativo.setSegundoNombre(dto.getSegundoNombre());
            administrativo.setPrimerApellido(dto.getPrimerApellido());
            administrativo.setSegundoApellido(dto.getSegundoApellido());
            administrativo.setTelefono(dto.getTelefono());
            administrativo.setCorreo(dto.getCorreo());

            if (dto.getFechaIngreso() != null) {
                administrativo.setFechaIngreso(new Date(dto.getFechaIngreso().getTime()));
            }

            administrativo.setSalario(dto.getSalario());
            if (dto.getContrasenia() != null && !dto.getContrasenia().isBlank()) {
                administrativo.setContrasenia(sec.hashingToSHA256(dto.getContrasenia()));
            }

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