import matplotlib.pyplot as plt
import seaborn as sns
import pandas as pd

sns.set()
sns.set(rc={'figure.figsize':(11.7,8.27)})
sns.set(font_scale=0.7)

times = pd.read_csv("../data/uber_time.csv")
time = times.pivot("min", "hour", "count")

# Draw a heatmap with the numeric values in each cell
f, ax = plt.subplots(figsize=(9, 6))
# sns.heatmap(time, annot=False, fmt="d", linewidth=.5,ax=ax)
sns.heatmap(time,ax=ax,cmap="PuBu")

ax.figure.savefig("../pic/uber_time.png")
