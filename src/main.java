
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class main {

	public static void main(String[] args) {
		
		int opcao = 0;
		
		do {
			// Imprimir menu
			menu();
	        System.out.print("Digite aqui sua opção: ");
    		Scanner sc = new Scanner(System.in);
	        opcao = Integer.parseInt(sc.nextLine());
	        
	        switch (opcao) {
	        	case 1:	
	        		List<String> sintomas = informarSintomas();
	            	System.out.println("Sintomas informados: " + diagnostico(sintomas));
	            	System.out.println("Deseja abrir menu principal? [Sn]");
	            	String resposta = "s";
	            	resposta = sc.next();
	            	if (resposta.equalsIgnoreCase("n")) {
	                    System.out.println("****** FIM DO PROGRAMA ******");
	            		System.exit(0);
	            	}
	            	break;
                case 0:
                    System.out.println("****** FIM DO PROGRAMA ******");
                    System.exit(0);
                default:
                    System.out.print("Opção Inválida!");
                    break;
            }

		} while(opcao != 0);
		
        System.out.println("****** FIM DO PROGRAMA ******");
        System.exit(0);
	}
	
	private static void menu () {
        System.out.println(" ****** Sistema Diagnóstico Médico ******");
        System.out.println(" * Escolha uma das opções abaixo *");
        System.out.println(" * 1 -> Diagnóstico *");
        System.out.println(" * 0 -> Sair *");
        System.out.println(" ********************************************");	
	}
	
	private static String diagnostico (List<String> sintomas) {
		String resultadoFinal = sintomas.toString();
		String[] sintomasCodiv19 = {
				"febre", 
				"tosse", 
				"cansaco", 
				"calafrios", 
				"falta_ar", 
				"dificuldade_respirar", 
				"fadiga", 
				"dores_musculares_corporais", 
				"dor_cabeça", 
				"perdade_sabor_cheiro", 
				"dor_garganta", 
				"congestao",
				"nariz_escorrendo", 
				"nausea",
				"vomito", 
				"diarreia"};
		
		String[] sintomasDengue  = {
				"dores_musculos", 
				"dores_costas", 
				"dores_ossos", 
				"febre", 
				"fadiga", 
				"mal_estar", 
				"vômito", 
				"perda_apetite", 
				"tremor", 
				"suor", 
				"dor_cabeça", 
				"manchas_avermelhadas_nausea", 
				"dor_corpo", 
				"dor_fundo_olhos", 
				"febre", 
				"dor_abdominal"};
		
		String[] sintomasGripe   = {
				"febre",
				"clafrios",
				"dor_cabeca",
				"tosse",
				"espirros",
				"nariz_escorrendo",
				"dor_garganta",
				"dor_muscular",
				"perda_apetite",
				"cansaco",
				"falta_ar",
				"fraqueza_muscular",};
		
		return resultadoFinal;
	}
	
	private static List<String> informarSintomas () {
    	List<String> sintomas = new ArrayList<String>();
    	
    	Scanner sc = new Scanner(System.in);
    	int i = 1;
    	String resposta = "s";
    	
    	System.out.println("//////////////////////////////////////////////////////////////////////////");
    	System.out.println("Para um melhor diagnóstico, será necessário informar pelo menos 3 sintomas!");
    	
    	do {
    		System.out.println("Por favor informe o "+i+"º sintoma:");
    	    sintomas.add(sc.next());
    	    
    	    i++;
    	    if(i > 3) {
    	    	System.out.println("Deseja informar mais um sintoma? [Sn]");
    	    	resposta = "";
	        	resposta = sc.next();
	        	if (resposta.equalsIgnoreCase("s")) {
        			System.out.println("Por favor informe o "+i+"º sintoma:");
        	    	sintomas.add(sc.next());
        	    	i++;
        	    	System.out.println("Deseja informa mais um sintoma? [Sn]");
        	    	resposta = "";
        	    	resposta = sc.next();
	        	}
    	    }
    	} while (resposta.equalsIgnoreCase("s"));
    	System.out.println("//////////////////////////////////////////////////////////////////////////");
    	
		return sintomas;
	}

}
