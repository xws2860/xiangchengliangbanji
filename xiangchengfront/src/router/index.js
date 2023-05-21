import { createRouter, createWebHistory } from 'vue-router'
import Index from '../views/Index.vue'
import Login from '../views/Login.vue'

const routes = [
    {
        path: "/",
        redirect: "/login"
    },
    {
        path: "/index",
        name:"index",
        component: Index,
        meta: { requiresAuth: true }
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    }
  ]
  
  const router = createRouter({
    history: createWebHistory(),
    routes
  })

  //全局的导航守卫，在路由跳转前检查是否存在 JWT token
  router.beforeEach((to, from, next) => {
    const requiresAuth = to.matched.some((record) => record.meta.requiresAuth);
    if (requiresAuth && !sessionStorage.getItem('jwt')) {
      next('/login');
    } else {
      next();
    }
  });
  
  export default router