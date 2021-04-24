import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.List;

public class main {

	// Metodo principal para iniciar o sistema.
	public static void main(String[] args) {
		// Variavel utilizada para receber entrada do usuário, podemos iniciar um diagnóstico ou encerrar o sistema.
		int opcao = -1;
		// Loop para que o usuário possa navegar no menu.
		do {
			// Imprimir menu
			menu();
	        System.out.print("\nDigite aqui sua opção:");
	        // Inicializando objeto Scanner, utilizado para receber entrada do usuário.
    		Scanner sc = new Scanner(System.in);
    		// Aguardando usuário digitar uma opção de acordo com o menu.
    		try {
    			opcao = sc.nextInt();
    		} catch (InputMismatchException e) {
                System.out.println("\nOpção inválida! Tente novamente!");
            }
	        // Switch case para executar codigo de acordo com a opção escolhida pelo usuário.
	        switch (opcao) {
	        	// Case para solicitar os sintomas ao usuário e realizar o pré-diagnóstico.
	        	case 1:	
	        		// Lista para que seja preenchida com o codigo de cada sintoma que o usuário digitar.
	        		List<Integer> sintomasSelecionados = informarSintomas();
	        		System.out.println("\nSintomas informados: ");
	        		// Lista que recebe todos os sintomas reconhecidos pelo sistema.
	        		List<String> sintomas = getSintomas();
	        		// Loop para imprimir os sintomas informados pelo usuário.
	        		for (int i=0; i < sintomasSelecionados.size(); i++) {
		            	System.out.println("- "+sintomas.get(sintomasSelecionados.get(i))); 	        			
	        		}
	            	System.out.println("\n***************** Diagnóstico *****************\n");
	            	// Neste momento será repassado como parâmetro para o metodo 'diagnostico' todos os sintomas informados pelo usuário e imprime o resultado.
	            	System.out.println(diagnostico(sintomasSelecionados));
	            	System.out.println("\n***********************************************");
	            	String resposta; 
	            	do {
			            System.out.println("\nDeseja voltar ao menu principal? [S/N]");
			            // Aguarda usuário digitar 's' (SIM) para voltar para menu principal, ou 'n' (NÂO) para encerrar o sistema.
			            resposta = sc.next();
		            	// Função que valida se o usuário digito 'n' (NÂO) para encerrar o sistema.
		            	if (resposta.equalsIgnoreCase("n")) {
		                    System.out.println("\n****** FIM DO PROGRAMA ******");
		            		System.exit(0);
		            	}
	            	} while((!resposta.equalsIgnoreCase("s")) && (!resposta.equalsIgnoreCase("n")));
	            	break;
	            // Case para encerrar o sistema caso o usuário digite 0.
                case 0:
                    System.out.println("\n****** FIM DO PROGRAMA ******");
                    System.exit(0);
            }
		} while(opcao != 0);	
        System.out.println("\n****** FIM DO PROGRAMA ******");
        // Encerrar o sistema.
        System.exit(0);
	}
	// Metodo para imprimir o menu principal.
	private static void menu () {
        System.out.println(" ****** Sistema Diagnóstico Médico ******");
        System.out.println(" * Escolha uma das opções abaixo ********");
        System.out.println(" * 1 -> Diagnóstico *********************");
        System.out.println(" * 0 -> Sair ****************************");
        System.out.println(" ****************************************");	
	}
	// Metodo para realizar diagnostico de acordo com os sintomas passado como parâmetro.
	private static String diagnostico (List<Integer> sintomasSelecionados) {
		// Variaveis para definir pontuação das doenças que mais teve sintomas informados pelo usuário.
		int pontuacaoCovid19;
		int pontuacaoGripe;
		int pontuacaoDengue;
		// As variaveis abaixo são para receber a pontuação
		pontuacaoCovid19 = getPontuacaoCovid19(sintomasSelecionados);
		pontuacaoGripe = getPontuacaoGripe(sintomasSelecionados);
		pontuacaoDengue = getPontuacaoDengue(sintomasSelecionados);
		// Função para analisar se a pontuação da doença Covid-19 e maior que as demais doenças para determinar a doença.
		if (pontuacaoCovid19 > pontuacaoGripe &&
			pontuacaoCovid19 > pontuacaoDengue) {
			return ">>>>>>>>>>>>>>>>>> Covid-19 <<<<<<<<<<<<<<<<<<<";
		}
		// Função para analisar se a pontuação da doença Gripe e maior que as demais doenças para determinar a doença.
		if (pontuacaoGripe > pontuacaoCovid19 &&
			pontuacaoGripe > pontuacaoDengue) {
			return ">>>>>>>>>>>>>>>>>>>> Gripe <<<<<<<<<<<<<<<<<<<<";
		}
		// Função para analisar se a pontuação da doença Dengue e maior que as demais doenças para determinar a doença.
		if (pontuacaoDengue > pontuacaoCovid19 &&
			pontuacaoDengue > pontuacaoGripe) { 
			return ">>>>>>>>>>>>>>>>>>> Dengue <<<<<<<<<<<<<<<<<<<<";
		}
		// Caso não consiga determinar a doença, retorna como diagnóstico impreciso.
		return "> Impreciso, por favor informe mais sintomas. <";
	}
	// Metodo para analisar se os sintomas informados pelo usuário constam na doença Covid-19.
	// Cada sintoma que consta na doença, a variavel pontuacao soma mais 1.
	private static int getPontuacaoCovid19 (List<Integer> sintomasSelecionados) {
		// Variavel para pontuação.
		int pontuacao = 0;
		// Variavel que recebe codigo de cada sintoma da doença Covid-19.
		int[] covid19 = getSintomasPorDoenca("covid-19");
		// Loop para analisar se os sintomas informados constam na doença Covid-19.
		for (int i=0; i < covid19.length; i++) {
			for (int k=0; k < sintomasSelecionados.size(); k++) {
				if (covid19[i] == sintomasSelecionados.get(k)) {
					pontuacao++;
				}
			}
		}
		return pontuacao;		
	}
	// Metodo para analisar se os sintomas informados pelo usuário constam na doença Gripe.
	// Cada sintoma que consta na doença, a variavel pontuacao soma mais 1.
	private static int getPontuacaoGripe(List<Integer> sintomasSelecionados) {
		int pontuacao = 0;
		// Variavel que recebe codigo de cada sintoma da doença Gripe.
		int[] gripe = getSintomasPorDoenca("gripe");
		// Loop para analisar se os sintomas informados constam na doença Gripe.
		for (int i=0; i < gripe.length; i++) {
			for (int k=0; k < sintomasSelecionados.size(); k++) {
				if (gripe[i] == sintomasSelecionados.get(k)) {
					pontuacao++;
				}
			}
		}
		return pontuacao;
	}
	// Metodo para analisar se os sintomas informados pelo usuário constam na doença Dengue e retonar a pontuação.
	// Cada sintoma que consta na doença, a variavel pontuacao soma mais 1.
	private static int getPontuacaoDengue(List<Integer> sintomasSelecionados) {
		int pontuacao = 0;
		// Variavel que recebe codigo de cada sintoma da doença Dengue.
		int[] dengue = getSintomasPorDoenca("dengue");
		// Loop para analisar se os sintomas informados constam na doença Dengue.
		for (int i=0; i < dengue.length; i++) {
			for (int k=0; k < sintomasSelecionados.size(); k++) {
				if (dengue[i] == sintomasSelecionados.get(k)) {
					pontuacao++;
				}
			}
		}
		return pontuacao;
	}
	// Metodo para solicitar ao usuário os sintomas.
	private static List<Integer> informarSintomas () {
		// Lista para receber codigo de cada sintoma que o usuário informar.
    	List<Integer> sintomasSelecionados = new ArrayList<Integer>();
    	Scanner sc = new Scanner(System.in);
    	int codigoSintoma = 0;
    	int i = 0;
    	System.out.println("//////////////////////////////////////////////////////////////////////////");
    	System.out.println("\nPara um melhor diagnóstico, será necessário informar pelo menos 3 sintomas!\n");
    	// Lista para receber todos os sintomas reconhecidos pelo sistema.
    	List<String> sintomas = getSintomas();
    	// Loop para imprimir sintomas.
    	for(int k = 1; k < sintomas.size(); k++) {
    		System.out.println(k + " - " + sintomas.get(k));
    	}
    	// Loop para receber todos os sintomas do usuário
    	do {
    		System.out.println("\nPor favor informe o código do sintoma: ("+(i+1)+")");
    		// Try catch para tratar exceção caso o usuário digitar um valor diferente de inteiro.
    		try {
    			// Variavel para receber codigo do sintoma.
    			codigoSintoma = sc.nextInt();
    			// Guarda codigo do sintoma na lista 'sintomasSelecionados'.
        		sintomasSelecionados.add(codigoSintoma);
        		i++;
       		// Caso digite algum valor diferente de número, gera uma exceção.
    		} catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("\nCódigo inválido! Tente novamente!");
            }
    		// Valida se o codigo do sintoma e maior que 29, pois existe somente 29 sintomas.
    	    if (codigoSintoma > 29) {
    	    	System.out.println("\nCódigo inválido! Tente novamente!");
    	    	i--;
    	        sintomasSelecionados.remove(i);
    	    }
    	    // Loop para validar se o codigo do sintoma já foi informado anteriormente.
    	    for(int j=0;j < (sintomasSelecionados.size()-1);j++) {
    	    	if(codigoSintoma == sintomasSelecionados.get(j)) { 
        	    	System.out.println("\nSintoma já informado! Digite novamente!");
        	    	i--;
        	        sintomasSelecionados.remove(i);
    	    	}
    	    }
    		// Função para solicitar o usuário se deseja informar mais um sintoma, depois que já tiver informado 3 sitnomas.
    	    if(i > 2) {     	
            	String resposta; 
            	do {
		            System.out.println("\nDeseja informar mais um sintoma? [S/N]");
		            // Aguarda usuário digitar 's' (SIM) para informar um novo sintoma, ou 'n' (NÂO) para realizar o diagnóstico.
		            resposta = sc.next();
            	} while((!resposta.equalsIgnoreCase("s")) && (!resposta.equalsIgnoreCase("n")));
            	// função que valida se o usuário digito 'n' (não).
            	if (resposta.equalsIgnoreCase("n")) {
                    break;
            	}
    	    }
    	} while (true);
    	System.out.println("//////////////////////////////////////////////////////////////////////////");
		return sintomasSelecionados;
	}
	// Metodo para preencher a lista com todos os sintomas baseado nas doenças Covid-19, Dengue e Gripe.
	private static List<String> getSintomas () {
    	// Lista para receber todos os sintomas reconhecidos pelo sistema.
		List<String> sintomas = new ArrayList<String>();
		// Recebe todos os sintomas baseado nas doenças Covid-19, Dengue e Gripe.
		// Obs.: O index definie ID de cada sintoma.
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
    			"Diarreia",
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
	// Metodo para retornar os sintomas da doença passada como parâmetro.
	private static int[] getSintomasPorDoenca (String doenca) {
		// Sintomas definidos conforme ID (index) preenchidos no metodo "informarSintomas" -> arrayList "sintomas".
		// ID dos sintomas da doença Covid-19.
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
		// ID dos sintomas da doença Dengue.
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
		// ID dos sintomas da doença Gripe.
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
		// Switch case para retonar os sintomas de acordo com a doença passada como parâmetro, caso não encontre, retorna null.
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
