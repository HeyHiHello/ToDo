// Display of tasks for project
<template>
  <div id="project-overview" class="main-widget">
    <div class="overview-header">
      <div class="project-data">
        <h2>{{ storedProject.title }}</h2>
        <h3>{{ storedProject.description }}</h3>
        <h4>{{ expireDateFormated }}</h4>
      </div>
      <div class="new-task-btn">
        <router-link :to="currentPath + '/task/new'">Новая задача</router-link>
      </div>
    </div>
    <task-overview class="missed-tasks" v-if="missedTasks.length > 0"
      headerText="Пропущеные" 
      :taskList="missedTasks"/>
    <task-overview class="current-tasks" v-if="currentTasks.length > 0"
      headerText="Текущие" 
      :taskList="currentTasks"/>
    <task-overview class="donetasks" v-if="doneTasks.length > 0"
      headerText="Завершенные" 
      :taskList="doneTasks"/>
  </div>
  <component :is="childLayout"></component>
</template>

<script>
import TaskOverview from '@/components/Overview/TaskOverview.vue';
import CoveringWidgetLayout from '@/layouts/CoveringWidgetLayout.vue';
export default {
  // watch: {
  //   storedProject(newProject, oldProject) {
  //     if (newProject.id == '') {
  //       this.$router.push({ path: '/today' });
  //     }
  //     console.log(newProject.id == '');
  //     console.log(newProject);
  //     console.log(oldProject);
  //   }
  // },
  components: {TaskOverview, CoveringWidgetLayout},
  computed: {
    projectId () {
      return Number(this.$route.params.id);
    },
    storedProject() {
      if (this.$store.getters.projectById(this.projectId)) {
        return this.$store.getters.projectById(this.projectId);
      }
      return {id: '', title:'', description:'', expireAt: new Date()}
      // return this.$store.getters.projectById(this.projectId);
    },
    missedTasks() {
      return this.$store.getters.filteredTasks(this.missedTaskPredicate);
    },
    currentTasks() {
      return this.$store.getters.filteredTasks(this.currentTaskPredicate);
    },
    doneTasks() {
      return this.$store.getters.filteredTasks(this.doneTaskPredicate);
    },
    childLayout() {
      return this.$route.meta.childLayout;
    },
    expireDateFormated() {
      if (this.storedProject.expireAt) {
        return this.storedProject.expireAt.toLocaleDateString('ru-RU');
      }
      return '';
    },
    currentPath() {
      let path = this.$route.path;
      if (path.at(path.length - 1) === '/') {
        path = path.split('');
        path.splice(path.length - 1, 1)
        path = path.join('');
      }
      return path;
    }
  }, 
  methods: {
    missedTaskPredicate(task) {
      return task.project.id === this.projectId &&
      !task.isDone && 
      task.expireAt &&
      task.expireAt <= new Date();
    },
    currentTaskPredicate(task) {
      return task.project.id === this.projectId &&
      task.isDone === false &&
      (task.expireAt > new Date() || !task.expireAt);
    },
    doneTaskPredicate(task) {
      return task.project.id === this.projectId && 
      task.isDone === true;
    }
  },
}
</script>

<style scoped>
.missed-tasks, .current-tasks, .missed-tasks, .donetasks {
  margin-top: 20px;
}

.overview-header {
  display: flex;
  text-align: left;
}

.project-data {
  flex-grow: 1;
}

.project-data h3 {
  color: #666666;
}
.project-data h4 {
  color: #dc4c3e;
}

.new-task-btn {
  background-color: #e6e6e6;
  display: inline-block;
  height: fit-content;
  padding: 5px;
  border-radius: 5px;
}

.new-task-btn:hover {
  background-color: #c2c2c2;
}
</style>