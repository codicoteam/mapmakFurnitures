package com.clinpride.SecurityPostgres.OnDemand.Repo;

import com.clinpride.SecurityPostgres.OnDemand.Models.OnDemandModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnDemandRepo  extends MongoRepository<OnDemandModel, String> {

}
