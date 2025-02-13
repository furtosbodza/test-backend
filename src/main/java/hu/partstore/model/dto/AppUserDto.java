package hu.partstore.model.dto;

public record AppUserDto(
	Long id,
	String name,
	String email,
	String password
)
{ }
