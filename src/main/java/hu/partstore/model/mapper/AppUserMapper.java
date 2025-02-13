package hu.partstore.model.mapper;

import hu.partstore.model.dto.AppUserDto;
import hu.partstore.model.entity.AppUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AppUserMapper {

	AppUserDto toDto(AppUser appUser);

	AppUser toEntity(AppUserDto dto);
}
