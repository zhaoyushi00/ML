# 手动定义一个均方差误差函数
import numpy as np
def loss_function(x, y, weight, bias):
    y_hat = weight*x + bias
    loss = y_hat - y
    cost = np.sum(loss**2)/(2*len(x))
    return cost