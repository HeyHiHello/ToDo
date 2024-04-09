<template>
  <div class="sign-up-widget settings-view">
    <form>
      <div class="username">
        <div class="input-header">
          <label for="username-input">Логин</label>
          <p class="error" 
            v-if="v$.userTempForm.username.$error">
            {{v$.userTempForm.username.$errors[0].$message}}
          </p>
        </div>
        <input type="text" id="username-input" v-model="userTempForm.username">
      </div>
      <div class="password">
        <div class="input-header">
          <label for="password-input">Пароль</label>
          <p class="error" 
            v-if="v$.userTempForm.password.$error">
            {{v$.userTempForm.password.$errors[0].$message}}
          </p>
        </div>
        <input type="password" id="password-input" v-model="userTempForm.password">
      </div>
      <div class="password-again">
        <div class="input-header">
          <label for="password-input">Повторите пароль</label>
          <p class="error" 
            v-if="v$.userTempForm.passwordAgain.$error">
            {{v$.userTempForm.passwordAgain.$errors[0].$message}}
          </p>
        </div>
        
        <input type="password" id="password-input" v-model="userTempForm.passwordAgain">
      </div>
      <div class="email">
        <div class="input-header">
          <label for="email-input">Email</label>
          <p class="error" 
            v-if="v$.userTempForm.email.$error">
            {{v$.userTempForm.email.$errors[0].$message}}
          </p>
        </div>
        <input type="text" id="email-input" v-model="userTempForm.email">
      </div>
      <div class="signin">
        <router-link to="/signin">Войти</router-link>
      </div>
        <div class="sign-up-btn" @click="signUpBtnClicked">
          <p>Зарегистрироваться</p>
        </div>
    </form>
  </div>
</template>

<script>
import {useVuelidate} from "@vuelidate/core"
import {required, email, sameAs, helpers, minLength, maxLength} from "@vuelidate/validators"

export default {
  setup() {
    return { v$: useVuelidate() }
  },
  data() {
    return {
      errors: {},
      userTempForm: {
        username: '',
        password: '',
        passwordAgain: '',
        email: ''
      }
    }
  },
  validations() {
    return {
      userTempForm: {
        username: {
          required: helpers.withMessage("Введите имя пользователя", required),
          minLength: helpers.withMessage("Минимум 3 символа", minLength(3)),
          maxLength: helpers.withMessage("Максимум 20 символов", maxLength(20))
        },
        password: {
          required: helpers.withMessage("Введите пароль", required),
          minLength: helpers.withMessage("Минимум 3 символа", minLength(3)),
          maxLength: helpers.withMessage("Максимум 20 символов", maxLength(20))
        },
        passwordAgain: {
          required: helpers.withMessage("Пароль еще раз", required), 
          sameAsPass: helpers.withMessage('Пароли не совпадают' , sameAs(this.userTempForm.password))
        },
        email: {
          required: helpers.withMessage("Введите email", required), 
          email: helpers.withMessage("Введите email", email)
        }
      }
    }
  },
  methods: {
    signUpBtnClicked() {
      this.v$.$validate().then(isValid => {
        if (isValid) {
          let userForm = {
            username: this.userTempForm.username,
            password: this.userTempForm.password,
            email: this.userTempForm.email,
          }
          this.$store.dispatch('signUp', {userForm: userForm});
        }
        return;
      });
    },
    checkForm() {
      
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

.sign-up-widget {
  width: 300px;
}

.sign-up-btn {
  padding: 10px;
  color: white;
  border-radius: 5px;
  width: 92%;
  background-color: #dc4c3e;
}

.password, .password-again, .email {
  margin-top: 10px;
}

.signin {
  padding: 5px;
}

.sign-up-btn:hover {
  background-color: #c3392c;
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
</style>