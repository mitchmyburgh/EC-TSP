Mitch Myburgh (MYBMIT001)
Solution to dynamic TSP

Results:
Lowest: 3653
Average: 4042.15
Highest: 4439

Compared with Michael Kyeyune (KYYMIC001)

Results:
Lowest: 3403
Average: 3892.51
Highest: 4337

The Kolmogorov-Smirnov test was run and it was found that both datasets are consistent with a normal distribution:
  Mitch's Data:
    KS finds the data is consistent with a normal distribution: P= 0.72 where the normal distribution has mean= 4039. and sdev= 166.8 
  Michael's Data:
    KS finds the data is consistent with a normal distribution: P= 0.64 where the normal distribution has mean= 3887. and sdev= 187.0

The KS test additionally found that:
	The maximum difference between the cumulative distributions, D, is: 0.4100 with a corresponding P of: 0.000


Since both results are consistent with a normal distribution (and therefore parametric), independent and the same size a 2 sample T-test was performed:

H0: mean_mitch - mean_michael = 0
H1: mean_mitch - mean_michael != 0
alpha = 0.025 (two tailed distribution)
p-value = 0.0000000003235251092

p-value<alpha

Result: Reject null hypothesis. Therefor the results are significantly different.

The p-value was calculated for a two tailed distribution for sets with unequals variance.

Explaination of results:

The results were significantly different, this can be explained by the differences in implementation. Michael mutated only the best performing chromosome, while I mutated the top 5 (with higher fitness individuals having more children), this resulted in higher exploitation for Michael, as only the best chromosome had children, which was advantageous due to the low number of generations available. In addition Michael always replaced the least fit individual, then resorting the list, while I ran through the parents and replaced the first individual to have a lower fitness than the current child, this resulted in keeping poor fitness individuals for multiple generations, while removing medium and high fitness individuals, whose genetic material may have made them more robust to cities moving, Michael replaced these poor fitness individuals, and kept the higher fitness individuals, both of these methods could be considered elitism, however Michael's approach produced better results. These two differences had the largest impact on the difference, however the replacement used by Michael probably made the biggest difference, as it keep higher fitness individuals resulting in higher averages. Both results were obtained using inversion, and no crossover as suggested in the slides on the TSP.
