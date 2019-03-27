// pages/particulars/cooperation/cooperation.js
const app = getApp()
var api = require('../../../util/api.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {

    cooperation: {
      title: "资源介绍",
      text: '全国运营，影响100w金融从业者，直接社群用户20w，涵盖基金、地金、拍照、一二级市场、证券、票据等领域的初级小白、资深从业者以及机构。',
      text_tex: '现面向金融界广招合作，金融知识、金融培训、考证考级、提供资金的业务方、一手项目方。',
      title_txt: '合作申请',
      time: "xxxx",
  
    },
    selectArray: [{
      "id": "0",
      "text": "投资方",
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
 
  },
  getDate: function (e) {
    console.log(e.detail.type)
    this.setData({
      type: e.detail.type
    })
  },
  linkManInput:function(e){
    this.setData({
      linkMan: e.detail.value
    })
  },
  phoneInput:function(e){
    this.setData({
      phone:e.detail.value
    })
  },
  agencyNameInput:function(e){
    this.setData({
      agencyName:e.detail.value
    })
  },
  typeInput:function(e){
    this.setData({
      type:e.detail.value
    })
  },
  onPublish:function(){
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