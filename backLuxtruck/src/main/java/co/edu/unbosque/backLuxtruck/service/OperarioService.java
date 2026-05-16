package co.edu.unbosque.backLuxtruck.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.backLuxtruck.dto.OperarioDTO;
import co.edu.unbosque.backLuxtruck.model.Especialidad;
import co.edu.unbosque.backLuxtruck.model.EspecialidadOperario;
import co.edu.unbosque.backLuxtruck.model.EstadoTrabajador;
import co.edu.unbosque.backLuxtruck.model.Maquina;
import co.edu.unbosque.backLuxtruck.model.Operario;
import co.edu.unbosque.backLuxtruck.model.Utilizar;
import co.edu.unbosque.backLuxtruck.repository.EspecialidadOperarioRepository;
import co.edu.unbosque.backLuxtruck.repository.EspecialidadRepository;
import co.edu.unbosque.backLuxtruck.repository.EstadoTrabajadorRepository;
import co.edu.unbosque.backLuxtruck.repository.MaquinaRepository;
import co.edu.unbosque.backLuxtruck.repository.OperarioRepository;
import co.edu.unbosque.backLuxtruck.repository.UtilizarRepository;

@Service
public class OperarioService {

    @Autowired
    private OperarioRepository operarioRepo;

    @Autowired
    private EstadoTrabajadorRepository estadoTrabajadorRepo;

    @Autowired
    private EspecialidadRepository especialidadRepo;

    @Autowired
    private EspecialidadOperarioRepository especialidadOperarioRepo;

    @Autowired
    private MaquinaRepository maquinaRepo;

    @Autowired
    private UtilizarRepository utilizarRepo;

    @Autowired
    private ModelMapper modelMapper;

    public int create(OperarioDTO dto) {
        Optional<Operario> found = operarioRepo.findById(dto.getIdPersona());
        if (found.isEmpty()) {
            Operario entity = modelMapper.map(dto, Operario.class);
            operarioRepo.save(entity);
            return 0;
        }
        return 1;
    }

    public List<OperarioDTO> getAll() {
        List<OperarioDTO> dtoList = new ArrayList<>();
        for (Operario o : operarioRepo.findAll()) {
            dtoList.add(modelMapper.map(o, OperarioDTO.class));
        }
        return dtoList;
    }


    public int update(int idPersona, OperarioDTO dto) {
        Optional<Operario> found = operarioRepo.findById(idPersona);
        if (found.isPresent()) {
            Operario operario = found.get();
            // Campos de Persona
            operario.setNumeroDocumento(dto.getNumeroDocumento());
            operario.setTipoDocumento(dto.getTipoDocumento());
            operario.setPrimerNombre(dto.getPrimerNombre());
            operario.setSegundoNombre(dto.getSegundoNombre());
            operario.setPrimerApellido(dto.getPrimerApellido());
            operario.setSegundoApellido(dto.getSegundoApellido());
            operario.setTelefono(dto.getTelefono());
            operario.setCorreo(dto.getCorreo());
            // Campos de Trabajador
            operario.setFechaIngreso((Date) dto.getFechaIngreso());
            operario.setSalario(dto.getSalario());
            operario.setContrasenia(dto.getContrasenia());
            // Campos propios de Operario
            operario.setDesempenio(dto.getDesempenio());
            operarioRepo.save(operario);
            return 0;
        }
        return 1;
    }

    public int delete(int idPersona) {
        Optional<Operario> found = operarioRepo.findById(idPersona);
        if (found.isPresent()) {
            operarioRepo.deleteById(idPersona);
            return 0;
        }
        return 1;
    }

    public int addEstadoToOperario(int idPersona, int idEstado) {
        Optional<Operario> operarioOpt = operarioRepo.findById(idPersona);
        Optional<EstadoTrabajador> estadoOpt = estadoTrabajadorRepo.findById(idEstado);
        if (operarioOpt.isPresent() && estadoOpt.isPresent()) {
            Operario operario = operarioOpt.get();
            operario.setEstadoTrabajador(estadoOpt.get());
            operarioRepo.save(operario);
            return 0;
        }
        return 1;
    }

   
    public int addEspecialidadToOperario(int idOperario, int idEspecialidad) {
        Optional<Operario> operarioOpt = operarioRepo.findById(idOperario);
        Optional<Especialidad> especialidadOpt = especialidadRepo.findById(idEspecialidad);
        if (operarioOpt.isPresent() && especialidadOpt.isPresent()) {
            EspecialidadOperario relacion = new EspecialidadOperario(especialidadOpt.get(), operarioOpt.get());
            especialidadOperarioRepo.save(relacion);
            return 0;
        }
        return 1;
    }

    public int addMaquinaToOperario(int idOperario, int idMaquina) {
        Optional<Operario> operarioOpt = operarioRepo.findById(idOperario);
        Optional<Maquina> maquinaOpt = maquinaRepo.findById(idMaquina);
        if (operarioOpt.isPresent() && maquinaOpt.isPresent()) {
            Utilizar relacion = new Utilizar(operarioOpt.get(), maquinaOpt.get());
            utilizarRepo.save(relacion);
            return 0;
        }
        return 1;
    }
}
