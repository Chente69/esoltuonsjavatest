package co.micia.projects.restapi.pricelist.dalbusiness.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import co.micia.projects.restapi.pricelist.dalbusiness.exceptions.ResourceNotFoundException;
import co.micia.projects.restapi.pricelist.daljpa.model.Brand;
import co.micia.projects.restapi.pricelist.daljpa.model.Price;
import co.micia.projects.restapi.pricelist.daljpa.repositories.BrandRepository;
import co.micia.projects.restapi.pricelist.daljpa.repositories.PriceRepository;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;


/**
 * Service that implements PriceService Interface 
 * @author José V Niño R
 * @version 1.0
 * @since 2023
 */
@Service
public class PriceServiceImpl implements PriceService{
	 private static final Logger log = LoggerFactory.getLogger(PriceServiceImpl.class);

	private PriceRepository priceRepository;
	private BrandRepository brandRepository;
	
    // Inject repo dependency 
	public PriceServiceImpl(PriceRepository priceRepository,BrandRepository brandRepository) {
		this.priceRepository = priceRepository;
		this.brandRepository = brandRepository;
	}
	
	 // Method to query all entities (Price) from table prices
	@Override
	public List<Price> getAllPrices() {
		return priceRepository.findAll();
	}
	
    /*
     * Todos los precios que coincidand con la fecha de consulta
     */
	public List<Price> findAllByStartDate(@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date localdatetime){
		return priceRepository.findAllByStartDate(localdatetime);
	}
	
	/*
	 * Consulta un precio que corresponde a un codigo de producto y lista de producto especifico
	 */
	public Price findPriceByStartDateAndProducIdAndBrandId(@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date date,String producId,Long brandId) {
		Price priceMax = null;
	        try {

	        	String newstr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	        	
	        	String s[] = newstr.split(" ",2);
	        	String datePart = s[0];
	        	String time = s[1];
	        	String  timeMin = " 00:00:00";
	        	String  timeMax = " 24:00:00";
	        	Date dateMin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datePart +timeMin);
	        	Date dateMax = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(datePart +timeMax);

	        	List<Price> pricelstAux = new ArrayList<Price>();
	        	List<Price> pricelst = new ArrayList<Price>();

	        	/*
	        	 * Obtine los precios cuya fecha inicial se encuentre dentro de la fecha de consulta y corresponda 
	        	 * a un producto y brand espefico
	        	 */
	        	pricelst = priceRepository.findAllByProducIdAndStartDateBetween(producId,dateMin,dateMax);

	        	/*
	        	 * Toma los precios cuya fecha sea mayor o igual al fecha que se consulta
	        	 */

	        	for (Price item : pricelst) {
	        		log.info("Item de Lista de precios preseleccionados: " + item.toString());		
	        		if((item.getStartDate().after(dateMin) || item.getStartDate().equals(dateMin)) && item.getBrandId()==brandId) pricelstAux.add(item);	
	        	}

	        	/*
	        	 * De la lista pricelst se toma el de mayor prioridad
	        	 */
	        	priceMax = pricelstAux.stream().max(Comparator.comparing(Price::getPriority)).orElse(null);
	        	if(priceMax==null)
	        		log.info("Precio Selecionado: Nulo");
	        	else
	        		log.info("Precio Selecionado: " + priceMax.toString());
	
	        } catch (Exception e1) {
	            e1.printStackTrace();
	        }

	      return priceMax;
	}

	@Override
	public Price createPrice(Price price) {
		Optional<Price> savedPrice = priceRepository.findPriceByStartDateAndEndDateAndProducIdAndPriceListAndCurr(price.getStartDate(),price.getEndDate(),price.getProducId(),price.getPriceList(),price.getCurr());
	       if(savedPrice.isPresent()){
	            throw new ResourceNotFoundException("Se encontró un Precio ya existente con los parametros :" + price.getStartDate()+", "+price.getEndDate()+", "+price.getProducId()+", "+price.getPriceList()+", "+price.getCurr());
	        }
           /*
            * Esto se ahce nacesario para mantener la integridad referencial entre lso
            * datos en la Base de Datos se debe persistir la entidad maestro antes
            * de crear cualquir entidad que la referencia ( Esta implementación es solo
            * por cuestiones practicas y para garantizar que se ejecuten correctamente las
            * pruebas unitarias.  La clase Brand solo se crea una sola vez y se hace al inicio de 
            * la aplicación
            */
	    	Brand _brand = brandRepository.findByName("ZARA");
           if (_brand !=null) {	    
        	   	log.info("Se obtiene el Brand " + "ZARA");
           		price.setBrand(_brand);
           }else {
           	Brand brand= new Brand(1L, "ZARA");
              	brandRepository.save(brand);
              	price.setBrand(brand);
              	log.info("Datos del  Brand Creado " + brand.toString());
           }    
	        return priceRepository.save(price);
	}


}
