package de.haw_hamburg.mensamatch.adapter.api.recommend;

import de.haw_hamburg.mensamatch.adapter.api.meal.model.MensaMealDto;
import de.haw_hamburg.mensamatch.domain.recommend.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/recommend")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @RequestMapping(method = RequestMethod.GET, value = "", produces = "application/json")
    public List<MensaMealDto> getRecommendations(Authentication authentication) {
        return recommendationService.recommend(authentication.getName()).stream().map(MensaMealDto::from).toList();
    }

}
