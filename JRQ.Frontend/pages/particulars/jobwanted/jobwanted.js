// pages/particulars/jobwanted/jobwanted.js
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
    array: ['苏州', '上海', '广东', '南京'],
    objectArray: [{
        id: 0,
        name: '苏州'
      },
      {
        id: 1,
        name: '上海'
      },
      {
        id: 2,
        name: '广东'
      },
      {
        id: 3,
        name: '南京'
      }
    ],
    index: 0,
    image: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/4.2/%E6%8B%9B%E8%81%98.png',
    jobCardItems: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.loadPositionsByCity(this.data.objectArray[this.data.index].name);

  },
  bindPickerChange: function(e) {
    this.setData({
      index: e.detail.value
    })
    this.loadPositionsByCity(this.data.objectArray[this.data.index].name);
  },

  loadPositionsByCity: function(city) {
    var that = this;
    wx.request({
      url: app.globalData.backendUrl + "jobCard/findByCity",
      data: {
        city: city
      },
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: (res) => {
        res.data.jobCardItems.forEach(item => {
          item.image = app.globalData.picUrl + item.image
        })
        that.setData({
          jobCardItems: res.data.jobCardItems
        })
      }
      
    })
  },

  checkResume: function(event){
    var dataset = event.currentTarget.dataset;

    var that = this;
    api.getMyInfo.call(this, app.getOpenid(), () => {    
      /* 复制myInfo到newMyInfo中 */
      that.data.newMyInfo = that.data.myInfo    
      that.setData(that.data)

      var info = "?uid=" + app.getOpenid() + "&jid=" + dataset.jid + "&expectPosition=" + dataset.expectposition + "&city=" + dataset.city;

      wx.navigateTo({
        url: '../resume/resume' + info
      })

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