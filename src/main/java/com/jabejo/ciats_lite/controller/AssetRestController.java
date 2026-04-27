package com.jabejo.ciats_lite.controller;

import com.jabejo.ciats_lite.dto.AssetRequest; // Import DTO baru kamu
import com.jabejo.ciats_lite.model.Asset;
import com.jabejo.ciats_lite.service.AssetService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
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

    @GetMapping("/paginated")
    public Page<Asset> getPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return service.getAllAssetsPaginated(page, size);
    }

    @PostMapping
    public Asset create(@Valid @RequestBody AssetRequest request) {
        return service.createAsset(
                request.getName(),
                request.getCategory(),
                request.getStatus()
        );
    }

    @PutMapping("/{id}")
    public Asset update(@PathVariable String id, @Valid @RequestBody AssetRequest request) {
        return service.updateAsset(
                id,
                request.getName(),
                request.getCategory(),
                request.getStatus()
        );
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        return service.deleteAsset(id);
    }
}