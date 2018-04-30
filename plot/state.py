import numpy as np
import seaborn as sns
import matplotlib as plt
import pandas as pd

sns.set(style="whitegrid")
sns.set(rc={'figure.figsize':(11.7,8.27)})
sns.set(font_scale=0.65)

states = pd.read_csv("state.csv")
state = states[['state','count']]

ax1 = sns.barplot(x="count",y="state",data=state,palette="PRGn")
ax1.set(xlabel='count', ylabel='state')

ax1.figure.savefig("state.png")
