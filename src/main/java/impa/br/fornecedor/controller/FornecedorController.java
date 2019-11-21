package impa.br.fornecedor.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import impa.br.fornecedor.model.Fornecedor;
import impa.br.fornecedor.dao.FornecedorRepository;

@RestController
public class FornecedorController {
	
	@Autowired
	private FornecedorRepository repository;
	
	
	@RequestMapping(
			value = "/fornecedores",
			method = RequestMethod.GET,
			produces = "application/json"
			)
	public ResponseEntity<List<Fornecedor>> obterTodos(){
		List<Fornecedor> fornecedores = repository.findAll();
		
		return new ResponseEntity<>(fornecedores, HttpStatus.OK);
	}

	@RequestMapping(
			value = "/fornecedores/{id}",
			method = RequestMethod.GET,
			produces = "application/json"
			)
	public ResponseEntity<Fornecedor>
		obterPeloId(@PathVariable(value = "id") Integer id){		
		
		Optional<Fornecedor> fornecedor = repository.findById(id);
		
		if (!fornecedor.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(fornecedor.get(), HttpStatus.OK);
		}

	}

	
	@ApiOperation(value = "Grava um fornecedor na base")
	@ApiResponses(
				value = {
						@ApiResponse(code = 200, message = "Retorna o fornecedor gravado"),
						@ApiResponse(code = 404, message = "Nenhum fornecedor gravado")
				}
			)
	@RequestMapping(
			value = "/fornecedores",
			method = RequestMethod.POST,
			produces = "application/json",
			consumes = "application/json"
			)
	public ResponseEntity<Fornecedor> salvar(@RequestBody Fornecedor fornecedor){
		return new ResponseEntity<>(repository.save(fornecedor), HttpStatus.CREATED);
	}
	
	@RequestMapping( 
			value = "/fornecedores/{id}",
			method = RequestMethod.PUT,
			produces = "application/json",
			consumes = "application/json"
			)
	public ResponseEntity<Fornecedor> 
		editar(@PathVariable(value = "id") Integer id, 
			   @RequestBody Fornecedor fornecedor){
		
		if (fornecedor == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			Fornecedor forncedorEditado = repository.saveAndFlush(fornecedor);
			return new ResponseEntity<>(forncedorEditado, HttpStatus.OK);
		}
	}
	
	@RequestMapping(
			value = "/fornecedores/{id}",
			method = RequestMethod.DELETE,
			produces = "application/json"
			)
	public ResponseEntity<Fornecedor>
		deletarPeloId(@PathVariable(value = "id") Integer id){		
		
		try {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
	public FornecedorRepository getDao() {
		
		return repository;
	}


	public void setDao(FornecedorRepository dao) {
		this.repository = dao;
	}

}
