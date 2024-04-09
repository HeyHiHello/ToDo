// Widget is placed in center. Background white and empty.
<template>
  <div class="settings-container">
    <router-view></router-view>
  </div>
</template>

<style scoped>
.settings-container {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100vh;
}
.settings-view {
  position: relative;
  padding: 20px;
  box-shadow: 0 0 10px #7f7f7f;
  top: 20%;
  left: 50%;
  transform: translate(-50%, 0);
}
</style>