package com.mongodbvehiculosgabo.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodbvehiculosgabo.app.entity.Reserva;

public interface ReservaRepository extends MongoRepository<Reserva, String> {
}
