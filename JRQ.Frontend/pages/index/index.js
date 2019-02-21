//index.js
//获取应用实例
const app = getApp()
var api = require('../../util/api.js')
const {
  bg1
} = require('../../util/data.js')

Page({
  data: {
    doucument: [],
    documentId: '',
    doucumentIdType: '',

    video: [],
    videoId: '',
    videoIdType: '',

    image: '../../default/default-pic.png',
    // showView:true,
    cards: [{
        thumbnail: '../../default/default-pic.png',
        articleName: '什么是金融？',
        summary: '一般指与货币流通及银行有关的东西'
      },
      {
        thumbnail: '../../default/default-pic.png',
        articleName: '什么是金融？',
        summary: '一般指与货币流通及银行有关的东西'

      },
      {
        thumbnail: '../../default/default-pic.png',
        articleName: '什么是金融？',
        summary: '一般指与货币流通及银行有关的东西'
      }
    ],

    // cards:[{
    //   face: '../../default/default-pic.png',

    // },{}]
    /*
    articles: [{
      id: 1,
      text: '《有效识别金融项目》课程。',
      images: [
        '../../default/default-pic.png',
        '../../default/default-pic.png',
        '../../default/default-pic.png'
      ],
      writerFace: '../../default/default-icon.png',
      writerName: '锄禾日当午',
      date: '2020-01-01',
      likeNum: 8888,
      kind: 'course'
    }, {
      id: 2,
      text: '与钧融资本成功签订2个亿的基金合约，环保领域。',
      images: [
        '../../default/default-pic.png',
        '../../default/default-pic.png',
        '../../default/default-pic.png'
      ],
      writerFace: '../../default/default-icon.png',
      writerName: '汗滴禾下土',
      date: '2020-01-01',
      likeNum: 9999,
      kind: 'document'
    }, {
      id: 3,
      text: '《有效识别金融项目》课程。',
      images: [
        '../../default/default-pic.png',
        '../../default/default-pic.png',
        '../../default/default-pic.png'
      ],
      writerFace: '../../default/default-icon.png',
      writerName: '锄禾日当午',
      date: '2020-01-01',
      likeNum: 8888,
      kind: 'project'
    }],
    */
    articles: [],
    // ad: {
    //   image: '../../default/default-pic.png',
    //   link: 'https://www.baidu.com'
    // },
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
      currentKind: 'course',
      searchCondition: null,
      articles: [],

    })
    this.showAll()
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

  showAll: function() {
    this.setData({
      currentKind: 'all',
      searchCondition: null,
      articles: [],
      lastId: "",
      lastIdType: ""
    })
    api.getAbstractList.call(this, 'all', app.getOpenid(), this.data.lastId, this.data.lastIdType)
    api.getAd.call(this, 'jump', (res) => {
      /*console.log(res)*/
      this.setData({
        jumpAd: res.ad.image
      })
    })
  },

  //展示文档
  showDocuments: function() {
    console.log("hwurhw")
    this.setData({
      'cards ': this.data.cards,

    });
    // this.setData({
    //   'cards': this.data.cards
    //   // currentKind: 'course',
    //   // searchCondition: null,
    //   // articles: [],
    //   // lastId: "",
    //   // lastIdType: "",
    //   // showView: (!this.data.showView)

    // })

    // api.getAbstractList.call(this, 'course', app.getOpenid(), this.data.lastId, this.data.lastIdType)
  },

  //展示视频
  showVideos: function() {
    this.setData({
      currentKind: 'document',
      searchCondition: null,
      articles: [],
      lastId: "",
      lastIdType: ""
    })
    api.getAbstractList.call(this, 'document', app.getOpenid(), this.data.lastId, this.data.lastIdType)
  },

  //展示项目
  showProjects: function() {
    this.setData({
      currentKind: 'project',
      searchCondition: null,
      articles: [],
      lastId: "",
      lastIdType: ""
    })
    api.getAbstractList.call(this, 'project', app.getOpenid(), this.data.lastId, this.data.lastIdType)

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
    if (!this.data.searchCondition) {
      this.showAll();
      return;
    }
    console.log('search article: ' + this.data.searchCondition)
    api.getAbstractListByCondition.call(this, app.getOpenid(), this.data.searchCondition)
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

  //更新搜索条件
  updateSearchCondition: function(e) {
    this.data.searchCondition = e.detail.value;
  },

  //搜索触发函数
  onSearch: function() {
    console.log('search service people: ' + this.data.searchCondition)
    api.getPersonListByCondition.call(this, app.getOpenid(), this.data.searchCondition)
  },

  // 新的修改
  // 展示文档
  showDocument: function() {
    this.setData({
      document: [],
      documentId: "",
      documentIdType: ""
    })
    api.getDocumentList.call(this, app.getOpenid(), this.data.documentId, this.data.documentIdType)

  },
  toDocument: function() {
    wx.navigateTo({
      url: '/pages/project/project',
    })

  },
  
  // 展示视频
  showVideo: function() {
    this.setData({
      video: [],
      videoId: "",
      videoIdType: ""
    })
    api.getVideoList.call(this, app.getOpenid(), this.data.videoId, this.data.videoIdType)

  },
  toVideo: function() {
    wx.navigateTo({
      url: '/pages/project/project',
    })
  }
})