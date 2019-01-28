// pages/community/community.js
//获取应用实例
const app = getApp();
var api = require('../../util/api.js')
import articleItem from '../../template/articleItem/articleItem'

Page({
  

  /**
   * 页面的初始数据
   */
  data:{
    articles:[],
    currentKind:null,

    writerFace: '',
    writerName: '',
    writerOpenid:'',
    content:'',
    date:'',
    images:null,
    writerOpenid:'',
    id:''
},

  // data: {
   
  //   articles: [{
  //     id: 1,
  //     content: '《有效识别金融项目》课程。',
  //     images: [
  //       '../../default/default-pic.png',
  //       '../../default/default-pic.png',
  //       '../../default/default-pic.png'
  //     ],
  //     writerOpenid: '123',
  //     writerFace: '../../default/default-icon.png',
  //     writerName: '锄禾日当午',
  //     date: '2020-01-01',
  //     likeNum: 8888
  //   }, {
  //     id: 2,
  //     text: '与钧融资本成功签订2个亿的基金合约，环保领域。',
  //     images: [
  //       '../../default/default-pic.png',
  //       '../../default/default-pic.png',
  //       '../../default/default-pic.png'
  //     ],
  //     writerFace: '../../default/default-icon.png',
  //     writerName: '汗滴禾下土',
  //     date: '2020-01-01',
  //     likeNum: 9999
  //   }, {
  //     id: 3,
  //     text: '《有效识别金融项目》课程。',
  //     images: [
  //       '../../default/default-pic.png',
  //       '../../default/default-pic.png',
  //       '../../default/default-pic.png'
  //     ],
  //     writerFace: '../../default/default-icon.png',
  //     writerName: '锄禾日当午',
  //     date: '2020-01-01',
  //     likeNum: 8888
  //   }]
  // },


  /**
   * 生命周期函数--监听页面加载a
   */
  onLoad:function(e){
    
    this.setData({
      currentKind:'latest',
      articles:[],
    })
    this.showAll()
  },

  //展示最热文章
  showAll: function () {
    this.setData({
      currentKind: "latest",
      articles: [],
      openid:"",
      id:"",
      
      //lastIdType: ""
    })
    // api.getFeedList.call(this, 'latest', app.getOpenid(), this.data.lastId)
    api.getFeedList.call(this, 'lasted', app.getOpenid(), this.data.id)
    
  },


  //展示一周内文章
  showWeekly: function () {
    this.setData({
      currentKind: 'weekly',
      articles: [],
      lastId: "",
      lastIdType: "",
      flag: false
    })
    api.getFeedList.call(this, 'weekly', app.getOpenid(), this.data.lastId, this.data.id)
  },

  //展示一月内文章
  showMonthly: function () {
    this.setData({
      currentKind: 'monthly',
      articles: [],
      lastId: "",
      lastIdType: ""
    })
    api.getFeedList.call(this, 'monthly', app.getOpenid(), this.data.lastId, this.data.id)
  },

  
  // onLoad: function (options) {
  //   //展示圈子文章
  //   this.setData({
  //     articles: []
  //   })
  //   api.getFeedList.call(this, app.getOpenid(), "")
  // },
  //获取当前滑块的index...
  bindchange: function (e) {
    const that = this;
    that.setData({
      currentData: e.detail.current
    })
  },
  //点击切换，滑块index赋值
  checkCurrent: function (e) {
    const that = this;

    if (that.data.currentData === e.target.dataset.current) {
      return false;
    } else {

      that.setData({
        currentData: e.target.dataset.current
      })
    }
    },

  onPullDownRefresh: function () {
    this.onLoad()
  },

  onReachBottom: function () {
    api.getFeedList.call(this, app.getOpenid(), this.data.lastId)
  },

  onShare: function () {
    console.log('on share')
  },

  //点赞数加一
  likePlus: function (e) {
    var id = e.currentTarget.dataset.id //获取当前feed的id
    var article = this.data.articles.filter((article)=>article.id===id)[0]
    api.likePlus.call(this, app.getOpenid(), 'feed', id, article)
  },

  previewImg: function (e) {
    articleItem.previewImg(e)
  },

  onClickThisFace: function (e) {
    articleItem.onClickThisFace(e)
  },

  onPublish: function () {
    wx.navigateTo({
      url: '../me/publishMyArticle/publishMyArticle',
    })
  }
})