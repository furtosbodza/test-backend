package hu.partstore.model.repository;

import hu.partstore.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository
	extends JpaRepository<Item, Long> {

	@Query("SELECT part FROM Item part order by part.name ")
	List<Item> findAllParts();

	//@Query("SELECT part FROM Item part order by part.name ")
	List<Item> findAllPartsByName(String name);

	//@Query("SELECT part FROM Item part order by part.name ")
	List<Item> findAllPartsBySupplier(String supplier);

	List<Item> findAllPartsByNameAndSupplier(String name, String supplier);

	@Query("SELECT DISTINCT parts.supplier FROM Item parts")
	List<String> findAllSupplier();

}
