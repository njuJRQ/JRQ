// pages/modifyMyCard/modifyMyCard.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    info: {
      face: '../../default/default-pic.png',
      username: 'USERNAME',
      phone: '13952146595',
      email: '13952146595@163.com',
      company: '美国永辉公司',
      department: 'IT技术部',
      position: 'T1初级经理',
      intro: '我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。'
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  
  },
  onSave: function(){
    console.log('save');
  }
})