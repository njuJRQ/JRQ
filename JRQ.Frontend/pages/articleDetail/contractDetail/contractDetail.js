// pages/articleDetail/contractDetail/contractDetail.js
const app = getApp();
var api = require('../../../util/api.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    image: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/yiqu/WechatIMG189.jpeg',
    images: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/yiqu/WechatIMG191.png',
    document: {},
    isShowPrice: false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    api.getIOSQualification.call(this, (res) => {
      this.setData({
        isShowPrice: res
      })
    })
    api.getLevelList.call(this, (levels) => {
      levels.forEach((level) => {
        /*console.log(level)*/
        switch (level.name) {
          case "298":
            that.data.discount298 = level.courseDiscountedRatio;
            break;
          case "998":
            that.data.discount998 = level.courseDiscountedRatio;
            break;
          default:
            break;
        }
      })
      that.setData(that.data)
    })
    try {
      api.getDocument.call(this, options.id)
    } catch (e) {
      console.log('获取编号为' + options.id + '的文档失败')
    }

  },
  onDownload: function() {
    var condition = true
    api.getIOSQualification.call(this, (res) => {
      console.log(res)
      condition = res
      if (condition) {
        this.setData({
          isShow: false
        })
      }
    })
    var that = this
    if (this.data.document.attachment) {
      api.getMyUser.call(this, app.getOpenid(), (res) => {
        if (res.levelName == "common") {
          wx.showModal({
            title: '您的权限为普通用户，无法下载',
            content: '即将跳转到升级账户页面',
            success: (res) => {
              if (res.confirm) {
                wx.navigateTo({
                  url: '/pages/me/updateMe/updateMe',
                })
              }
            }
          })

        } else {
          api.uploadDocument.call(this, this.data.document.attachment, () => {
            that.setData({
              isDownLoadAttachment: true
            })
          })
        }
      })
    } else {
      wx.showModal({
        content: '该项目不存在附件',
        showCancel: false
      })
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