// pages/me/pay/pay.js
const app = getApp();
var api = require('../../../util/api.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    price: 0
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    api.getMyCredit.call(this, app.getOpenid())
  },

  bindCancel: function () {
    wx.navigateBack({})
  },

  bindSave: function (e) {
    var that = this;
    var amount = e.detail.value.amount;

    if (amount == "" || amount * 1 < 0) {
      wx.showModal({
        title: '错误',
        content: '请填写正确的充值金额',
        showCancel: false
      })
      return
    }
    /*test*/

    wx.request({
      url: app.globalData.backendUrl + "pay",
      method: "PUT",
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/json'
      },
      data: {
        openid: app.getOpenid(),
        price: amount
      },
      success: (res) => {
        if (res.statusCode == 200) {
          var requestPaymentParams = res.data
          wx.requestPayment({
            timeStamp: requestPaymentParams.timeStamp,
            nonceStr: requestPaymentParams.nonceStr,
            package: requestPaymentParams.pakcage,
            signType: requestPaymentParams.signType,
            paySign: requestPaymentParams.paySign,
            success: (res) => {
              wx.showToast({
                title: '充值成功',
                icon: 'success',
                duration: 1000
              })
              that.onLoad()
            }
          })
        } else if (res.statusCode == 404) {
          wx.showToast({
            title: '充值失败',
            icon: 'none',
            duration: 1000
          });
        }
      }
    })
  }
})