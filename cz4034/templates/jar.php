
 <?php 
	$variable = $_POST['sID'];
	exec("del /f newlyCrawled.csv,xml-import.xml");
	exec("java -jar TwitterCrawl.jar $variable 1000",$response);
	$message=end($response);
	echo $message;        
	
?>

