// menu to select project to be displayed
<template>
  <div id="project-menu">
    <div id="project-menu-header">
      <h2>Проекты</h2>
    </div>
    <project-menu-item 
      v-for="project in projects" :key="project.id" 
      :project="project" 
      :ref="setItemRef" 
      @SubMenuOpened="onSubMenuOpened" 
      @SubMenuClosed="onSubMenuClosed"/>
    <new-project-btn/>
  </div>
  
</template>

<script>
import NewProjectBtn from './NewProjectBtn.vue';
import ProjectMenuItem from './ProjectMenuItem.vue';

export default {
  components: { ProjectMenuItem, NewProjectBtn },
  data() {
    return {
      projectMenuItems: [],
      lastItemOpened: ''
    }
  },
  methods: {
    setItemRef(item) {
      if (item) {
        this.projectMenuItems.push(item);
      }
    },
    onSubMenuOpened(menuItem) {
      this.lastItemOpened = menuItem;
      this.$emit('SubMenuOpened');
    },
    onSubMenuClosed(menuItem) {
      if (this.lastItemOpened === menuItem) {
        this.lastItemOpened = '';
      }
    },
    closeAllButLastSubMenus() {
      for (let submenu in this.projectMenuItems) {
        if (this.projectMenuItems[submenu] !== this.lastItemOpened) {
          this.projectMenuItems[submenu].closeSubMenu();
        }
      }
      this.lastItemOpened = '';
    },
    closeAllSubMenus() {
      for (let submenu in this.projectMenuItems) {
        this.projectMenuItems[submenu].closeSubMenu();
      }
      this.lastItemOpened = '';
    },
  },
  computed: {
    projects() {
      return this.$store.state.projects;
    }
  },
  beforeUpdate() {
    this.itemRefs = []
  },
}
</script>

<style scoped>
#project-menu-header {
  color: #666666;
}
</style>