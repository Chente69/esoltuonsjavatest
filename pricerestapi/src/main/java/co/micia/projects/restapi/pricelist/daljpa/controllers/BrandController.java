package co.micia.projects.restapi.pricelist.daljpa.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.micia.projects.restapi.pricelist.daljpa.model.Brand;
import co.micia.projects.restapi.pricelist.daljpa.repositories.BrandRepository;
/**
 * Class with CRUb Brand methods to expose REST API WS
 * @author José V Niño R
 * @version 1.0
 * @since 2023
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class BrandController {

	@Autowired
	BrandRepository brandRepository; // Inject dependency to the Repository component (Bean)
	
    /**
     * This method corresponds to HTTP GET method, that Queries all the Brand entities stored in H2 pricedb Data Base
     * 
     * @return ResponseEntity<List<Brand>> Brand Entity Collection
     */
	  @GetMapping("/brands")
	  public ResponseEntity<List<Brand>> getAllBrands() {
	    try {
	        List<Brand> brands = new ArrayList<Brand>();

	        brandRepository.findAll().forEach(brands::add);

	        if (brands.isEmpty()) {
	          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }

	        return new ResponseEntity<>(brands, HttpStatus.OK);
	     } catch (Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	     }	  
	  }
	  
	    /**
	     * This method corresponds to HTTP GET method, that Queries by the identifier one specific Brand entities stored in H2 pricedb Data Base
	     * @param id: is the Brand unique identifier to query
	     * @return ResponseEntity<Brand>: Brand Entity
	     */
		  @GetMapping("/brands/{id}")
		  public ResponseEntity<Brand> getBrandById(@PathVariable("id") long id) {
			    Optional<Brand> todoData = brandRepository.findById(id);

			    if (todoData.isPresent()) {
			      return new ResponseEntity<>(todoData.get(), HttpStatus.OK);
			    } else {
			      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			    }	  
		  }
		  
	    /**
	     * This method corresponds to HTTP POST method, that saves (in H2 pricedb Data Base) the specific  Brand entity gives by param
	     * @param todo: is the Brand entity to create
	     * @return ResponseEntity<Brand>: Brand Entity created
	     */	  
		  @PostMapping("/brands")
		  public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) {
		    try {
		    	Brand _brand = brandRepository
		          .save(brand);
		      return new ResponseEntity<>(_brand, HttpStatus.CREATED);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		  }		  
		  
}
