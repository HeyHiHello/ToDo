// Side bar with ocerciew menu and project menu
<template>
  <div id="sidebar">
    <user-menu ref="userMenu" @SubMenuOpened="onUserSubmenuOpened"/>
    <overview-menu ref="overviewMenu"/>
    <project-menu ref="projectMenu" 
    @SubMenuOpened="onProjectSubmenuOpened" 
    @deleteProject="onDeleteProject" 
    @changeProject="onChangeProject"/>
  </div>
</template>

<script>
import OverviewMenu from './OverviewMenu.vue'
import ProjectMenu from './ProjectMenu.vue'
import UserMenu from './UserMenu.vue'
export default {
  components: { UserMenu, OverviewMenu, ProjectMenu },
  methods: {
    onUserSubmenuOpened() {
      this.$refs.projectMenu.closeAllSubMenus();
    },
    onProjectSubmenuOpened() {
      this.$refs.userMenu.closeSubMenu();
      this.$refs.projectMenu.closeAllButLastSubMenus();
    },
    onDeleteProject(projectId) {
      this.$emit('deleteProject', projectId);
    },
    onChangeProject(projectId) {
      this.$emit('changeProject', projectId);
    }
  }
}
</script>

<style scoped>

#sidebar {
  background-color: #faf8f7;
}

#project-menu {
  margin-top: 20px;
}
</style>