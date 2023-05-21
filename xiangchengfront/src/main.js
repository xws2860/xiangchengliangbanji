import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
import mitt from 'mitt'

import * as ElementPlusIconsVue from '@element-plus/icons-vue'

const app = createApp(App)
app.use(ElementPlus)
app.use(router)

//使用icon
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
app.component(key, component)
}

// 注册全局事件总线
app.config.globalProperties.$eventBus = mitt();
app.mount('#app')
