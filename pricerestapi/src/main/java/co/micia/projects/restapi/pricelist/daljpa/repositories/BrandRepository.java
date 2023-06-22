package co.micia.projects.restapi.pricelist.daljpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.micia.projects.restapi.pricelist.daljpa.model.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
	/*
	 * Metodo para obtener un brand con un nombre especifico del repositorio de datos
	 */
	Brand findByName(String name);
}
