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

import il.co.sela.targil.dto.CardFromTemplateInputDto;
import il.co.sela.targil.dto.CardInputDto;
import il.co.sela.targil.dto.CardOutputDto;
import il.co.sela.targil.dto.TemplateInputDto;
import il.co.sela.targil.service.cards.ICardsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(description = "REST API Controller for methods interacting with cards.")
@RestController
@RequestMapping("/greetingCard")
public class GreetingCardsController {
	@Autowired
	ICardsService cardService;
	
	@ApiOperation("Getting list of card templates. Public free access method.")
	@GetMapping("/catalog")
	public List<CardOutputDto> getCatalog() {
		return cardService.getCatalog();
	}
	
	@ApiOperation("Getting list of card templates of the user. Token access method.")
	@GetMapping("/templates")
	public List<CardOutputDto> getMyTemplates() {
		return cardService.getMyTemplates();
	}

	@ApiOperation("Getting list of cards of the user. Token access method.")
	@GetMapping("/cards")
	public List<CardOutputDto> getMyCards() {
		return cardService.getMyCards();
	}

	@ApiOperation("Creating card by given template. Token access method.")
	@PostMapping("/byTemplate/{id}")
	public CardOutputDto createCardByTemplate(
			@ApiParam("Data transfer object with fields filled on the client side according given template.")
			@RequestBody CardFromTemplateInputDto cardFromTemplateInputDto, 
			@ApiParam("Id of template.")
			@PathVariable String id) {
		return cardService.createCardByTemplate(cardFromTemplateInputDto, id);
	}

	@ApiOperation("Creating new template. Token access method.")
	@PostMapping("/newTemplate")
	public CardOutputDto createNewTemplate(
			@ApiParam("Data transfer object for the template to be created.")
			@RequestBody TemplateInputDto templateInputDto) {
		return cardService.createNewTemplate(templateInputDto);
	}

	@ApiOperation("Creating new card from scratch. Token access method.")
	@PutMapping("/newCard")
	public CardOutputDto createNewCard(
			@ApiParam("Data transfer object for the card to be created.")
			@RequestBody CardInputDto cardInputDto) {
		return cardService.createNewCard(cardInputDto);
	}
	
	@ApiOperation("Removing card by id. Token access method.")
	@DeleteMapping("remove/{id}")
	public boolean removeCard(
			@ApiParam("Id of the card to be removed.")
			@PathVariable String id) {
		return cardService.removeCard(id);
	}
}