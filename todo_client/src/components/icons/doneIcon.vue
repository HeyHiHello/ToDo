<script>
export default {
  name: "doneIcon",
  props: {
    isDone: {
        default: false,
    }
  }
}
</script>

<template>
  <svg class="doneIcon" viewBox="-50 -50 100 100">
    <circle cx="0" cy="0" r="45"/>
    <path v-bind:class="{'hidden': !isDone}" d="M -20 0 L -5 15 L 20 -15"/>
  </svg>
</template>

<style scoped>
circle {
  fill: none;
  stroke: gray;
  stroke-width: 7px;
}
path {
  fill: none;
  stroke: gray;
  stroke-width: 10px;
  stroke-linecap: round;
}

.hidden {
  display: none;
}

.doneIcon:hover .hidden {
  display: block;
}
</style>