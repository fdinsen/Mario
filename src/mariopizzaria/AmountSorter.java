package mariopizzaria;

import java.util.Comparator;

public class AmountSorter implements Comparator<IndividualStatistics>{
    
    @Override
    public int compare(IndividualStatistics stat1, IndividualStatistics stat2) {
        return ((Integer)stat1.getAmountOfSales()).compareTo((Integer)stat2.getAmountOfSales());
    }
}
