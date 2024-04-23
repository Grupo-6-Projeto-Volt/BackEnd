package sptech.school.projetovolt.service.usuario.dto;

import sptech.school.projetovolt.entity.usuario.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioCriacaoDto dto) {
        if (dto == null) return null;

        Usuario entity = new Usuario();
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setTelefone(dto.getTelefone());
        entity.setCategoria((short) 0);
        return entity;
    }

    public static UsuarioConsultaDto toUsuarioConsultaDto(Usuario entity) {
        if (entity == null) return null;

        UsuarioConsultaDto dto = new UsuarioConsultaDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
        Short categoria = entity.getCategoria();

        if (categoria == 0) {
            dto.setCategoria("Admin");
        } else {
            dto.setCategoria("Cliente");
        }
        return dto;
    }
}
