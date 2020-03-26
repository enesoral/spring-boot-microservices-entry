package com.enesoral.ratingsdataservice.models;

import java.util.Arrays;
import java.util.List;

public class UserRating {

    private String userId;
    private List<Rating> userRatings;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Rating> getUserRatings() {
        return userRatings;
    }

    public void setUserRatings(List<Rating> userRatings) {
        this.userRatings = userRatings;
    }

    public void initData(String userId) {
        this.setUserId(userId);
        this.setUserRatings(Arrays.asList(
                new Rating("1234", 3),
                new Rating("5678", 4)
        ));
    }
}
