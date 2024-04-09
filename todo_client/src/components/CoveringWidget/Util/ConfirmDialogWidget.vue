<template>
<div class="confirm-dialog-layout">
  <div class="confirm-message">
    <h1>{{dialogHeader}}</h1>
  </div>
  <div class="confirm-warning" v-if="dialogWarning">
    <h2>{{ dialogWarning }}</h2>
  </div>

  <dialog-btn-widget 
    @CancelSignal="$emit('CancelSignal')" 
    @ConfirmSignal="$emit('ConfirmSignal')" 
    :confirmBtnText="confirmBtnText"
    :cancelBtnText="cancelBtnText">
      
    </dialog-btn-widget>
</div>
</template>

<script>
import DialogBtnWidget from '@/components/CoveringWidget/Util/DialogBtnWidget.vue'
export default {
  components:{DialogBtnWidget},
  props: {
    confirmBtnText: {default: 'Подтвердить'}, 
    cancelBtnText: {default: 'Отменить'},
    dialogHeader: {required: true},
    dialogWarning: {}
  },
  emits: ['CancelSignal', 'ConfirmSignal']
}
</script>

<style scoped>
.confirm-dialog-layout {
  background-color: white;
  width: fit-content;
  padding: 30px;
  border-radius: 5px;
  box-shadow: 0 0 10px #7f7f7f;
}

.confirm-warning {
  color: #dc4c3e;
}

</style>