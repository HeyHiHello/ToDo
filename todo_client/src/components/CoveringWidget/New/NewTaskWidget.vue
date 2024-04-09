<template>
  <div class="new-task-widget covering-widget">
    <div class="widget-header"><h1>Создать задачу</h1></div>
    <task-form v-model:formData="formData" :v="v$">
      <dialog-btn-widget 
        confirmBtnText="Создать" 
        @CancelSignal="onCancelSignal" 
        @ConfirmSignal="onConfirmSignal"/>
    </task-form>
  </div>
</template>

<script>
import DialogBtnWidget from '../Util/DialogBtnWidget.vue'
import TaskForm from '../Form/TaskForm.vue'
import { helpers, required } from '@vuelidate/validators'
import useVuelidate from '@vuelidate/core'
export default {
  components:{TaskForm, DialogBtnWidget},
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
        priority: 0,
        isDone: false,
        projectId: Number(this.$route.params.id)
      }
    }
  },
  validations() {
    return {
      formData: {
        title: {required: helpers.withMessage('Введите название', required)},
        description: {},
        expireAt: {},
        priority: {},
        isDone: {},
        projectId: {},
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
          this.$store.dispatch('createTask', {taskId: this.taskId, formData: this.formData});
          this.$router.back();
        }
      });
    }
  }
}
</script>

<style scoped>
.new-task-widget {
  background-color: white;
  width: fit-content;
  border-radius: 5px;
  box-shadow: 0 0 10px #7f7f7f;
}

.widget-header {
  border-bottom: 0.5px solid #666666;
}

.widget-header h1 {
  font-size: 1.5em;
}
</style>