import java.util.ArrayList;
import java.util.Random;

class Chromosome {

    /**
     * The list of cities, which are the genes of this chromosome.
     */
    protected int[] cityList;

    /**
     * The cost of following the cityList order of this chromosome.
     */
    protected double cost;

    /**
     * @param cities The order that this chromosome would visit the cities.
     */
    Chromosome(City[] cities) {
        Random generator = new Random();
        cityList = new int[cities.length];
        //cities are visited based on the order of an integer representation [o,n] of each of the n cities.
        for (int x = 0; x < cities.length; x++) {
            cityList[x] = x;
        }

        //shuffle the order so we have a random initial order
        for (int y = 0; y < cityList.length; y++) {
            int temp = cityList[y];
            int randomNum = generator.nextInt(cityList.length);
            cityList[y] = cityList[randomNum];
            cityList[randomNum] = temp;
        }

        calculateCost(cities);
    }
    public Chromosome(Chromosome another) {
        this.cityList = another.getCities();
    }

    /**
     * Calculate the cost of the specified list of cities.
     *
     * @param cities A list of cities.
     */
    void calculateCost(City[] cities) {
        cost = 0;
        for (int i = 0; i < cityList.length - 1; i++) {
            double dist = cities[cityList[i]].proximity(cities[cityList[i + 1]]);
            cost += dist;
        }

        cost += cities[cityList[0]].proximity(cities[cityList[cityList.length - 1]]); //Adding return home
    }

    /**
     * Get the cost for this chromosome. This is the amount of distance that
     * must be traveled.
     */
    double getCost() {
        return cost;
    }

    /**
     * @param i The city you want.
     * @return The ith city.
     */
    int getCity(int i) {
        return cityList[i];
    }

    int[] getCities() {
        int[] return_cities = new int[this.cityList.length];
        for(int i=0; i<this.cityList.length; i++){
           return_cities[i]=this.cityList[i];
        }
        return return_cities;
    }

    /**
     * Set the order of cities that this chromosome would visit.
     *
     * @param list A list of cities.
     */
    void setCities(int[] list) {
        for (int i = 0; i < cityList.length; i++) {
            cityList[i] = list[i];
        }
    }

    /**
     * Set the index'th city in the city list.
     *
     * @param index The city index to change
     * @param value The city number to place into the index.
     */
    void setCity(int index, int value) {
        cityList[index] = value;
    }

    /**
     * Sort the chromosomes by their cost.
     *
     * @param chromosomes An array of chromosomes to sort.
     * @param num         How much of the chromosome list to sort.
     */
    public static void sortChromosomes(Chromosome chromosomes[], int num) {
        Chromosome ctemp;
        boolean swapped = true;
        while (swapped) {
            swapped = false;
            for (int i = 0; i < num - 1; i++) {
                if (chromosomes[i].getCost() > chromosomes[i + 1].getCost()) {
                    ctemp = chromosomes[i];
                    chromosomes[i] = chromosomes[i + 1];
                    chromosomes[i + 1] = ctemp;
                    swapped = true;
                }
            }
        }
    }

    public static Chromosome[] mutate_3_point (Chromosome chromosomes[]) {
        Chromosome[] return_chromosomes = new Chromosome[chromosomes.length];
        Random rand = new Random();
        double mutation_probability = 0.1;
        for (int i = 0; i<chromosomes.length; i++){
            if (rand.nextDouble() < mutation_probability) {
                int length = chromosomes[i].getCities().length;

                //up to and not including length
                int start = rand.nextInt(length);
                int end = rand.nextInt((length- start))+start; //check this
                int position = rand.nextInt(length-(end-start)); // check this

                return_chromosomes[i] = new Chromosome(chromosomes[i]);

                int[] cities = return_chromosomes[i].getCities();
                int[] new_cities = new int[cities.length];
                int count=0;
                for (int j = start; j <= (end); j++){
                    new_cities[position+count] = cities[j];
                    cities[j] = -1;
                    count++;
                }
                count = 0;
                for (int j = 0; j < cities.length; j++){
                    if (cities[j] != -1){
                        while (new_cities[count] != 0) {
                            count++;
                        }
                        new_cities[count] = cities[j] ;
                    }
                }

                return_chromosomes[i].setCities(new_cities);
            } else {
              return_chromosomes[i] = new Chromosome(chromosomes[i]);
            }
        }
        return return_chromosomes;
    }
}
