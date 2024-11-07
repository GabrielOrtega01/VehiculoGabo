package com.mongodbvehiculosgabo.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodbvehiculosgabo.app.entity.Vehiculo;

public interface VehiculoRepository extends MongoRepository<Vehiculo, String> {
}
