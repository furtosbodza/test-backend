package hu.partstore.model.dto;

public record AppUserDto(
	Long id,
	String email,
	String password
)
{ }
