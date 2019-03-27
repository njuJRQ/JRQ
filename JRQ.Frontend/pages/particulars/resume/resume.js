// pages/particulars/resume/resume.js
const app = getApp();
var api = require('../../../util/api.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    jobCardItem : {
      name: '胡某某',
      studu: '南京林业大学-平面设计',
      image: "/img/user.png",
      icon_user:'/img/icon_user.png',
      icon_age:'/img/icon_age.png',
      icon_eduo:"/img/icon_eduo.png",
      experience:'两年实习经历',
      expect:'求职期望',
      type:'平面设计师,南京',
      money:'10k-14k',
      txt:"行业不限",
      practice:'实习经历',
      tilte_name:"南京某某某公司",
      time:'2018.5.16-2019.9.26',
      
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options.id+'4556')
    try {
      api.getFindById.call(this, options.id)
    } catch (e) {
      console.log('获取编号为' + options.id + '的文档失败')
    }
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