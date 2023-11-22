package com.clinpride.SecurityPostgres.OnDisplay.Repo;

import com.clinpride.SecurityPostgres.OnDisplay.Models.OnDisplayModels;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnDisplayRepo extends MongoRepository<OnDisplayModels, String> {

}
