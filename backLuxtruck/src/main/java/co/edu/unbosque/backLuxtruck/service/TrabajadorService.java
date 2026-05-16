package co.edu.unbosque.backLuxtruck.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.backLuxtruck.dto.TrabajadorDTO;
import co.edu.unbosque.backLuxtruck.model.Trabajador;
import co.edu.unbosque.backLuxtruck.repository.TrabajadorRepository;

@Service
public class TrabajadorService {

    @Autowired
    private TrabajadorRepository trabajadorRepo;

    @Autowired
    private ModelMapper modelMapper;

    // ----------------------------------------------------
    // CREATE
    // ----------------------------------------------------
    public int create(TrabajadorDTO dto) {
        Optional<Trabajador> found = trabajadorRepo.findById(dto.getIdPersona());
        if (found.isEmpty()) {
            Trabajador entity = modelMapper.map(dto, Trabajador.class);
            trabajadorRepo.save(entity);
            return 0;
        }
        return 1;
    }

    public ArrayList<TrabajadorDTO> getAll() {
        ArrayList<TrabajadorDTO> dtoList = new ArrayList<>();
        for (Trabajador t : trabajadorRepo.findAll()) {
            dtoList.add(modelMapper.map(t, TrabajadorDTO.class));
        }
        return dtoList;
    }


    public int update(int idPersona, TrabajadorDTO dto) {
        Optional<Trabajador> found = trabajadorRepo.findById(idPersona);
        if (found.isPresent()) {
            Trabajador trabajador = found.get();
            // Campos de Persona
            trabajador.setNumeroDocumento(dto.getNumeroDocumento());
            trabajador.setTipoDocumento(dto.getTipoDocumento());
            trabajador.setPrimerNombre(dto.getPrimerNombre());
            trabajador.setSegundoNombre(dto.getSegundoNombre());
            trabajador.setPrimerApellido(dto.getPrimerApellido());
            trabajador.setSegundoApellido(dto.getSegundoApellido());
            trabajador.setTelefono(dto.getTelefono());
            trabajador.setCorreo(dto.getCorreo());
            // Campos propios de Trabajador
            trabajador.setFechaIngreso((Date) dto.getFechaIngreso());	
            trabajador.setSalario(dto.getSalario());
            trabajador.setContrasenia(dto.getContrasenia());
            trabajadorRepo.save(trabajador);
            return 0;
        }
        return 1;
    }

    public int delete(int idPersona) {
        Optional<Trabajador> found = trabajadorRepo.findById(idPersona);
        if (found.isPresent()) {
            trabajadorRepo.delete(found.get());
            return 0;
        }
        return 1;
    }
}