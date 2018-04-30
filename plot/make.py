import numpy as np
import seaborn as sns
import matplotlib.pyplot as plt
import pandas as pd

sns.set(style="whitegrid")
sns.set(rc={'figure.figsize':(11.7,8.27)})
sns.set(font_scale=0.55)

makes = pd.read_csv("make.csv")
make = makes[['make','count']]

ax = sns.barplot(x="make",y="count",data=make,palette="RdYlBu")
ax.set(xlabel='make', ylabel='count')

ax.set_xticklabels(ax.get_xticklabels(),rotation=45)
ax.figure.savefig("make.png")
