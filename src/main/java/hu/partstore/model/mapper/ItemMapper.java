package hu.partstore.model.mapper;

import hu.partstore.model.dto.ItemDto;
import hu.partstore.model.dto.SupplierDto;
import hu.partstore.model.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {


	Item updateItem(ItemDto itemDto, @MappingTarget Item item);

	Item toParts(ItemDto itemDto);

	ItemDto toPartsDto(Item part);

	List<ItemDto> toPartsDtoList(List<Item> partsList);

	@Mapping(target = "name", source = "supplier")
	SupplierDto toSupplierDto(String supplier);

	List<SupplierDto> toSupplierDtoList(List<String> supplierList);

}
