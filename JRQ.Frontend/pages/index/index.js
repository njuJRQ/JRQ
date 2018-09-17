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
    ad: {
      image: '../../default/default-pic.png',
      link: 'https://www.baidu.com'
      }
  },
  //事件处理函数
  //onLoad函数
  onShow: function() {
    api.getAd.call(this) //展示广告
    api.getAbstractList.call(this, 'course', app.getOpenid()) //展示课程类文章
  },

  //点击广告跳转
  onAd: function() {
    wx.navigateTo({
      url: 'ad/ad?url=' + this.data.ad.link
    })
  },

  //展示课程
  showCourses: function() {
    console.log('show courses')
    api.getAbstractList.call(this, 'course', app.getOpenid())
  },

  //展示文档
  showDocuments: function() {
    console.log('show documents');
    api.getAbstractList.call(this, 'document', app.getOpenid())
  },

  //展示项目
  showProjects: function() {
    console.log('show projects');
    api.getAbstractList.call(this, 'project', app.getOpenid())
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
    api.likePlus.call(this, app.getOpenid(), kind, id) //点赞+1
  },
  
  //广告图片加载失败
  errorAdImage: function () {
    this.setData({
      ad: {
        image: app.globalData.defaultPic,
        link: 'http://www.baidu.com'
      }
    })
  }
})