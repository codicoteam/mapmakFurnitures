package com.clinpride.SecurityPostgres.Promotions.Repo;

import com.clinpride.SecurityPostgres.Promotions.Models.PromotionModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepo extends MongoRepository<PromotionModel, String> {

}
