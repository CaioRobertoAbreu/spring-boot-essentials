package academy.devdojo.springboot2.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class PaginationConfigurer implements WebMvcConfigurer {

    private static final String NOME_FILME = "nome";
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        var pagination = new PageableHandlerMethodArgumentResolver();
        pagination.setFallbackPageable(PageRequest.of(0, 5, Sort.Direction.ASC, NOME_FILME));
        resolvers.add(pagination);
    }
}
