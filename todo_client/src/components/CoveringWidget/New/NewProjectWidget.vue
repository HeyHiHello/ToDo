<template>
  <div class="new-project-widget covering-widget">
    <div class="widget-header"><h1>Создать проект</h1></div>
    <project-form v-model:formData="formData" :v="v$">
      <dialog-btn-widget
        @CancelSignal="onCancelSignal" 
        @ConfirmSignal="onConfirmSignal" 
        confirmBtnText="Создать">
      </dialog-btn-widget>
    </project-form>
  </div>
</template>

<script>
import ProjectForm from '../Form/ProjectForm.vue'
import DialogBtnWidget from '../Util/DialogBtnWidget.vue'
import useVuelidate from '@vuelidate/core';
import { helpers, required } from '@vuelidate/validators';

export default {
  components:{ ProjectForm, DialogBtnWidget },
  setup() {
    return {
      v$: useVuelidate()
    }
  },
  data() {
    return {
      formData: {
        title: '',
        description: '',
        expireAt: '',
      }
    }
  },
  validations() {
    return {
      formData: {
        title: {required: helpers.withMessage('Введите название', required)},
        description: {},
        expireAt: {},
      }
    }
  },
  methods: {
    onCancelSignal() {
      this.$router.back();
    },
    onConfirmSignal() {
      this.v$.$validate().then(isValid => {
        if (isValid) {
          this.$store.dispatch('createProject', {formData: this.formData});
          this.$router.back();
        }
      });
    }
  },
}
</script>

<style scoped>
.new-project-widget {
  background-color: white;
  width: 30%;
  padding: 30px;
  border-radius: 5px;
  box-shadow: 0 0 10px #7f7f7f;
  font-weight: bolder;
}

.widget-header h1 {
  font-size: 1.5em;
}
</style>