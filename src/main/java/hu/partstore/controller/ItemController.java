package hu.partstore.controller;

import hu.partstore.model.dto.ItemDto;
import hu.partstore.model.dto.ItemSearchDto;
import hu.partstore.model.dto.SupplierDto;
import hu.partstore.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/item/api")
public class ItemController {

	@Autowired
	private ItemService storeService;
	/*
	@PostMapping("/user/login")
	public ResponseEntity<AppUserDto> loginUser(AppUserDto userDto) {
        /*return new ResponseEntity<>(
                storeService.authenticateUser(userDto),
                HttpStatus.OK);*/
		/*return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/user/regist")
	public AppUserDto registerUser(AppUserDto userDto) {
		return storeService.registerUser(userDto);
	}*/

	@GetMapping("/supplier/list")
	public ResponseEntity<List<SupplierDto>> listSuppliers() {
		return new ResponseEntity<>(storeService.listSuppliers(), HttpStatus.OK);
	}

	//Create – Létrehozás, Read – Listázás, Update – Módosítás,
	//Delete – Törlés
	@PostMapping("/parts/list")
	public ResponseEntity<List<ItemDto>> searchParts(@RequestBody ItemSearchDto searchDto) {
		return new ResponseEntity<>(storeService.searchParts(searchDto), HttpStatus.OK);
	}

	@PostMapping("/parts/create")
	public ResponseEntity<ItemDto> createPart(@RequestBody ItemDto partDto) {
		return new ResponseEntity<>(storeService.createPart(partDto), HttpStatus.OK);
	}



}
