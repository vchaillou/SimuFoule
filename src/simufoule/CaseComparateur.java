package simufoule;

import java.util.Comparator;

public class CaseComparateur implements Comparator<Case> {

	@Override
	public int compare(Case arg0, Case arg1) {
		return arg0.getValeur()-arg1.getValeur();
	}

}
