package com.clinpride.SecurityPostgres.Hiring.Repo;

import com.clinpride.SecurityPostgres.Hiring.models.HiringModel;
import com.clinpride.SecurityPostgres.Orders.models.OrderModels;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HiringRepo extends MongoRepository<HiringModel, String> {
    List<HiringModel> findByCustomerEmail(String email);
    Optional<HiringModel> findByHiringId(String orderId);
}
