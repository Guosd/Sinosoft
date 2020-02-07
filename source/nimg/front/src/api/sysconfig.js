/**
 * 系统配置相关书写到此处
 */
export const selectTranslate = info => {
  return axios.request({
    url: 'sysconfig/translate',
    data: info,
    method: 'post'
  })
}
