import React from 'react';
import { NativeEventEmitter, NativeModules } from 'react-native';
const { WaltzImp } = NativeModules;

class Waltz extends NativeEventEmitter {
    constructor(nativeModule){
        super(nativeModule);
        this.initManager = nativeModule.initManager;
        this.login = nativeModule.login;
        this.showQR = nativeModule.showQR;
    }
}

export default new Waltz(WaltzImp)