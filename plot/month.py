import numpy as np
import seaborn as sns
import matplotlib as plt
import pandas as pd

sns.set(style="whitegrid")
sns.set(rc={'figure.figsize':(11.7,8.27)})

months = pd.read_csv("../data/uber_month.csv")

month = months[['month', 'count']]

ax0 = sns.barplot(x="month",y="count",data=month,palette="BuPu")
ax0.set(xlabel='month', ylabel='count')

ax0.figure.savefig("../pic/uber_month.png")
