package com.jabejo.ciats_lite.controller;

import com.jabejo.ciats_lite.model.Asset;
import com.jabejo.ciats_lite.service.AssetService;
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
}