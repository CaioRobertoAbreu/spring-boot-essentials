package academy.devdojo.springboot2.client;

import academy.devdojo.springboot2.domain.AtualizaFilmeRequest;
import academy.devdojo.springboot2.domain.CadastraFilmeRequest;
import academy.devdojo.springboot2.domain.Filme;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Log4j2
public class SpringClient {
    public static void main(String[] args) {

        final var URL = "http://localhost:8080/filmes";
        final var FIND_BY_ID_OR_DELETE = "http://localhost:8080/filmes/{id}";
        final var LIST_ALL = "http://localhost:8080/filmes/listAll";

        try{
            restTemplateGetForEntity(FIND_BY_ID_OR_DELETE);
            restTemplateGetForObject(FIND_BY_ID_OR_DELETE);
            restTemplateGetExchange(LIST_ALL);
//            restTemplatePostForEntity(URL);
//            restTemplatePostForObject(URL);
//            restTemplatePostExchange(URL);
            restTemplatePut(URL);
            restTemplatePutExchange(URL);
            restTemplateDelete(FIND_BY_ID_OR_DELETE, URL);
            restTemplateDeleteExchange(FIND_BY_ID_OR_DELETE, URL);

        }catch (Exception ex){
            log.error("Um erro ocorreu ao realizar alguma das requisicoes.\n{}, {}, {}",
                    URL, FIND_BY_ID_OR_DELETE, LIST_ALL);
        }


    }

    private static void restTemplateGetForEntity(String url){
        //Returns Entity
        log.info("========ENTITY===========");
        var getForEnitty = new RestTemplate().getForEntity(url, Filme.class, 2);
        log.info(getForEnitty);
    }

    private static void restTemplateGetForObject(String url){
        //Returns Object
        log.info("========OBJECT===========");
        var getForObject = new RestTemplate().getForObject(url, Filme.class, 2);
        log.info(getForObject);
    }

    private static void restTemplateGetExchange(String url){
        //Can be returns list or other object
        log.info("========LIST-OBJECTS===========");
        var filmesList =  new RestTemplate().exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Filme>>() {} );
        log.info(filmesList);
    }

    private static void restTemplatePostForEntity(String url){
        log.info("========POST===========");
        var requestObject = new CadastraFilmeRequest("It - A coisa");
        var postForEntity = new RestTemplate().postForEntity(url, requestObject, Filme.class);
        log.info(postForEntity);
    }

    private static void restTemplatePostForObject(String url){
        log.info("========POST===========");
        var requestObject = new CadastraFilmeRequest("It - A coisa parte 2");
        var postForObject = new RestTemplate().postForObject(url, requestObject, Filme.class);
        log.info(postForObject);
    }

    private static void restTemplatePostExchange(String url){
        log.info("========POST===========");
        var requestObject = new CadastraFilmeRequest("Cruela");
        var postForObject = new RestTemplate().exchange(url, HttpMethod.POST, new HttpEntity<>(requestObject),
                                                                            Filme.class);
        log.info(postForObject);
    }

    private static void restTemplatePut(String url){
        log.info("========PUT===========");
        var requestObject = new AtualizaFilmeRequest(26L, "It - A coisa 2");
        new RestTemplate().put(url, requestObject);
        log.info("Filme Atualizado com sucesso");
    }

    private static void restTemplatePutExchange(String url){
        log.info("========PUT===========");
        var requestObject = new AtualizaFilmeRequest(26L, "It - A coisa parte 2");
        var putExchange = new RestTemplate().exchange(url, HttpMethod.PUT, new HttpEntity<>(requestObject), Void.class);
        log.info(putExchange);
    }

    private static void restTemplateDelete(String urlDelete, String urlPost){
        var object = new RestTemplate()
                .postForObject(urlPost, new CadastraFilmeRequest("Filme para ser deletado"), Filme.class);

        Assert.notNull(object.getId(), "Id nao deveria ser nulo");

        log.info("========DELETE===========");
        new RestTemplate().delete(urlDelete, object.getId());
        log.info("Filme Deletado com sucesso");
    }

    private static void restTemplateDeleteExchange(String urlDelete, String urlPost){
        var object = new RestTemplate()
                .postForObject(urlPost, new CadastraFilmeRequest("Filme para ser deletado 2"), Filme.class);

        Assert.notNull(object.getId(), "Id nao deveria ser nulo");

        log.info("========DELETE===========");
        var deleteExchange = new RestTemplate().exchange(urlDelete, HttpMethod.DELETE, null, Void.class, object.getId());
        log.info(deleteExchange);
    }

}
