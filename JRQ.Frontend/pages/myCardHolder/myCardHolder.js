// pages/myCardHolder/myCardHolder.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    cards: [
      {
        username: 'USERNAME',
        medals: [
          '../../default/default-icon.png',
          '../../default/default-icon.png',
          '../../default/default-icon.png',
          '../../default/default-icon.png'],
        phone: '123456789',
        email: '123456789@163.com',
        company: '美国永辉有限公司',
        department: 'IT技术部',
        position: 'IT初级经理',
        intro: '我要在代码的世界里飞翔。'
      }, {
        username: 'USERNAME',
        medals: [
          '../../default/default-icon.png',
          '../../default/default-icon.png',
          '../../default/default-icon.png',
          '../../default/default-icon.png'],
        phone: '123456789',
        email: '123456789@163.com',
        company: '美国永辉有限公司',
        department: 'IT技术部',
        position: 'IT初级经理',
        intro: '我要在代码的世界里飞翔。'
      }, {
        username: 'USERNAME',
        medals: [
          '../../default/default-icon.png',
          '../../default/default-icon.png',
          '../../default/default-icon.png',
          '../../default/default-icon.png'],
        phone: '123456789',
        email: '123456789@163.com',
        company: '美国永辉有限公司',
        department: 'IT技术部',
        position: 'IT初级经理',
        intro: '我要在代码的世界里飞翔。'
      }
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  
  },
  //搜索触发函数
  onSearch: function() {
    console.log('on search')
  },
  //展示新收到的名片
  showNewCards: function () {
    console.log('show new cards')
  },
  //展示我持有的名片
  showCurrentCards: function () {
    console.log('show current cards')
  },
  //展示拥有我名片的
  showWhoHasMyCard: function () {
    console.log('show who has my card')
  },
})