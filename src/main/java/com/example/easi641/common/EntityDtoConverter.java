package com.example.easi641.common;

import com.example.easi641.dto.*;
import com.example.easi641.entities.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EntityDtoConverter {
    private final ModelMapper modelMapper;

    public EntityDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public JuegoResponse convertJuegoToDto(Juego juego){
        return modelMapper.map(juego, JuegoResponse.class);
    }

//    public Juego convertEntityToDto(JuegoRequest juegoRequest){
//        return modelMapper.map(juegoRequest, Juego.class);
//    }

    public List<JuegoResponse> convertJuegoToDto(List<Juego> juegos){
        return juegos.stream()
                .map(this::convertJuegoToDto) //igual a this::converEntutyToDto
                .collect(Collectors.toList());
    }

    public CategoriaResponse convertCategoriaToDto(Categoria categoria){
        return modelMapper.map(categoria, CategoriaResponse.class);
    }

    public List<CategoriaResponse> convertCategoriaToDto(List<Categoria> canciones){
        return canciones.stream()
                .map(this::convertCategoriaToDto) //igual a this::converEntutyToDto
                .collect(Collectors.toList());
    }

    public DetalleJuegoResponse convertDetalleJuegoToDto(DetalleJuego detalleJuego){
        return modelMapper.map(detalleJuego, DetalleJuegoResponse.class);
    }

    public DesarrolladorDto convertDesarrolladorToDto(Desarrollador desarrollador){ return modelMapper.map(desarrollador, DesarrolladorDto.class);}

    public List<DesarrolladorDto> convertDesarrolladorToDto(List<Desarrollador> desarrolladores){
        return desarrolladores.stream()
                .map(this::convertDesarrolladorToDto)
                .collect(Collectors.toList());
    }

    public ProyectoDto convertProyectoToDto(Proyecto proyecto){ return modelMapper.map(proyecto, ProyectoDto.class);}

    public List<ProyectoDto> convertProyectoToDto(List<Proyecto> proyectos){
        return proyectos.stream()
                .map(this::convertProyectoToDto)
                .collect(Collectors.toList());
    }

    public UserDto convertUserToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public List<UserDto> convertUserToDto(List<User> users) {
        return users.stream()// Stream<User>
                .map(this::convertUserToDto)// Stream<UserDto>
                .collect(Collectors.toList());// List<UserDto>
    }
}
