const app = getApp()

function getAbstractList(kind, openid, that) {
  wx.request({
    url: app.globalData.backendUrl + "getAbstractList",
    data: {
      kind: kind,
      openid: openid
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      that.setData({
        articles: res.data.abstractList
      })
    }
  })
}

function getCourse (id, that) {
  wx.request({
    url: app.globalData.backendUrl + "getCourse",
    data: {
      id: id
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      that.setData({
        course: res.data.course
      })
    }
  })
}

function getDocument(id, that) {
  wx.request({
    url: app.globalData.backendUrl + "getDocument",
    data: {
      id: id
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      that.setData({
        document: res.data.document
      })
    }
  })
}

function getProject(id, that) {
  wx.request({
    url: app.globalData.backendUrl + "getProject",
    data: {
      id: id
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      that.setData({
        project: res.data.project
      })
    }
  })
}

function getAd(that) {
  /**
   * 方法：getAd
   * 参数：
   * 无
   */
  wx.request({
    url: app.globalData.backendUrl + "getAd",
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      that.setData({
        ad: res.data.ad,
        adLink: res.data.link
      })
    }
  })
}

function likePlus(kind, articleId, openid, context) {
  wx.request({
    url: app.globalData.backendUrl + "likePlus",
    data: {
      kind: kind,
      articleId: articleId,
      openid: openid
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      context.article.likeNum++
      context.that.setData(context.that.data)
    }
  })
}

function purchaseCourse(courseId, openid, that) {
  wx.request({
    url: app.globalData.backendUrl + "purchaseCourse",
    data: {
      courseId: courseId,
      openid: openid
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      //do nothing
    }
  })
}

function getPersonList(kind, that) {
  wx.request({
    url: app.globalData.backendUrl + "getPersonList",
    data: {
      kind: kind
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      that.setData({
        cards: res.data.personList
      })
      that.addLabel()
    }
  })
}

function getMyInfo(openid, that) {
  /**
   * 方法：getUser
   * 参数：
   * 无
   */
  wx.request({
    url: app.globalData.backendUrl + "getMyUser",
    data: {
      openid: openid
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      that.setData({
        myInfo: res.data.user
      })
    }
  })
}

function getOtherInfo(myid, otherid, that) {
  /**
   * 方法：getUser
   * 参数：
   * 无
   */
  wx.request({
    url: app.globalData.backendUrl + "getOtherCard",
    data: {
      userOpenid: myid,
      otherOpenid: otherid
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      that.setData({
        myInfo: res.data.card
      })
    }
  })
}

function checkMyReceivedCard(senderOpenid, receiverOpenid) {
  wx.request({
    url: app.globalData.backendUrl + "checkMyReceivedCard",
    data: {
      senderOpenid: senderOpenid,
      receiverOpenid: receiverOpenid
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      //do nothing
    }
  })
}

function publishMyArticle (openid, kind, content, photos, that) {
  //TODO
  /**
   * 方法：publishMyArticle
   * 参数：
   * 文本内容：content
   */
  wx.request({
    url: app.globalData.backendUrl + "publishMyArticle",
    data: {
      openid: openid,
      kind: kind,
      content: content,
      photos: photos
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      //do nothing
    }
  })
}

function modifyMyInfo(that) {
  /**
   * 方法：updateUser
   * 参数：
   * 用户头像：face
   * 用户名：username
   * 电话：phone
   * 邮箱：email
   * 城市：city
   * 公司：company
   * 部门：department
   * 职位：position
   * 个人简介：intro
   */
  wx.uploadFile({
    url: app.globalData.backendUrl + "uploadhead/" + app.getOpenid(),
    filePath: that.data.newMyInfo.face,
    name: 'file',
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    success: (res) => {
      wx.request({
        //上传用户信息
        url: app.globalData.backendUrl + "updateMyProfile",
        data: {
          openId: app.getOpenid(),
          username: that.data.newMyInfo.username,
          face: res.data.facePath, //res结果返回图片存放的路径
          phone: that.data.newMyInfo.phone,
          email: that.data.newMyInfo.email,
          city: that.data.newMyInfo.city,
          company: that.data.newMyInfo.company,
          department: that.data.newMyInfo.department,
          position: that.data.newMyInfo.position,
          intro: that.data.newMyInfo.intro,
          label: that.newMyInfo.label
        },
        header: {
          'Authorization': 'Bearer ' + app.getToken(),
          'content-type': 'application/x-www-form-urlencoded'
        },
        method: 'GET',
        success: (res) => {
          wx.showToast({
            title: '修改成功',
            icon: 'succes',
            duration: 1000,
            mask: true
          })
        }
      })
    }
  })
  
}

function getPersonListByCondition (condition, that) {
  /**
   * 方法：searchCards
   * 参数：
   * 搜索条件：condition
   */
  wx.request({
    url: app.globalData.backendUrl + "getPersonListByCondition",
    data: {
      condition: that.data.searchCondition
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      that.setData({
        cards: res.data.persons
      })
    }
  })
}

function getMyPersonList (openid, kind, that) {
  /**
   * 方法：getMyPersonList
   * 参数：
   * 用户openId：openId
   * 展示类别：kind
   */
  wx.request({
    url: app.globalData.backendUrl + "getMyCardList",
    data: {
      openid: openid,
      kind: kind
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      that.setData({
        cards: res.data.persons
      })
    }
  })
}

function getMyHistoryAbstractList (openid, that) {
  /**
   * 方法：getMyHistoryAbstractList
   * 参数：用户openId：openId
   */
  wx.request({
    url: app.globalData.backendUrl + "getMyHistoryAbstractList",
    data: {
      openid: openid
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      that.setData({
        myArticles: res.data.abstractList
      })
    }
  })
}

module.exports = {
  getAbstractList: getAbstractList,
  getCourse: getCourse,
  getDocument: getDocument,
  getProject: getProject,
  getAd: getAd,
  likePlus: likePlus, 
  purchaseCourse: purchaseCourse,
  getPersonList: getPersonList,
  getMyInfo: getMyInfo,
  getOtherInfo: getOtherInfo,
  checkMyReceivedCard: checkMyReceivedCard,
  publishMyArticle: publishMyArticle,
  modifyMyInfo: modifyMyInfo,
  getPersonListByCondition: getPersonListByCondition,
  getMyPersonList: getMyPersonList,
  getMyHistoryAbstractList: getMyHistoryAbstractList
}