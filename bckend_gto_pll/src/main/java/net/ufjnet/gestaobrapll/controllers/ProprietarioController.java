package net.ufjnet.gestaobrapll.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import net.ufjnet.gestaobrapll.dtos.ProprietarioDTO;
import net.ufjnet.gestaobrapll.services.GestaoProprietario;

@Tag(name = "Endpoint de Proprietário") 
@AllArgsConstructor
@RestController
@RequestMapping("/gto/proprietarios/v1")
public class ProprietarioController {
	
	
	private GestaoProprietario service;
		
	@Operation(summary = "Busca todos os proprietários")
	@GetMapping
	public ResponseEntity<CollectionModel<ProprietarioDTO>> buscarTodos(
			@RequestParam(value="page", defaultValue = "0") int page,
			@RequestParam(value="limit", defaultValue = "12") int limit,
			@RequestParam(value="direction", defaultValue = "asc") String direction) {
		
		
			Direction sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
			
			Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "nome"));
			
			
			Page<ProprietarioDTO> result = service.findAll(pageable);
			result.stream()
					.forEach(p -> p.add(linkTo(methodOn(ProprietarioController.class).buscarUm(p.getCodigo())).withSelfRel()));
			
			return ResponseEntity.ok(CollectionModel.of(result));
	}
	
	
	@Operation(summary = "Busca um proprietário pelo código")
	@GetMapping("/{id}")
	public ResponseEntity<ProprietarioDTO> buscarUm(@PathVariable Integer id) {
		ProprietarioDTO objDTO = service.findById(id);
		objDTO.add(linkTo(methodOn(ProprietarioController.class).buscarUm(id)).withSelfRel());
		return ResponseEntity.ok(objDTO);
		
	}
	
	
	@Operation(summary = "Busca um proprietário pelo nome")
	@GetMapping("/nome/{nome}")
	public ResponseEntity<ProprietarioDTO> buscarNome(@PathVariable String nome) {
		ProprietarioDTO objDTO = service.findByNome(nome);
		objDTO.add(linkTo(methodOn(ProprietarioController.class).buscarUm(objDTO.getCodigo())).withSelfRel());
		return ResponseEntity.ok(objDTO);
	}
	
	@Operation(summary = "Busca um proprietário pelo cpf")
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<ProprietarioDTO> buscarCPF(@PathVariable String cpf) {
		ProprietarioDTO objDTO = service.findByCpf(cpf);
		objDTO.add(linkTo(methodOn(ProprietarioController.class).buscarUm(objDTO.getCodigo())).withSelfRel());
		return ResponseEntity.ok(objDTO);
		
	}
	
	@Operation(summary = "Busca um proprietário pelo e-mail")
	@GetMapping("/email/{email}")
	public ResponseEntity<ProprietarioDTO> buscarEMAIL(@PathVariable String email) {
		ProprietarioDTO objDTO = service.findByEmail(email);
		objDTO.add(linkTo(methodOn(ProprietarioController.class).buscarUm(objDTO.getCodigo())).withSelfRel());
		return ResponseEntity.ok(objDTO);

	}
	
	
	
	@Operation(summary = "Insere um novo proprietário")
	@PostMapping
	public ResponseEntity<ProprietarioDTO> incluir(@Valid @RequestBody ProprietarioDTO obj) {
		ProprietarioDTO objDTO = service.save(obj);
		objDTO.add(linkTo(methodOn(ProprietarioController.class).buscarUm(objDTO.getCodigo())).withSelfRel());
		return ResponseEntity.created(null).body(objDTO);
	}
	
	@Operation(summary = "Atualiza um proprietário")
	@PutMapping
	public ResponseEntity<ProprietarioDTO> atualizar(@RequestBody ProprietarioDTO objBody) {
		
		ProprietarioDTO objDTO = service.update(objBody);
		objDTO.add(linkTo(methodOn(ProprietarioController.class).buscarUm(objDTO.getCodigo())).withSelfRel());
		
		return ResponseEntity.ok(objDTO);
		
	}
	
	@Operation(summary = "Exclui um proprietário por id")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir (@PathVariable Integer id) {
		
		if (!service.existById(id)) {
			return ResponseEntity.notFound().build();
		}
		 
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	

}
