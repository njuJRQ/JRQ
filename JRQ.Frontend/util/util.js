const Promise = require("./es6-promise.min.js")

function getTodayDate () {
  var date = new Date()
  return [date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-')
}

function error(msg) {
  wx.showModal({
    title: msg,
    showCancel: false
  })
}

function flashInfo(msg) {
  wx.showToast({
    title: msg,
    icon: 'none'
  })
}

module.exports = {
  getTodayDate: getTodayDate,
  Promise: Promise,
  error: error,
  flashInfo: flashInfo
}