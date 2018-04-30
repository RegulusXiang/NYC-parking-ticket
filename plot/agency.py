import numpy as np
import seaborn as sns
import matplotlib as plt
import pandas as pd

sns.set(style="whitegrid")
sns.set(rc={'figure.figsize':(11.7,8.27)})
# sns.set(font_scale=0.65)

agencies = pd.read_csv("agency.csv")
agency = agencies[['agency','count']]

ax = sns.barplot(x="agency",y="count",data=agency,palette="RdBu")
ax.set(xlabel='agency', ylabel='count')

ax.figure.savefig("agency.png")
