A. Assuming one is using xampp to run PHP file

1. Place the project folder under htdocs folder in xampp
2. Start the xampp service
3. Open command prompt
4. Change directory to "solr-8.4.1" folder, which is located inside the project folder
5. Run the following command: "bin\solr.cmd start" to start solr service
5a. Run the following command: "bin\solr.cmd stop -p 8983" to stop solr service
6. Open any web browser
7. Type in "localhost"
8. Direct xampp to the project folder

B. To delete all the records inside solr

1. Follow step 1 to step 4 under section A
2. Run the following command "java -Dc=twitter -jar example\exampledocs\post.jar delete.xml"
3. Refresh the webpage to view the changes made

C. To manually import data into solr
1. Follow step 1 to step 4 under section A
2. Run the following command "java -Dc=twitter -jar example\exampledocs\post.jar (filename).xml"
3. Refresh the webpage to view the changes made