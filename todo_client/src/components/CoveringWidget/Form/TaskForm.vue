<template>
  <div class="flex-container">
    <div class="left-container">
      <div class="task-title">
        <div class="input-header">
          <label for="task-title-input">Название</label>
          <p class="error" v-if="v.formData.title.$error">{{ v.formData.title.$errors[0].$message }}</p>
        </div>
        <input id="task-title-input" type="text" v-model="theForm.title" @input="updateFromData">
      </div>
      <div class="task-description">
        <label for="task-description-input">Описание</label>
        <textarea id="task-description-input" rows="8" v-model="theForm.description" @input="updateFromData"></textarea>
      </div>
    </div>
    <div class="right-container">
      <div class="project-title">
        <label for="project-input">Проект</label>
        <select id="project-input" v-model="theForm.projectId" @change="updateFromData">
          <option v-for="project in projects" :key="project.id" :value="project.id">{{ project.title }}</option>
        </select>
      </div>
      <div class="expire-date">
        <label for="task-expire-input">Срок выполнения</label>
        <input type="datetime-local" id="task-expire-input" v-model="_expireAt" @input="updateFromData">
      </div>
      <div class="priority">
        <label for="priority-input">Приоритет</label>
        <select id="priority-input" v-model="theForm.priority" @change="updateFromData">
          <option value="0">Без приоритета</option>
          <option value="1">Приоритет 1</option>
          <option value="2">Приоритет 2</option>
          <option value="3">Приоритет 3</option>
          <option value="4">Приоритет 4</option>
          <option value="5">Приоритет 5</option>
        </select>
      </div>
      <slot/>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    formData:{},
    v:{},
  },
  data() {
    console.log(this.v);
    return {
      theForm: {
        title: this.formData.title,
        description: this.formData.description,
        expireAt: this.formData.expireAt,
        priority: this.formData.priority,
        isDone: this.formData.isDone,
        projectId: this.formData.projectId
      }
    }
  },
  computed: {
    taskId() {
      return Number(this.$route.params.childId);
    },
    projects() {
      return this.$store.state.projects;
    },
    _expireAt: {
      get() {
        if (this.formData.expireAt) {
          return this.theForm.expireAt.toISOString().slice(0, 16);
        }
        return '';
      },
      set(newValue) {
        this.theForm.expireAt = new Date(newValue);
      }
    },
  },
  methods: {
    updateFromData() {
      this.$emit('update:formData', this.theForm);
    }
  }
}
</script>

<style scoped>
  .flex-container {
  display: flex;
}

label {
  display: block;
  text-align: left;
  flex-grow: 1;
  font-size: 0.9em;
}

.right-container, .left-container {
  padding: 0 30px  30px 30px;
}

.right-container {
  background-color: #faf8f7;
  border-left: 0.5px solid #666666;
}

.priority {
  margin-bottom: 10px;
}

input, textarea, select {
  width: 100%;
}

.error {
  color: #c3392c;
}

p.error {
  font-weight: bold;
  font-size: 0.8em;
}

.input-header {
  display: flex;
}
</style>