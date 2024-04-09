<template>
<div class="confirm-delete-project-widget covering-widget">
  <confirm-dialog-widget 
    confirmBtnText="Удалить" 
    :dialogHeader="'Хотите удалить проект ' + projectTitle"
    :dialogWarning="'Это действие нельзя отменить'"
    @CancelSignal="onCancelSignal" 
    @ConfirmSignal="onConfirmSignal"/>
</div>
</template>

<script>
import ConfirmDialogWidget from '@/components/CoveringWidget/Util/ConfirmDialogWidget.vue';
export default {
  components: { ConfirmDialogWidget },
  emits:['ConfirmDelete', 'CancelDelete'],
  computed: {
    project() {
      return this.$store.getters.projectById(Number(this.$route.params.childId));
    },
    projectId() {
      return Number(this.$route.params.childId);
    },
    projectTitle() {
      if (this.project) {
        return this.project.title;
      }
      return 'temp';
    }
  },
  methods: {
    onCancelSignal() {
      this.$router.back();
    },
    onConfirmSignal() {
      if (this.projectId == Number(this.$route.params.id)) {
        this.$router.push('/today');
      } else {
        this.$router.back();
      }
      this.$store.dispatch('deleteProject', {projectId: this.projectId});
    },
  }
}
</script>

<style scoped>
.confirm-delete-project-widget {
  width: fit-content;
}
</style>