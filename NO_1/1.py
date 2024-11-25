from keras import models
from keras.src.datasets import mnist
from keras.src.layers import Conv2D, MaxPooling2D, Dropout, Flatten, Dense
from keras.src.utils import to_categorical

(X_train_image, y_train_lable), (X_test_image, y_test_lable) = mnist.load_data()

X_train = X_train_image.reshape(60000, 28, 28, 1)
X_test = X_test_image.reshape(10000, 28, 28, 1)
# 特征转换为one—hot编码
y_train = to_categorical(y_train_lable, num_classes=10)
y_test = to_categorical(y_test_lable, num_classes=10)



model = models.Sequential() # 用顺序模型建立网络结构
model.add(Conv2D(32,(3,3), activation='relu', input_shape=(28,28,1)))
model.add(MaxPooling2D(pool_size=(2,2)))
model.add(Conv2D(64,(3,3), activation='relu'))
model.add(MaxPooling2D(pool_size=(2,2)))
# Dropout 层来减少神经网络训练过程中的过拟合问题
# Dropout 层会在训练过程中随机丢弃一部分神经元的输出，从而提高模型的泛化能力。
model.add(Dropout(0.25))
model.add(Flatten())
model.add(Dense(128, activation='relu'))
model.add(Dropout(0.5))
model.add(Dense(10, activation='softmax'))
# 编译模型

model.compile(optimizer='rmsprop', loss='categorical_crossentropy', metrics=['accuracy'])

model.fit(X_train, y_train,
          validation_split=0.3,
          epochs=5,
          batch_size=128)