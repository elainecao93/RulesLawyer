package service;

import contract.cards.Card;
import contract.searchRequests.CardSearchRequest;
import contract.searchResults.SearchResult;
import repository.SearchRepository;

import java.util.List;

import static ingestion.card.JsonCardIngestionService.getCards;
import static java.util.stream.Collectors.toList;

public class RawCardSearchService {

    SearchRepository<Card> repository;

    public RawCardSearchService() {
        repository = new SearchRepository<>(getCards());
    }

    public List<Card> getCardsWithOracleFallback(CardSearchRequest request) {
        List<SearchResult<Card>> cardSearchResults = repository.getSearchResult(request.includeOracle());
        return cardSearchResults.stream()
                .map(SearchResult::getEntry)
                .collect(toList());
    }

    //TODO more of this
}
