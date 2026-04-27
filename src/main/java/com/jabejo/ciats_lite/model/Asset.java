package com.jabejo.ciats_lite.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "assets")
public class Asset implements Serializable {
    @Id
    private String id;

    @NotBlank(message = "Nama aset tidak boleh kosong!")
    @Size(min = 3, message = "Nama aset minimal 3 karakter")
    private String name;

    @NotBlank(message = "Kategori tidak boleh kosong!")
    private String category;
    private AssetStatus status;
}
