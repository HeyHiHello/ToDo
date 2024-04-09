<template>
    <component :is="layout">
    </component>
</template>

<script>
import CoveringWidgetLayout from './layouts/CoveringWidgetLayout.vue';
import MainLayout from './layouts/MainLayout.vue';
import SettingsLayout from './layouts/SettingsLayout.vue';

  export default {
    computed: {
      layout() {
        return this.$route.meta.layout;
      }
    },
    components: {
      MainLayout, CoveringWidgetLayout, SettingsLayout
    },
  }
</script>

<style lang="scss">
* {
  margin: 0;
}
a {
  text-decoration: none;
  color: black;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
