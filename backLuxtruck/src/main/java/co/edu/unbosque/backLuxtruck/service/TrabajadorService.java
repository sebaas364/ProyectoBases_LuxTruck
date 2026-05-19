package co.edu.unbosque.backLuxtruck.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.backLuxtruck.dto.LoginDTO;
import co.edu.unbosque.backLuxtruck.dto.TrabajadorDTO;
import co.edu.unbosque.backLuxtruck.model.Trabajador;
import co.edu.unbosque.backLuxtruck.repository.AdministrativoRepository;
import co.edu.unbosque.backLuxtruck.repository.OperarioRepository;
import co.edu.unbosque.backLuxtruck.repository.TrabajadorRepository;
import co.edu.unbosque.backLuxtruck.repository.VendedorRepository;
import co.edu.unbosque.backLuxtruck.security.SecurityConfig;

@Service
public class TrabajadorService {

    @Autowired
    private TrabajadorRepository trabajadorRepo;
    
    @Autowired
    private VendedorRepository vendedorRepo;

    @Autowired
    private OperarioRepository operarioRepo;

    @Autowired
    private AdministrativoRepository administrativoRepo;
    
  private SecurityConfig sec;
    
    public TrabajadorService() {
    	sec = new SecurityConfig();
    }


    @Autowired
    private ModelMapper modelMapper;

    public int create(TrabajadorDTO dto) {
        Optional<Trabajador> found = trabajadorRepo.findByNumeroDocumento(dto.getNumeroDocumento());

        if (found.isEmpty()) {
            Trabajador entity = modelMapper.map(dto, Trabajador.class);
            entity.setIdPersona(null);
            entity.setContrasenia(sec.hashingToSHA256(dto.getContrasenia()));
            entity.setFechaIngreso(new Date(dto.getFechaIngreso().getTime()));

            trabajadorRepo.save(entity);
            return 0;
        }

        return 1;
    }

    public ArrayList<TrabajadorDTO> getAll() {
        ArrayList<TrabajadorDTO> dtoList = new ArrayList<>();

        for (Trabajador t : trabajadorRepo.findAll()) {
            TrabajadorDTO dto = modelMapper.map(t, TrabajadorDTO.class);
            if (t.getFechaIngreso() != null) {
                dto.setFechaIngreso(new Date(t.getFechaIngreso().getTime()));
            }
            dtoList.add(dto);
        }

        return dtoList;
    }

    public int update(int idPersona, TrabajadorDTO dto) {
        Optional<Trabajador> found = trabajadorRepo.findById(idPersona);

        if (found.isPresent()) {
            Trabajador trabajador = found.get();

            trabajador.setNumeroDocumento(dto.getNumeroDocumento());
            trabajador.setTipoDocumento(dto.getTipoDocumento());
            trabajador.setPrimerNombre(dto.getPrimerNombre());
            trabajador.setSegundoNombre(dto.getSegundoNombre());
            trabajador.setPrimerApellido(dto.getPrimerApellido());
            trabajador.setSegundoApellido(dto.getSegundoApellido());
            trabajador.setTelefono(dto.getTelefono());
            trabajador.setCorreo(dto.getCorreo());

            if (dto.getFechaIngreso() != null) {
                trabajador.setFechaIngreso(new Date(dto.getFechaIngreso().getTime()));
            }

            trabajador.setSalario(dto.getSalario());
            if (dto.getContrasenia() != null && !dto.getContrasenia().isBlank()) {
                trabajador.setContrasenia(sec.hashingToSHA256(dto.getContrasenia()));
            }

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
    
    public TrabajadorDTO login(LoginDTO dto) {
    	Optional<Trabajador> found = trabajadorRepo.findByCorreo(dto.getCorreo());

        if (found.isPresent()) {
            Trabajador trabajador = found.get();
            String hashIngresado = sec.hashingToSHA256(dto.getContrasenia());

            if (trabajador.getContrasenia().equals(hashIngresado)) {
                TrabajadorDTO resultado = modelMapper.map(trabajador, TrabajadorDTO.class);

                int id = trabajador.getIdPersona();

                if (vendedorRepo.existsById(id)) {
                    resultado.setRol("VENDEDOR");
                } else if (operarioRepo.existsById(id)) {
                    resultado.setRol("OPERARIO");
                } else if (administrativoRepo.existsById(id)) {
                    resultado.setRol("ADMINISTRATIVO");
                } else {
                    resultado.setRol("TRABAJADOR");
                }

                return resultado;
            }
        }	
		return null;
    }
}