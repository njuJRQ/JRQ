// pages/me/updateMe/updateMe.js
const app = getApp();
var api = require('../../../util/api.js')
var util = require('../../../util/util.js')

Page({

  /**
   * 页面的初始数据
   */
  data: {
  
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  
  },
  updateTo298: function () {
    api.updateMe(app.getOpenid(), '298', 298, util.getTodayDate())
  },
  updateTo998: function () {
    api.updateMe(app.getOpenid(), '998', 998, util.getTodayDate())
  },
  updateToEnterprise: function () {
    api.setMyUserAsEnterprise(app.getOpenid())
  }
})