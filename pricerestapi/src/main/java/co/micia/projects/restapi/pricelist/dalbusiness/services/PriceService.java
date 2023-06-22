package co.micia.projects.restapi.pricelist.dalbusiness.services;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import co.micia.projects.restapi.pricelist.daljpa.model.Price;
/**
 * Interface with CRUD methods uses for  the controller class to expose REST WS
 * @author José V Niño R
 * @version 1.0
 * @since 2023
 */
public interface PriceService {

	List<Price> getAllPrices();
	List<Price> findAllByStartDate(@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date localdatetime);
	Price findPriceByStartDateAndProducIdAndBrandId(@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date date,String producId,Long brandId);
	Price createPrice(Price price);
}
