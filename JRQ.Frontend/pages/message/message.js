// pages/message.js
//获取应用实例
const app = getApp()
Page({
  /**
   * 页面的初始数据
   */
  data: {
    

  },


  bindGetUserInfo(e) {
    console.log(e.detail.userInfo)
  },


  formSubmit: function (e) {
    wx.showToast({
      title: '已留言',
      icon: 'success'
    })
    var that = this;
    var liuyantext = e.detail.value.liuyantext; //获取表单所有name=liuyantext的值 
    var nickName = e.detail.value.nickname; //获取表单所有name=nickName的值 
    var headimg = e.detail.value.headimg; //获取表单所有name=headimg的值 
    wx.request({
      url: 'http://localhost/liuyanserver/liuyan.php?liuyantext=' + liuyantext + '&nickname=' + nickName + '&headimg=' + headimg,
      data: {
        liuyantext,
        nickName,
        headimg
      },
      header: { 'Content-Type': 'application/json' },
      success: function (res) {
        console.log(res.data)
        that.setData({
          re: res.data,
        })
        wx.hideToast();
      }
    })
  },

  onPullDownRefresh: function () {
    wx.showNavigationBarLoading();
    var that = this
    wx.request({
      url: 'http://localhost/liuyanserver/loadliuyan.php',
      headers: {
        'Content-Type': 'application/json'
      },
      success: function (res) {
        //将获取到的json数据，存在名字叫list的这个数组中
        that.setData({
          liuyanlist: res.data,
          //res代表success函数的事件对，data是固定的，liuyanlist是数组
        })
        // 隐藏导航栏加载框
        wx.hideNavigationBarLoading();
        // 停止下拉动作
        wx.stopPullDownRefresh();
      }
    })
  },

  //加载最新数据
  onLoad: function () {
    var that = this
    wx.request({
      url: 'http://localhost/liuyanserver/loadliuyan.php',
      headers: {
        'Content-Type': 'application/json'
      },
      success: function (res) {
        //将获取到的json数据，存在名字叫list的这个数组中
        that.setData({
          liuyanlist: res.data,
          //res代表success函数的事件对，data是固定的，liuyanlist是数组
        })
      }
    })
  }
})