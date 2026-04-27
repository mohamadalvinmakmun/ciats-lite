package com.jabejo.ciats_lite.controller;

import com.jabejo.ciats_lite.model.Asset;
import com.jabejo.ciats_lite.service.AssetService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetRestController {

    private final AssetService service;

    public AssetRestController(AssetService service) {
        this.service = service;
    }

    @GetMapping
    public List<Asset> getAll() {
        return service.getAllAssets();
    }

    @GetMapping("/{id}")
    public Asset getById(@PathVariable String id) {
        return service.getAssetById(id);
    }

    @PostMapping
    public Asset create(@Valid @RequestBody Asset asset) {
        return service.createAsset(asset.getName(), asset.getCategory(), asset.getStatus());
    }

    @PutMapping("/{id}")
    public Asset update(@PathVariable String id, @Valid @RequestBody Asset asset) {
        return service.updateAsset(id, asset.getName(), asset.getCategory(), asset.getStatus());
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        return service.deleteAsset(id);
    }
}