package co.micia.projects.restapi.pricelist;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import co.micia.projects.restapi.pricelist.daljpa.model.Brand;
import co.micia.projects.restapi.pricelist.daljpa.repositories.BrandRepository;




@Configuration
public class ApplicationConfig {
	 
	 private static final Logger log = LoggerFactory.getLogger(ApplicationConfig.class);

	  @Bean
	  CommandLineRunner initDatabase(BrandRepository repository) {
        /*
         * Inicializa la Base de Datos, insertando el Brand ZARA 
         * requerido para integridad de las pruebas y para poder crear Precios
         * ya que tiene referencia a un Brand en la entidad de persistencia
         */
	    return args -> {
	      log.info("Preloading " + repository.save(new Brand(1L, "ZARA")));
	    };
	  }
    




}
