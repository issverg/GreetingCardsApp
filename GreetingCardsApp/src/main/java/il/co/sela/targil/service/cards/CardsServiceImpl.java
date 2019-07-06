package il.co.sela.targil.service.cards;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import il.co.sela.targil.dao.GreetingCardsRepository;
import il.co.sela.targil.dao.UsersRepository;
import il.co.sela.targil.domain.Client;
import il.co.sela.targil.domain.GreetingCard;
import il.co.sela.targil.dto.CardInputDto;
import il.co.sela.targil.dto.CardOutputDto;
import il.co.sela.targil.dto.TemplateInputDto;
import il.co.sela.targil.exceptions.NotFoundException;

@Service
public class CardsServiceImpl implements ICardsService {
	@Autowired
	GreetingCardsRepository cardsRepo;
	@Autowired
	UsersRepository usersRepo;
	@Autowired
	MongoTemplate template;

	@Override
	public List<CardOutputDto> getCatalog() {
		Query query = new Query();
		query.addCriteria(Criteria.where("isPublic").is(true));
		List<GreetingCard> cards = template.find(query, GreetingCard.class);
		
		return convertListOfCardsToListOfCardDtos(cards);
	}
	
	@Override
	public List<CardOutputDto> getMyTemplates() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		Query query = new Query();
		query.addCriteria(Criteria.where("owner").is(email).and("isTemplate").is(true));
		List<GreetingCard> cards = template.find(query, GreetingCard.class);
		
		return convertListOfCardsToListOfCardDtos(cards);
	}

	@Override
	public List<CardOutputDto> getMyCards() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		Query query = new Query();
		query.addCriteria(Criteria.where("owner").is(email).and("isTemplate").is(false));
		List<GreetingCard> cards = template.find(query, GreetingCard.class);
		
		return convertListOfCardsToListOfCardDtos(cards);
	}

	@Override
	public CardOutputDto createCardByTemplate(CardInputDto cardInputDto, String id) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		GreetingCard card = cardsRepo.findById(id).get();
		GreetingCard newCard = GreetingCard.builder()
								.id(card.getId())
								.title(card.getTitle())
								.enteringWord(card.getEnteringWord())
								.prefix(cardInputDto.getPrefix())
								.firstName(cardInputDto.getFirstName())
								.lastName(cardInputDto.getLastName())
								.celebration(card.getCelebration())
								.wishes(cardInputDto.getWishes())
								.endingWord(card.getEndingWord())
								.signature(cardInputDto.getSignature())
								.backgroundImageUrl(card.getBackgroundImageUrl())
								.owner(email)
								.isTemplate(false)
								.isPublic(cardInputDto.isPublic())
								.build();
		cardsRepo.save(newCard);
		
		return convertCardToCardDto(newCard);
	}

	@Override
	public CardOutputDto createNewTemplate(TemplateInputDto templateInputDto) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		GreetingCard card = GreetingCard.builder()
								.title(templateInputDto.getTitle())
								.enteringWord(templateInputDto.getEnteringWord())
								.celebration(templateInputDto.getCelebration())
								.endingWord(templateInputDto.getEndingWord())
								.backgroundImageUrl(templateInputDto.getBackgroundImageUrl())
								.isPublic(templateInputDto.isPublic())
								.isTemplate(true)
								.owner(email)
								.build();
		card = cardsRepo.save(card);
		
		Client client = usersRepo.findById(email).get();
		client.getMyTemplates().add(card.getId());
		usersRepo.save(client);
		
		return convertCardToCardDto(card);
	}

	@Override
	public CardOutputDto createNewCard(CardInputDto cardInputDto) {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		GreetingCard card = GreetingCard.builder()
								.title(cardInputDto.getTitle())
								.enteringWord(cardInputDto.getEnteringWord())
								.prefix(cardInputDto.getPrefix())
								.firstName(cardInputDto.getFirstName())
								.lastName(cardInputDto.getLastName())
								.celebration(cardInputDto.getCelebration())
								.wishes(cardInputDto.getWishes())
								.endingWord(cardInputDto.getEndingWord())
								.signature(cardInputDto.getSignature())
								.backgroundImageUrl(cardInputDto.getBackgroundImageUrl())
								.owner(email)
								.isTemplate(false)
								.isPublic(cardInputDto.isPublic())
								.build();
		card = cardsRepo.save(card);
		
		Client client = usersRepo.findById(email).get();
		client.getMyCards().add(card.getId());
		usersRepo.save(client);
		
		return convertCardToCardDto(card);
	}
	
	private List<CardOutputDto> convertListOfCardsToListOfCardDtos(List<GreetingCard> cards) {
		List<CardOutputDto> list = new ArrayList<>();
		for (GreetingCard card : cards) {
			list.add(convertCardToCardDto(card));
		}
		
		return list;
	}

	private CardOutputDto convertCardToCardDto(GreetingCard card) {
		return new CardOutputDto(card.getId(), card.getTitle(), card.getEnteringWord(), card.getPrefix(),
								 card.getFirstName(), card.getLastName(), card.getCelebration(),
								 card.getWishes(), card.getEndingWord(), card.getSignature(), card.getBackgroundImageUrl());
	}

	@Override
	public boolean removeCard(String id) {
		GreetingCard card = cardsRepo.findById(id).orElseThrow(() -> 
				new NotFoundException("There is no greeting card with such id: " + id));
		Client client = usersRepo.findById(SecurityContextHolder.getContext().getAuthentication().getName()).get();
		
		if (!(card.isPublic() && card.isTemplate()) || client.getRoles().contains("ROLE_ADMIN")) {
			cardsRepo.delete(card);
			return true;
		}
		
		return false;
	}
}