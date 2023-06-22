	package co.micia.projects.restapi.pricelist.daljpa.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.micia.projects.restapi.pricelist.dalbusiness.services.PriceService;
import co.micia.projects.restapi.pricelist.daljpa.model.Price;
import co.micia.projects.restapi.pricelist.daljpa.repositories.BrandRepository;
import co.micia.projects.restapi.pricelist.daljpa.repositories.PriceRepository;
/**
 * Rest Controler Component use to expose API Rest JSON web services for Price Entity
 * @author José V Niño R
 * @version 1.0
 * @since 2023
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class PriceController {
	private static final Logger log = LoggerFactory.getLogger(PriceController.class);
	@Autowired
	PriceRepository priceRepository; // Inject dependency to the Repository component (Bean)

	@Autowired
	BrandRepository brandRepository; // Inject dependency to the Repository component (Bean)
	
	PriceService priceService;
	
	/*
	 * Injecta el servicio pricesService por constructor del controlador
	 */
	@Autowired
	public PriceController(PriceService priceService) {
		this.priceService = priceService;
	}

    /**
     * This method corresponds to HTTP GET method, that Queries all the Price entities stored in H2 pricedb Data Base
     * 
     * @return ResponseEntity<List<Price>> Price Entity Collection
     */
	  @GetMapping("/prices")
	  public ResponseEntity<List<Price>> getAllPrices() {
	    try {
	        List<Price> prices = new ArrayList<Price>();

	        priceService.getAllPrices().forEach(prices::add);
	        if (prices.isEmpty()) {
	          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }

	        return new ResponseEntity<>(prices, HttpStatus.OK);
	     } catch (Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	     }	  
	  }

	    /**
	     * This method corresponds to HTTP POST method, that Save the Price entities stored in H2 pricedb Data Base
	     * 
	     * @return ResponseEntity<Price> Price Entity
	     */
	  @PostMapping("/prices")
	  public ResponseEntity<Price> savePrice(@RequestBody Price price) {
	    try {
       
    		return new ResponseEntity<Price>(priceService.createPrice(price), HttpStatus.CREATED);
	     } catch (Exception e) {
	    	 log.info("Error al crear price: " + e.getMessage());
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	     }	  
	  }
	  
	  /*
	   * Consuta  el precio de la lista de precios para una fecha especifica, producto y moneda
	   * Toma todos los precios desde la fecha de inicio y luego toma el de mayor prioridad
	   */
	  @GetMapping("/prices/v1")
	  public ResponseEntity<Price> getAllPricesByProductAndBrandAndDate(@RequestParam("date") @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date date, @RequestParam("producId") String productId, @RequestParam("brandId") Long brandId) {
	    try {
	        List<Price> prices = new ArrayList<Price>();
	        //Buscar en lista de precios desde rango de la fecha de consulta y tomar la de mayor prioridad
	        Price _price = priceService.findPriceByStartDateAndProducIdAndBrandId(date,productId,brandId);
	        if (_price !=null)  
	        	log.info("HTTP GE Precio Selecionado: " + _price.toString());
	        else 
	        	log.info("HTTP GE Precio Selecionado: Nulo " );
	        
	        if (_price == null) {
	          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }

	        return new ResponseEntity<>(_price, HttpStatus.OK);
	     } catch (Exception e) {
	        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	     }	  
	  
  
	  }




}
