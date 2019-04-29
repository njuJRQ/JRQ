// pages/modifyMyCard/modifyMyCard.js
const app = getApp()
var api = require('../../../util/api.js')

Page({
  /**
   * 页面的初始数据
   */
  data: {
    labelArray: ['承兑汇票', '股票质押', '大宗减持', '定增', '短拆过桥', '企业信用贷', '供应链金融', '商业保理', '融资租赁', '股权融资', '并购重组', '壳资源', '基金产品', '资产证券化', '土地并购', '自定义'],
    userTag1: [{
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
      }, {
        name: "交通物流",
        state: false
      }, {
        name: "能源环保电",
        state: false,
      }, {
        name: '教育.养老',
        state: false
      },
      {
        name: "材料.化工",
        state: false
      }, {
        name: '电商.消费',
        state: false
      }, {
        name: '土地.物业',
        state: false
      }, {
        name: '汽车.军工',
        state: false
      }, {
        name: '农林.牧海',
        state: false
      }, {
        name: '竹子.园林',
        state: false
      }, {
        name: '航空.通信',
        state: false
      }, {
        name: '生活.服务',
        state: false
      }, {
        name: 'TML.大数据',
        state: false
      }, {
        name: '物联',
        state: false
      }, {
        name: '金融.其他'
      }
    ],
    userTag2: [{
      name: '并购.重组',
      state: false
    }, {
      name: '定增.转股',
      state: false
    }, {
      name: '壳与.牌照',
      state: false
    }, {
      name: '质押.抵押',
      state: false
    }, {
      name: '短融.过桥',
      state: false
    }, {
      name: '大宗.交易',
      state: false
    }, {
      name: '信托.资管',
      state: false
    }, {
      name: '债券.ABS',
      state: false
    }, {
      name: '保理.融租',
      state: false
    }, {
      name: '港股.美股',
      state: false
    }, {
      name: '公募.私募',
      state: false
    }, {
      name: 'IPO.挂牌'
    }, {
      name: '不良资产',
      state: false
    }, {
      name: '银行业务',
      state: false
    }, {
      name: '种子天使',
      state: false
    }, {
      name: 'A轮',
      state: false
    }, {
      name: 'B轮及以上',
      state: false
    }],
    showUserTagsModal: false,
    selectedUserTags: [],
    showUserTagsModal2: false,
    selectedUserTags2: [],

    labelIndex: 0,
    myInfo: {
      face: 'http://junrongcenter.oss-cn-beijing.aliyuncs.com/default/default-pic.png',
      username: 'USERname',
      phone: 'phone',
      email: 'email',
      company: 'company',
      city: 'city',
      department: 'department',
      position: 'position',
      intro: 'intro',
      card: '',
    },
    cardDisplay: 'none',
    uploadOrModifyCard: '上传名片',
    uploadOrModifyCardPic: '/pages/me/img/revise.png',
    newMyInfo: {},
  },

  userTagSelect1: function(e) {
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
    if (newSelectedUserTags.length <= 3) {
      this.setData({
        selectedUserTags: newSelectedUserTags
      })
    }
    this.updateAllTag1();
  },

  userTagSelect2: function(e) {
    var selectItem = e.target.id;
    var selectedUserTags = this.data.selectedUserTags2;
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
    if (newSelectedUserTags.length <= 3) {
      this.setData({
        selectedUserTags2: newSelectedUserTags
      })
    }
    this.updateAllTag2();
  },

  letShowUserTagsModal: function() {
    this.setData({
      showUserTagsModal: true
    })
  },

  letShowUserTagsModal2: function() {
    this.setData({
      showUserTagsModal2: true
    })
  },

  userTagsModalCancel: function() {
    this.setData({
      showUserTagsModal: false,
      showUserTagsModal2: false,
    })
    console.log(this.data.showUserTagsModal)
  },

  userTagsModalConfirm1: function() {
    var newMyInfo = this.data.newMyInfo;
    newMyInfo.label = this.data.selectedUserTags;
    this.setData({
      newMyInfo: newMyInfo,
      showUserTagsModal: false
    })
  },

  userTagsModalConfirm2: function() {
    var newMyInfo = this.data.newMyInfo;
    newMyInfo.label2 = this.data.selectedUserTags2;
    this.setData({
      newMyInfo: newMyInfo,
      showUserTagsModal2: false
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

  updatename: function(e) {
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

  updateCard: function () {
    var that = this;
    wx.chooseImage({
      count: 1,
      sizeType: ['original', 'compressed'],
      sourceType: ['album', 'camera'],
      success: function (res) {
        var tempFilePath = res.tempFilePaths[0]
        console.log(tempFilePath)
        that.data.myInfo.card = tempFilePath
        that.data.newMyInfo.card = tempFilePath
        that.data.cardDisplay='block'
        that.data.uploadOrModifyCard='修改名片'
        that.setData(that.data)
        
        api.modifyMyCard.call(this)
      },
    })
  },

  updateAllTag1: function(e) {
    var newAllUserTags = [];
    for (var i = 0; i < this.data.userTag1.length; i++) {
      if (this.data.selectedUserTags.indexOf(this.data.userTag1[i].name) >= 0) {
        newAllUserTags.push({
          name: this.data.userTag1[i].name,
          state: true
        })
      } else {
        newAllUserTags.push({
          name: this.data.userTag1[i].name,
          state: false
        })
      }
    }
    this.setData({
      userTag1: newAllUserTags
    })
  },

  updateAllTag2: function(e) {
    var newAllUserTags = [];
    for (var i = 0; i < this.data.userTag2.length; i++) {
      if (this.data.selectedUserTags2.indexOf(this.data.userTag2[i].name) >= 0) {
        newAllUserTags.push({
          name: this.data.userTag2[i].name,
          state: true
        })
      } else {
        newAllUserTags.push({
          name: this.data.userTag2[i].name,
          state: false
        })
      }
    }
    this.setData({
      userTag2: newAllUserTags
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
        /* 判断是否有名片 */
        //console.log(that.data.myInfo.card);
        if (typeof (that.data.myInfo.card) != 'undefined' && that.data.myInfo.card != '') {
          that.data.cardDisplay = 'block'
          that.data.uploadOrModifyCard = '修改名片'
        }
        that.setData(that.data)
        that.updateAllTag1();
        that.updateAllTag2();
      })
    })

    api.getMyCard.call(this, app.getOpenid)
    
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
    console.log(this.data.newMyInfo)
    api.modifyMyInfo.call(this)
  }
})