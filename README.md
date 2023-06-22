Evaluación técnica para la solución para el puesto de desarrollador senior Java fullstack

Aplicación Spring Boot para consultar el precio de mayor prioridad de una fecha para un código de producto y lista de precios con métodos de servicios CRUD REST

Para ejecutar la aplicación, el usuario debe compilar el proyecto maven que contiene las fuentes del código mediante el comando: mvn spring-boot:run

Una vez que la aplicación se está ejecutando, puede usar una herramienta como POSMAN para acceder a los puntos de entrada de los servicios web implementados, como se muestra a continuación:

use esta URL y HTTP GET METHOD para consultar todos: http://localhost:8080/api/prices/v1
debe ingresar los parametros del Reques creando las duplas nombre valor he ingresando los valores tal como se muestra en el llamado de los Test:
//Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)
		       paramsMap = new LinkedMultiValueMap<>();
		       paramsMap.add("date", "2020-06-16 21:00:00");
		       paramsMap.add("producId", "35455");
		       paramsMap.add("brandId", "1");
		       
		       mockMvc.perform(get(PRICE_PATH +"/v1").contentType(MediaType.APPLICATION_JSON)
		    	   .params(paramsMap))
		           .andExpect(status().isNoContent())
		           .andDo(print());	
