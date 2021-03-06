package net.ufjnet.gestaobrapll.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name="PROPRIETARIOS")

public class Proprietario {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo_prop")
	private Integer codigo;
	
	@Column(name="nome_prop", nullable = false)
	private String nome;
	
	
	@Column(name="cpf_prop", nullable = false)
	private String cpf;
	
	
	@Column(name="email_prop", nullable = false)
	private String email;

	

}
