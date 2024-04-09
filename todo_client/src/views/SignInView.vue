<template>
  <div class="sign-in-widget settings-view">
    <div class="username">
      <div class="input-header">
        <label for="username-input">Логин</label>
        <p class="error" v-if="v$.userForm.username.$error">{{ v$.userForm.username.$errors[0].$message }}</p>
      </div>
      <input type="text" id="username-input" v-model="userForm.username">
    </div>
    <div class="password">
      <div class="input-header">
        <label for="password-input">Пароль</label>
        <p class="error" v-if="v$.userForm.password.$error">{{ v$.userForm.password.$errors[0].$message }}</p>
      </div>
      <input type="password" id="password-input" v-model="userForm.password">
    </div>
    <div class="singup">
      <router-link to="/signup">Регистрация</router-link>
    </div>
    <div class="sign-in-btn" @click="signInBtnClicked">
      <p>Войти</p>
    </div>
  </div>
</template>

<script>
import { helpers, required } from '@vuelidate/validators';
import useVuelidate from '@vuelidate/core';


export default {
  setup() {
    return {v$: useVuelidate()};
  },
  data() {
    return {
      userForm: {
        username: '',
        password: '',
      }
    }
  },
  validations() {
    return {
      userForm: {
        username: {
          required: helpers.withMessage('Введите имя пользователя', required),
        },
        password: {
          required: helpers.withMessage('Введите пароль', required)
        }
      }
    }
  },
  methods: {
    signInBtnClicked() {
      this.v$.$validate().then(isValid => {
        if (isValid) {
          this.$store.dispatch('signIn', {userForm: this.userForm});
          this.$router.push('/today');
        }
      });
    }
  }
  
}
</script>

<style scoped>
.settings-view {
  position: relative;
  padding: 20px;
  box-shadow: 0 0 10px #7f7f7f;
  top: 20%;
  left: 50%;
  transform: translate(-50%, 0);
  border-radius: 5px;
}

.sign-in-widget {
  width: 300px;
}

.password {
  margin-top: 10px;
}
.singup {
  padding: 5px;
}

.sign-in-btn {
  padding: 10px;
  color: white;
  border-radius: 5px;
  width: 92%;
  background-color: #dc4c3e;
}

.sign-in-btn:hover {
  background-color: #c3392c;
}

a {
  text-decoration: underline;
  color: #7f7f7f;
}

a:hover {
  color: #dc4c3e;
}

label {
  flex-grow: 1;
  display: block;
  text-align: left;
}

input {
  width: 100%;
}

.error {
  color: #c3392c;
}

p.error {
  font-weight: bold;
  font-size: 0.8em;
}

.input-header {
  display: flex;
}
</style>