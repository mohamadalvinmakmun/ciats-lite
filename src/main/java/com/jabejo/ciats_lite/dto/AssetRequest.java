package com.jabejo.ciats_lite.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AssetRequest {
    @NotBlank(message = "Nama asset wajib diisi")
    private String name;

    @NotBlank(message = "Kategori wajib diisi")
    private String category;

    private String status;
}
