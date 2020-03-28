package com.enesoral.moviecatalogservice.resources;

import com.enesoral.moviecatalogservice.models.CatalogItem;
import com.enesoral.moviecatalogservice.models.Movie;
import com.enesoral.moviecatalogservice.models.UserRating;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    private final RestTemplate restTemplate;

    public MovieCatalogResource(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/{userId}")
    @HystrixCommand(fallbackMethod = "getFallbackCatalog")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        UserRating userRating = restTemplate.getForObject("http://ratings-data-service/ratings/user/" + userId, UserRating.class);

        return userRating.getUserRatings().stream()
                .map(rating -> {
                    Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
                    return new CatalogItem(movie.getTitle(), movie.getOverview(), rating.getRating());
                })
                .collect(Collectors.toList());
    }

    private List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId) {
        return Collections.singletonList(new CatalogItem("No movie", "No description", 0));
    }
}


/* Alternative way to call api
Movie movie = webClientBuilder.build()
                        .get()
                        .uri("localhost:8082/movies/" + rating.getMovieId())
                        .retrieve()
                        .bodyToMono(Movie.class)
                        .block();
*/