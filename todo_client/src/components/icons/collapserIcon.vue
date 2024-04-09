<script>
export default {
  name: "collapserIcon",
  props: {
    closedAngle: {
      default: 0
    },
    openAngle: {
      default: 270
    },
    isOpened: {
      default: false
    }
  },
  computed: {
    angle() {
      return this.isOpened ? this.openAngle : this.closedAngle;
    }
  },
}
</script>

<template>
  <svg class="addButtonIcon" viewBox="-50 -50 100 100" :style="{transform: 'rotate('+angle+'deg)'}">
    <polyline points="25 -45 -25 0 25 45"/>
  </svg>
</template>

<style scoped>
svg {
  fill: none;
  stroke: #7a7a7a;
  stroke-width: 15px;
  stroke-linecap: round;
  stroke-linejoin: round;
}
</style>