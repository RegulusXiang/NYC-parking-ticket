import numpy as np
import seaborn as sns
import matplotlib as plt
import pandas as pd

sns.set(style="whitegrid")
sns.set(rc={'figure.figsize':(11.7,8.27)})
sns.set(font_scale=0.6)

bodies = pd.read_csv("body.csv")
body = bodies[['body','count']]

ax = sns.barplot(x="body",y="count",data=body,palette="BrBG")
ax.set(xlabel='body', ylabel='count')

ax.set_xticklabels(ax.get_xticklabels(),rotation=45)
ax.figure.savefig("body.png")
