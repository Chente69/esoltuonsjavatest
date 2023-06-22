package co.micia.projects.restapi.pricelist.daljpa.service;
import org.junit.jupiter.api.extension.ExtendWith;


import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import co.micia.projects.restapi.pricelist.dalbusiness.services.PriceServiceImpl;
import co.micia.projects.restapi.pricelist.daljpa.model.Brand;
import co.micia.projects.restapi.pricelist.daljpa.model.Price;
import co.micia.projects.restapi.pricelist.daljpa.repositories.PriceRepository;
/**
 * Interface with client methods uses for  testing the Sevices Methods exposed by PriceServiceImp
 * @author José V Niño R
 * @version 1.0
 * @since 2023
 */
@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {

	/*
	 * Inject the components needed to test different cases
	 */
	@InjectMocks
	PriceServiceImpl priceService;
	
	@Mock
	PriceRepository pricerepo;
	
    @Mock
    private ModelMapper mapper;
    

    /*
     * Test case para probar que se consultan todos los precios de la lista de precios sin ninguna condición
     */
    @Test
    void testGetAll() {

        
        Brand brand = new Brand(1L,"ZARA");
        Date dateStart = null;
		try {
			dateStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-05-15 10:00:00");
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        Date dateEnd = null;
		try {
			dateEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-07-15 23:59:59");
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        Long pricelist = 1L;
        Byte priority = 1;
        Price price1 = new Price(brand,dateStart,dateEnd,pricelist,"35455",priority,35.6D,"EUR");
         try {
			dateStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-06-14 15:00:00");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
         try {
			dateEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-06-14 18:30:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         Price price2 = new Price(brand,dateStart,dateEnd,pricelist,"35455",priority,35.6D,"EUR");
         
        List<Price> priceList = Lists.newArrayList(price1, price2);
        Mockito.when(pricerepo.findAll()).thenReturn(priceList);

       priceService.getAllPrices();

       Mockito.verify(pricerepo).findAll();

    }
 
    /*
     * Test case para probar que se consultan todos los precios de la lista de precios son de una fecha de inicio especifica
     */
    @Test
    void testGetAllByDate() {
        Brand brand = new Brand(1L, "ZARA");
        Date dateStart = null;
        Date dateEnd = null;
        Date dateFind = null;
        Long pricelist = 1L;
        Byte priority = 1;
 
		try {
			dateStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-05-15 10:00:00");
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			dateEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-07-15 23:59:59");
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

        Price price1 = new Price(brand,dateStart,dateEnd,pricelist,"35455",priority,35.6D,"EUR");
        

         try {
			dateStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-06-14 15:00:00");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
         try {
			dateEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-06-14 18:30:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         Price price2 = new Price(brand,dateStart,dateEnd,pricelist,"35455",priority,35.6D,"EUR");
 

         try {
			dateStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-06-15 16:00:00");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
         try {
			dateEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-12-31 23:59:59");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         Price price3 = new Price(brand,dateStart,dateEnd,pricelist,"35455",priority,35.6D,"EUR");
         
        List<Price> priceList = Lists.newArrayList(price1, price2,price3);
        
        try {
			dateFind = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-06-14 15:00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Mockito.when(pricerepo.findAllByStartDate(dateFind)).thenReturn(Lists.newArrayList(price1));



        priceService.findAllByStartDate(dateFind);
        Mockito.verify(pricerepo).findAllByStartDate(dateFind);
 	
    }
 
    /*
     * Test case para probar que se consultan todos los precios de la lista de precios que se encuentran en un rango de una fecha de inicio
     * y fecha final especifica, para un código de producto dado y una moneda
     */
    @Test
    void testGetPriceByDate()  {
        Brand brand = new Brand(1L,"ZARA");
        Date dateStart = null;
        Date dateEnd = null;
        Date dateFind = null;

        Long pricelist = 1L;
        Byte priority = 1;
 
		try {
			dateStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-06-14 00:00:00");
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			dateEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-12-31 23:59:59");
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

        Price price1 = new Price(brand,dateStart,dateEnd,pricelist,"35455",priority,35.6D,"EUR");
         try {
			dateStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-06-14 15:00:00");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
         try {
			dateEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-06-14 18:30:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         Price price2 = new Price(brand,dateStart,dateEnd,pricelist,"35455",priority,25.45,"EUR");
 

         try {
			dateStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-06-15 00:00:00");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
         try {
			dateEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-06-15 11:00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         Price price3 = new Price(brand,dateStart,dateEnd,pricelist,"35455",priority,30.50,"EUR");
 
         
         try {
			dateStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-06-15 16:00:00");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
         try {
			dateEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-06-17 24:00:00");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         Price price4 = new Price(brand,dateStart,dateEnd,pricelist,"35455",priority,38.95,"EUR");         
        
         List<Price> priceList = Lists.newArrayList(price1, price2,price3,price4);
        
         try {
 			dateFind = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-06-14 15:00:00");
 		} catch (ParseException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
         
      	String newstr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateFind);
    	
      	String s[] = newstr.split(" ",2);
      	String datePart = s[0];
      	String time = s[1];
      	String  timeMin = " 00:00:00";
      	String  timeMax = " 24:00:00";
      	Date dateMin = null;
      	Date dateMax = null;
      	try {
			 dateMin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datePart +timeMin);
			 dateMax = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datePart +timeMax);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      	
      	
         Mockito.when(pricerepo.findAllByProducIdAndStartDateBetween("35455",dateMin,dateMax)).thenReturn(Lists.newArrayList(price1));

         // Cosultar todos los precios en un rango de fechas para un producto y moneda
         priceService.findPriceByStartDateAndProducIdAndBrandId(dateFind,"35455",1L);
         

         Mockito.verify(pricerepo).findAllByProducIdAndStartDateBetween("35455",dateMin,dateMax);
    }

    
}
