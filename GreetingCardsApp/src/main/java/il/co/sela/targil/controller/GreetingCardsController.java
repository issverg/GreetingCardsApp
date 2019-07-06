package il.co.sela.targil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import il.co.sela.targil.dto.CardInputDto;
import il.co.sela.targil.dto.CardOutputDto;
import il.co.sela.targil.dto.TemplateInputDto;
import il.co.sela.targil.service.cards.ICardsService;

@RestController
@RequestMapping("/greetingCard")
public class GreetingCardsController {
	@Autowired
	ICardsService cardService;
	
	@GetMapping("/catalog")
	public List<CardOutputDto> getCatalog() {
		return cardService.getCatalog();
	}
	
	@GetMapping("/templates")
	public List<CardOutputDto> getMyTemplates() {
		return cardService.getMyTemplates();
	}

	@GetMapping("/cards")
	public List<CardOutputDto> getMyCards() {
		return cardService.getMyCards();
	}

	@PostMapping("/byTemplate/{id}")
	public CardOutputDto createCardByTemplate(@RequestBody CardInputDto cardInputDto, @PathVariable String id) {
		return cardService.createCardByTemplate(cardInputDto, id);
	}

	@PostMapping("/newTemplate")
	public CardOutputDto createNewTemplate(@RequestBody TemplateInputDto templateInputDto) {
		return cardService.createNewTemplate(templateInputDto);
	}

	@PutMapping("/newCard")
	public CardOutputDto createNewCard(@RequestBody CardInputDto cardInputDto) {
		return cardService.createNewCard(cardInputDto);
	}
	
	@DeleteMapping("remove/{id}")
	public boolean removeCard(@PathVariable String id) {
		return cardService.removeCard(id);
	}
}