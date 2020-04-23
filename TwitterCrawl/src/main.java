import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.io.FileWriter;
import com.opencsv.CSVWriter;

public class main {

    public static void main(String[] args) {
        /*
            ToDO: Create a text file which indicate all the other news source
                    - To automate the whole searching process from different sources
                  Create a loop to search at least 5k- 10k of tweet from each source
                    - Almost all the needed materials are in this page
                  Store all data into an arraylist, if you need then later export it into txt file
         */

        int sID=Integer.parseInt(args[0]);

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("xJBKzaqxQiiV4EEkq2kamDopn")
                .setOAuthConsumerSecret("cubJVmCSlbSGrdDNLlSM2P2P9YZF2FtIt3mh7izZKaHcFycJVz")
                .setOAuthAccessToken("3229045309-QXL281PQ5SwwnLq2Myf8qYjZkP2NNy6lj0ottAI")
                .setOAuthAccessTokenSecret("tzZtMyu3RYlQFUXiBoLbNfOxhaXz0yVKSVumEqv33SMqs")
                .setHttpConnectionTimeout(100000)
                .setDebugEnabled(true);
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        int tweetSaved = 0;
        long tweetID = Long.MAX_VALUE;

        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int totalTweetNeed = 10000;
        ArrayList<Status> tweets = new ArrayList<Status>();

        int sourceID = 1;
        String line = null;

        for (int i = 0; i < sourceID; i++) {

            line = scanner.nextLine();
        }
        // process the line
        System.out.println("From: " + line + " lang: en");
        String source = "from:" + line + " lang:en";
        // Query is to set the source
        Query query = new Query(source);
        // set the starting search date
        query.setSince("2020-02-21");
        // set the ending search date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date todaysDate = new Date();
        String testDateString = formatter.format(todaysDate);
        System.out.println("String in yyy-MM-dd format is: " + testDateString);
        query.setUntil(testDateString);
        CSVWriter writer = null;
        try {
            File directory = new File(".");
            writer = new CSVWriter(new FileWriter(directory + "/newlyCrawled.csv",true));
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (tweets.size() < totalTweetNeed) {
            if (totalTweetNeed - tweetSaved > 100) {
                //Maximum each query is 100
                query.setCount(100);
            }
            else{
                query.setCount(totalTweetNeed - tweetSaved);
            }


//            query.setCount(1);
            QueryResult result = null;
            try {
                result = twitter.search(query);
                RateLimitStatus status = result.getRateLimitStatus();
                if (status.getRemaining() == 0) {
                    try {
                        Thread.sleep(status.getSecondsUntilReset() * 1000);
                    } catch (InterruptedException e) {
                        // ...
                    }
                } else {
                    tweets.addAll(result.getTweets());
//                    File file = new File("output.txt");
//                    try (FileWriter fw = new FileWriter(file, true)) {
//                        PrintWriter pw = new PrintWriter(fw, true);
                    //Instantiating the CSVWriter class
                    //Writing data to a csv file
//                    String header[] = {"user id",	"tweets" ,"content","created on","tweets id"};
                    //Writing data to the csv file
//                    writer.writeNext(header);


                        for (Status status2 : result.getTweets()) {
                            Date date = status2.getCreatedAt();
                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                            String strDate = dateFormat.format(date);
                            String tweetsID=Long.toString(status2.getId());
                            String data[] ={status2.getUser().getScreenName() , status2.getText(), strDate, tweetsID};
                            //Writing data to the csv file
                            writer.writeNext(data);
                            writer.flush();

//                            pw.println("@" + status2.getUser().getScreenName() + ":" + status2.getText());
//                            pw.println("Create on: " + status2.getCreatedAt() + " - ID: " + status2.getId());
                            if (status2.getId() < tweetID)
                                tweetID = status2.getId();
                        }
                        //Flushing data from writer to file
                        writer.flush();
                        System.out.println("Data entered");
                        System.out.println("No. of tweets stored: " + tweets.size());
                        System.out.println("remaining left: " + status.getRemaining());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }write
                   query.setMaxId(tweetID-1);

                }
            } catch (TwitterException e) {

                e.printStackTrace();
                System.out.println("Twitter API Limit reached");
                new Thread("App-exit") {
                    @Override
                    public void run() {
                        System.exit(0);
                    }
                }.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Data Crawled Successfully");

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread("App-exit") {
            @Override
            public void run() {
                System.exit(0);
            }
        }.start();
    }
}
