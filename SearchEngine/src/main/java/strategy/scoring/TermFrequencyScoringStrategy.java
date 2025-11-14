package strategy.scoring;

import entities.Document;
import entities.Posting;

public class TermFrequencyScoringStrategy implements ScoringStrategy {
    @Override
    public double calculateScore(String term, Posting posting, Document document) {
        // The simplest strategy: score is just the term frequency.
        return posting.getFrequency();
    }
}