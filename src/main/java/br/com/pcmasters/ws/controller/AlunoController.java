package br.com.pcmasters.ws.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.pcmasters.ws.model.Aluno;
import br.com.pcmasters.ws.service.AlunoService;

@RestController
public class AlunoController {

	
	@Autowired
	AlunoService alunoService;
	
	
	@RequestMapping(method=RequestMethod.POST, value="/alunos", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Aluno> cadastrarAluno(@RequestBody Aluno aluno) {		
		Aluno alunoCadastrado = alunoService.cadastrar(aluno);		
		return new ResponseEntity<Aluno>(alunoCadastrado, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/alunos", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Aluno>> buscarTodosAlunos() {		
		Collection<Aluno>alunosBuscados = alunoService.buscarTodos();		
		return new ResponseEntity<>(alunosBuscados, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/alunos/{id}")
	public ResponseEntity<Aluno> excluirAluno(@PathVariable  Integer id) {		
		
		Aluno alunoEncontrado = alunoService.buscarPorId(id);
		if(alunoEncontrado == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		alunoService.excluir(alunoEncontrado);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@RequestMapping(method=RequestMethod.PUT, value="/alunos", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Aluno> alterarAluno(@RequestBody Aluno aluno) {		
		
		Aluno alunoAlterado = alunoService.alterar(aluno);		
		
		return new ResponseEntity<>(alunoAlterado, HttpStatus.OK);
	}
	
}
