package br.com.pcmasters.ws.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pcmasters.ws.model.Aluno;
import br.com.pcmasters.ws.repository.AlunoRepository;

@Service
public class AlunoService {

	@Autowired
	AlunoRepository alunoRepository;
	
	public Aluno cadastrar(Aluno aluno) {		
		return alunoRepository.save(aluno);
	}
	
	public Collection<Aluno> buscarTodos(){
		return alunoRepository.findAll();
	}
	
	public void excluir (Aluno aluno) {
		alunoRepository.delete(aluno);
	}
	
	public Aluno buscarPorId (Integer id) {
		return alunoRepository.findOne(id);
	}
	
	public Aluno alterar(Aluno aluno) {
		return alunoRepository.save(aluno);
	}
	
}
