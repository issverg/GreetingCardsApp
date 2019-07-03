package il.co.sela.targil.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import il.co.sela.targil.domain.Client;

public interface UsersRepository extends MongoRepository<Client, String> {

}