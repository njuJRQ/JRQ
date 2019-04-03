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
    array: ['北京', '上海', '广东', '南京'],
    objectArray: [
      {
        id: 0,
        name: '北京'
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
image:'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/4.2/%E6%8B%9B%E8%81%98.png',
    jobCardItems: [{
      id: 0,
      title: '张三',
      money: '10k-14k',
      text: '着棋设计 未融资',
      site: '南京 玄武区 大行宫 ',
      time: '3年-5年',
      tainer: '本科',
      image: '/img/user.png',
      name:'许善-CEO'
    },
      {
        id: 0,
        title: '李四',
        money: '10k-14k',
        text: '着棋设计 未融资',
        site: '南京 玄武区 大行宫',
        time: '3年-5年',
        tainer: '本科',
        image: '/img/user.png',
        name: '许善-CEO'
      },
      {
        id: 0,
        title: '王五',
        money: '10k-14k',
        text: '着棋设计 未融资',
        site: '南京 玄武区 大行宫',
        time: '3年-5年',
        tainer: '本科',
        image: '/img/user.png',
        name: '许善-CEO'
      }, {
        id: 0,
        title: '李某某',
        money: '10k-14k',
        text: '着棋设计 未融资',
        site: '南京 玄武区 大行宫',
        time: '3年-5年',
        tainer: '本科',
        image: '/img/user.png',
        name: '许善-CEO'
      }, {
        id: 0,
        title: '张亮',
        money: '10k-14k',
        text: '着棋设计 未融资',
        site: '南京 玄武区 大行宫',
        time: '3年-5年',
        tainer: '本科',
        image: '/img/user.png',
        name: '许善-CEO'
      },]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    api.getAll.call(this,(res)=>{
      console.log(res)
    })
  },
  bindPickerChange: function (e) {
    console.log('picker发送选择改变，携带值为', e.detail.value)
    this.setData({
      index: e.detail.value
    })
  },
  onTouchThisArticle: function (e) {
    var id = e.currentTarget.dataset.id //获取当前文章id
    var kind = e.currentTarget.dataset.kind
    wx.navigateTo({
      url: '../resume/resume?id=' + id
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