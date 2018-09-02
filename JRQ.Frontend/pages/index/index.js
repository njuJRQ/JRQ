//index.js
//获取应用实例
const app = getApp()
var api = require('../../util/api.js')

Page({
  data: {
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
    ad: '../../default/default-pic.png',
    adLink: 'https://www.baidu.com'
  },
  //事件处理函数
  //onLoad函数
  onLoad: function() {
    api.getAd(this) //展示广告
    api.getAbstractList('course', app.getOpenid(), this) //展示课程类文章
  },
  //点击广告跳转
  onAd: function() {
    wx.navigateTo({
      url: 'ad/ad'
    })
  },
  //展示课程
  showCourses: function() {
    console.log('show courses')
    api.getAbstractList('course', app.getOpenid(), this)
  },
  //展示文档
  showDocuments: function() {
    console.log('show documents');
    api.getAbstractList('document', app.getOpenid(), this)
  },
  //展示项目
  showProjects: function() {
    console.log('show projects');
    api.getAbstractList('project', app.getOpenid(), this)
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
  likePlus: function(event) {
    var id = event.currentTarget.dataset.id //获取当前文章id
    var article = this.getCurrentArticle(id) //获取当前文章
    api.likePlus(article.kind, id, app.getOpenid(), {
      article: article,
      that: this
    })
  },
  //获取当前文章
  getCurrentArticle: function(id) {
    var that = null;
    for (var i = 0; i < this.data.articles.length; i++) {
      if (this.data.articles[i].id == id)
        that = this.data.articles[i];
    }
    return that;
  }
})