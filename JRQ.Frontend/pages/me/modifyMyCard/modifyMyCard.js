// pages/modifyMyCard/modifyMyCard.js
const app = getApp()
const api = require('../../../util/api.js')
const util = require("../../../util/util.js")

Page({

  /**
   * 页面的初始数据
   */
  data: {
    labelArray: ['承兑汇票', '股票质押', '大宗减持', '定增', '短拆过桥', '企业信用贷', '供应链金融', '商业保理', '融资租赁', '股权融资', '并购重组', '壳资源', '基金产品', '资产证券化', '土地并购', '自定义'],
    labelIndex: 0,
    myInfo: {
      face: '../../../default/default-pic.png',
      username: 'USERNAME',
      phone: '13952146595',
      email: '13952146595@163.com',
      company: '美国永辉公司',
      city: '亚太区',
      department: 'IT技术部',
      position: 'T1初级经理',
      intro: '我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。'
    }
  },

  updateFace: function() {
    const that = this;
    wx.chooseImage({
      count: 1,
      sizeType: ['original', 'compressed'],
      sourceType: ['album', 'camera'],
      success: (res) => {
        const tempFilePath = res.tempFilePaths[0]
        console.log(tempFilePath)
        that.data.myInfo.face = tempFilePath
        that.data.newFace = tempFilePath
        that.setData(that.data)
      },
    })
  },

  bindLabelPickerChange: function(e) {
    this.setData({
      labelIndex: e.detail.value
    })
    this.data.myInfo.label = this.data.labelArray[this.data.labelIndex];
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    const that = this
    api.getClassificationList.call(this, () => {
      api.getMyInfo.call(this, app.getOpenid(), () => {
        /* 更新labelIndex */
        const label = that.data.myInfo.label
        const labelArray = that.data.labelArray
        let index = labelArray.findIndex((l) => l == label)
        if (index === -1) index = 0
        that.data.labelIndex = index
        /* 复制myInfo到newMyInfo中 */
        //that.data.newMyInfo = that.data.myInfo
        that.setData(that.data)
      })
    })
  },

  onSave: function(e) {
    let newMyInfo = e.detail.value
    newMyInfo.openid = app.getOpenid()
    newMyInfo.label = this.data.labelArray[newMyInfo.label]
    if (this.data.newFace) {
      newMyInfo.face = this.data.newFace
    }
    console.log(newMyInfo)
    /* 检查输入合法性 */
    if (!(newMyInfo.phone === "" || /^1[34578]\d{9}$/.test(newMyInfo.phone))) {
      wx.showToast({
        title: '手机号码有误，请重填',
        icon: 'none'
      })
      return
    }
    if (!newMyInfo.email == "" &&
        !(/^\w+((.\w+)|(-\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+).[A-Za-z0-9]+$/.test(newMyInfo.email))) {
      wx.showToast({
        title: '邮箱地址有误，请重填',
        icon: 'none'
      })
      return
    }

    api.uploadFormId(app.getOpenid(), e.detail.formId)
      .then((res) => api.modifyMyInfo(newMyInfo))
      .then((res) => {
        wx.hideLoading()
        wx.showToast({
          title: '修改成功',
          icon: 'success',
          duration: 1000,
          mask: true
        })
        setTimeout(() => {
          wx.navigateBack()
        }, 1000)
      })
      .catch(() => {
        wx.showToast({
          title: '修改失败，请检查网络',
          icon: 'none'
        })
      })
  }
})