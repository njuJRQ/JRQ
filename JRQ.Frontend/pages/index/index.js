//index.js
//获取应用实例
const app = getApp()
var api = require('../../util/api.js')
const {
  bg1
} = require('../../util/data.js')

Page({
  data: {

    moreType: true,
    isShowView: true,
    isShow: true,
   
    height: 290,
    height_video: 400,
    image: 'http://junrongcenter.oss-cn-beijing.aliyuncs.com/default/default-pic.png',
    // showView:true,
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
    videos:[],
    // ad: {
    //   image: 'http://junrongcenter.oss-cn-beijing.aliyuncs.com/default/default-pic.png',
    //   link: 'https://www.baidu.com'
    // },
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

    this.setData({
      kind: 'document',
      currentKind: 'course',
      searchCondition: null,
      lastId: "",
      lastIdType: "",
      videoId: "",
      videoIdType: "",
      articles: [],
      videos:[]
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
  showAll1: function () {
    console.log('showAll1 success!')
    // this.judgeView()
    this.setData({
      kind: 'document',
      searchCondition: null,
      articles: [],
      videoId: "",
      videoIdType: "",
      isShow:true,
      isShowView: true,
      height: 290,
      height_video:400
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
      isShow:true,
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
      isShow:true,
      isShowView: false,
      moreType: true,
      height: ''
    })
    api.getAbstractList.call(this, 'document', app.getOpenid(), this.data.lastId, this.data.lastIdType)
  },
  showVideos: function () {

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
  // updateSearchCondition: function(e) {
  //   this.data.searchCondition = e.detail.value;
  // },

  //搜索触发函数
  // onSearch: function() {
  //   if (!this.data.searchCondition) {
  //     this.showAll();
  //     return;
  //   }
  //   console.log('search article: ' + this.data.searchCondition)
  //   api.getAbstractListByCondition.call(this, app.getOpenid(), this.data.searchCondition)
  // },

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

  //更新搜索条件
  updateSearchCondition: function(e) {
    this.data.searchCondition = e.detail.value;
  },

  //搜索触发函数
  onSearch: function() {
    console.log('search service people: ' + this.data.searchCondition)
    api.getPersonListByCondition.call(this, app.getOpenid(), this.data.searchCondition)
  },



})