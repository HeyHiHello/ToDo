<template>
  <div class="project-title">
    <div class="input-header">
      <label for="project-title-input">Название</label>
      <p class="error" v-if="v.formData.title.$error">{{v.formData.title.$errors[0].$message}}</p>
    </div>
    <input id="project-title-input" type="text" v-model="theForm.title" @input="updateFromData">
  </div>
  <div class="project-description">
    <div class="input-header">
      <label for="project-description-input">Описание</label>
      <p class="error" v-if="v.formData.description.$error">{{v.formData.description.$errors[0].$message}}</p>
    </div>
    <textarea id="project-description-input" type="text" rows="5" v-model="theForm.description" @input="updateFromData"/>
  </div>
  <div class="project-expire">
    <div class="input-header">
      <label for="project-expire-input">Срок выполнение</label>
      <p class="error" v-if="v.formData.expireAt.$error">{{v.formData.expireAt.$errors[0].$message}}</p>
    </div>
    <input id="project-expire-input" type="datetime-local" v-model="_expireAt" @input="updateFromData"/>
  </div>
  <slot/>
</template>

<script>
export default {
  props:{
    formData: {
      type: Object,
      default: () => ({}),
    },
    v: {
      type: Object
    }
  },
  data() {
    return {
      theForm: {
        title: this.formData.title,
        description: this.formData.description,
        expireAt: this.formData.expireAt
      }
    }
  },
  computed: {
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
input, textarea {
  width: 100%;
}

label {
  display: block;
  text-align: left;
  flex-grow: 1;
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