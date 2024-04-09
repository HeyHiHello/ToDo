// Display of one task in task list of project / today / week overview
// With change delete done and project btns
<template>
  <div class="task-item">
    <div class="done-btn">
      <done-icon :isDone="task.isDone" @click="onDoneBtnClicked"/>
    </div>
    <div class="task-info">
      <div class="taskTitle">{{ task.title }}</div>
      <div class="taskDescription">{{ task.description }}</div>
      <div class="taskExpire">{{ expireDateFormated }}</div>
    </div>
    <div class="right-block">
      <div class="btn-block">
        <div class="edit-btn">
          <router-link :to="currentPath + '/task/change/' + task.id"><pencil-icon class="icon"/></router-link>
          
        </div>
        <div class="delete-btn">
          <router-link :to="currentPath + '/task/delete/' + task.id"><trash-icon class="icon"/></router-link>
        </div>
      </div>
      <div class="project-btn">
        <router-link :to="'/project/' + task.project.id" class="project-title">{{ task.project.title }}</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import doneIcon from '../icons/doneIcon.vue'
import PencilIcon from '../icons/pencilIcon.vue'
import TrashIcon from '../icons/trashIcon.vue'
export default {
  components: { doneIcon, PencilIcon, TrashIcon },
  props: {
    task: {required: true},
  },
  emits: ['ChangeBtnClicked', 'DeleteBtnClicked', 'DoneBtnClicked'],
  computed: {
    expireDateFormated() {
      if (this.task.expireAt) {
        return this.task.expireAt.toLocaleDateString('ru-RU');
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
    onDoneBtnClicked() {
      this.$store.dispatch('toggleTaskDone', {taskId: this.task.id});
      this.$emit('DoneBtnClicked', this.task.id);
    },
  }
}
</script>

<style scoped>
  .task-item {
    width: 100%;
    display: flex;
  }

  .task-info {
    flex-grow: 1;
    text-align: left;
    margin-left: 5px;
  }

  .taskTitle {
    font-size: 1em;
    font-weight: bolder;
  }

  .taskDescription {
    height: 20px;
    color: #666666;
    font-size: 0.9em;
  }

  .taskExpire {
    color: #dc4c3e;
    font-size: 0.8em;
  }

  .done-btn {
    vertical-align: middle;
    width: 30px;
  }

  .done-btn svg {
    margin-top: 50%;
    display: none;
  }

  .task-item:hover .done-btn svg {
    display: block;
  }

  .icon {
    width: 25px;
  }

  .btn-block {
    display: flex;
  }

  .edit-btn, .delete-btn {
    width: 25px;
    height: 25px;
    border-radius: 5px;
  }

  .edit-btn svg, .delete-btn svg {
    display: none;
  }

  .task-item:hover .edit-btn svg, .task-item:hover .delete-btn svg {
    display: block;
  }

  .edit-btn:hover, .delete-btn:hover {
    background-color: #e6e6e6;
  }

  .project-title {
    color: #666666;
    font-size: 0.8em;
  }

  .project-btn {
    border-radius: 5px;
  }

  .project-btn:hover {
    background-color: #e6e6e6;
  }
</style>