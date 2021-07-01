package net.ufjnet.gestaobrapll.ExceptionHadler;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class StandardError {
	
	private Integer codigo;
	private LocalDateTime momento;
	private String descricao;
	private List<Fields> campos;
	
	@AllArgsConstructor
	@Getter
	@Setter
	public static class Fields {
		private String nome;
		private String mensagem;
	}
	

}
