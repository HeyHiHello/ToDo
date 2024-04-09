<script>
export default {
  name: "addIcon"
}
</script>

<template>
  <svg class="addButtonIcon" viewBox="-50 -50 100 100">
    <circle cx="0" cy="0" r="40"/>
    <line x1="-25" y1="0" x2="25" y2="0"/>
    <line x1="0" y1="-25" x2="0" y2="25"/>
  </svg>
</template>

<style scoped>
circle {
  fill: #dc4c3e;
  stroke: #dc4c3e;
  stroke-width: 5px;
}
line {
  stroke-linecap: round;
  stroke: white;
  stroke-width: 5px;
}
</style>