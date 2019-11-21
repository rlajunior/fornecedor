package impa.br.fornecedor.controller;

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

import impa.br.fornecedor.dao.FornecedorRepository;
import impa.br.fornecedor.model.Fornecedor;

	@RestController
	@RequestMapping(value="/api")
	public class FornecedorController {
		

		@Autowired
		FornecedorRepository fornecedorRepository;
		
		@GetMapping("/fornecedores")
		public List<Fornecedor> listaFornecedores(){
			return fornecedorRepository.findAll();
			
		}	

		@PostMapping("/fornecedores")
		public Fornecedor salvaFornecedor(@RequestBody Fornecedor fornecedor) {
			return fornecedorRepository.save(fornecedor);
		}
		
		@DeleteMapping("/fornecedores")
		public void deletaFornecedor(@RequestBody Fornecedor fornecedor) {
			fornecedorRepository.delete(fornecedor);
		}
		
		@PutMapping("/fornecedores")
		public Fornecedor atualizaFornecedor(@RequestBody Fornecedor fornecedor) {
			return fornecedorRepository.save(fornecedor);
		}
		
}
