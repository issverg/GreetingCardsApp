package il.co.sela.targil.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import il.co.sela.targil.domain.GreetingCard;

@Repository
public interface GreetingCardsRepository extends MongoRepository<GreetingCard, Long> {

}