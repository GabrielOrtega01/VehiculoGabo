package com.mongodbvehiculosgabo.app.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodbvehiculosgabo.app.entity.Pago;
public interface PagoRepository extends MongoRepository<Pago, String> {
}
