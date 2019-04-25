// pages/me/publishMyArticle/publishMyArticle.js
const app = getApp();
var api = require('../../../util/api.js')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    cooperation: [{
      title: "资源介绍",
      text: '全国运营，影响100w金融从业者，直接社群用户20w，涵盖基金、地金、拍照、一二级市场、证券、票据等领域的初级小白、资深从业者以及机构。',
      text_tex: '现面向金融界广招合作，金融知识、金融培训、考证考级、提供资金的业务方、一手项目方。',
      title_txt: '合作申请',
      time1: "【业务合作】资金方&项目方  刘总助：18013112074",
      time2: "【资源合作】课程& 培训 & 猎头 & 活动  朱总助：13013889616"

    }],
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
    publishPhotos: [],
    images: [],
    publishInputValue: "",
    publishType: "",
    linkMan: "",
    phone: "",
    agencyName: "",
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(){
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
                url: '../modifyMyCard/modifyMyCard'
              })
            }
          }
        })
      }
    })
  },
  onShow: function(options) {
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
  //获取textarea输入文本内容
  bindInputValue: function(e) {
    this.setData({
      publishInputValue: e.detail.value
    })
  },
  //获取textarea输入文本内容
  bindProjectInfo: function(e) {
    this.setData({
      projectInfo: e.detail.value
    })
  },

  //上次用户图片
  onUploadPhotos: function() {
    var that = this;
    wx.chooseImage({
      count: 3,
      sizeType: ['original', 'compressed'],
      sourceType: ['album', 'camera'],
      success: (res) => {
        wx.uploadFile({
          url: app.globalData.backendUrl + "uploadFeed",
          filePath: res.tempFilePaths[0],
          name: 'image',
          success: (response) => {
            var images = that.data.images;
            var photos = that.data.publishPhotos.concat(res.tempFilePaths[0]);
            images = images.concat(JSON.parse(response.data));
            that.setData({
              images: images,
              publishPhotos: photos
            })
          }
        })
      },
    })
  },

  //选择发布文章类型
  onChooseType: function(e) {
    this.setData({
      publishType: e.currentTarget.dataset.t
    })
    console.log(this.data.publishType)
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
  getDate: function(e) {
    console.log(e.detail.projectRef)
    this.setData({
      projectRef: e.detail.projectRef
    })
  },
  //发布文章
  onPublish: function() {
    api.addFeed.call(
      this,
      app.getOpenid(),
      this.data.publishType,
      this.data.publishInputValue,
      this.data.publishPhotos,
      this.data.phone,
      this.data.linkMan,
      this.data.agencyName,
      this.data.projectRef,
      this.data.projectInfo,
      this.data.images
    )
    console.log(this.data.images)

  }
})