package co.micia.projects.restapi.pricelist;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;



@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfig.class)
class ApplicationConfigTest {
    @Autowired
    ApplicationConfig applicationConfig;


}