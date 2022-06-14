package questao3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Principal {
	/*
	 * 3) Dado um vetor que guarda o valor de faturamento di�rio de uma
	 * distribuidora, fa�a um programa, na linguagem que desejar, que calcule e
	 * retorne: � O menor valor de faturamento ocorrido em um dia do m�s; � O maior
	 * valor de faturamento ocorrido em um dia do m�s; � N�mero de dias no m�s em
	 * que o valor de faturamento di�rio foi superior � m�dia mensal.
	 * 
	 * IMPORTANTE: a) Usar o json ou xml dispon�vel como fonte dos dados do
	 * faturamento mensal; b) Podem existir dias sem faturamento, como nos finais de
	 * semana e feriados. Estes dias devem ser ignorados no c�lculo da m�dia;
	 * 
	 * 
	 * NOME: GUILHERME HENRIQUE CHAVES JABOUR
	 */

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {

		// Aqui vai o caminho do projeto at� a fonte de dados: no nosso caso, o arquivo dados.json
		//Caso execute-o em outro diretorio que n�o a raiz, o programa n�o funcionar� corretamente
		Object ob = new JSONParser().parse(new FileReader("C:/questao3/src/questao3/dados.json"));

		JSONArray js = (JSONArray) ob;

		ObjectMapper mapper = new ObjectMapper();
		List<Faturamento> faturamentos = new ArrayList<>();
		double menor = 0;
		double maior = 0;
		double total = 0;
		double media = 0;
		int diasComFaturamento = 0;

		for (int i = 0; i < 30; i++) {

			String jsonString = js.get(i).toString();
			Faturamento faturamento = mapper.readValue(jsonString, Faturamento.class);

			if (faturamento.getValor() != 0) {
				if (i == 0) {
					menor = faturamento.getValor();
				}
				if (faturamento.getValor() < menor) {
					menor = faturamento.getValor();
				}
				if (maior < faturamento.getValor()) {
					maior = faturamento.getValor();
				}
				total += faturamento.getValor();
				faturamentos.add(faturamento);
				diasComFaturamento++;
			}	
		}

		media = total / diasComFaturamento;

		//System.out.println(total);
		//System.out.println(diasComFaturamento);
		System.out.println("Media = " + media);
		System.out.println("Menor Faturamento = " + menor);
		System.out.println("Maior faturamento = " +maior);
	}

}
