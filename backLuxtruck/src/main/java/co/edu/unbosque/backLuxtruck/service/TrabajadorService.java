package co.edu.unbosque.backLuxtruck.service;

import java.util.List;
import org.springframework.stereotype.Service;
import co.edu.unbosque.backLuxtruck.dto.TrabajadorDTO;
import co.edu.unbosque.backLuxtruck.model.Trabajador;
import co.edu.unbosque.backLuxtruck.repository.TrabajadorRepository;

@Service
public class TrabajadorService {
    private final TrabajadorRepository repo;

    public TrabajadorService(TrabajadorRepository repo) {
        this.repo = repo;
    }

    private TrabajadorDTO toDto(Trabajador e) {
        TrabajadorDTO d = new TrabajadorDTO();
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
        return d;
    }

    private Trabajador toEntity(TrabajadorDTO d) {
        Trabajador e = new Trabajador();
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
        return e;
    }

    public TrabajadorDTO registrar(TrabajadorDTO dto) {
        return toDto(repo.save(toEntity(dto)));
    }

    public TrabajadorDTO actualizar(Integer id, TrabajadorDTO dto) {
        Trabajador e = repo.findById(id.intValue())
                .orElseThrow(() -> new NotFoundException("Trabajador no encontrado"));
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
        return toDto(repo.save(e));
    }

    public TrabajadorDTO obtener(Integer id) {
        return repo.findById(id.intValue()).map(this::toDto)
                .orElseThrow(() -> new NotFoundException("Trabajador no encontrado"));
    }

    public List<TrabajadorDTO> listar() {
        return ServiceUtils.toList(repo.findAll()).stream().map(this::toDto).toList();
    }

    public void eliminar(Integer id) {
        if (!repo.existsById(id.intValue()))
            throw new NotFoundException("Trabajador no encontrado");
        repo.deleteById(id.intValue());
    }
}