<script>
export default {
  name: "hashTagIcon"
}
</script>

<template>
  <svg class="projectIcon" viewBox="-50 -50 100 100">
    <g>
      <line x1="-5" y1="-45" x2="-35" y2="45"/>
      <line x1="35" y1="-45" x2="5" y2="45"/>
      <line x1="-45" y1="-15" x2="45" y2="-15"/>
      <line x1="-45" y1="15" x2="45" y2="15"/>
    </g>
  </svg>
</template>

<style scoped>
svg {
  stroke: #7a7a7a;
}
g {
  stroke-linecap: round;
  stroke-width: 7px;
}
</style>