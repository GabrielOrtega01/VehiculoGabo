package com.mongodbvehiculosgabo.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodbvehiculosgabo.app.entity.Administrador;

public interface AdministradorRepository extends MongoRepository<Administrador, String> {

}