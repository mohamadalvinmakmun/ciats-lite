package com.jabejo.ciats_lite.controller;

import com.jabejo.ciats_lite.model.Asset;
import com.jabejo.ciats_lite.service.AssetService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AssetController {
    private final AssetService service;

    public AssetController(AssetService service) {
        this.service = service;
    }

    @QueryMapping
    public List<Asset> getAllAssets(){
        return service.getAllAssets();
    }

    @QueryMapping
    public Asset getAssetById(@Argument String id) {
        return service.getAssetById(id);
    }

    @MutationMapping
    public Asset createAsset(@Argument String name, @Argument String category, @Argument String status) {
        return service.createAsset(name, category, status);
    }

    @MutationMapping
    public Asset updateAsset(@Argument String id, @Argument String name, @Argument String category, @Argument String status) {
        return service.updateAsset(id, name, category, status);
    }

    @MutationMapping
    public String deleteAsset(@Argument String id) {
        return service.deleteAsset(id);
    }
}




