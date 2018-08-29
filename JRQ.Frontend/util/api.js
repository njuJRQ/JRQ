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
    url: app.globalData.backendUrl + "getUser",
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

function publishMyArticle (openid, content, photos, that) {
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
   * 公司：company
   * 部门：department
   * 职位：position
   * 个人简介：intro
   */
  wx.request({
    url: app.globalData.backendUrl + "updateUser",
    data: {
      openId: app.getOpenid(),
      face: that.data.face,
      username: that.data.username,
      phone: that.data.phone,
      email: that.data.email,
      company: that.data.company,
      department: that.data.department,
      position: that.data.position,
      intro: that.data.intro
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
    url: app.globalData.backendUrl + "getMyPersonList",
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
  publishMyArticle: publishMyArticle,
  modifyMyInfo: modifyMyInfo,
  getPersonListByCondition: getPersonListByCondition,
  getMyPersonList: getMyPersonList,
  getMyHistoryAbstractList: getMyHistoryAbstractList
}