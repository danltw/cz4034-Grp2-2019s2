# TwitterCrawling 

This java project will crawl data from twitter using the API Twitter4J. An csv file will be generated after their run.

## System Requirements

OS: Windows or any flavor of Unix that supports Java.

JVM: Java 5 or later


## How to use?

The following consumer key only enables user to crawl limited number of tweets every 15mins.
``` 
ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("xJBKzaqxQiiV4EEkq2kamDopn")
                .setOAuthConsumerSecret("cubJVmCSlbSGrdDNLlSM2P2P9YZF2FtIt3mh7izZKaHcFycJVz")
                .setOAuthAccessToken("3229045309-QXL281PQ5SwwnLq2Myf8qYjZkP2NNy6lj0ottAI")
                .setOAuthAccessTokenSecret("tzZtMyu3RYlQFUXiBoLbNfOxhaXz0yVKSVumEqv33SMqs")
                .setHttpConnectionTimeout(100000)
                .setDebugEnabled(true);
```
Change sourceID to corresponding newsid in the input.txt to crawl specific data from different news id. Note: every 15 mins can only crawl 1 newid.
```
int sourceID = 1;
        String line = null;

        for (int i = 0; i < sourceID; i++) {

            line = scanner.nextLine();
        }
```
The execution of the code will crawl the data from todays date until it hit the maximum limit given by Twitter.
```
Date todaysDate = new Date();
        String testDateString = formatter.format(todaysDate);
        System.out.println("String in yyy-MM-dd format is: " + testDateString);
```
Newly crawled data will be written to the newlyCrawled.csv file. Subsequent data will be appended to the file after every run.
```
 try {
            File directory = new File(".");
            writer = new CSVWriter(new FileWriter(directory + "/newlyCrawled.csv",true));
        } catch (IOException e) {
            e.printStackTrace();
        }
```
## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
Twitter4J is released under Apache License 2.0.
