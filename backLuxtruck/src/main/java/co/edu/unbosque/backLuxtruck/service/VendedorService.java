package co.edu.unbosque.backLuxtruck.service;

import java.util.List;
import org.springframework.stereotype.Service;
import co.edu.unbosque.backLuxtruck.dto.VendedorDTO;
import co.edu.unbosque.backLuxtruck.model.Vendedor;
import co.edu.unbosque.backLuxtruck.repository.VendedorRepository;

@Service
public class VendedorService {
    private final VendedorRepository repo;

    public VendedorService(VendedorRepository repo) {
        this.repo = repo;
    }

    private VendedorDTO toDto(Vendedor e) {
        VendedorDTO d = new VendedorDTO();
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
        d.comision        = e.getComision();
        d.setZonaVenta(e.getZonaVenta());
        return d;
    }

    private Vendedor toEntity(VendedorDTO d) {
        Vendedor e = new Vendedor();
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
        e.setComision(d.comision);
        e.setZonaVenta(d.getZonaVenta());
        return e;
    }

    public VendedorDTO registrar(VendedorDTO dto) {
        return toDto(repo.save(toEntity(dto)));
    }

    public VendedorDTO actualizar(Integer id, VendedorDTO dto) {
        Vendedor e = repo.findById(id.intValue())
                .orElseThrow(() -> new NotFoundException("Vendedor no encontrado"));
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
        e.setComision(dto.comision);
        e.setZonaVenta(dto.getZonaVenta());
        return toDto(repo.save(e));
    }

    public VendedorDTO obtener(Integer id) {
        return repo.findById(id.intValue()).map(this::toDto)
                .orElseThrow(() -> new NotFoundException("Vendedor no encontrado"));
    }

    public List<VendedorDTO> listar() {
        return ServiceUtils.toList(repo.findAll()).stream().map(this::toDto).toList();
    }

    public void eliminar(Integer id) {
        if (!repo.existsById(id.intValue()))
            throw new NotFoundException("Vendedor no encontrado");
        repo.deleteById(id.intValue());
    }
}