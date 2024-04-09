<template>
  <div class="change-project-widget covering-widget">
    <div class="widget-header"><h1>Изменить задачу</h1></div>
    <task-form v-model:formData="formData" :v="v$">
      <dialog-btn-widget 
          confirmBtnText="Сохранить" 
          @CancelSignal="onCancelSignal" 
          @ConfirmSignal="onConfirmSignal">
      </dialog-btn-widget>
    </task-form>
  </div>
</template>

<script>
import DialogBtnWidget from '@/components/CoveringWidget/Util/DialogBtnWidget.vue'
import TaskForm from '../Form/TaskForm.vue'
import { helpers, required } from '@vuelidate/validators'
import useVuelidate from '@vuelidate/core'

export default {
  components: {DialogBtnWidget, TaskForm},
  emits: ['CancelChange', 'ConfirmChange'],
  setup() {
    return {
      v$: useVuelidate()
    }
  },
  data() {
    return {
      formData: {
        title: this.$store.getters.taskById(Number(this.$route.params.childId)).title,
        description: this.$store.getters.taskById(Number(this.$route.params.childId)).description,
        expireAt: this.$store.getters.taskById(Number(this.$route.params.childId)).expireAt,
        priority: this.$store.getters.taskById(Number(this.$route.params.childId)).priority,
        isDone: this.$store.getters.taskById(Number(this.$route.params.childId)).isDone,
        projectId: this.$store.getters.taskById(Number(this.$route.params.childId)).project.id
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
  computed: {
    taskId() {
      return Number(this.$route.params.childId);
    },
  },
  methods: {
    onCancelSignal() {
      this.$router.back();
    },
    onConfirmSignal() {
      this.v$.$validate().then(isValid => {
        if (isValid) {
          this.$store.dispatch('changeTask', {taskId: this.taskId, formData: this.formData});
          this.$router.back();
        }
      });
    }
  }
}
</script>


<style scoped>
.change-project-widget {
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