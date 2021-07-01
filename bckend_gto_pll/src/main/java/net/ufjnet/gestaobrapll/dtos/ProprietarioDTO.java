package net.ufjnet.gestaobrapll.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.ufjnet.gestaobrapll.models.Proprietario;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper=false, onlyExplicitlyIncluded = true)
@JsonPropertyOrder({"codigo_proprietario", "nome_proprietario", "email_proprietario", "cpf_proprietario"})
public class ProprietarioDTO extends RepresentationModel<ProprietarioDTO> implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("codigo_proprietario")
	@EqualsAndHashCode.Include
	private Integer codigo;
	
	@JsonProperty("nome_proprietario")
	@NotBlank
	@Size(max=60)
	private String nome;
	
	@JsonIgnore
	//@JsonProperty("cpf_proprietario")
	@NotBlank
	private String cpf;
	
	@JsonProperty("email_proprietario")
	@NotBlank
	@Email
	private String email;
	
	public ProprietarioDTO (Proprietario obj) {
		this.codigo = obj.getCodigo();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		
	}
	
}
