// pages/particulars/contract/contract.js
const app = getApp();
var api = require('../../../util/api.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
documents:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    api.getContractList.call(this)
    // api.getDocumentList.call(this)

  },
  onTouchThisArticle: function (e) {
    var id = e.currentTarget.dataset.id //获取当前文章id
    console.log(id)
    var kind = e.currentTarget.dataset.kind
    wx.navigateTo({
      url: '../../articleDetail/contractDetail/contractDetail?id=' + id
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})