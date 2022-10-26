package br.ufc.persistence.pratica09;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.ufc.persistence.ui.CRUDAlunoTurmas;
import br.ufc.persistence.ui.CRUDAlunos;
import br.ufc.persistence.ui.CRUDTurmas;

@SpringBootApplication
@EntityScan("br.ufc.persistence.entity")
@ComponentScan( "br.ufc.persistence")
@EnableJpaRepositories("br.ufc.persistence.dao")
public class Pratica09Application implements CommandLineRunner{
	
	@Autowired
	private CRUDAlunos crAlunos;
	@Autowired
	private CRUDTurmas crTurmas;
	@Autowired
	private CRUDAlunoTurmas crAluTur;

	public static void main(String[] args) {
//		SpringApplication.run(Pratica09Application.class, args);
		SpringApplicationBuilder builder = new SpringApplicationBuilder(Pratica09Application.class);
//		SpringApplicationBuilder builder = new SpringApplicationBuilder(CRUDTurmas.class);
//		SpringApplicationBuilder builder = new SpringApplicationBuilder(CRUDAlunoTurmas.class);

		builder.headless(false).run(args);
	}
		
	
	
	@Override
	public void run(String... args) throws Exception {
		String menu = "Escolha uma opção:\n"
				+ "1 - Chamar CRUDAlunos\n"
				+ "2 - CRUDTurmas\n"
				+ "3 - CRUDALunosTurmas\n"
				+ "4 - Sair";
		char opcao;
		
		do { 
			opcao = JOptionPane.showInputDialog(menu).charAt(0);
			
			switch( opcao ) {
			case '1': crAlunos.menu(); 
					  break;
			case '2': crTurmas.menu();
					  break;
			case '3': crAluTur.menu();
					  break;
			case '4': break;
			
			default: JOptionPane.showMessageDialog( null, "Opção inválida" );
					  break;
			}
			
		
		} while(opcao != '8');
		
	}
		
}


