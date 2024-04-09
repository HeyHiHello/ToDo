// List of task contains Task Items
<template>
  <div class="task-overview">
    <div class="overview-header">
      <div class="overview-collapser" @click="onCollapserClicked"><collapser-icon :closedAngle="180" :isOpened="isOpened"/></div>
      <div class="header-text"><h3>{{ headerText }}</h3></div>
    </div>
    <div class="line"></div>
    <div class="task-list" v-show="isOpened">
      <div class="task-list-elem" v-for="task in taskList" :key="task.id" >
        <task-item 
          :task="task" 
          @DeleteBtnClicked="onDeleteBtnClicked" 
          @ChangeBtnClicked="onChangeBtnClicked"/>
        <div class="line"></div>
      </div>
      
    </div>
  </div>
</template>

<script>
import collapserIcon from '../icons/collapserIcon.vue'
import TaskItem from './TaskItem.vue'
export default {
  components: { collapserIcon, TaskItem },
  props: {
    headerText: {required: true},
    taskList: {required: true,},
  },
  emits: ['DeleteTask', 'ChangeTask'],
  data() {
    return {
      isOpened: true,
    }
  },
  methods: {
    onCollapserClicked() {
      this.isOpened = !this.isOpened;
    },
    onDeleteBtnClicked(taskId) {
      this.$emit('DeleteTask', taskId);
    },
    onChangeBtnClicked(taskId) {
      this.$emit('ChangeTask', taskId);
    }
  }
}
</script>

<style scoped>
.overview-header {
  display: flex;
}
.overview-collapser {
  width: 20px;
}
.header-text {
  margin-left: 5px;
}
.line {
  height: 1px;
  width: 95%;
  margin-left: 25px;
  background-color: #a4a4a4;
}

.overview-collapser {
  padding: 1px 3px 1px 3px;
  border-radius: 5px;
}

.overview-collapser:hover {
  background-color: #e6e6e6;
}

.overview-collapser svg {
  margin-bottom: -6px;
}

.task-item {
  margin-bottom: 10px;
  margin-top: 10px;
}
</style>