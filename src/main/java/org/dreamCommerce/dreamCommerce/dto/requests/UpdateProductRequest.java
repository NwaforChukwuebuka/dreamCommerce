package org.dreamCommerce.dreamCommerce.dto.requests;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Data
public class UpdateProductRequest {
    private String name;
    private String description;
    private String price;
    private List<MultipartFile> images;
}
