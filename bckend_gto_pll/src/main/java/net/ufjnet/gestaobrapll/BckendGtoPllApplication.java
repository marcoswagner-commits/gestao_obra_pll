package net.ufjnet.gestaobrapll;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import net.ufjnet.gestaobrapll.models.Proprietario;
import net.ufjnet.gestaobrapll.repositories.ProprietarioDAO;

@EnableAutoConfiguration  
@ComponentScan
@SpringBootApplication
public class BckendGtoPllApplication implements CommandLineRunner {

	@Autowired
	private ProprietarioDAO propDAO;
	
	public static void main(String[] args) {
		SpringApplication.run(BckendGtoPllApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Proprietario p1 = new Proprietario(1,"Arthur","123","arthur@gmail.com");
		Proprietario p2 = new Proprietario(2,"Paulo Ricardo","456","paulor@gmail.com");
		Proprietario p3 = new Proprietario(3,"Bruna","789","bruna@gmail.com");
				
		propDAO.saveAll(Arrays.asList(p1,p2,p3));
		
		
	}

}
