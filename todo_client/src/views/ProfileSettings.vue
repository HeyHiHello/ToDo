// Change password and email settings window
<template>
<div class="profile-settings settings-view">
  <h2>Настройки профиля</h2>
  <div class="username"><strong>{{ user.username }}</strong></div>
  <div class="password-settings">
    <div class="settings-header"><h3>Изменить пароль</h3></div>
    <div class="password">
      <div class="old-password">
        <div class="input-header">
          <label for="password-input">Старый пароль</label>
          <p class="error" v-if="v$.passwordFormData.oldPassword.$error">{{ v$.passwordFormData.oldPassword.$errors[0].$message }}</p>
        </div>
        <input type="password" id="password-input" v-model="passwordFormData.oldPassword">
      </div>
      <div class="new-password">
        <div class="input-header">
          <label for="new-password-input">Новый пароль</label>
          <p class="error" v-if="v$.passwordFormData.newPassword.$error">{{ v$.passwordFormData.newPassword.$errors[0].$message }}</p>
        </div>
        <input type="password" id="new-password-input" v-model="passwordFormData.newPassword">
      </div>
      <div class="new-password-again">
        <div class="input-header">
          <label for="new-password-again-input">Новый пароль</label>
          <p class="error" v-if="v$.passwordFormData.newPasswordAgain.$error">{{ v$.passwordFormData.newPasswordAgain.$errors[0].$message }}</p>
        </div>
        <input type="password" id="new-password-again-input" v-model="passwordFormData.newPasswordAgain">
      </div>
    </div>
    <div class="save-btn" @click="changeUserPassword">
      <p>Сохранить</p>
    </div>
  </div>
  
  <div class="email-settings">
    <div class="settings-header">
      <h3>Изменить Email</h3>
    </div>
    <div class="email">
      <div class="input-header">
        <label for="email-input">Новый Email</label>
        <p class="error" v-if="v$.emailFormData.email.$error">{{ v$.emailFormData.email.$errors[0].$message }}</p>
      </div>
      <input type="email" id="email-input" v-model="emailFormData.email">
    </div>
    <div class="save-btn" @click="changeUserEmail">
      <p>Сохранить</p>
    </div>
  </div>
  <div class="line"></div>
  <div class="cancel-btn">
    <router-link to="/today">Вернуться на главную</router-link>
  </div>
</div>
</template>

<script>
import useVuelidate from '@vuelidate/core';
import { email, helpers, maxLength, minLength, required, sameAs } from '@vuelidate/validators';
export default {
  components: {},
  setup() {
    return {
      v$: useVuelidate()
    }
  },
  data() {
    return {
      passwordFormData: {
        oldPassword: '',
        newPassword: '',
        newPasswordAgain: '',
      },
      emailFormData: {
        email: this.$store.state.user.email,
      }
    }
  },
  validations() {
    return {
      passwordFormData: {
        oldPassword: {
          required: helpers.withMessage("Введите пароль", required)
        },
        newPassword: {
          required: helpers.withMessage("Введите пароль", required),
          minLength: helpers.withMessage("Минимум 3 символа", minLength(3)),
          maxLength: helpers.withMessage("Максимум 20 символов", maxLength(20))
        },
        newPasswordAgain: {
          required: helpers.withMessage("Пароль еще раз", required), 
          sameAsPass: helpers.withMessage('Пароли не совпадают' , sameAs(this.passwordFormData.newPassword))
        },
      },
      emailFormData: {
        email: {
          required: helpers.withMessage('Введите email', required),
          email: helpers.withMessage('Введите email', email)
        }
      }
    }
  },
  computed: {
    user() {
      return this.$store.state.user;
    }
  },
  methods: {
    changeUserEmail() {
      this.v$.emailFormData.$validate().then(isValid => {
        if (isValid) {
          this.$store.dispatch('changeUserEmail', {newEmail: this.emailFormData.email});
          this.$router.push('/today');
        }
      });
    },
    changeUserPassword() {
      this.v$.passwordFormData.$validate().then(isValid => {
        if (isValid) {
          this.$store.dispatch('changeUserPassword', {
            passwordForm: {
              oldPassword: this.passwordFormData.oldPassword,
              newPassword: this.passwordFormData.newPassword
            }
          });
          this.$router.push('/today');
        }
      });
    }
  }
}
</script>

<style scoped>
.line {
  height: 1px;
  width: 96%;
  margin-left: 2%;
  background-color: #a4a4a4;
  margin-top: 10px;
}

.profile-settings {
  width: 20%;
  border-radius: 5px;
}

.password-settings {
  margin-top: 10px;
}

.btn-block {
  margin-top: 10px;
}

label {
  display: block;
  text-align: left;
  flex-grow: 1;
}

input {
  width: 100%;
}

.email-settings {
  margin-top: 20px;
}

.current-email p {
  text-align: left;
  display: inline-block;
}

.save-btn {
  width: fit-content;
  margin-left: 50%;
  margin-top: 10px;
  transform: translate(-50%, 0);
  background-color: #dc4c3e;
  color: white;
  border-radius: 5px;
}

.save-btn:hover {
  background-color: #c3392c;
}

.save-btn p {
  display: block;
  padding: 5px;
}

.cancel-btn {
  margin-top: 10px;
  background-color: #dedede;
  color: #444444;
  border-radius: 5px;
}

.cancel-btn:hover {
  background-color: #b5b5b5;
}

.cancel-btn a {
  display: block;
  padding: 5px;
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