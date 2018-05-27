'use strict'

var { NativeModules } = require('react-native')


var MomoPayment = NativeModules.MomoPaymentModule || {};

var MomoPaymentAndroid = {
    requestPayment(merchantname,merchantnamelabel,merchantcode,amount,description,language,username,fee, environment) {
        return MomoPayment.requestPayment(merchantname,merchantnamelabel,merchantcode,amount,description,language,username,fee, environment);
    },
};

module.exports = MomoPaymentAndroid;
