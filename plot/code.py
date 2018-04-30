import numpy as np
import seaborn as sns
import matplotlib as plt
import pandas as pd

sns.set(style="whitegrid")
sns.set(rc={'figure.figsize':(11.7,8.27)})
sns.set(font_scale=0.6)

codes = pd.read_csv("code.csv")
code = codes[['code','count']]

ax = sns.barplot(x="code",y="count",data=code,palette="deep")
ax.set(xlabel='code', ylabel='count')

ax.figure.savefig("code.png")
