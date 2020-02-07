import Vue from 'vue'
import VueI18n from 'vue-i18n'
import { localRead } from '@/libs/util'
import customZhCn from './lang/zh-CN'
import customZhTw from './lang/zh-TW'
import customEnUs from './lang/en-US'
import zhCnLocale from 'iview/src/locale/lang/zh-CN'
import enUsLocale from 'iview/src/locale/lang/en-US'
import zhTwLocale from 'iview/src/locale/lang/zh-TW'

import axios from '@/libs/api.request'

Vue.use(VueI18n)

// 自动根据浏览器系统语言设置语言
const navLang = navigator.language
const localLang = (navLang === 'zh-CN' || navLang === 'en-US') ? navLang : false
let lang = localLang || localRead('local') || 'zh-CN'

Vue.config.lang = lang

let messages = {
  'zh-CN': Object.assign(zhCnLocale, customZhCn),
  'zh-TW': Object.assign(zhTwLocale, customZhTw),
  'en-US': Object.assign(enUsLocale, customEnUs)
}

// 想后台请求数据
// eslint-disable-next-line no-new

// vue-i18n 6.x+写法
Vue.locale = () => {}

const i18n = new VueI18n({
  locale: lang,
  messages
})

axios.request({
  url: 'sysconfig/translate',
  data: { 'transType': lang },
  method: 'post'
}).then(res => {
  // messages['zh-CN'] = res.data.data
  if (lang === 'zh-CN') {
    console.log('设置中文！！！！！！', messages)
    i18n.setLocaleMessage('zh-CN', Object.assign(zhCnLocale, res.data.data))
  } else if (lang === 'zh-TW') {
    i18n.setLocaleMessage('zh-TW', Object.assign(zhTwLocale, res.data.data))
  } else if (lang === 'en-US') {
    i18n.setLocaleMessage('en-US', Object.assign(enUsLocale, res.data.data))
  }
})

export default i18n
