package com.mongodbvehiculosgabo.app.repository;

	import org.springframework.data.mongodb.repository.MongoRepository;
	import com.mongodbvehiculosgabo.app.entity.Factura;

	public interface FacturaRepository extends MongoRepository<Factura, String> {
	}

