<template>
  <div class="change-project-widget covering-widget">
      <div class="widget-header"><h1>Изменить проект</h1></div>
      <project-form v-model:formData="formData" :v="v$">
        <dialog-btn-widget
          @CancelSignal="onCancelSignal" 
          @ConfirmSignal="onConfirmSignal" 
          confirmBtnText="Сохранить">
        </dialog-btn-widget>
      </project-form>
  </div>
</template>

<script>
import DialogBtnWidget from '@/components/CoveringWidget/Util/DialogBtnWidget.vue';
import ProjectForm from '@/components/CoveringWidget/Form/ProjectForm.vue';
import useVuelidate from '@vuelidate/core';
import { helpers, required } from '@vuelidate/validators';
export default {
  components: { DialogBtnWidget, ProjectForm },
  emits: ['ConfirmChange', 'CancelChange'],
  setup() {
    return {
      v$: useVuelidate()
    }
  },
  data() {
    return {
      formData: {
        title: this.$store.getters.projectById(Number(this.$route.params.childId)).title,
        description: this.$store.getters.projectById(Number(this.$route.params.childId)).description,
        expireAt: this.$store.getters.projectById(Number(this.$route.params.childId)).expireAt,
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
          this.$store.dispatch('changeProject', {projectId: this.projectId, formData: this.formData});
          this.$router.back();
        }
      });
    }
  },
  computed: {
    projectId() {
      return Number(this.$route.params.childId);
    },
  },
  
}
</script>

<style scoped>
.change-project-widget {
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