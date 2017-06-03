#Apriori Algorithm for CP3403 Data Mining
##How to run:
1. Arguments: \<filename\> \<min support\> \<min confidence\>
2. In the command line run: java -jar DataMiningAprioriAlgorithm.jar online_retail.csv 800 0
3. Wait approximately 1 minute
4. Results should be:
```
JUMBO BAG RED RETROSPOT => JUMBO BAG PINK POLKADOT(Support: 833, Confidence: 0.39)
JUMBO BAG PINK POLKADOT => JUMBO BAG RED RETROSPOT(Support: 833, Confidence: 0.68)
```
