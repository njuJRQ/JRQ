// pages/modifyMyCard/modifyMyCard.js
const app = getApp()
var api = require('../../../util/api.js')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    labelArray: ['承兑汇票', '股票质押', '大宗减持', '定增', '短拆过桥', '企业信用贷', '供应链金融', '商业保理', '融资租赁', '股权融资', '并购重组', '壳资源', '基金产品', '资产证券化', '土地并购', '自定义'],
    allUserTags: [{
      name: '高科·智造',
      state: false
    }, {
      name: '医疗健康',
      state: false
    }, {
      name: '文娱·体游',
      state: false
    }, {
      name: 'AI·芯片',
      state: false
    }],
    showUserTagsModal: false,
    selectedUserTags: [],

    labelIndex: 0,
    myInfo: {
      face: 'http://junrongcenter.oss-cn-beijing.aliyuncs.com/default/default-pic.png',
      username: 'USERNAME',
      phone: 'phone',
      email: 'email',
      company: 'company',
      city: 'city',
      department: 'department',
      position: 'position',
      intro: 'intro'
    },
    newMyInfo: {}
  },

  userTagSelect: function(e) {
    var selectItem = e.target.id;
    var selectedUserTags = this.data.selectedUserTags;
    var newSelectedUserTags = [];
    var isAlreadyExists = false;
    for (var i = selectedUserTags.length - 1; i >= 0; i--) {
      if (selectedUserTags[i] !== selectItem) {
        newSelectedUserTags.push(selectedUserTags[i]);
      } else {
        isAlreadyExists = true;
      }
    }
    if (!isAlreadyExists) {
      newSelectedUserTags.push(selectItem);
    }
    this.setData({
      selectedUserTags: newSelectedUserTags
    })
    this.updateAllTag();
  },

  letShowUserTagsModal: function() {
    this.setData({
      showUserTagsModal: true
    })
  },

  userTagsModalCancel: function() {
    this.setData({
      showUserTagsModal: false
    })
  },

  userTagsModalConfirm: function() {
    var newMyInfo = this.data.newMyInfo;
    newMyInfo.label = this.data.selectedUserTags;
    this.setData({
      newMyInfo: newMyInfo,
      showUserTagsModal: false
    })
  },

  updateFace: function() {
    var that = this;
    wx.chooseImage({
      count: 1,
      sizeType: ['original', 'compressed'],
      sourceType: ['album', 'camera'],
      success: function(res) {
        var tempFilePath = res.tempFilePaths[0]
        console.log(tempFilePath)
        that.data.myInfo.face = tempFilePath
        that.data.newMyInfo.face = tempFilePath
        that.setData(that.data)
      },
    })
  },

  updateName: function(e) {
    this.data.newMyInfo.name = e.detail.value;
  },

  updatePhone: function(e) {
    this.data.newMyInfo.phone = e.detail.value;
  },

  updateEmail: function(e) {
    this.data.newMyInfo.email = e.detail.value;
  },

  updateCity: function(e) {
    this.data.newMyInfo.city = e.detail.value;
  },

  updateCompany: function(e) {
    this.data.newMyInfo.company = e.detail.value;
  },

  updateDepartment: function(e) {
    this.data.newMyInfo.department = e.detail.value;
  },

  updatePosition: function(e) {
    this.data.newMyInfo.position = e.detail.value;
  },

  updateIntro: function(e) {
    this.data.newMyInfo.intro = e.detail.value;
  },

  updateAllTag: function(e) {
    var newAllUserTags = [];
    for (var i = 0; i < this.data.allUserTags.length; i++) {
      if (this.data.selectedUserTags.indexOf(this.data.allUserTags[i].name) >= 0) {
        newAllUserTags.push({
          name: this.data.allUserTags[i].name,
          state: true
        })
      } else {
        newAllUserTags.push({
          name: this.data.allUserTags[i].name,
          state: false
        })
      }
    }
    this.setData({
      allUserTags: newAllUserTags
    })
  },

  bindLabelPickerChange: function(e) {
    this.setData({
      labelIndex: e.detail.value
    })
    this.data.newMyInfo.label = this.data.labelArray[this.data.labelIndex];
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this
    api.getClassificationList.call(this, () => {
      api.getMyInfo.call(this, app.getOpenid(), () => {
        /* 更新labelIndex */
        const label = that.data.myInfo.label
        const labelArray = that.data.labelArray
        var index = labelArray.findIndex((l) => l == label)
        if (index === -1) index = 0
        that.data.labelIndex = index
        /* 复制myInfo到newMyInfo中 */
        that.data.newMyInfo = that.data.myInfo
        that.selectedUserTags = label
        that.setData(that.data)
        that.updateAllTag();
      })
    })
  },

  onSave: function() {
    /* 检查输入合法性 */
    if (!(this.data.newMyInfo.phone === "" || /^1[34578]\d{9}$/.test(this.data.newMyInfo.phone))) {
      wx.showToast({
        title: '手机号码有误，请重填',
        icon: 'none'
      })
      return
    }
    if (!(this.data.newMyInfo.email === "" || /^\w+((.\w+)|(-\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+).[A-Za-z0-9]+$/.test(this.data.newMyInfo.email))) {
      wx.showToast({
        title: '邮箱地址有误，请重填',
        icon: 'none'
      })
      return
    }
    api.modifyMyInfo.call(this)
  }
})