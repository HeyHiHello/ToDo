<template>
  <div class="btn-block">
    <div class="confirm-btn btn" @click="onConfirmBtnClicked"><p>{{ confirmBtnText }}</p></div>
    <div class="cancel-btn btn" @click="onCancelBtnClicked"><p>{{ cancelBtnText }}</p></div>
  </div>
</template>

<script>
export default {
  props: {
    cancelBtnText: {default: 'Отменить'},
    confirmBtnText: {default: 'Подтвердить'},
  },
  methods: {
    onCancelBtnClicked() {
      this.$emit('CancelSignal');
    },
    onConfirmBtnClicked() {
      this.$emit('ConfirmSignal');
    },
  },
  emits: ['CancelSignal', 'ConfirmSignal']
}
</script>

<style scoped>
.btn-block {
  display: flex;
  flex-direction: row-reverse;
}

.btn {
  display: inline-block;
  border-radius: 5px;
  padding: 5px;
}

.cancel-btn {
  background-color: #dedede;
  color: #444444;
}

.cancel-btn:hover {
  background-color: #b5b5b5;
}

.confirm-btn {
  background-color: #dc4c3e;
  color: white;
  margin-left: 5px;
}

.confirm-btn:hover {
  background-color: #c3392c;
}
</style>