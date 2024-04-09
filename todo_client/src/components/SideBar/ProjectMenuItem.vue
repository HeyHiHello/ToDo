// project btn with option btn and option menu(change or delete project)
<template>
  <div class="project-menu-item">
    <router-link 
      class="project_btn"
      :to="'/project/' + project.id" 
      active-class="active"> 
      <hash-tag-icon class="hashtag-icon"/>
      <div class="project-title">
        {{ project.title }}
      </div>
      <options-icon class="option-icon" @click.prevent="toggleMenu"/>
    </router-link>
    <div class="project-options-menu" v-show="isOptionMenuOpened">
      <router-link :to="currentPath + '/project/change/' + project.id" @click="closeSubMenu">Изменить</router-link>
      <router-link :to="currentPath + '/project/delete/' + project.id" @click="closeSubMenu">Удалить</router-link>
    </div>
  </div>
</template>

<script>
import hashTagIcon from '../icons/hashTagIcon.vue';
import OptionsIcon from '../icons/optionsIcon.vue';
export default {
  components: { hashTagIcon, OptionsIcon },
  data() {
    return {
      isOptionMenuOpened: false,
    }
  },
  props:{
    project:{required: true},
  },
  methods: {
    toggleMenu() {
      this.isOptionMenuOpened = !this.isOptionMenuOpened;
      if (this.isOptionMenuOpened) {
        this.$emit('SubMenuOpened', this);
      } else {
        this.$emit('SubMenuClosed', this);
      }
    },
    closeSubMenu() {
      this.isOptionMenuOpened = false;
      this.$emit('SubMenuClosed', this);
    },
  },
  computed: {
    currentPath() {
      let path = this.$route.path;
      if (path.at(path.length - 1) === '/') {
        path = path.split('');
        path.splice(path.length - 1, 1)
        path = path.join('');
      }
      return path;
    }
  }
}
</script>

<style scoped>
.project_btn {
  display: flex;
  padding: 5px;
  margin: 5px;
  border-radius: 5px;
  text-align: left;
}

.project_btn:hover {
  background-color: #f6eeee;
}

.project-title {
  flex-grow: 1;
  display: inline-block;
  margin-left: 3px;
}

.active {
  background-color: #fee6e3;
}
.active:hover {
  background-color: #fee6e3;
}

.option-icon, .hashtag-icon {
  width: 20px;
}

.option-icon {
  display: none;
}

.option-icon:hover  {
  stroke: #dc4c3e;
}

.project_btn:hover .option-icon {
  display: inline-block;
}

.project_btn:hover .hashtag-icon {
  stroke: #dc4c3e;
  fill: #dc4c3e;
}

.project-options-menu {
  box-shadow: black 0 0 10px;
  margin-left: 3px;
  border-radius: 5px;
  padding: 3px;
  position: absolute;
  width: 22%;
  background-color: white;
  z-index: 10;
}

.project-options-menu > a {
  display: block;
  padding: 5px;
  border-radius: 5px;
}

.project-options-menu > a:hover {
  background-color: #fee6e3;
}


</style>