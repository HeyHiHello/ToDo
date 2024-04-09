<template>
<div class="confirm-delete-task-widget covering-widget">
  <confirm-dialog-widget 
    confirmBtnText="Удалить" 
    :dialogHeader="'Вы хотите удалить задачу ' + taskTitle"
    dialogWarning="Это действие нельзя отменить" 
    @CancelSignal="onCancelSignal" 
    @ConfirmSignal="onConfirmSignal"/>
</div>

  
</template>

<script>
import ConfirmDialogWidget from '@/components/CoveringWidget/Util/ConfirmDialogWidget.vue'
export default {
  components: {ConfirmDialogWidget},
  computed: {
    task() {
      let taskId = Number(this.$route.params.childId);
      return this.$store.getters.taskById(taskId);
    },
    taskId() {
      return Number(this.$route.params.childId);
    },
    taskTitle() {
      if (this.task) {
        return this.task.title;
      }
      return 'temp';
    }
  },
  methods: {
    onCancelSignal() {
      this.$router.back();
    },
    onConfirmSignal() {
      this.$router.back();
      this.$store.dispatch('deleteTask', {taskId: this.taskId});
    }
  },
}
</script>

<style scoped>
.confirm-delete-task-widget {
  width: fit-content;
}
</style>