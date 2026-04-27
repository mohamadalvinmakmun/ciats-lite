package com.jabejo.ciats_lite.repository;

import com.jabejo.ciats_lite.model.Asset;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends MongoRepository<Asset,String> {
}
