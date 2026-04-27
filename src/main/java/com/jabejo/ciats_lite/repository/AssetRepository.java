package com.jabejo.ciats_lite.repository;

import com.jabejo.ciats_lite.model.Asset;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends MongoRepository<Asset,String> {
    List<Asset> findByCategoryIgnoreCase(String category);
}
