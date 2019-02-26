// pages/me/userInfo.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    openid:'',
    lastId: "",
    lastIdType: "",

    myInfo: {
      // writerface:'../../default/default-icon.png',
      axis: [
        {
          time: '2-1',
          name: '张三',
          event: '外媒报道称，特朗普21日呼吁美国电信企业加大力度建设速度更快的5G网络。'
        },
        {
          time: '2-18',
          name: '王三',
          event: '外媒报道称，特朗普21日呼吁美国电信企业加大力度建设速度更快的5G网络。'
        },
        {
          time: '2-17',
          name: '张三',
          event: '外媒报道称，特朗普21日呼吁美国电信企业加大力度建设速度更快的5G网络。'
        },
        {
          time: '2-16',
          name: '张三',
          event: '外媒报道称，特朗普21日呼吁美国电信企业加大力度建设速度更快的5G网络。'
        },
        {
          time: '2-1',
          name: '张三',
          event: '外媒报道称，特朗普21日呼吁美国电信企业加大力度建设速度更快的5G网络。'
        },
        {
          time: '2-18',
          name: '王三',
          event: '外媒报道称，特朗普21日呼吁美国电信企业加大力度建设速度更快的5G网络。'
        }],

      writerface:[
        '/pages/me/img/writerface.jpg',
        '/pages/me/img/huiyuan-icon.png',
        '/pages/me/img/bianjian-icon.png',
        '/pages/me/img/bt.jpg',
        '/pages/me/img/VIP-icon.png'

      ],
      username: 'EMILY',
      position: '财务',
      company: '北京大同信托有限公司',
      
      label: ['内构重组', '短融过桥', '大宗交易', '银行业务', '包里融租']
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  //发布信息
  onPublish: function () {
    console.log('publish')
    wx.navigateTo({
      url: 'publishMyArticle/publishMyArticle',
    })
  },


  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  // 修改
  // 获取项目列表
  getProjectList: function () {
    this.setData({
      articles: [],
      lastId: "",
      lastIdType: ""
    })
    api.getAbstractList.call(this, app.getOpenid(), this.data.projectId, this.data.projectIdType)
  },
})