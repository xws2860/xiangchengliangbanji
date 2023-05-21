//这层中的axios.js是对axios的进一步功能封装，然后利用这个request.js对axios.js在进一步封装，为了变得更好用
import instance from "./axios"
const axios = ({
    method,
    url,
    data,
    config
}) => {
    method = method.toLowerCase();
    if (method == 'post') {
        return instance.post(url, data, {...config})
    } else if (method == 'get') {
        return instance.get(url, {
            params: data,
            ...config
        })
    } else if (method == 'delete') {
        return instance.delete(url, {
            params: data,
            ...config
        }, )
    } else if (method == 'put') {
        return instance.put(url, data,{...config})
    } else {
        console.error('未知的method' + method)
        return false
    }
}
export default axios
