package il.co.sela.targil.service.cards;

import java.util.List;

import il.co.sela.targil.dto.CardFromTemplateInputDto;
import il.co.sela.targil.dto.CardInputDto;
import il.co.sela.targil.dto.CardOutputDto;
import il.co.sela.targil.dto.TemplateInputDto;

public interface ICardsService {
	
	List<CardOutputDto> getCatalog();
	
	List<CardOutputDto> getMyTemplates();
	
	List<CardOutputDto> getMyCards();
	
	CardOutputDto createCardByTemplate(CardFromTemplateInputDto cardFromTemplateInputDto, String id);
	
	CardOutputDto createNewTemplate(TemplateInputDto templateInputDto);
	
	CardOutputDto createNewCard(CardInputDto cardInputDto);
	
	boolean removeCard(String id);
}