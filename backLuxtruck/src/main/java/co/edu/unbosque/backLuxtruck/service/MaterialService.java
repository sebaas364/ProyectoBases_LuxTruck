package co.edu.unbosque.backLuxtruck.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.backLuxtruck.dto.MaterialDTO;
import co.edu.unbosque.backLuxtruck.model.Material;
import co.edu.unbosque.backLuxtruck.repository.MaterialRepository;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepo;

    @Autowired
    private ModelMapper modelMapper;


    public int create(MaterialDTO dto) {
        Optional<Material> found = materialRepo.findById(dto.getIdMaterial());
        if (found.isEmpty()) {
            Material entity = modelMapper.map(dto, Material.class);
            materialRepo.save(entity);
            return 0;
        }
        return 1;
    }

    public List<MaterialDTO> getAll() {
        List<MaterialDTO> dtoList = new ArrayList<>();
        for (Material m : materialRepo.findAll()) {
            dtoList.add(modelMapper.map(m, MaterialDTO.class));
        }
        return dtoList;
    }

    public int update(int idMaterial, MaterialDTO dto) {
        Optional<Material> found = materialRepo.findById(idMaterial);
        if (found.isPresent()) {
            Material material = found.get();
            material.setNombre(dto.getNombre());
            material.setValorUnitario(dto.getValorUnitario());
            material.setCantidadDisponible(dto.getCantidadDisponible());
            material.setUnidadMedida(dto.getUnidadMedida());
            materialRepo.save(material);
            return 0;
        }
        return 1;
    }

    public int delete(int idMaterial) {
        Optional<Material> found = materialRepo.findById(idMaterial);
        if (found.isPresent()) {
            materialRepo.deleteById(idMaterial);
            return 0;
        }
        return 1;
    }
}
