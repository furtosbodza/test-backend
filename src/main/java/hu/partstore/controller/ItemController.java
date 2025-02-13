package hu.partstore.controller;

import hu.partstore.model.dto.ItemDto;
import hu.partstore.model.dto.ItemSearchDto;
import hu.partstore.model.dto.SupplierDto;
import hu.partstore.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/item/api")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@GetMapping("/supplier/list")
	public ResponseEntity<List<SupplierDto>> listSuppliers() {
		return new ResponseEntity<>(itemService.listSuppliers(), HttpStatus.OK);
	}

	@GetMapping("/item/{id}")
	public ResponseEntity<ItemDto> getItem(@PathVariable("id") String itemId) {
		return new ResponseEntity<>(itemService.getItem(Long.valueOf(itemId)), HttpStatus.OK);
	}

	@PostMapping("/item/list")
	public ResponseEntity<List<ItemDto>> searchParts(@RequestBody ItemSearchDto searchDto) {
		return new ResponseEntity<>(itemService.searchParts(searchDto), HttpStatus.OK);
	}

	@PostMapping("/item/create")
	public ResponseEntity<ItemDto> createPart(@RequestBody ItemDto partDto) {
		return new ResponseEntity<>(itemService.createOrModifyPart(partDto), HttpStatus.OK);
	}

	@PutMapping("/item/modify")
	public ResponseEntity<ItemDto> modifyPart(@RequestBody ItemDto itemDto) {
		return new ResponseEntity<>(itemService.createOrModifyPart(itemDto), HttpStatus.OK);
	}

	@DeleteMapping("/item/delete/{id}")
	public ResponseEntity deletePart(@PathVariable("id") Long itemId) {
		itemService.deletePart(itemId);
		return new ResponseEntity<>(HttpStatus.OK);
	}




}
