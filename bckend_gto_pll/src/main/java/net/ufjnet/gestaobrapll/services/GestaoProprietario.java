package net.ufjnet.gestaobrapll.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.ufjnet.gestaobrapll.dtos.ProprietarioDTO;
import net.ufjnet.gestaobrapll.models.Proprietario;
import net.ufjnet.gestaobrapll.repositories.ProprietarioDAO;
import net.ufjnet.gestaobrapll.services.exception.BusinessException;

@Service
public class GestaoProprietario {
	
	@Autowired
	private ProprietarioDAO dao;
	
	@Transactional(readOnly = true)
	public Page<ProprietarioDTO> findAll(Pageable pageable) {
		Page<Proprietario> result = dao.findAll(pageable);
		return result.map(obj -> new ProprietarioDTO(obj));
		
	}
	
	
	@Transactional(readOnly = true)
	public ProprietarioDTO findById(Integer id) {
		Proprietario result = dao.findById(id)
				.orElseThrow(() -> new BusinessException("Proprietário não encontrado!"));
						
			return new ProprietarioDTO(result);
	}
	
	@Transactional(readOnly = true)
	public ProprietarioDTO findByNome(String nome) {
		Proprietario result = dao.findByNome(nome)
				.orElseThrow(() -> new BusinessException("Proprietário não encontrado!"));
		
		return new ProprietarioDTO(result);
				
	}
	
	@Transactional(readOnly = true)
	public ProprietarioDTO findByCpf(String cpf) {
		Proprietario result = dao.findByCpf(cpf)
				.orElseThrow(() -> new BusinessException("Proprietário não encontrado!"));
		
		return new ProprietarioDTO(result);
	}
	
	@Transactional(readOnly = true)
	public ProprietarioDTO findByEmail(String email) {
		Proprietario result = dao.findByEmail(email)
				.orElseThrow(() -> new BusinessException("Proprietário não encontrado!"));
		
		return new ProprietarioDTO(result);
	}
	
	
	@Transactional
	public ProprietarioDTO save(ProprietarioDTO obj) {
		
		Proprietario entity = new Proprietario(obj.getCodigo(),obj.getNome(),obj.getCpf(),obj.getEmail());
		
		boolean cpfExists = dao.findByCpf(obj.getCpf())
							.stream()
							.anyMatch(objResult -> !objResult.equals(entity));
		
		if (cpfExists) {
			throw new BusinessException("CPF já existente!");
		}
		
		boolean emailExists = dao.findByEmail(obj.getEmail())
				.stream()
				.anyMatch(objResult -> !objResult.equals(entity));

		if (emailExists) {
		throw new BusinessException("E-mail já existente, preste atenção!!!");
		}

		
		
		return new ProprietarioDTO(dao.save(entity));
		
	}
	
	
	@Transactional
	public ProprietarioDTO update(ProprietarioDTO obj) {
		Proprietario entity = dao.findById(obj.getCodigo())
				.orElseThrow(() -> new BusinessException("Proprietário não encontrado!"));
		
		entity.setNome(obj.getNome());
		entity.setCpf(obj.getCpf());
		entity.setEmail(obj.getEmail());
		
		return new ProprietarioDTO(dao.save(entity));
		
	}

	
	
	@Transactional
	public void deleteById(Integer id) {
		dao.deleteById(id);
	}
	
	public boolean existById(Integer id) {
		return dao.existsById(id);
	}
	

}
