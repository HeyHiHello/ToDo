// MainLayout is used for overview widgets contains sidebar
<template>
  <div id="layout">
    <sidebar-component/>
    <router-view/>
  </div>
</template>

<script>
import SidebarComponent from '@/components/SideBar/SidebarComponent.vue'
export default {
  components: { SidebarComponent},
}
</script>

<style>
#layout {
  display: flex;
}

#sidebar {
  width: 20%;
  height: 100vh;
  overflow: auto;
}

.main-widget {
  overflow: auto;
  height: 94vh;
  padding: 20px 20px 20px 20px;
  flex-grow: 1;
}
</style>