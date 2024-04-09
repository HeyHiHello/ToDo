// Overview of next week and missed task
<template>
  <div id="today-overview" class="main-widget"> 
    <div class="overview-header">
      <h2>На неделе</h2>
    </div>
    <task-overview class="missed-tasks" v-if="missedTasks.length !== 0"
      headerText="Пропущеные" 
      :taskList="missedTasks" 
      @DeleteTask="onDeleteTask" 
      @ChangeTask="onChangeTask"/>
    <task-overview class="week-tasks" v-if="weekTasks.length !== 0"
      headerText="На неделе" 
      :taskList="weekTasks" 
      @DeleteTask="onDeleteTask" 
      @ChangeTask="onChangeTask"/>
  </div>
  <component :is="childLayout"></component>
</template>

<script>
import TaskOverview from '@/components/Overview/TaskOverview.vue';
import CoveringWidgetLayout from '@/layouts/CoveringWidgetLayout.vue';

export default {
  components: {TaskOverview, CoveringWidgetLayout},
  computed: {
    childLayout() {
      return this.$route.meta.childLayout;
    },
    missedTasks() {
      return this.$store.state.tasks.filter(this.missedTaskPredicate);
    },
    weekTasks() {
      return this.$store.state.tasks.filter(this.weekTaskPredicate);
    }
  }, 
  methods: {
    onDeleteTask(taskId) {
      this.$emit('DeleteTask', taskId);
    },
    onChangeTask(taskId) {
      this.$emit('ChangeTask', taskId);
    },
    missedTaskPredicate(task) {
      return task.isDone === false &&
      task.expireAt &&
      task.expireAt < new Date();
    },
    weekTaskPredicate(task) {
      let nextWeek = new Date();
      nextWeek.setDate(nextWeek.getDate() + 7);
      return task.isDone === false &&
      task.expireAt < nextWeek && task.expireAt > new Date();
    }
  }
}
</script>

<style scoped>
.missed-tasks, .week-tasks {
  margin-top: 20px;
}

.overview-header {
  display: flex;
  text-align: left;
}
</style>