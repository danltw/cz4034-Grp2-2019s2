import csv
import re
from datetime import datetime
import pandas as pd
import os

directory = os.path.dirname(os.path.abspath(__file__))
data = pd.read_csv(os.path.join(directory, "finalOutput_remove_id - dateformatted.csv"))

#create data frame
df = pd.DataFrame(data)
# print(df)
print(df.dtypes)

df['DateTime'] = pd.to_datetime(df['created on'])
print(df.dtypes)

df['DateTime'] = df['DateTime'].dt.strftime('%Y-%m-%d %H:%M')
print(df['DateTime'])

df.to_csv(os.path.join(directory, "finalOutput_remove_id - dateformatted.csv"))