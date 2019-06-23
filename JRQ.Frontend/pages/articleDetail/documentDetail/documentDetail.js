// pages/articleDetail/documentDetail/documentDetail.js
const app = getApp()
var api = require('../../../util/api.js')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    document: {
      isShow: true,
      id: 3,
      title: '这是一个文档标题',
      content: '这是文档的内容',
      writerName: '锄禾日当午',
      date: '2018-1-1',
      likeNum: 999,
      isShowPrice: false,
      price:0,
      attachments:[],
      attachment:'',
      previews:[]
    },
    //isDownLoadAttachments:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that=this;
    api.getIOSQualification.call(this, (res) => {
      this.setData({
        isShowPrice: res
      })
    })
    api.getLevelList.call(this, (levels) => {
      levels.forEach((level) => {
        /*console.log(level)*/
        switch (level.name) {
          case "298": that.data.discount298 = level.courseDiscountedRatio; break;
          case "998": that.data.discount998 = level.courseDiscountedRatio; break;
          default: break;
        }
      })
      that.setData(that.data)
    })
    try {
      api.getDocument.call(this, options.id)
      
      
    } catch (e) {
      console.log('获取编号为' + options.id + '的文档失败')
    }
    


  },

  onDownload: function(event) {
    var condition = true
    api.getIOSQualification.call(this, (res) => {
      console.log(res)
      condition = res
      if (condition) {
        this.setData({
          isShow: false
        })
      }
    })
    var that = this
      api.getMyUser.call(this, app.getOpenid(), (res) => {
        if (res.levelName == "common") {
          wx.showModal({
            title: '您的权限为普通用户，无法下载',
            content: '即将跳转到升级账户页面',
            success: (res) => {
              if (res.confirm) {
                wx.navigateTo({
                  url: '/pages/me/updateMe/updateMe',
                })
              }
            }
          })

        } else {
          var index = event.currentTarget.dataset.index
          console.log([index])
          console.log(this.data.document.attachments[index])
          api.downloadFile.call(this, this.data.document.attachments[index], () => {
            // let isDownLoadAttachment = "isDownLoadAttachments[" + index + "]"
            // that.setData({
            //   [isDownLoadAttachment]: true,
            // })
          })
        }
      })
    
  },

  // onOpen: function (event) {
  //   var that = this
  //   wx.openDocument({
  //     filePath: that.data.savedFilePath,
  //   })
  // },

  previewImg: function(event) {
    var src = event.currentTarget.dataset.src; //获取data-src
    //图片预览
    wx.previewImage({
      urls: [src] // 需要预览的图片http链接列表
    })
  },
})