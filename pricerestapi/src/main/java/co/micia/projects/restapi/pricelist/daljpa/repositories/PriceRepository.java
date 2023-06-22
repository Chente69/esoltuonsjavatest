package co.micia.projects.restapi.pricelist.daljpa.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.format.annotation.DateTimeFormat;

import co.micia.projects.restapi.pricelist.daljpa.model.Price;

public interface PriceRepository extends JpaRepository<Price, Long> {

	  /*
	   * Metodo para consultar todos los precios de la lista de precios que corresponden a una fecha de inicio
	   */
	  List<Price> findAllByStartDate(@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date localdatetime);
	   
	  /*
	   * Metodo para consultar todos los precios de la lista de precios que se encuentran entre a una fecha de inicio
	   * y una fecha final y que son del un codigo de producto dado y una moneda
	   */
	   List<Price> findAllByProducIdAndStartDateBetween(String producId,
			   @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date dateini,
			   @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date dateend);
	  /*
	   * Metodo para consultar un precio de la lista de precios que se encuentran entre a una fecha de inicio
	   * y una fecha final y que son del un codigo de producto dado, lista de producto y una moneda ( un precio unico)
	   */	   
	   Optional<Price> findPriceByStartDateAndEndDateAndProducIdAndPriceListAndCurr(Date startDate,Date endDate,String producId,Long priceList,String curr);

}
