// pages/me/updateMe/updateMe.js
const app = getApp();
var api = require('../../../util/api.js')
var util = require('../../../util/util.js')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    price: 0,
    price298: 298,
    price998: 998,
    priceEnterprise: '免费',
    isHideModalput: true,
    username: '',
    password: '',
    isAdminUsernameExistent: false,
    isEnterprise: false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onShow: function (options) {
    api.getMyCredit.call(this, app.getOpenid())
    api.getLevelList.call(this)
    api.getPrivilegeList.call(this)
  },

  updateTo298: function () {
    wx.showModal({
      title: '确认升级',
      content: '你确认以¥298的价格升级为298用户吗？',
      success: (res) => {
        if (res.confirm)
          api.updateMe.call(this, app.getOpenid(), '298', 298, util.getTodayDate())
      }
    })
  },

  updateTo998: function () {
    wx.showModal({
      title: '确认升级',
      content: '你确认以¥998的价格升级为998用户吗？',
      success: (res) => {
        if (res.confirm)
          api.updateMe.call(this, app.getOpenid(), '998', 998, util.getTodayDate())
      }
    })
  },

  updateToEnterprise: function () {
    var that = this
    wx.showModal({
      title: '确认升级',
      content: '你确认升级为企业用户吗？',
      success: (res) => {
        if (res.confirm) {
          that.setData({
            isHideModalput: false
          })
        }
      }
    })
  },

  //取消按钮
  cancel: function () {
    this.setData({
      isHideModalput: true
    });
  },
  //确认
  confirm: function () {
    this.setData({
      isHideModalput: true
    })
    api.setMyUserAsEnterprise.call(this, app.getOpenid(), this.data.username, this.data.password)
  },
    
  updateUsername: function (e) {
    this.data.username = e.detail.value;
    var that = this
    api.isAdminUsernameExistent.call(this, this.data.username, (isAdminUsernameExistent) => {
      that.setData({
        isAdminUsernameExistent: isAdminUsernameExistent
      })
    })
  },

  updatePassword: function (e) {
    this.data.password = e.detail.value;
  },

  goToPay: function () {
    wx.navigateTo({
      url: '../pay/pay'
    })
  }
})