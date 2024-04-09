// Widget is placed in center. Background gray transperent
<template>
  <div class="covering-container">
    <router-view name='covering'/>
  </div>
</template>

<style scoped>
.covering-container {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100vh;
  background-color: #7f7f7f9b;
}

.covering-widget {
  position: relative;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
</style>