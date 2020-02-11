import axios from '@/libs/api.request'

export const login = ({ userName, password }) => {
  const data = {
    userName,
    password
  }
  return axios.request({
    url: 'login',
    data,
    method: 'post'
  })
}

export const getUserInfo = (token) => {
  return axios.request({
    url: 'user/permission',
    params: {
      token
    },
    method: 'post'
  })
}

export const logout = (token) => {
  return axios.request({
    url: 'logout',
    method: 'post'
  })
}

export const getUnreadCount = () => {
  return axios.request({
    url: 'message/count',
    method: 'get'
  })
}

export const getMessage = () => {
  return axios.request({
    url: 'message/init',
    method: 'get'
  })
}

export const getContentByMsgId = msg_id => {
  return axios.request({
    url: 'message/content',
    method: 'get',
    params: {
      msg_id
    }
  })
}

export const hasRead = msg_id => {
  return axios.request({
    url: 'message/has_read',
    method: 'post',
    data: {
      msg_id
    }
  })
}

export const removeReaded = msg_id => {
  return axios.request({
    url: 'message/remove_readed',
    method: 'post',
    data: {
      msg_id
    }
  })
}

export const restoreTrash = msg_id => {
  return axios.request({
    url: 'message/restore',
    method: 'post',
    data: {
      msg_id
    }
  })
}

/**
 * 查询角色信息
 * @param info
 * @returns
 */
export const querySysRole = info => {
  return axios.request({
    url: 'user/queryForPage',
    data: info,
    method: 'post'
  })
}

export const updateSysRole = info => {
  return axios.request({
    url: 'user/updateSysRole',
    data: info,
    method: 'post'
  })
}

export const deleteSysRole = info => {
  return axios.request({
    url: 'user/deleteSysRole',
    data: info,
    method: 'post'
  })
}

export const saveSysRole = info => {
  return axios.request({
    url: 'user/saveSysRole',
    data: info,
    method: 'post'
  })
}
