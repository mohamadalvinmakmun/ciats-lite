package com.jabejo.ciats_lite.service;

import com.jabejo.ciats_lite.model.Asset;
import com.jabejo.ciats_lite.repository.AssetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssetServiceTest {

    @Mock
    private AssetRepository repository;

    @InjectMocks
    private AssetService assetService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAssetById_Success() {
        Asset mockAsset = Asset.builder()
                .id("1")
                .name("Laptop Tes")
                .status("Active")
                .build();

        when(repository.findById("1")).thenReturn(Optional.of(mockAsset));
        Asset result = assetService.getAssetById("1");
        assertNotNull(result);
        assertEquals("Laptop Tes", result.getName());
        verify(repository, times(1)).findById("1");
    }
}