const Promise = require("./es6-promise.min.js")

function getTodayDate () {
  var date = new Date()
  return [date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-')
}

module.exports = {
  getTodayDate: getTodayDate,
  Promise: Promise
}