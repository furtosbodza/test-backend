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
import java.util.Optional;

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
			resultList = partsRepository.findAllOrderByNameAsc();
		} else if (StringUtils.isBlank(searchDto.supplier())) {
			resultList = partsRepository.findAllByNameOrderByNameAsc(searchDto.name());
		} else if (StringUtils.isBlank(searchDto.name())) {
			resultList = partsRepository.findAllPartsBySupplier(searchDto.supplier());
		} else {
			resultList = partsRepository.findAllPartsByNameAndSupplier(searchDto.name(), searchDto.supplier());
		}
		return partsMapper.toPartsDtoList(resultList);
	}

	public ItemDto getItem(Long id) {
		Item item = findItemById(id);
		if (item == null) {
			return null;
		}
		return partsMapper.toPartsDto(item);
	}

	public ItemDto createOrModifyPart(ItemDto partDto) {
		if (partDto == null) {
			return null;
		}
		if (partDto.id() == null) {
			return createItem(partDto);
		} else {
			return modifyItem(partDto);
		}
	}

	public void deletePart(Long itemId) {
		partsRepository.deleteById(itemId);
	}

	private Item findItemById(Long itemId) {
		if (itemId == null) {
			return null;
		}
		Optional<Item> itemOpt = partsRepository.findById(itemId);
		if (itemOpt.isEmpty()) {
			throw new IllegalArgumentException("A megadott azoosito nem letezik!");
		}
		return itemOpt.get();
	}

	private ItemDto modifyItem(ItemDto partDto) {
		Item item = findItemById(partDto.id());
		if (item == null) {
			return null;
		}
		return partsMapper.toPartsDto(
			partsRepository.save(
				partsMapper.updateItem(partDto,item)));
	}

	private ItemDto createItem(ItemDto partDto) {
		//create
		return partsMapper.toPartsDto(
			partsRepository.save(
				partsMapper.toParts(partDto)));
	}


}
