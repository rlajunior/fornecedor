package impa.br.fornecedor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

}
