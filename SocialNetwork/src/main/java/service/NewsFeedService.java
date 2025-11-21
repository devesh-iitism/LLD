package service;

import entities.Post;
import entities.User;
import strategy.ChronologicalStrategy;
import strategy.NewsFeedGenerationStrategy;

import java.util.List;

public class NewsFeedService {
    private NewsFeedGenerationStrategy strategy;

    public NewsFeedService() {
        this.strategy = new ChronologicalStrategy(); // Default strategy
    }

    public void setStrategy(NewsFeedGenerationStrategy strategy) {
        this.strategy = strategy;
    }

    public List<Post> getNewsFeed(User user) {
        return strategy.generateFeed(user);
    }
}