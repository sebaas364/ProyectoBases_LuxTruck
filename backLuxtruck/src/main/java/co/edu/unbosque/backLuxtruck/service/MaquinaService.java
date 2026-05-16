package co.edu.unbosque.backLuxtruck.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.backLuxtruck.dto.MaquinaDTO;
import co.edu.unbosque.backLuxtruck.model.EstadoMaquina;
import co.edu.unbosque.backLuxtruck.model.Maquina;
import co.edu.unbosque.backLuxtruck.model.Material;
import co.edu.unbosque.backLuxtruck.model.Producto;
import co.edu.unbosque.backLuxtruck.model.Producir;
import co.edu.unbosque.backLuxtruck.model.Usar;
import co.edu.unbosque.backLuxtruck.repository.EstadoMaquinaRepository;
import co.edu.unbosque.backLuxtruck.repository.MaquinaRepository;
import co.edu.unbosque.backLuxtruck.repository.MaterialRepository;
import co.edu.unbosque.backLuxtruck.repository.ProducirRepository;
import co.edu.unbosque.backLuxtruck.repository.ProductoRepository;
import co.edu.unbosque.backLuxtruck.repository.UsarRepository;

@Service
public class MaquinaService {

    @Autowired
    private MaquinaRepository maquinaRepo;

    @Autowired
    private EstadoMaquinaRepository estadoMaquinaRepo;

    @Autowired
    private MaterialRepository materialRepo;

    @Autowired
    private UsarRepository usarRepo;

    @Autowired
    private ProductoRepository productoRepo;

    @Autowired
    private ProducirRepository producirRepo;

    @Autowired
    private ModelMapper modelMapper;

    // ----------------------------------------------------
    // CREATE
    // ----------------------------------------------------
    public int create(MaquinaDTO dto) {
        Optional<Maquina> found = maquinaRepo.findById(dto.getIdMaquina());
        if (found.isEmpty()) {
            Maquina entity = modelMapper.map(dto, Maquina.class);
            maquinaRepo.save(entity);
            return 0;
        }
        return 1;
    }

    public List<MaquinaDTO> getAll() {
        List<MaquinaDTO> dtoList = new ArrayList<>();
        for (Maquina m : maquinaRepo.findAll()) {
            dtoList.add(modelMapper.map(m, MaquinaDTO.class));
        }
        return dtoList;
    }

    public int update(int idMaquina, MaquinaDTO dto) {
        Optional<Maquina> found = maquinaRepo.findById(idMaquina);
        if (found.isPresent()) {
            Maquina maquina = found.get();
            maquina.setNumeroSerie(dto.getNumeroSerie());
            maquina.setTipo(dto.getTipo());
            maquinaRepo.save(maquina);
            return 0;
        }
        return 1;
    }

    public int delete(int idMaquina) {
        Optional<Maquina> found = maquinaRepo.findById(idMaquina);
        if (found.isPresent()) {
            maquinaRepo.deleteById(idMaquina);
            return 0;
        }
        return 1;
    }

    public int addEstadoToMaquina(int idMaquina, int idEstado) {
        Optional<Maquina> maquinaOpt = maquinaRepo.findById(idMaquina);
        Optional<EstadoMaquina> estadoOpt = estadoMaquinaRepo.findById(idEstado);
        if (maquinaOpt.isPresent() && estadoOpt.isPresent()) {
            Maquina maquina = maquinaOpt.get();
            maquina.setEstadoMaquina(estadoOpt.get());
            maquinaRepo.save(maquina);
            return 0;
        }
        return 1;
    }

    public int addMaterialToMaquina(int idMaquina, int idMaterial, String descripcion) {
        Optional<Maquina> maquinaOpt = maquinaRepo.findById(idMaquina);
        Optional<Material> materialOpt = materialRepo.findById(idMaterial);
        if (maquinaOpt.isPresent() && materialOpt.isPresent()) {
            Usar relacion = new Usar(materialOpt.get(), maquinaOpt.get(), descripcion);
            usarRepo.save(relacion);
            return 0;
        }
        return 1;
    }

   
    public int addProductoToMaquina(int idMaquina, int idProducto, String tiempoProduccion) {
        Optional<Maquina> maquinaOpt = maquinaRepo.findById(idMaquina);
        Optional<Producto> productoOpt = productoRepo.findById(idProducto);
        if (maquinaOpt.isPresent() && productoOpt.isPresent()) {
            Producir relacion = new Producir(maquinaOpt.get(), productoOpt.get(), tiempoProduccion);
            producirRepo.save(relacion);
            return 0;
        }
        return 1;
    }
}
