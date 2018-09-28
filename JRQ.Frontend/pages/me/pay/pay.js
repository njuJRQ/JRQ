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
    /*wxpay.wxpay(app, amount, 0, "/pages/my/index");*/
  }
})