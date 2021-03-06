const app = getApp()
var api = require('../../../util/api.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    selectArray: [{
        "id": "0",
        "text": "实控人",
        "projectRef": 'ACTUAL_CONTROLLER'
      }, {
        "id": "1",
        "text": "核心股东",
        "projectRef": 'CORE_OF_SHAREHOLDERS'

      },
      {
        "id": "2",
        "text": "雇员",
        "projectRef": 'EMPLOYEE'

      }, {
        "id": "3",
        "text": "一手第三方",
        "projectRef": 'THIRD_PARTY'
      }
    ],
    phone: '',
    agencyName: "",
    identityInfo: '',
    linkMan: '',
    projectRef: '',
    projectInfo: '',
    marketType: 'GOLD_MARKET',
    image:''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    console.log(options.marketType)
    this.setData({
      marketType: options.marketType
    })
    api.getImage.call(this, this.data.marketType)

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
 projectInfoInput:function(e){
    this.setData({
      projectInfo:e.detail.value
    })
  },
  getDate: function(e) {
    console.log(e.detail.projectRef)
    this.setData({
      projectRef: e.detail.projectRef
    })
  },
  onPublish: function() {
    // console.log(this.data.marketType+'4556')
    let agencyName = this.data.agencyName
    let phone = this.data.phone
    let linkMan = this.data.linkMan
    let projectInfo = this.data.projectInfo
    if (agencyName == '') {
      wx: wx.showToast({
        title: '请输入机构名'
      })
      return false
    }
    else if (phone == '') {
      wx: wx.showToast({
        title: '请输入手机号'
      })
      return false
    }
    else if (linkMan == '') {
      wx: wx.showToast({
        title: '请输入联系人'
      })
      return false
    }
    else if (projectInfo == '') {
      wx: wx.showToast({
        title: '请输入项目信息'
      })
      return false
    }
    else {
      api.businessAdd.call(
        this,
        app.getOpenid(),
        this.data.linkMan,
        this.data.agencyName,
        this.data.projectRef,
        this.data.projectInfo,
        this.data.phone,
        this.data.marketType,
      )
      console.log(this.data.marketType+'456789')
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