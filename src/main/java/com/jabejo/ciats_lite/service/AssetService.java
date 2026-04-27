package com.jabejo.ciats_lite.service;

import com.jabejo.ciats_lite.model.Asset;
import com.jabejo.ciats_lite.repository.AssetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AssetService {
    private final AssetRepository repository;

    private final SequenceGeneratorService sequenceGenerator;

    public AssetService(AssetRepository repository,SequenceGeneratorService sequenceGenerator){
        this.repository = repository;
        this.sequenceGenerator = sequenceGenerator;
    }

    public List<Asset> findAssetsByName(String name) {
        return repository.findAll().stream()
                .filter(asset -> asset.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    public Page<Asset> getAllAssetsPaginatedAndSorted(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("asc")?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(page, size, sort);
        return repository.findAll(pageable);
    }

    public List<Asset> getAllAssets() {
        return repository.findAll();
    }

    public List<Asset> getAssetByCategory(String category) {
        return repository.findByCategoryIgnoreCase(category);
    }

    public Map<String, Long> getAssetCountByCategory(){
        return repository.findAll().stream()
                .collect(Collectors.groupingBy(
                        Asset::getCategory,
                        Collectors.counting()
                ));
    }

    @Cacheable(value = "assetCache", key = "#id")
    public Asset getAssetById(String id) {
        log.info("MENGAMBIL DATA DARI MONGODB. JIKA LOG INI MUNCUL, BERARTI DATA BELUM DI-CACHE DI REDIS");
        return repository.findById(id).orElse(null);
    }

    public Asset createAsset(String name, String category, String status) {
        Asset asset = Asset.builder()
                .id(sequenceGenerator.generateSequence("asset_sequence"))
                .name(name)
                .category(category)
                .status(status)
                .build();
        return repository.save(asset);
    }

    @CachePut(value = "assetCache", key = "#id")
    public Asset updateAsset(String id, String name, String category,String status) {
        Asset asset = repository.findById(id).orElseThrow(() -> new RuntimeException("Aset Tidak Ditemukan"));
        if (name != null) asset.setName(name);
        if (category != null) asset.setCategory(category);
        if (status != null) asset.setStatus(status);
        return repository.save(asset);
    }

    @CacheEvict(value = "assetCache", key = "#id")
    public String deleteAsset(String id) {
        repository.deleteById(id);

        return "Aset dengan ID " + id + " berhasil dihapus secara permanen dari Mongodb dan Redis";
    }
}
