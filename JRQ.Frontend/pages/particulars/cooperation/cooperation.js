// pages/particulars/cooperation/cooperation.js
const app = getApp()
var api = require('../../../util/api.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    image: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/4.2/%E5%95%86%E5%8A%A1%E5%90%88%E4%BD%9C.jpg',
    proiew: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/4.2/%E8%B5%84%E6%BA%90%E4%BB%8B%E7%BB%8D.png',
    selectArray: [{
        "id": "0",
        "text": "资金方",
        "type": 'INVESTOR'
      }, {
        "id": "1",
        "text": "项目方",
        "type": 'PROJECT'

      },
      {
        "id": "2",
        "text": "知识付费",
        "type": 'PAY_FOR_KNOWLEDGE'

      }, {
        "id": "3",
        "text": "考级培训",
        "type": 'EMPLOYS_TRAINING'
      }, {
        "id": "4",
        "text": "法务财税",
        "type": 'EMPLOYS_TRAINING'
      }, {
        "id": "5",
        "text": "其他服务",
        "type": 'EMPLOYS_TRAINING'
      }
    ],
    phone: '',
    agencyName: "",
    identityInfo: '',
    linkMan: '',
    files: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    /* 获取用户信息 */
    var that = this
    api.getMyInfo.call(this, app.getOpenid(), () => {
      that.setData({
        linkMan: that.data.myInfo.username,
        phone: that.data.myInfo.phone,
        agencyName: that.data.myInfo.company,
      })
      /* 验证信息是否完整 */
      if (typeof (this.data.linkMan) == 'undefined' || this.data.linkMan == ''
        || typeof (this.data.phone) == 'undefined' || this.data.phone == ''
        || typeof (this.data.agencyName) == 'undefined' || this.data.agencyName == '') {
        wx.showModal({
          title: '提示',
          content: '您的个人信息尚不完整，是否立刻前往填写？',
          success: function (res) {
            if (res.confirm) {
              wx.navigateTo({
                url: '../../me/modifyMyCard/modifyMyCard'
              })
            }
          }
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    /* 重新获取用户信息 */
    var that = this
    api.getMyInfo.call(this, app.getOpenid(), () => {
      that.setData({
        linkMan: that.data.myInfo.username,
        phone: that.data.myInfo.phone,
        agencyName: that.data.myInfo.company,
      })
    })
  },
  getDate: function(e) {
    console.log(e.detail.type)
    this.setData({
      type: e.detail.type
    })
  },
  linkManInput: function(e) {
    this.setData({
      linkMan: e.detail.value
    })
  },
  phoneInput: function(e) {
    this.setData({
      phone: e.detail.value
    })
  },
  agencyNameInput: function(e) {
    this.setData({
      agencyName: e.detail.value
    })
  },
  typeInput: function(e) {
    this.setData({
      type: e.detail.value
    })
  },
  onPublish: function() {
    api.getPartnership.call(
      this,
      this.data.phone,
      this.data.linkMan,
      this.data.agencyName,
      this.data.files,
      this.data.identityInfo,
      this.data.type,

    )
  },
  changeAvatar: function(e) {
    var _this = this
    wx.chooseImage({
      count: 6, // 默认9
      sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
      sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
      success: function(res) {
        var tempFilePaths = res.tempFilePaths;
        var newFile = []
        for (var i = 0; i < _this.data.files.length; i++) {
          newFile.push(_this.data.files[i])
        }
        newFile.push(res.tempFilePaths[0])
        _this.setData({
          files: newFile
        })
        wx.uploadFile({
          url: getApp().globalData.backendUrl + '/partnership/uploadImg',
          filePath: res.tempFilePaths[0], //要上传文件资源的路径 String类型 
          name: 'avatar',
          header: {
            "Content-Type": "multipart/form-data"
          },
          formData: {
            //和服务器约定的token, 一般也可以放在header中
            'session_token': wx.getStorageSync('session_token')
          },
          success: function(res) {
            var data = res.data;
            console.log(data + 'lll');
          }
        })
      }
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

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