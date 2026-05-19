package co.edu.unbosque.backLuxtruck.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.backLuxtruck.dto.EspecialidadDTO;
import co.edu.unbosque.backLuxtruck.model.Especialidad;
import co.edu.unbosque.backLuxtruck.repository.EspecialidadRepository;

@Service
public class EspecialidadService {

    @Autowired
    private EspecialidadRepository especialidadRepo;

    @Autowired
    private ModelMapper modelMapper;

    public int create(EspecialidadDTO dto) {
    	if (dto != null) {
    		Especialidad entity = modelMapper.map(dto, Especialidad.class);
    		entity.setIdEspecialidad(null);
    		return 0;
    	}
        return 1;
    }

    public List<EspecialidadDTO> getAll() {
        List<EspecialidadDTO> dtoList = new ArrayList<>();
        for (Especialidad e : especialidadRepo.findAll()) {
            dtoList.add(modelMapper.map(e, EspecialidadDTO.class));
        }
        return dtoList;
    }

    public int update(int idEspecialidad, EspecialidadDTO dto) {
        Optional<Especialidad> found = especialidadRepo.findById(idEspecialidad);
        if (found.isPresent()) {
            Especialidad especialidad = found.get();
            especialidad.setNombreEspecialidad(dto.getNombreEspecialidad());
            especialidadRepo.save(especialidad);
            return 0;
        }
        return 1;
    }

    public int delete(int idEspecialidad) {
        Optional<Especialidad> found = especialidadRepo.findById(idEspecialidad);
        if (found.isPresent()) {
            especialidadRepo.deleteById(idEspecialidad);
            return 0;
        }
        return 1;
    }
}
