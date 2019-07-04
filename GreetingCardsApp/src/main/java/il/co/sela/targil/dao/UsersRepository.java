package il.co.sela.targil.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import il.co.sela.targil.domain.Client;

@Repository
public interface UsersRepository extends MongoRepository<Client, String> {

}