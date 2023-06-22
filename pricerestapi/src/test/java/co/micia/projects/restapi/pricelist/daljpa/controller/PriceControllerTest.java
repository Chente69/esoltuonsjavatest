package co.micia.projects.restapi.pricelist.daljpa.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.micia.projects.restapi.pricelist.ApplicationConfig;
import co.micia.projects.restapi.pricelist.dalbusiness.services.PriceServiceImpl;
import co.micia.projects.restapi.pricelist.daljpa.model.Brand;
import co.micia.projects.restapi.pricelist.daljpa.model.Price;
import co.micia.projects.restapi.pricelist.daljpa.repositories.BrandRepository;
import co.micia.projects.restapi.pricelist.daljpa.repositories.PriceRepository;



import org.springframework.boot.test.context.SpringBootTest;

/**
 * Test Component with client methods uses for  testing the Controller Methods exposed by PriceController
 * @author José V Niño R
 * @version 1.0
 * @since 2023
 */
@SpringBootTest
@AutoConfigureMockMvc
@Import(ApplicationConfig.class)
public class PriceControllerTest {
	private static final String PRICE_PATH = "/api/prices";
	private static final String BRAND_PATH = "/api/brands";
	private static final Logger log = LoggerFactory.getLogger(PriceControllerTest.class);
	/*
	 * Inject the components needed to test different cases
	 */	
	@Autowired
	private MockMvc mockMvc;
	    
	@Autowired
	private ObjectMapper objectMapper;	
		    
			
	@Mock
	private PriceRepository pricerepo;
	
	@Mock
	private BrandRepository brandrepo;

	@InjectMocks
	PriceServiceImpl priceService;
	

    
    List<Price> proces = new ArrayList<Price>();

	   
    /*
     * Test case para probar que se consultan todos los precios de la lista de precios desde los metodos HTPP de la API
     * expuestas en el controlador PriceController
     */       
	    @Test
	    void consultarPreciosParaUnProductoBrandYFecha() throws Exception,ParseException {
	        Brand brand = new Brand(1L, "ZARA");
	        Date dateStart = null;
	        Date dateEnd = null;
	        Date QryDate = null;
	        Long pricelist = 1L;
	        Byte priority = 1;
	        Price price1 = null;
	        Price price2 = null;
	        Price price3 = null;
	        Price price4 = null;
	        Price price5 = null;
		    List<Price> prices = new ArrayList<Price>();
		    Long brandId = 1L;
	        
	        mockMvc.perform(post(BRAND_PATH).contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(brand)))
	                .andExpect(status().isCreated())
	                .andDo(print());
	        

	        
	        when(brandrepo.findById(brandId)).thenReturn(Optional.of(brand));
	        this.mockMvc.perform(get(BRAND_PATH + "/{id}", brandId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(brandId))
            .andExpect(jsonPath("$.name").value(brand.getName()))
            .andDo(print()); 
	        

			dateStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-06-14 00:00:00");
			dateEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-12-31 23:59:59");
			priority = 0;
	        price1 = new Price(brand,dateStart,dateEnd,pricelist,"35455",priority,35.6D,"EUR");
	        
	        price1.setBrand(brand);
	       
	        System.out.println("Test Datos del  Brand Creado " + price1.getBrand().toString());
	        
	        mockMvc.perform(post(PRICE_PATH).contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(price1)))
	                .andExpect(status().isCreated())
	                .andDo(print());
	        
	        prices.add(price1);
	        
			dateStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-06-14 15:00:00");
			dateEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-06-14 18:30:00");
			priority = 1;
			price2 = new Price(brand,dateStart,dateEnd,pricelist,"35455",priority,25.45D,"EUR");
	        mockMvc.perform(post(PRICE_PATH).contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(price2)))
	        .andExpect(status().isCreated())
	                .andDo(print());
	          
	        prices.add(price2);

			dateStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-06-15 00:00:00");
			dateEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-06-15 11:00:00");
			priority = 1;
	        price3 = new Price(brand,dateStart,dateEnd,pricelist,"35455",priority,30.50D,"EUR");
	        
	        mockMvc.perform(post(PRICE_PATH).contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(price3)))
	                .andExpect(status().isCreated())
	                .andDo(print());
	        prices.add(price3);

			dateStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-06-15 16:00:00");
			dateEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-06-20 24:00:00");
			priority = 1;
	        price4 = new Price(brand,dateStart,dateEnd,pricelist,"35455",priority,30.50D,"EUR");
	        
	        mockMvc.perform(post(PRICE_PATH ).contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(price4)))
	                .andExpect(status().isCreated())
	                .andDo(print());
	        prices.add(price4);
	        
	        
	       
	        
			dateStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-06-15 06:00:00");
			dateEnd = null;
			priority = 1;
	        price5 = new Price(brand,dateStart,dateEnd,pricelist,"35455",priority,30.50D,"EUR");
	        
	       mockMvc.perform(post(PRICE_PATH).contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(price5)))
	                .andExpect(status().isCreated())
	                .andDo(print());

		       List<Price> priceList = Lists.newArrayList(price1, price2,price3,price4,price5);
		       Mockito.when(pricerepo.findAll()).thenReturn(priceList);

		       priceService.getAllPrices();

		       Mockito.verify(pricerepo).findAll();
		       
		       
		       //Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)
		       MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
		       paramsMap.add("date", "2020-06-14 10:00:00");
		       paramsMap.add("producId", "35455");
		       paramsMap.add("brandId", "1");
		       log.info("Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA) " );	
		       
		       mockMvc.perform(get(PRICE_PATH +"/v1").contentType(MediaType.APPLICATION_JSON)
		    	   .params(paramsMap))
		           .andExpect(status().isOk())
		           .andExpect(jsonPath("$.size()").value(9))
		           .andDo(print());
		       
		       
		       //Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)
		       paramsMap = new LinkedMultiValueMap<>();
		       paramsMap.add("date", "2020-06-14 16:00:00");
		       paramsMap.add("producId", "35455");
		       paramsMap.add("brandId", "1");
		       log.info("Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)" );
		       
		       mockMvc.perform(get(PRICE_PATH +"/v1").contentType(MediaType.APPLICATION_JSON)
		    	   .params(paramsMap))
		           .andExpect(status().isOk())
		           .andExpect(jsonPath("$.size()").value(9))
		           .andDo(print());
		       
		       
		       //Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)
		       paramsMap = new LinkedMultiValueMap<>();
		       paramsMap.add("date", "2020-06-14 21:00:00");
		       paramsMap.add("producId", "35455");
		       paramsMap.add("brandId", "1");
		       log.info("Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)" );
		       
		       mockMvc.perform(get(PRICE_PATH +"/v1").contentType(MediaType.APPLICATION_JSON)
		    	   .params(paramsMap))
		           .andExpect(status().isOk())
		           .andExpect(jsonPath("$.size()").value(9))
		           .andDo(print());		
		       
		       
		       
		       //Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)
		       paramsMap = new LinkedMultiValueMap<>();
		       paramsMap.add("date", "2020-06-15 10:00:00");
		       paramsMap.add("producId", "35455");
		       paramsMap.add("brandId", "1");
		       
		       mockMvc.perform(get(PRICE_PATH +"/v1").contentType(MediaType.APPLICATION_JSON)
		    	   .params(paramsMap))
		           .andExpect(status().isOk())
		           .andExpect(jsonPath("$.size()").value(9))
		           .andDo(print());	
		       
		       //Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
		       paramsMap = new LinkedMultiValueMap<>();
		       paramsMap.add("date", "2020-06-16 21:00:00");
		       paramsMap.add("producId", "35455");
		       paramsMap.add("brandId", "1");
		       
		       mockMvc.perform(get(PRICE_PATH +"/v1").contentType(MediaType.APPLICATION_JSON)
		    	   .params(paramsMap))
		           .andExpect(status().isNoContent())
		           .andDo(print());	
	    
	    
	    }
	

}
