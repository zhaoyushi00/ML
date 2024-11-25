# 梯度下降
import numpy as np

from NO_3.MSE import loss_function


def gradient_descent(x, y, w, b, lr, iter):
    l_history = np.zeros(iter) # 初始化记录梯度下降过程中损失的数组
    w_history = np.zeros(iter) # 初始化记录梯度下降过程中权重的数组
    b_history = np.zeros(iter) # 初始化记录梯度下降过程中偏置的数组
    for i in range(iter): # 进行梯度下降的迭代, 就是下多少级台阶
        y_hat = w*x + b # 这是向量化运算实现的假设函数
        loss = y_hat-y # 这是中间过程, 求得的是假设函数预测的 y'和真正的 y 值间的差值
        derivative_w = x.T.dot(loss)/len(x) # 对权重求导, len(X)是样本总数
        derivative_b = sum(loss)*1/len(x) # 对偏置求导
        w = w - lr*derivative_w # 结合学习速率 alpha 更新权重
        b = b - lr*derivative_b # 结合学习速率 alpha 更新偏置
        l_history[i] = loss_function(x, y, w, b) # 梯度下降过程中损失的历史记录
        w_history[i] = w # 梯度下降过程中权重的历史记录
        b_history[i] = b # 梯度下降过程中偏置的历史记录
    return l_history, w_history, b_history # 返回梯度下降过程中的数据