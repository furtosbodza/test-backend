package hu.partstore.service;


import hu.partstore.model.dto.AppUserDto;
import hu.partstore.model.entity.AppUser;
import hu.partstore.model.mapper.AppUserMapper;
import hu.partstore.model.repository.AppUserRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Transactional
@Service
public class AuthService {

	@Autowired
	AppUserRepository appUserRepository;

	@Autowired
	AppUserMapper appUserMapper;

	public AppUserDto loginUser(AppUserDto userDto) {
		if (userDto == null
			|| StringUtils.isBlank(userDto.email())
			|| StringUtils.isBlank(userDto.password())
		) {
			return null;
		}
		Optional<AppUser> appUserOpt = appUserRepository.findByEmailAndPassword(userDto.email(), userDto.password());
		return appUserOpt.map(appUser -> appUserMapper.toDto(appUser)).orElse(null);
	}

	public AppUserDto registerUser(AppUserDto userDto) {
		if (userDto == null
			|| StringUtils.isBlank(userDto.email())
			|| StringUtils.isBlank(userDto.password())
		) {
			throw new IllegalArgumentException("Hiányos adatok!");
		}
		return appUserMapper.toDto(
			appUserRepository.save(
				appUserMapper.toEntity(userDto)));
	}

}
