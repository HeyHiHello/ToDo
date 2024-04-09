// Overview of today and missed tasks
<template>
  <div id="today-overview" class="main-widget">
    <div class="overview-header">
      <h2>Сегодня</h2>
    </div>
    <task-overview class="missed-tasks" v-if="missedTasks.length > 0"
      headerText="Пропущеные" 
      :taskList="missedTasks"/>
    <task-overview class="today-tasks" v-if="todayTasks.length > 0"
      headerText="Сегодня" 
      :taskList="todayTasks"/>
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
    todayTasks() {
      return this.$store.state.tasks.filter(this.todayTaskPredicate);
    }
  }, 
  methods: {
    missedTaskPredicate(task) {
      return task.isDone === false &&
      task.expireAt &&
      task.expireAt < new Date();
    },
    todayTaskPredicate(task) {
      let tomorrow = new Date(new Date().toDateString());
      tomorrow.setDate(tomorrow.getDate() + 1);
      
      return task.isDone == false &&
      task.expireAt < tomorrow && task.expireAt > new Date();
    }
  },
}
</script>

<style scoped>
.missed-tasks, .today-tasks {
  margin-top: 20px;
}

.overview-header {
  display: flex;
  text-align: left;
}
</style>