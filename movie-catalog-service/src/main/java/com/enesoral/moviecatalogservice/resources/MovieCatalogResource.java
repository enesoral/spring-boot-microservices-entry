package com.enesoral.moviecatalogservice.resources;

import com.enesoral.moviecatalogservice.models.CatalogItem;
import com.enesoral.moviecatalogservice.models.Movie;
import com.enesoral.moviecatalogservice.models.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    private final RestTemplate restTemplate;
    private final WebClient.Builder webClientBuilder;

    public MovieCatalogResource(RestTemplate restTemplate, WebClient.Builder webClientBuilder) {
        this.restTemplate = restTemplate;
        this.webClientBuilder = webClientBuilder;
    }

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 4),
                new Rating("5678", 3)
        );

        return ratings.stream().map(rating -> {
                //Movie movie = restTemplate.getForObject("localhost:8082/movies/" + rating.getMovieId(), Movie.class);
                Movie movie = webClientBuilder.build()
                        .get()
                        .uri("localhost:8082/movies/" + rating.getMovieId())
                        .retrieve()
                        .bodyToMono(Movie.class)
                        .block();
                return new CatalogItem(movie.getName(), "Test description", rating.getRating());
        }).collect(Collectors.toList());
    }
}
