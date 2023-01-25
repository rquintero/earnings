# Code Challenge: Capital Gains		
## Architecture Design
![Architecture](arch-capital-gains-enus.jpg?raw=true "Architecture")

 
### Domain
Consists of a basic class that encapsulate the attributes a Stock Market Operation has. Worthy to mention that attributes should keep precise amounts due they are currencies. We’ll have only to kind of operations aka. BUY or SELL.
### View
The view consists of a class that reads the console input and generates a list of Json objects as strings.
 It also contains an output adapter in charge of showing the tax amounts as Json objects on the console. 
### Service
It encapsulates the business core. The strategy was to create an aggregator that calculates the tax for each of the StockMarketOperation instances on operations list while adding each of the taxes to the returning list of Number.
### Application Flow
•	StockApp instantiates an InputReader and gets the list of Json operations as plain/text;

•	StockApp creates an instance of AggregatorProcessor(AGP) injecting a TaxAggregator and the operation list in the constructor;

•	StockApp calls run on the AGP.

•	AggregatorProcessor  deserializes  each market operation Json String in an instance of StockMarketOpertion and adds it to the TaxAggregator operation list one by one.

•	TaxAggregator calculates the tax for each operation meanwhile aggregating loss, stock units and stock total amount.

•	 TaxAggregator returns a list of numbers having the tax for each operation.

•	StockApp uses an OutputAdapter to present information on the console.

## Implementation Details 

•	The list of StockMarketOperation(SMO) is immutable.

•	Didn’t de-serialize the input string as a collection of Java objects using a library.

•	Extracted the Json objects from the Json collection.

•	Instead of setting the list of SMOs on the aggregator or adding all to it, I de-serialized 
one by one from the Json object string to a SMO and add it to the aggregator list one by one.

•	The above was done to not only keep the immutability of the aggregator list but also load it on O(n) complexity.

•	The only external jar library added to the project was com.google.code.gson.

•	Even test cases manage integer amounts I designed it thinking of currencies may handle cents and we could present the taxes in different number formats if needed.

## Additional Test Cases

### Case #7

Operation | #Unit Cost | #Quantity | #Tax | #Explanation
--- | --- | --- | --- |--- 
buy | 10.5 | 10000 | 0 | Buying stocks do not pay taxes | 289 | 285 | 287 | 287 | 272 | 276 | 269
sell|  2	  |  5000	| 0	| Loss of R$ 42500: total amount is less than R$ 20000, but we should deduct that loss
buy	| 10	  | 10000	| 0	| Buying stocks do not pay taxes
sell|	20.5	| 10000	| 12000 |	Profit of R$ 102500 and R$42500 losses you can deduct: tax is 20% of that

Input:

[{"operation":"buy", "unit-cost":10.5, "quantity": 10000},{"operation":"sell", "unit-cost":2, "quantity": 5000},{"operation":"buy", "unit-cost":10, "quantity": 10000},{"operation":"sell", "unit-cost":20.5, "quantity": 10000}]

Output:

[{"tax": 0},{"tax": 0},{"tax": 0},{"tax": 12000}]

## Running Instructions

1.	Install Java JDK 1.8+

2.	Set JAVA_HOME environment variable

3.	Install Gradle

4.	On a terminal change directory to the root folder that contains “build.gradle” file.

5.	Run “gradle jar”

6.	Change directory to ./build/libs

7.	Run “java -jar stock-1.0-SNAPSHOT.jar”
 

### Docker

•	To faster have a running environment I decided to add the file on ./build/libs/stock-1.0-SNAPSHOT.jar to the delivery and create a Dockerfile for the project.

1.	On the application root folder run “docker build -t stock-nu .”

2.	 Then run “docker run -it stock-nu”
