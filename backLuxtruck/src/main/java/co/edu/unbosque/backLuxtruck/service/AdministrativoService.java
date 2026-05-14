package co.edu.unbosque.backLuxtruck.service;

import java.util.List;
import org.springframework.stereotype.Service;
import co.edu.unbosque.backLuxtruck.dto.AdministrativoDTO;
import co.edu.unbosque.backLuxtruck.dto.TrabajadorDTO;
import co.edu.unbosque.backLuxtruck.model.Administrativo;
import co.edu.unbosque.backLuxtruck.model.Trabajador;
import co.edu.unbosque.backLuxtruck.repository.AdministrativoRepository;
import co.edu.unbosque.backLuxtruck.repository.TrabajadorRepository;

@Service
public class AdministrativoService {
    private final AdministrativoRepository repo;
    private final TrabajadorRepository trabajadorRepo;

    public AdministrativoService(AdministrativoRepository repo, TrabajadorRepository trabajadorRepo) {
        this.repo = repo;
        this.trabajadorRepo = trabajadorRepo;
    }

    private AdministrativoDTO toDto(Administrativo e) {
        AdministrativoDTO d = new AdministrativoDTO();
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
        if (e.getTrabajador() != null) {
            TrabajadorDTO tDto = new TrabajadorDTO();
            tDto.idPersona = e.getTrabajador().getIdPersona().intValue();
            tDto.primerNombre = e.getTrabajador().getPrimerNombre();
            tDto.correo = e.getTrabajador().getCorreo();
            d.setTrabajadordto(tDto);
        }
        return d;
    }

    private Administrativo toEntity(AdministrativoDTO d) {
        Administrativo e = new Administrativo();
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
        if (d.getTrabajadordto() != null && d.getTrabajadordto().idPersona != null) {
            Trabajador t = trabajadorRepo.findById(d.getTrabajadordto().idPersona.intValue())
                    .orElseThrow(() -> new NotFoundException("Trabajador asociado no encontrado"));
            e.setTrabajador(t);
        }
        return e;
    }

    public AdministrativoDTO registrar(AdministrativoDTO dto) {
        return toDto(repo.save(toEntity(dto)));
    }

    public AdministrativoDTO actualizar(Integer id, AdministrativoDTO dto) {
        Administrativo e = repo.findById(id.intValue())
                .orElseThrow(() -> new NotFoundException("Administrativo no encontrado"));
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
        if (dto.getTrabajadordto() != null && dto.getTrabajadordto().idPersona != null) {
            Trabajador t = trabajadorRepo.findById(dto.getTrabajadordto().idPersona.intValue())
                    .orElseThrow(() -> new NotFoundException("Trabajador asociado no encontrado"));
            e.setTrabajador(t);
        }
        return toDto(repo.save(e));
    }

    public AdministrativoDTO obtener(Integer id) {
        return repo.findById(id.intValue()).map(this::toDto)
                .orElseThrow(() -> new NotFoundException("Administrativo no encontrado"));
    }

    public List<AdministrativoDTO> listar() {
        return ServiceUtils.toList(repo.findAll()).stream().map(this::toDto).toList();
    }

    public void eliminar(Integer id) {
        if (!repo.existsById(id.intValue()))
            throw new NotFoundException("Administrativo no encontrado");
        repo.deleteById(id.intValue());
    }
}