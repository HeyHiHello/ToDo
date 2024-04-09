import { createApp } from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'

createApp(App).use(store).use(router).mount('#app')

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.isRequireAuth)) {
    // this route requires auth, check if logged in
    // if not, redirect to login page.
    if (!store.state.user.username) {
      next({ path: '/signin' })
    } else {
      next() // go to wherever I'm going
    }
  } else {
    next() // does not require auth, make sure to always call next()!
  }
});