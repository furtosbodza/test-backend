package hu.partstore.model.repository;

import hu.partstore.model.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository
	extends JpaRepository<Item, Long> {

	@Query("SELECT item FROM Item item order by item.name ")
	List<Item> findAllOrderByNameAsc();

	@Query("SELECT item FROM Item item WHERE item.name LIKE %:name% order by item.name ")
	List<Item> findAllByNameOrderByNameAsc(String name);

	//@Query("SELECT item FROM Item item order by item.name ")
	List<Item> findAllPartsBySupplier(String supplier);

	List<Item> findAllPartsByNameAndSupplier(String name, String supplier);

	@Query("SELECT DISTINCT parts.supplier FROM Item parts")
	List<String> findAllSupplier();

}
