import json
from flask import Flask, request
from tensorflow import keras
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
import numpy as np
from numpy.lib.function_base import append
from google.cloud import storage

############# model #####################
model = Sequential()
model.add(Dense(10, activation='relu', input_shape=[18,]))
model.add(keras.layers.Dropout(0.4))
model.add(Dense(10, activation='relu'))
model.add(Dense(1, activation='sigmoid'))
model.compile(loss='binary_crossentropy', optimizer='sgd', metrics=['accuracy'])

def hello_world(request):
    storage_client = storage.Client()
    bucket = storage_client.get_bucket('percobaan-demo')
    model_weights = bucket.blob('partsekian4.h5')
    model_weights.download_to_filename('/tmp/partsekian4.h5')
    model.load_weights('/tmp/partsekian4.h5')
    
    request_json = request.json['data']
    temp = []
    for i in request_json:
        temp.append(int(i))
    temp = np.array(temp)
    prediction = model.predict(temp.reshape(-1,18)).tolist()

    response_json = {"data" : prediction}

    return json.dumps(response_json)
    
   
