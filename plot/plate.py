import numpy as np
import seaborn as sns
import matplotlib as plt
import pandas as pd

sns.set(style="whitegrid")
sns.set(rc={'figure.figsize':(11.7,8.27)})
sns.set(font_scale=0.6)

plates = pd.read_csv("plate.csv")
plate = plates[['plate','count']]

ax = sns.barplot(x="plate",y="count",data=plate,palette="PiYG")
ax.set(xlabel='plate', ylabel='count')

ax.figure.savefig("plate.png")
