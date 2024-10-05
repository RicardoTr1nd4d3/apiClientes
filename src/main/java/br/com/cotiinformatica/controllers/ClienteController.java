package br.com.cotiinformatica.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.dtos.ClienteRequestDto;
import br.com.cotiinformatica.entities.Cliente;
import br.com.cotiinformatica.repositories.ClienteRepository;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

	@PostMapping
	public String post(@RequestBody ClienteRequestDto request) throws Exception {

		var cliente = new Cliente();

		cliente.setId(UUID.randomUUID());
		cliente.setNome(request.getNome());
		cliente.setCpf(request.getCpf());
		cliente.setTelefone(request.getTelefone());
		cliente.setEmail(request.getEmail());

		var clienteRepository = new ClienteRepository();

		if (!clienteRepository.isExists(cliente.getCpf(), cliente.getId())) {
			clienteRepository.create(cliente);
			return "Cliente cadastrado com sucesso.";
		} else {
			return "O CPF '" + cliente.getCpf() + "' informado já está cadastrado para outro cliente. Tente novamente.";
		}

	}

	@PutMapping("{id}")
	public String put(@PathVariable UUID id, @RequestBody ClienteRequestDto request) throws Exception {

		var clienteRepository = new ClienteRepository();
		var cliente = clienteRepository.getById(id);

		if (cliente != null) {

			cliente.setNome(request.getNome());
			cliente.setCpf(request.getCpf());
			cliente.setTelefone(request.getTelefone());
			cliente.setEmail(request.getEmail());

			if (!clienteRepository.isExists(cliente.getCpf(), cliente.getId())) {
				clienteRepository.update(cliente);
				return "Cliente atualizado com sucesso.";
			} else {
				return "Não é possível atualizar os dados pois o CPF '" + cliente.getCpf()
						+ "' já pertence a outro cliente.";
			}

		} else {
			return "Cliente nao encontrado. Verifique o Id";
		}

	}

	@DeleteMapping("{id}")
	public String delete(@PathVariable UUID id) throws Exception {

		var clienteRepository = new ClienteRepository();
		var cliente = clienteRepository.getById(id);

		if (cliente != null) {

			clienteRepository.delete(id);

			return "Cliente excluido com sucesso !";

		} else {
			return "Cliente nao encontrado. Verifique o Id";
		}

	}

	@GetMapping
	public List<Cliente> get() throws Exception {

		var clienteRepository = new ClienteRepository();
		return clienteRepository.getAll();
	}
	
	@GetMapping("{id}")
	public Cliente getById(@PathVariable UUID id) throws Exception {
		
		//instanciando o repositório e retornando a consulta de cliente
		var clienteRepository = new ClienteRepository();
		return clienteRepository.getById(id);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
