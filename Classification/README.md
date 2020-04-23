# Classification

The python files will preprocess and run classification/topic modelling tasks on the csv file.

## System Requirements
Ensure that your system has the following installed:

- Anaconda IDE with Jupyter Notebook.
- Python 3.7 or later.
- Python Packages Used:
    - pandas
    - re
    - nltk
    - string
    - os
    - numpy
    - pprint
    - spacy
    - multiprocessing
    - time
    - datetime
    - gensim
    - wordcloud
    - textblob
    - pyLDAvis
    - matplotlib
    - seaborn
    - sklearn
    - logging
    - warnings
    
## Using preprocess.ipynb
This file contains the python code to clean and preprocess the raw finalOutput_crawled.csv file from the crawler. Simply run the code to output a cleaned
csv file named finalOutput_cleaned.csv.

## Using classification.ipynb
This file contains the python code to perform classification and topic modelling on the crawled texts. The file should be run 2 times.

The first pass should be run with the following code commented:
```
df_en = pd.read_csv('finalOutput_scored_ensemblePredicted.csv')
en_score = df_en["ensemble_predict"]
select_en = en_score.iloc[:2000,].astype(int)
cohen_kappa_en = cohen_kappa_score(select_manual, select_en)
print("Inter Annotation Agreement - Ensemble Learning: ", cohen_kappa_en.astype(str))
print("Ensemble Learning Summary: ", classification_report(select_manual, select_en))
print("Ensemble Learning Scores: ", precision_recall_fscore_support(select_manual, select_en, average='weighted'))
```

Then run **ensemble learning.ipynb**.

Lastly, second pass of **classification.ipynb** again with the above codes uncommented.

A file **LDA_vis.html** will be generated. This file shows the intertopical distancing map with the words associated to each topic.
