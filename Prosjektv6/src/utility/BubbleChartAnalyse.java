package utility;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import model.answer.Answer;
import model.answer.TextAnswerInterface;

public class BubbleChartAnalyse {

	HashMap<String, WordCounter> mapWords = new HashMap<String, WordCounter>();
	// List<Answer> textCollection;
	WordCounter[] tableSortedByFrequency;
	WordCounter[] tableSortedByuniqueAppearances;
	String ignorWords = "";

	public BubbleChartAnalyse(List<Answer> text) {

			ignorWords = "the be and of a in to it i that for you he with on do say this they at but we his from hat not n't by she or as what their can who get if would her all my about will as there so when which them some me take into him your could than other how then its our two these also because more no here give";

		for (Answer answer : text) {
			if (answer instanceof TextAnswerInterface)
				addToMap(answer);
		}

		tableSortedByFrequency = sortByFrequency();
		tableSortedByuniqueAppearances = sortByAppearance();


	}

	public WordCounter[] sortByFrequency() {
		WordCounter[] table = convMapToTable();
		fletteSort(table, 0, table.length - 1, "frq");
		WordCounter[] table_res = (mapWords.size() > 20) ? new WordCounter[20] : new WordCounter[mapWords.size()];
		for (int i = 0; i < table_res.length; i++)
			table_res[i] = table[i];
		return table_res;
	}

	public WordCounter[] sortByAppearance() {
		WordCounter[] table = convMapToTable();
		fletteSort(table, 0, table.length - 1, "app");
		WordCounter[] table_res = (mapWords.size() > 20) ? new WordCounter[20] : new WordCounter[mapWords.size()];
		for (int i = 0; i < table_res.length; i++)
			table_res[i] = table[i];
		return table_res;
	}

	public void addToMap(Answer answer) {

		// String st="a aÅ@a_, , 3 @,";
		// String [] aa = st.split("[\\W&&[^øæåØÆÅ]]");

		TextAnswerInterface a = (TextAnswerInterface) answer;
		String[] textAnswerWords = a.getText().split("[\\W&&[^øæåØÆÅ]]");

		for (String w : textAnswerWords) {
			if (w != null && !w.equals("") && !ignorWords.contains(w.toLowerCase())) {
				w = w.toLowerCase();
				
				WordCounter token = mapWords.get(w);

				if (token == null) {
					token = new WordCounter(w);
					mapWords.put(w, token);
				}

				if (token.getFlag() != answer.getId()) {
					token.setFlag(answer.getId());
					token.increaseNumberOfappearance();
				}

				token.increaseFrq();
			}
		}

	}

	public WordCounter[] convMapToTable() {
		WordCounter[] table = new WordCounter[mapWords.size()];
		Iterator<WordCounter> it = mapWords.values().iterator();
		int i = 0;
		while (it.hasNext()) {
			table[i] = it.next();
			i++;
		}
		return table;
	}


	private void flette_frq(WordCounter[] tabell, int forste, int midten, int siste, String sortEtter) {
		int stor = siste - forste + 1;
		WordCounter[] hjelpeTabell = new WordCounter[stor];

		// Initierer lokale indeksar

		// start og slutt på venstre deltabell
		int forste1 = forste;
		int siste1 = midten;

		// start og slutt på høyre deltabell
		int forste2 = midten + 1;
		int siste2 = siste;

		/*
		 * Så lenge begge deltabellene ikke er tomme, kopier det minste
		 * elementet til hjelpetabellen.
		 */
		int indeks = 0;

		while ((forste1 <= siste1) && (forste2 <= siste2)) {
			// if (tabell[forste1].compareToFrq(tabell[forste2]) > 0) {
			boolean b = false;
			if (sortEtter.equals("frq")) {
				b = tabell[forste1].compareToFrq(tabell[forste2]) > 0;
			} else if (sortEtter.equals("app")) {
				b = tabell[forste1].compareToApp(tabell[forste2]) > 0;
			}

			if (b) {

				hjelpeTabell[indeks] = tabell[forste1];
				forste1++;
			} else {
				hjelpeTabell[indeks] = tabell[forste2];
				forste2++;
			}
			indeks++;
		} // while

		// kopiere resten av venstre del (kan vere tom)
		while (forste1 <= siste1) {
			hjelpeTabell[indeks] = tabell[forste1];
			forste1++;
			indeks++;
		} // while

		// kopiere resten av høyre del (kan vere tom)
		while (forste2 <= siste2) {
			hjelpeTabell[indeks] = tabell[forste2];
			forste2++;
			indeks++;
		} // while

		// Kopier resultatet tilbake til den originale tabellen
		int h = 0;
		for (indeks = forste; indeks <= siste; indeks++) {
			tabell[indeks] = hjelpeTabell[h++];
		}
	}// flette */

	/*
	 * Sorterer en del av tabell[forste...siste]. Litt annnerlede enn i bok
	 */
	public void fletteSort(WordCounter[] tabell, int forste, int siste, String sortEtter) {
		if (forste < siste && ("frq".equals(sortEtter) || "app".equals(sortEtter))) { // minst
																						// to
																						// element
			int midten = (forste + siste) / 2;

			// Sorter venstre halvdel tabell[forste,midten];
			fletteSort(tabell, forste, midten, sortEtter);

			// Sorter høgre halvdel tabell[midten+1,..siste]
			fletteSort(tabell, midten + 1, siste, sortEtter);

			// Fletter de to halvdelene
			flette_frq(tabell, forste, midten, siste, sortEtter);
		}
	}// fletteSort

	public HashMap<String, WordCounter> getMapWords() {
		return mapWords;
	}

	public void setMapWords(HashMap<String, WordCounter> mapWords) {
		this.mapWords = mapWords;
	}

	public WordCounter[] getTableSortedByFrequency() {
		return tableSortedByFrequency;
	}

	public WordCounter[] getTableSortedByuniqueAppearances() {
		return tableSortedByuniqueAppearances;
	}

}
