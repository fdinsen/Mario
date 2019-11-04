package mariopizzaria;

import java.util.Comparator;

public class AmountSorter implements Comparator<IndividualStatistics>{
    
    @Override
    public int compare(IndividualStatistics stat1, IndividualStatistics stat2) {
        return Integer.compare(stat1.getAmountOfSales(), stat2.getAmountOfSales());
    }
}