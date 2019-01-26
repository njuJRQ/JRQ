//index.js
//获取应用实例
const app = getApp()
const api = require('../../util/api.js')
const data = require('../../util/data.js')
const util = require('../../util/util.js')

Page({
  data: {
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
    ad: {
      image: '../../default/default-pic.png',
      link: 'https://www.baidu.com'
    },
    currentKind: null,
    searchCondition: null,
    lastId: "",
    lastIdType: "",
    flag: false,
    bg1: data.bg1,
  },

  //事件处理函数
  onLoad: function() {
    this.setData({
      currentKind: 'course',
      searchCondition: null,
      articles: []
    })
    this.showProjects()
  },

  updateArticles: function (articles) {
    if (articles.length <= 0) {
      return
    }
    articles.forEach((article) => {
      article.images = article.images.map((image) => app.globalData.picUrl + image)
      article.writerFace = app.globalData.picUrl + article.writerFace
      switch (article.kind) {
        case 'course':
          article.kindName = "课程";
          break;
        case 'document':
          article.kindName = "文档";
          break;
        case 'project':
          article.kindName = "项目";
          break;
        default:
          break;
      }
    })
    this.setData({
      articles: this.data.articles.concat(articles),
      lastId: articles[articles.length - 1].id,
      lastIdType: articles[articles.length - 1].kind
    })
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
    api.getAbstractList('project', app.getOpenid(), this.data.lastId, this.data.lastIdType)
      .then(this.updateArticles)
      .catch((res) => {
        util.error("获取文章列表失败")
      })

  },
  toProjects: function() {
    wx.navigateTo({
      url: '/pages/index/project',
      success: function(res) {
        console.log('success')
      },
      fail: function(res) {},
      complete: function(res) {},
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
  }
})