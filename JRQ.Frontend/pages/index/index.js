//index.js
//获取应用实例
const app = getApp()
var api = require('../../util/api.js')
const {
  bg1
} = require('../../util/data.js')

Page({
  data: {
    array: [{
        id: '0',
        src: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/%E8%AF%BE%E7%A8%8B.png',
        text: '课程培训'
      },
      {
        id: '1',
        src: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/%E8%80%83%E8%AF%81%E8%80%83%E7%BA%A7.png',
        text: '考证考级'
      },
      {
        id: '2',
        src: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/%E6%9C%BA%E6%9E%84%E6%8B%9B%E8%81%98.png',
        text: '机构招聘'
      },
      {
        id: '3',
        src: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/%E6%8A%A5%E5%91%8A%E6%96%87%E4%B9%A6.png',
        text: '报告文档'
      }, {
        id: '4',
        src: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/hehuo.png',
        text: "事业合伙"
      },
      {
        id: "5",
        src: "https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/%E5%90%88%E5%90%8C.png",
        text: "标准合同"
      },
      {
        id: "6",
        src: "https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/%E5%95%86%E5%8A%A1%E5%90%88%E4%BD%9C.png",
        text: "商务合作"
      },
      {
        id: "7",
        src: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/%E5%9F%8E%E5%B8%82%E7%A4%BE%E7%BE%A4.png',
        text: "城市社群"
      }

    ],
    moreType: true,
    isShowView: true,
    isShow: true,
    isShowPrice: true,
    height: 290,
    height_video: 400,
    image: 'http://junrongcenter.oss-cn-beijing.aliyuncs.com/default/default-pic.png',
    showView: true,
    cards: [{
        thumbnail: 'http://junrongcenter.oss-cn-beijing.aliyuncs.com/default/default-pic.png',
        articleName: '什么是金融？',
        summary: '一般指与货币流通及银行有关的东西'
      },
      {
        thumbnail: 'http://junrongcenter.oss-cn-beijing.aliyuncs.com/default/default-pic.png',
        articleName: '什么是金融？',
        summary: '一般指与货币流通及银行有关的东西'

      },
      {
        thumbnail: 'http://junrongcenter.oss-cn-beijing.aliyuncs.com/default/default-pic.png',
        articleName: '什么是金融？',
        summary: '一般指与货币流通及银行有关的东西'
      }
    ],

    articles: [],
    videos: [],
    kind: null,
    currentKind: null,
    searchCondition: null,
    lastId: "",
    lastIdType: "",
    flag: false,
    bg1: bg1,
  },

  //事件处理函数
  onLoad: function() {
    var condition = true
    api.getIOSQualification.call(this, (res) => {
      console.log(res)
      condition = res
      if (!condition) {
        this.setData({
          isShowPrice: false
        })
      }
    })
    this.setData({
      kind: 'document',
      currentKind: 'course',
      searchCondition: null,
      lastId: "",
      lastIdType: "",
      videoId: "",
      videoIdType: "",
      articles: [],
      videos: []
    })
    // this.showAll()
    this.showVideos()
    this.showAll1()


    api.getAd.call(this, 'index', (res) => {
      this.setData({
        ad: res.ad
      })
    })
  },

  //onShow函数
  onShow: function() {
    this.setData({
      searchCondition: null
    })
  },

  //点击广告跳转
  onAd: function() {
    wx.navigateTo({
      url: '../ad/ad?url=' + this.data.ad.link
    })
  },

  moreAction: function() {
    var that = this;

    var type = this.data.moreType;
    if (type) {
      that.setData({
        height: '',
        moreType: false
      })
    } else {
      that.setData({
        height: 290,
        moreType: true
      })
    }

  },
  moresAction: function() {
    var that = this;

    var type = this.data.moreType;
    console.log(type + '123123')
    if (type) {
      that.setData({
        height_video: '',
        moreType: false
      })
    } else {
      that.setData({
        height_video: 400,
        moreType: true
      })
    }
  },
  showAll1: function() {
    console.log('showAll1 success!')
    // this.judgeView()
    this.setData({
      kind: 'document',
      searchCondition: null,
      articles: [],
      videoId: "",
      videoIdType: "",
      isShow: true,
      isShowView: true,
      height: 290,
      height_video: 400
    })
    // api.getAbstractList.call(this, 'all', app.getOpenid(), this.data.lastId, this.data.lastIdType)
    api.getAbstractListByLikeNum.call(this, 'document', app.getOpenid())



    // api.getAd.call(this, 'jump', (res) => {
    //   // /*console.log(res)*/
    //   this.setData({
    //     jumpAd: res.ad.image
    //   })
    // })
  },

  showAll: function() {
    console.log('showAll success!')
    // this.judgeView()
    this.setData({
      currentKind: 'all',
      searchCondition: null,
      articles: [],
      lastId: "",
      lastIdType: "",
      isShow: true,
      isShowView: true,
      height: 290
    })
    api.getAbstractList.call(this, 'all', app.getOpenid(), this.data.lastId, this.data.lastIdType)

  },

  //展示文档
  showDocuments: function() {
    // this.judgeView()
    this.setData({
      currentKind: 'document',
      searchCondition: null,
      articles: [],
      lastId: "",
      lastIdType: "",
      isShow: true,
      isShowView: false,
      moreType: true,
      height: ''
    })
    api.getAbstractList.call(this, 'document', app.getOpenid(), this.data.lastId, this.data.lastIdType)
  },
  showVideos: function() {

    var that = this;
    // that.judgeView()
    that.setData({
      currentKind: 'course',
      searchCondition: null,
      videos: [],
      lastId: "",
      lastIdType: "",
      isShowView: true,
      isShow: false,
      moreType: true,
      height_video: ''
    })

    api.getAbstractListVideo.call(this, 'course', app.getOpenid(), this.data.lastId, this.data.lastIdType)
  },

  judgeView: function() {
    var that = this;
    var kind = this.data.currentKind;
    switch (kind) {
      case 'all':
        that.setData({
          isShow: true,
          isShowView: true,
          height: 290,
          height_video: 400
        })
        break;
      case 'document':
        that.setData({
          isShow: true,
          isShowView: false,
          height: 290,
          height_video: ''
        })
        break;
      case 'course':
        that.setData({
          isShow: false,
          isShowView: true,
          height: '',
          height_video: 400
        })
      default:

    }
  },

  toProjects: function() {
    wx.navigateTo({
      url: '/pages/project/project',
    })
  },

  //展示文章详情
  onTouchThisArticle: function(e) {
    var id = e.currentTarget.dataset.id //获取当前文章id
    var kind = e.currentTarget.dataset.kind
    var url = '../articleDetail/'
    switch (kind) {
      case 'course':
        url += 'courseDetail/courseDetail?id=' + id
        break;
      case 'document':
        url += 'documentDetail/documentDetail?id=' + id
        break;
      case 'project':
        url += 'projectDetail/projectDetail?id=' + id
        break;
      default:
    }
    wx.navigateTo({
      url: url
    })
  },

  //点赞数加一
  likePlus: function(e) {
    var id = e.currentTarget.dataset.id //获取当前文章id
    var kind = e.currentTarget.dataset.kind //获取当前文章kind
    var article = this.data.articles.filter((article) => article.id === id)[0]
    api.likePlus.call(this, app.getOpenid(), kind, id, article) //点赞+1
  },

  //更新搜索条件
  updateSearchCondition: function(e) {
    this.data.searchCondition = e.detail.value;
  },

  //搜索触发函数
  onSearch: function() {
    var searchText = this.data.searchCondition;
    if ("课程培训".includes(searchText)) {
      wx.navigateTo({
        url: '../particulars/curriculum/curriculum',
      })
    } else if ("考证考级".includes(searchText)) {
      wx.navigateTo({
        url: '../particulars/particulars/verificine/verificine',
      })
    } else if ("机构招聘".includes(searchText)) {
      wx.navigateTo({
        url: '../particulars/particulars/jobwanted/jobwanted',
      })
    } else if ("报告文档".includes(searchText)) {
      wx.navigateTo({
        url: '../particulars/particulars/documentation/documentation',
      })
    } else if ("事业合伙".includes(searchText)) {
      wx.navigateTo({
        url: '../particulars/particulars/partnership/partnership',
      })
    } else if ("标准合同".includes(searchText)) {
      wx.navigateTo({
        url: '../particulars/particulars/contract/contract',
      })
    } else if ("商务合作".includes(searchText)) {
      wx.navigateTo({
        url: '../particulars/particulars/cooperation/cooperation',
      })
    } else if ("城市社群".includes(searchText)) {
      wx.navigateTo({
        url: '../particulars/particulars/association/association',
      })
    } else if ("业务".includes(searchText)) {
      wx.switchTab({
        url: '../business/business',
      })
    } else if ("项目".includes(searchText)) {
      wx.switchTab({
        url: '../projects/project',
      })
    } else if ("人脉".includes(searchText)) {
      wx.switchTab({
        url: '../contact/contact',
      })
    } else {
      wx.switchTab({
        url: '../me/userInfo',
      })
    }
  },

  onReachBottom: function() {
    api.getAbstractList.call(this, this.data.currentKind, app.getOpenid(), this.data.lastId, this.data.lastIdType)
  },
  showMask: function() {
    this.setData({
      flag: false
    })
  },
  closeMask: function() {
    this.setData({
      flag: true
    })
  },
  touchMask: function() {
    wx.navigateTo({
      url: '/pages/me/updateMe/updateMe',
    })
  },


  //展示资金类
  showCapitalClass: function(event) {
    this.setData({
      currentKind: 'capital',
      currentKindName: this.data.capitalClassDesc,
      searchCondition: null
    })
    api.getPersonList.call(this, 'capital')
  },

  //点击当前文章触发函数
  onClickThisCard: function(e) {
    var id = e.currentTarget.dataset.id
    wx.navigateTo({
      url: '../me/myHistory/myHistory?id=' + id,
    })
  },

  //通过id获取不同页面
  catchTapCategory: function(e) {
    var that = this;
    var id = e.currentTarget.dataset.id //获取当前文章id
    var goodsId = e.currentTarget.dataset.goodsid;
    console.log('goodsId:' + goodsId);
    if ("0" == e.currentTarget.dataset.goodsid) {
      wx.navigateTo({
        url: '../particulars/curriculum/curriculum?goodsId=' + goodsId
      })
    }
    if ("2" == e.currentTarget.dataset.goodsid) {
      wx.navigateTo({
        url: '../particulars/jobwanted/jobwanted?goodsId=' + goodsId
      })
    }
    if ("1" == e.currentTarget.dataset.goodsid) {
      wx.navigateTo({
        url: '../particulars/verificine/verificine?goodsId=' + goodsId
      })
    }
    if ("4" == e.currentTarget.dataset.goodsid) {
      wx.navigateTo({
        url: '../particulars/partnership/partnership?goodsId=' + goodsId
      })
    }
    if ("6" == e.currentTarget.dataset.goodsid) {
      wx.navigateTo({
        url: '../particulars/cooperation/cooperation?goodsId=' + goodsId
      })
    }
    if ("3" == e.currentTarget.dataset.goodsid) {
      wx.navigateTo({
        url: '../particulars/documentation/documentation?goodsId=' + goodsId
      })
    }
    if ("7" == e.currentTarget.dataset.goodsid) {
      wx.navigateTo({
        url: '../particulars/association/association?goodsId=' + goodsId
      })
    }
    if ("5" == e.currentTarget.dataset.goodsid) {
      wx.navigateTo({
        url: '../particulars/contract/contract?goodsId=' + goodsId
      })
    }
  },
})