package dcmq.edu.BaiTapCuoiKy_64131905.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Phục vụ ảnh cũ trong static/images
    	registry.addResourceHandler("/uploads/img/**")
        .addResourceLocations("file:" + System.getProperty("user.dir") + "/uploads/img/");


        registry.addResourceHandler("/uploads/images/**")
        .addResourceLocations("file:uploads/images/");

    }
}
