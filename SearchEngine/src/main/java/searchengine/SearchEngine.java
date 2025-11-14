package searchengine;

import entities.*;
import strategy.ranking.RankingStrategy;
import strategy.scoring.ScoringStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchEngine {
    private static SearchEngine instance;
    private final InvertedIndex invertedIndex;
    private final DocumentStore documentStore;
    private ScoringStrategy scoringStrategy;
    private RankingStrategy rankingStrategy;

    private SearchEngine() {
        this.invertedIndex = new InvertedIndex();
        this.documentStore = new DocumentStore();
    }

    public static synchronized SearchEngine getInstance() {
        if(instance == null) {
            instance = new SearchEngine();
        }
        return instance;
    }

    public void setScoringStrategy(ScoringStrategy scoringStrategy) {
        this.scoringStrategy = scoringStrategy;
    }

    public void setRankingStrategy(RankingStrategy rankingStrategy) {
        this.rankingStrategy = rankingStrategy;
    }

    public void indexDocuments(List<Document> documents) {
        for (Document doc : documents) {
            indexDocument(doc);
        }
    }

    public void indexDocument(Document doc) {
        documentStore.addDocument(doc);
        Map<String, Integer> termFrequencies = new HashMap<>();

        // Tokenize title and content. Split by non-word characters.
        String text = (doc.getTitle() + " " + doc.getContent()).toLowerCase();
        String[] tokens = text.split("\\W+");

        // Calculate term frequencies for the current document.
        for (String token : tokens) {
            if (!token.isEmpty()) {
                termFrequencies.merge(token, 1, Integer::sum);
            }
        }

        // Add each term and its frequency to the inverted index.
        for (Map.Entry<String, Integer> entry : termFrequencies.entrySet()) {
            invertedIndex.add(entry.getKey(), doc.getId(), entry.getValue());
        }
    }

    public List<SearchResult> search(String query) {
        String processedQuery = query.toLowerCase();

        // 1. Get postings from the inverted index for the query term.
        List<Posting> postings = invertedIndex.getPostings(processedQuery);

        // 2. Map postings to SearchResult objects.
        List<SearchResult> results = new ArrayList<>();
        for (Posting posting : postings) {
            Document doc = documentStore.getDocument(posting.getDocumentId());
            if (doc != null) {
                double score = scoringStrategy.calculateScore(processedQuery, posting, doc);
                results.add(new SearchResult(doc, score));
            }
        }

        rankingStrategy.rank(results);

        return results;
    }
}
