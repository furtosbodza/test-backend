package hu.partstore.model.dto;

public record ItemDto(
	Long id,
	String name,
	String supplier,
	Double price
)
{ }
