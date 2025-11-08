package autocomplete;

import strategy.FrequencyBasedRanking;
import strategy.RankingStrategy;

public class AutocompleteSystemBuilder {
    private RankingStrategy rankingStrategy = new FrequencyBasedRanking(); // Default strategy
    private int maxSuggestions = 10; // Default limit

    public AutocompleteSystemBuilder withRankingStrategy(RankingStrategy strategy) {
        this.rankingStrategy = strategy;
        return this;
    }

    public AutocompleteSystemBuilder withMaxSuggestions(int max) {
        this.maxSuggestions = max;
        return this;
    }

    public AutocompleteSystem build() {
        return new AutocompleteSystem(rankingStrategy, maxSuggestions);
    }
}
