package il.co.sela.targil.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import il.co.sela.targil.domain.GreetingCard;

public interface GreetingCardsRepository extends MongoRepository<GreetingCard, Long> {

}