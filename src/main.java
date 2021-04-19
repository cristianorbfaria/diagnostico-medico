
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

public class main {

	public static void main(String[] args) {
		
		int opcao = 0;
		
		do {
			// Imprimir menu
			menu();
	        System.out.print("\nDigite aqui sua opção:");
    		Scanner sc = new Scanner(System.in);
	        opcao = Integer.parseInt(sc.nextLine());
	        
	        switch (opcao) {
	        	case 1:	
	        		List<Integer> sintomasSelecionados = informarSintomas();
	        		System.out.println("\nSintomas informados: ");
	        		List<String> sintomas = getSintomas();
	        		for (int i=0; i < sintomasSelecionados.size(); i++) {
		            	System.out.println("- "+sintomas.get(sintomasSelecionados.get(i))); 	        			
	        		}
	            	System.out.println("\n***************** Diagnóstico *****************\n");
	            	System.out.println(diagnostico(sintomasSelecionados));
	            	System.out.println("\n***********************************************");
	            	System.out.println("\nDeseja voltar ao menu principal? [S/N]");
	            	String resposta = "s";
	            	resposta = sc.next();
	            	if (resposta.equalsIgnoreCase("n")) {
	                    System.out.println("\n****** FIM DO PROGRAMA ******");
	            		System.exit(0);
	            	}
	            	break;
                case 0:
                    System.out.println("\n****** FIM DO PROGRAMA ******");
                    System.exit(0);
                default:
                    System.out.println("\nOpção Inválida!");
                    break;
            }

		} while(opcao != 0);
		
        System.out.println("\n****** FIM DO PROGRAMA ******");
        System.exit(0);
	}
	
	private static void menu () {
        System.out.println(" ****** Sistema Diagnóstico Médico ******");
        System.out.println(" * Escolha uma das opções abaixo ********");
        System.out.println(" * 1 -> Diagnóstico *********************");
        System.out.println(" * 0 -> Sair ****************************");
        System.out.println(" ****************************************");	
	}
	
	private static String diagnostico (List<Integer> sintomasSelecionados) {
		int pontuacaoCovid19;
		int pontuacaoGripe;
		int pontuacaoDengue;		
		
		pontuacaoCovid19 = getPontuacaoCovid19(sintomasSelecionados);
		pontuacaoGripe = getPontuacaoGripe(sintomasSelecionados);
		pontuacaoDengue = getPontuacaoDengue(sintomasSelecionados);
		
		if (pontuacaoCovid19 > pontuacaoGripe &&
			pontuacaoCovid19 > pontuacaoDengue) {
			return ">>>>>>>>>>>>>>>>>> Covid-19 <<<<<<<<<<<<<<<<<<<";
		}
		
		if (pontuacaoGripe > pontuacaoCovid19 &&
			pontuacaoGripe > pontuacaoDengue) {
			return ">>>>>>>>>>>>>>>>>>>> Gripe <<<<<<<<<<<<<<<<<<<<";
		}
		
		if (pontuacaoDengue > pontuacaoCovid19 &&
			pontuacaoDengue > pontuacaoGripe) { 
			return ">>>>>>>>>>>>>>>>>>> Dengue <<<<<<<<<<<<<<<<<<<<";
		}
		
		return "> Impreciso, por favor informe mais sintomas. <";
	}

	private static int getPontuacaoCovid19 (List<Integer> sintomasSelecionados) {
		int pontuacao = 0;
			
		int[] covid19 = getSintomasPorDoenca("covid-19");
		
		for (int i=0; i < covid19.length; i++) {
			for (int k=0; k < sintomasSelecionados.size(); k++) {
				if (covid19[i] == sintomasSelecionados.get(k)) {
					pontuacao++;
				}
			}
		}
		
		return pontuacao;		
	}
	
	private static int getPontuacaoGripe(List<Integer> sintomasSelecionados) {
		int pontuacao = 0;
		
		int[] gripe = getSintomasPorDoenca("gripe");
		
		for (int i=0; i < gripe.length; i++) {
			for (int k=0; k < sintomasSelecionados.size(); k++) {
				if (gripe[i] == sintomasSelecionados.get(k)) {
					pontuacao++;
				}
			}
		}
		
		return pontuacao;
	}
	
	private static int getPontuacaoDengue(List<Integer> sintomasSelecionados) {
		int pontuacao = 0;
		
		int[] dengue = getSintomasPorDoenca("dengue");
		
		for (int i=0; i < dengue.length; i++) {
			for (int k=0; k < sintomasSelecionados.size(); k++) {
				if (dengue[i] == sintomasSelecionados.get(k)) {
					pontuacao++;
				}
			}
		}
		
		return pontuacao;
	}
	
	private static List<Integer> informarSintomas () {
    	List<Integer> sintomasSelecionados = new ArrayList<Integer>();
    	
    	Scanner sc = new Scanner(System.in);
    	int codigoSintoma = 0;
    	int i = 0;
    	
    	System.out.println("//////////////////////////////////////////////////////////////////////////");
    	System.out.println("\nPara um melhor diagnóstico, será necessário informar pelo menos 3 sintomas!\n");
    	
    	List<String> sintomas = getSintomas();
    	
    	// Imprimir sintomas 
    	for(int k = 1; k < sintomas.size(); k++) {
    		System.out.println(k + " - " + sintomas.get(k));
    	}
    	
    	do {
    		System.out.println("\nPor favor informe o código do sintoma:");
    		try {
    			codigoSintoma = sc.nextInt();
        		sintomasSelecionados.add(codigoSintoma);
        	    i++;
    		} catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("\nCódigo inválido! Tente novamente");
                i--;
            }
    		
    	    if(i > 2) {
    	    	System.out.println("\nDeseja informar mais um sintoma? [S/N]");
	        	String resposta = sc.next();
	        	if (resposta.equalsIgnoreCase("n")) {
	        		break;
	        	}
    	    }
    	} while (true);
    	System.out.println("//////////////////////////////////////////////////////////////////////////");
    	
		return sintomasSelecionados;
	}
	
	private static List<String> getSintomas () {
    	
		List<String> sintomas = new ArrayList<String>();
		
		// O index definie ID de cada sintoma
    	sintomas.addAll(Arrays.asList(
    			"",
    			"Febre",
    			"Tosse",
    			"Cansaço",
    			"Calafrios",
    			"Falta de ar",
    			"Dificuldade para respirar",
    			"Fadiga",
    			"Dor muscular",
    			"Dor de cabeça",
    			"Dor de garganta",
    			"Perda de sabor",
    			"Perda de cheiro",
    			"Congestão",
    			"Vômito",
    			"Diarréia",
    			"Dor nas costas",
    			"Dor nos ossos",
    			"Dor no corpo",
    			"Dor no fundo dos olhos",
    			"Dor abdominal",
    			"Mal-estar",
    			"Perda de apetite",
    			"Tremor",
    			"Suor",
    			"Manchas avermelhadas",
    			"Náusea",
    			"Espirros",
    			"Nariz escorrendo",
    			"Fraqueza muscular"));
    	
		return sintomas;
	}
	
	private static int[] getSintomasPorDoenca (String doenca) {
		
		// Sintomas definidos conforme ID
		// Associação do ID com nome do sintoma encontra-se no metodo "informarSintomas" -> arrayList "sintomas"
		// O index define ID do sintoma
		int[] sintomasCodiv19 = {
				1, 
				2, 
				3, 
				4, 
				5, 
				6, 
				7, 
				8, 
				9,
				10,
				11,
				12,
				13, 
				26,
				14, 
				15};
		
		int[] sintomasDengue  = {
				8, 
				16, 
				17, 
				1, 
				7, 
				21, 
				14, 
				22, 
				23, 
				24, 
				9, 
				25,
				26, 
				18, 
				19,  
				20};
		
		int[] sintomasGripe   = {
				1,
				4,
				9,
				2,
				27,
				28,
				10,
				8,
				22,
				3,
				5,
				29};
		
		switch (doenca) {
		case "covid-19":
			return sintomasCodiv19;
		case "dengue":
			return sintomasDengue;
		case "gripe":
			return sintomasGripe;
		default:
			return null;
		
		}
		
	}
}
