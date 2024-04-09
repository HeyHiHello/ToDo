import { createRouter, createWebHistory } from 'vue-router';


const mainLayoutMeta = {
  layout: 'main-layout',
  isRequireAuth: true,
}

// All overview widget use this children to show tranperent background widgets.
const coveringWidgetPaths = [
  {
    path: 'task/delete/:childId',
    meta: {childLayout: 'covering-widget-layout', isRequireAuth: true},
    components: {covering: ()=>import('@/components/CoveringWidget/Delete/ConfirmDeleteTaskWidget.vue')}
  },
  {
    path: 'task/change/:childId',
    meta: {childLayout: 'covering-widget-layout', isRequireAuth: true,},
    components: {covering: ()=>import('@/components/CoveringWidget/Change/ChangeTaskWidget.vue')}
  },
  {
    path: 'project/delete/:childId',
    meta: {childLayout: 'covering-widget-layout', isRequireAuth: true,},
    components: {covering: ()=>import('@/components/CoveringWidget/Delete/ConfirmDeleteProjectWidget.vue')}
  },
  {
    path: 'project/change/:childId',
    meta: {childLayout: 'covering-widget-layout', isRequireAuth: true,},
    components: {covering: ()=>import('@/components/CoveringWidget/Change/ChangeProjectWidget.vue')}
  },
  {
    path: 'project/new',
    meta: {childLayout: 'covering-widget-layout', isRequireAuth: true,},
    components: {covering: ()=>import('@/components/CoveringWidget/New/NewProjectWidget.vue')}
  },
  {
    path: 'task/new',
    meta: {childLayout: 'covering-widget-layout', isRequireAuth: true,},
    components: {covering: ()=>import('@/components/CoveringWidget/New/NewTaskWidget.vue')}
  },
]


const routes = [
  {
    path: '/',
    redirect: '/today',
  },
  {
    path: '/today',
    name: 'today',
    meta: mainLayoutMeta,
    component: () => import('@/views/TodayOverview.vue'),
    children: coveringWidgetPaths
  },
  {
    path: '/week',
    name: 'week',
    meta: mainLayoutMeta,
    component: () => import('@/views/WeekOverview.vue'),
    children: coveringWidgetPaths
  },
  {
    path: '/project/:id',
    name: 'project',
    meta: mainLayoutMeta,
    component: () => import('@/views/ProjectOverview.vue'),
    children: coveringWidgetPaths
  },
  {
    path: '/settings/profile',
    name: 'profile',
    meta: {layout: 'settings-layout', isRequireAuth: true},
    component: () => import('@/views/ProfileSettings.vue'),
  },
  {
    path: '/signin',
    name: 'signin',
    meta: {layout: 'settings-layout', isRequireAuth: false},
    component: () => import('@/views/SignInView.vue'),
  },
  {
    path:'/signup',
    name: 'signup',
    meta: {layout: 'settings-layout', isRequireAuth: false},
    component: () => import('@/views/SignUpView.vue'),
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
