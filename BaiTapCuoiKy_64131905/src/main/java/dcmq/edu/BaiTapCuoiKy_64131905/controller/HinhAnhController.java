package dcmq.edu.BaiTapCuoiKy_64131905.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

@Controller
public class HinhAnhController {

    private final String imageDir = System.getProperty("user.dir") + "/src/main/resources/static/images/";

    @GetMapping("/images/{tenFile:.+}")
    public ResponseEntity<Resource> hienThiHinhAnh(@PathVariable String tenFile) {
        try {
            Path filePath = Paths.get(imageDir).resolve(tenFile).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() && resource.isReadable()) {
                String contentType = Files.probeContentType(filePath);
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_TYPE, contentType != null ? contentType : "application/octet-stream")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}