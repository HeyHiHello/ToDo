<template>
  <div class="new-project-btn">
    <router-link  :to="currentPath + '/project/new'">Новый проект</router-link>
  </div>
</template>

<script>
export default {
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
.new-project-btn {
  text-align: left;
  margin: 5px;
  border-radius: 5px;
}

.new-project-btn:hover {
  background-color: #f6eeee;
}

.new-project-btn a {
  display: block;
  padding: 5px;
}
</style>