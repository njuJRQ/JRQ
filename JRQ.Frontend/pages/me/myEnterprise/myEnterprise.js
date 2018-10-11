// pages/me/myEnterprise.js
const app = getApp();
var api = require('../../../util/api.js')

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
    api.getMyEnterpriseAdmin.call(this, app.getOpenid())
  }
})