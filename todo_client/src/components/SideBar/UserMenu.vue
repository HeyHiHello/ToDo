//sidebar top btn opens menu with user settings and user quit
<template>
  <div id="user-block">
      <div id="user-menu-btn" @click="onUserBtnClicked" :class="{active: isMenuOpened}">
        <div id="user-icon"><user-icon/></div>
        <div id="username"><p>{{ username }}</p></div>
        <div id="usermenu-collapser"><collapser-icon :isOpened="isMenuOpened"/></div>
      </div>
      <div id="user-menu" v-show="isMenuOpened">
        <router-link to="/settings/profile">Настройки</router-link>
        <a @click.prevent="signOut">Выйти</a>
      </div>
    </div>
</template>

<script>
import userIcon from '../icons/userIcon.vue';
import collapserIcon from '../icons/collapserIcon.vue';
export default {
  components: { userIcon, collapserIcon},
  data () {
    return {
      isMenuOpened: false,
    }
  },

  methods: {
    onUserBtnClicked() {
      this.isMenuOpened = !this.isMenuOpened;
      this.$emit('SubMenuOpened');
    },
    closeSubMenu() {
      this.isMenuOpened = false;
    },
    signOut() {
      this.$store.state.user = {};
      this.$store.state.auth = {};
      this.$router.push('/signin');
    }
  },
  computed: {
    username() {
      return this.$store.state.user.username;
    }
  }
  
}
</script>

<style scoped>
  #user-menu-btn {
    display: flex;
    margin: 5px;
    padding: 3px;
    border-radius: 5px;
    align-items: center;
    background-color: #f6eeee;
  }

  #user-menu-btn:hover {
    background-color: #fee6e3;
  }

  #user-icon {
    width: 30px;
  }

  #username {
    margin-left: 10px;
    text-align: left;
    flex-grow: 1;
    font-size: 1.2em;
    font-weight: bolder;
  }

  #usermenu-collapser {
    width: 20px;
    transform: translate(0, 3px);
  }

  #user-menu {
    box-shadow: black 0 0 10px;
    margin-left: 3px;
    border-radius: 5px;
    padding: 3px;
    position: absolute;
    width: 22%;
    background-color: white;
    z-index: 10;
  }

  #user-menu > a {
    display: block;
    padding: 5px;
    border-radius: 5px;
  }

  #user-menu > a:hover {
    background-color: #fee6e3;
  }

  #user-menu-btn.active {
    background-color: #fee6e3;
  }
</style>