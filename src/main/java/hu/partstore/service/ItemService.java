package hu.partstore.service;

import hu.partstore.model.dto.ItemDto;
import hu.partstore.model.dto.ItemSearchDto;
import hu.partstore.model.dto.SupplierDto;
import hu.partstore.model.entity.Item;
import hu.partstore.model.mapper.ItemMapper;
import hu.partstore.model.repository.ItemRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class ItemService {

	@Autowired
	private ItemRepository partsRepository;

	@Autowired
	ItemMapper partsMapper;

	public List<SupplierDto> listSuppliers() {
		return partsMapper.toSupplierDtoList(partsRepository.findAllSupplier());
	}

	public List<ItemDto> searchParts(ItemSearchDto searchDto) {
		List<Item> resultList;
		if (StringUtils.isBlank(searchDto.name()) && StringUtils.isBlank(searchDto.supplier())) {
			resultList = partsRepository.findAllParts();
		} else if (StringUtils.isBlank(searchDto.supplier())) {
			resultList = partsRepository.findAllPartsByName(searchDto.name());
		} else if (StringUtils.isBlank(searchDto.name())) {
			resultList = partsRepository.findAllPartsBySupplier(searchDto.supplier());
		} else {
			resultList = partsRepository.findAllPartsByNameAndSupplier(searchDto.name(), searchDto.supplier());
		}
		return partsMapper.toPartsDtoList(resultList);
	}

	public ItemDto createPart(ItemDto partDto) {
		return partsMapper.toPartsDto(
			partsRepository.save(
				partsMapper.toParts(partDto)));

	}
}
