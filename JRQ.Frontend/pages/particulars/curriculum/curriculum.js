//index.js
// 获取应用实例
const app = getApp()
var api = require('../../../util/api.js')
const {
  bg1
} = require('../../../util/data.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    courseList: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    if (options.courseGroupId) {
      var courseGroupId = options.courseGroupId;
      var that = this
      wx.request({
        url: app.globalData.backendUrl + "courseGroup/findById",
        header: {
          'Authorization': 'Bearer ' + app.getToken(),
          'content-type': 'application/x-www-form-urlencoded'
        },
        method: 'GET',
        data: {
          id: courseGroupId
        },
        success: (res) => {
          res.data.courseGroupItem.courseList.forEach(item => {
            item.image = app.globalData.picUrl + item.image
          })
          that.setData({
            courseList: res.data.courseGroupItem.courseList
          })
        }
      })
    } else {
      api.getCourseList.call(this)
    }
  },
  //展示文章详情
  onTouchThisArticle: function(e) {
    var id = e.currentTarget.dataset.id //获取当前文章id
    var kind = e.currentTarget.dataset.kind
    wx.navigateTo({
      url: '../../articleDetail/courseDetail/courseDetail?id=' + id
    })
  },
  /** 
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})