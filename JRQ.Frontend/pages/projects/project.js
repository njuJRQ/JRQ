// pages/projects/project.js
const app = getApp();
var api = require('../../util/api.js')
import articleItem from '../../template/articleItem/articleItem'
Page({
  data: {
    isShow: true,
    isShowVIP: true,
    articles: [],
    writerFace: '',
    writerName: '',
    writerOpenid: '',
    content: '',
    date: '',
    images: null,
    // writerOpenid: '',
    id: '',
    // test:'你好吗',
    openid: '',
    currentKind: null,
    curentTab: '',
    searchCondition: null,

    // 页面配置  
    winWidth: 0,
    winHeight: 0,
    // tab切换 
    currentTab: 0,

    boxOpacity: 1,

    //占半位的字符数组
    halfChar: ['0','1','2','3','4','5','6','7','8','9','.',',', '/','(',')','[',']','{','}','!','`','^','*','-','+','=',':',';','"','\'']
  },
  onLoad: function() {

    var condition = true
    api.getIOSQualification.call(this, (res) => {
      console.log(res)
      condition = res
      if (condition) {
        this.setData({
          isShowVIP: false
        })
      }
    })
    var that = this;
    that.showPrior()
    // 获取系统信息 
    wx.getSystemInfo({
      success: function(res) {
        console.log(res)
        that.setData({
          winWidth: res.windowWidth,
          winHeight: res.windowHeight
        });
      }
    })
  },

  likePlus: function(event) {
    var that = this;
    var articles = this.data.articles;
    var id = event.currentTarget.dataset.id;
    for (var i = 0; i < articles.length; i++) {
      if (articles[i].id === id) {
        wx.request({
          url: app.globalData.backendUrl + "likePlus",
          data: {
            openid: app.getOpenid(),
            kind: "feed",
            articleId: id
          },
          header: {
            'Authorization': 'Bearer ' + app.getToken(),
            'content-type': 'application/x-www-form-urlencoded'
          },
          method: 'GET',
          success: (res) => {
            if (articles[i].hasLiked) {
              articles[i].likeNum--;
            } else {
              articles[i].likeNum++;
            }
            articles[i].hasLiked = !articles[i].hasLiked
            that.setData({
              articles: articles
            })
          }
        })
        return;
      }
    }
  },

  // 滑动切换tab 
  bindChange: function(e) {
    console.log('1-----' + e)
    var that = this;
    that.setData({
      currentTab: e.detail.current
    })
    console.log(this.data.currentTab)
    switch (this.data.currentTab) {

      case 0:
        console.log('0enter showPrior')
        //isShow: false
        that.showPrior()
        break;
      case 1:
        console.log('1enter showHot')
        //isShow: false
        that.showHot()
        break;
      case 2:
        console.log('2enter showTime')
        //isShow: false
        that.showTime()
        break;
      default:
    }
    console.log("bing")



  },

  // 点击tab切换 
  swichNav: function(e) {
    
    var currentTabIndex = e.currentTarget.dataset.current;
    var that = this;
    if (this.data.currentTab === currentTabIndex) {
      return false;
    } else {
      that.setData({
        currentTab: currentTabIndex
      })
      console.log(this.data.currentTab)
      /* 不要再切换了，会自动触发bindchange，再调用函数则在点击nav后会触发两次刷新
      switch (this.data.currentTab) {
        case '0':
          console.log('enter showPrior')
          //isShow: false
          that.showPrior()
          break;
        case '1':
          //isShow: false
          that.showHot()
          break;
        case '2':
          //isShow: false
          that.showTime()
          break;
        default:
      }*/
      
    }

  },

  showPrior: function() {
    console.log('enter showPrior')
    this.setData({
      currentKind: "isPreferred",
      articles: [],
      openid: "",
      id: "",
      //lastIdType: ""
    })

    console.log("showPrior")
    api.getFeedList.call(this, 'isPreferred', app.getOpenid(), this.data.id, "", this.examineExpand)
    console.log("showPrior success")
  },

  showHot: function() {
    this.setData({
      currentKind: 'hot',
      articles: [],
      lastId: "",
      lastIdType: "",
      flag: false
    })
    api.getFeedList.call(this, 'hot', app.getOpenid(), this.data.lastId, this.data.id, this.examineExpand)
  },
  showTime: function() {
    this.setData({
      currentKind: 'time',
      searchCondition: null,
      articles: [],
      lastId: "",
      lastIdType: ""
    })

    api.getFeedList.call(this, 'time', app.getOpenid(), this.data.lastId, this.data.id, this.examineExpand)
  },

  onPullDownRefresh: function() {
    this.onLoad()
  },

  onReachBottom: function() {
    api.getFeedList.call(this, 'time', app.getOpenid(), this.data.lastId, this.data.id, this.examineExpand)
  },

  examineExpand: function(){
    var that = this;
    for (var index in that.data.articles) {
      if (that.countLines(that.data.articles[index].content) > 5) {
        that.data.articles[index].canExpand = 1; // 可以展开
        that.data.articles[index].isExpanded = false;
      }
      else {
        that.data.articles[index].canExpand = 0;
        that.data.articles[index].isExpanded = true;
      }
      that.data.articles[index].ExpandOrNarrow = '展开';
    }
    
    that.setData(that.data);
  },

  countLines: function(content){
    var count = 0;
    var line = 1;
    for(var i = 0; i < content.length; i++){
      if(content.charAt(i) == '\n'){
        line = line + 1;
        count = 0;
      }
      else if (this.takeHalfPlace(content.charAt(i))){
        count = count + 0.5;
        if (count >= 22) {
          line = line + 1;
          count = 0;
        }
      }
      else{
        count = count + 1;
        if (count >= 22) {    // 因为可能为0.5，所以不能用等于22
          line = line + 1;
          count = 0;
        }
      }
    }
    return line;
  },
  /* 是否只占半个汉字的位置 */
  takeHalfPlace: function(c){
      if(this.data.halfChar.indexOf(c)>-1) return true;
      return false;
  },
  changeExpand: function(event){
    var that = this;
    var articleid = event.currentTarget.dataset.articleid;
    for (var index in that.data.articles) {
      if (that.data.articles[index].id == articleid) {
        that.data.articles[index].isExpanded = !that.data.articles[index].isExpanded;
        that.data.articles[index].ExpandOrNarrow = 
          that.data.articles[index].ExpandOrNarrow == '展开' ? '收起' : '展开';
        that.setData(that.data);
        break;
      }
    }
  },
  
  itemclick(event) {
    //不要点击整个item，而是点击头像才跳转到history
    //articleItem.onClickThisFace(event.currentTarget.dataset.id)
    //console.log(this.data.articles);  //test only!!!
  },

  /* 虽然使用了template，但模版最终也是渲染在调用者project.wxml上，所以模版的bindtap事件可以写在这里 */
  onClickThisFace: function (event) {
    wx.navigateTo({
      url: '../me/myHistory/myHistory?id=' + event.currentTarget.dataset.id,
    })
  },
  previewImg: function (event) {
    var src = event.currentTarget.dataset.src; //获取data-src
    var imgList = event.currentTarget.dataset.list; //获取data-list
    //图片预览
    wx.previewImage({
      current: src, // 当前显示图片的http链接
      urls: imgList // 需要预览的图片http链接列表
    })
  },

  // 滑动屏幕浏览时半透明化
  onScrollStart: function (ev) {
    this.setData({
      boxOpacity: 0.7
    })
  },

  onScrollStop: function (ev) {
    this.setData({
      boxOpacity: 1
    })
  },  
})