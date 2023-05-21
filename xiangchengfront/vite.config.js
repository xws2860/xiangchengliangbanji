import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  define: {
    'process.env':{}
  },
  optimizeDeps: {
    include: ['axios'],
  },
  server: {
    host:'0.0.0.0',
    cors: true,
    open: true,
    proxy: {
      '/api': {
        target: 'http://127.0.0.1:8081',   //代理接口
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '')  // 重定向
      }
    }
  }
})
