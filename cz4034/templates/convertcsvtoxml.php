<?php
if($_POST['action'] == 'convert') {

	//Creates XML string and XML document using the DOM 
	$xml = new DomDocument('1.0', 'UTF-8'); 

	// Set the formatOutput attribute of xml to true
	$xml->formatOutput = true; 

	//Add root node
	$root = $xml->createElement('add');
	$xml->appendChild($root);

if (($handle = fopen("newlyCrawled.csv", "r")) !== FALSE) {
    while (($data = fgetcsv($handle, 1000, ",")) !== FALSE) {
		$num = count($data);
		if (htmlspecialchars($data[1]) !== '') {
			$entry = $xml->createElement('doc');
			$root->appendChild($entry);
			for ($c=0; $c < $num; $c++) {
				if ($c != 3)
					$field = $entry->appendChild($xml->createElement("field", htmlspecialchars($data[$c])));
				if ($c == 0)
					$field->setAttribute("name", "user_id");
				else if ($c == 1)
					$field->setAttribute("name", "tweets_content");
				else if ($c == 2)
					$field->setAttribute("name", "created_on");
			}
		}
    }
    fclose($handle);
}

// Save as file
$xml->save('xml-import.xml'); // save as file
exec("java -Dc=twitter -jar ..\solr-8.4.1\\example\\exampledocs\post.jar ..\\templates\xml-import.xml");
}

?>
