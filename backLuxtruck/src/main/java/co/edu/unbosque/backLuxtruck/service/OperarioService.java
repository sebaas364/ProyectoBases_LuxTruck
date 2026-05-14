package co.edu.unbosque.backLuxtruck.service;

import java.util.List;
import org.springframework.stereotype.Service;
import co.edu.unbosque.backLuxtruck.dto.OperarioDTO;
import co.edu.unbosque.backLuxtruck.dto.TrabajadorDTO;
import co.edu.unbosque.backLuxtruck.model.Operario;
import co.edu.unbosque.backLuxtruck.model.Trabajador;
import co.edu.unbosque.backLuxtruck.repository.OperarioRepository;
import co.edu.unbosque.backLuxtruck.repository.TrabajadorRepository;

@Service
public class OperarioService {
    private final OperarioRepository repo;
    private final TrabajadorRepository trabajadorRepo;

    public OperarioService(OperarioRepository repo, TrabajadorRepository trabajadorRepo) {
        this.repo = repo;
        this.trabajadorRepo = trabajadorRepo;
    }

    private OperarioDTO toDto(Operario e) {
        OperarioDTO d = new OperarioDTO();
        d.idPersona       = e.getIdPersona().intValue();
        d.numeroDocumento = Integer.parseInt(e.getNumeroDocumento());
        d.tipoDocumento   = e.getTipoDocumento();
        d.primerNombre    = e.getPrimerNombre();
        d.segundoNombre   = e.getSegundoNombre();
        d.primerApellido  = e.getPrimerApellido();
        d.segundoApellido = e.getSegundoApellido();
        d.telefono        = Integer.parseInt(e.getTelefono());
        d.correo          = e.getCorreo();
        d.fechaIngreso    = e.getFechaIngreso().toLocalDate();
        d.salario         = e.getSalario().intValue();
        d.estado          = e.getEstado();
        d.contrasenia     = e.getContrasenia();
        d.setDesempenio(e.getDesempenio());
        if (e.getTrabajador() != null) {
            TrabajadorDTO tDto = new TrabajadorDTO();
            tDto.idPersona = e.getTrabajador().getIdPersona().intValue();
            tDto.primerNombre = e.getTrabajador().getPrimerNombre();
            tDto.correo = e.getTrabajador().getCorreo();
            d.setTrabajadordto(tDto);
        }
        return d;
    }

    private Operario toEntity(OperarioDTO d) {
        Operario e = new Operario();
        e.setNumeroDocumento(String.valueOf(d.numeroDocumento));
        e.setTipoDocumento(d.tipoDocumento);
        e.setPrimerNombre(d.primerNombre);
        e.setSegundoNombre(d.segundoNombre);
        e.setPrimerApellido(d.primerApellido);
        e.setSegundoApellido(d.segundoApellido);
        e.setTelefono(String.valueOf(d.telefono));
        e.setCorreo(d.correo);
        e.setFechaIngreso(java.sql.Date.valueOf(d.fechaIngreso));
        e.setSalario((double) d.salario);
        e.setEstado(d.estado);
        e.setContrasenia(d.contrasenia);
        e.setDesempenio(d.getDesempenio());
        if (d.getTrabajadordto() != null && d.getTrabajadordto().idPersona != null) {
            Trabajador t = trabajadorRepo.findById(d.getTrabajadordto().idPersona.intValue())
                    .orElseThrow(() -> new NotFoundException("Trabajador asociado no encontrado"));
            e.setTrabajador(t);
        }
        return e;
    }

    public OperarioDTO registrar(OperarioDTO dto) {
        return toDto(repo.save(toEntity(dto)));
    }

    public OperarioDTO actualizar(Integer id, OperarioDTO dto) {
        Operario e = repo.findById(id.intValue())
                .orElseThrow(() -> new NotFoundException("Operario no encontrado"));
        e.setNumeroDocumento(String.valueOf(dto.numeroDocumento));
        e.setTipoDocumento(dto.tipoDocumento);
        e.setPrimerNombre(dto.primerNombre);
        e.setSegundoNombre(dto.segundoNombre);
        e.setPrimerApellido(dto.primerApellido);
        e.setSegundoApellido(dto.segundoApellido);
        e.setTelefono(String.valueOf(dto.telefono));
        e.setCorreo(dto.correo);
        e.setFechaIngreso(java.sql.Date.valueOf(dto.fechaIngreso));
        e.setSalario((double) dto.salario);
        e.setEstado(dto.estado);
        e.setContrasenia(dto.contrasenia);
        e.setDesempenio(dto.getDesempenio());
        if (dto.getTrabajadordto() != null && dto.getTrabajadordto().idPersona != null) {
            Trabajador t = trabajadorRepo.findById(dto.getTrabajadordto().idPersona.intValue())
                    .orElseThrow(() -> new NotFoundException("Trabajador asociado no encontrado"));
            e.setTrabajador(t);
        }
        return toDto(repo.save(e));
    }

    public OperarioDTO obtener(Integer id) {
        return repo.findById(id.intValue()).map(this::toDto)
                .orElseThrow(() -> new NotFoundException("Operario no encontrado"));
    }

    public List<OperarioDTO> listar() {
        return ServiceUtils.toList(repo.findAll()).stream().map(this::toDto).toList();
    }

    public void eliminar(Integer id) {
        if (!repo.existsById(id.intValue()))
            throw new NotFoundException("Operario no encontrado");
        repo.deleteById(id.intValue());
    }
}