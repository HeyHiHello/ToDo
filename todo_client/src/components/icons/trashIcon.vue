<script>
export default {
  name: "trashIcon"
}
</script>

<template>
  <svg class="trashIcon " viewBox="-50 -50 100 100">
    <g>
      <path d="M 0 45 L 20 45
            Q 25 45 25 40 L 30 -27 L -30 -27 L -25 40
            Q -25 45 -20 45 L 0 45"/>
      <line x1="15" y1="30" x2="15" y2="-15"/>
      <line x1="-15" y1="30" x2="-15" y2="-15"/>
      <line x1="0" y1="30" x2="0" y2="-15"/>
      <path d="M 0 -27 L 35 -27 L 35 -37 L -35 -37 L -35 -27 L 0 -27"/>
      <path d="M 0 -37 L 7 -37 L 7 -45 L -7 -45 L -7 -37 L 0 -37"/>
    </g>
  </svg>
</template>

<style scoped>
g {
  stroke: #7a7a7a;
  fill: none;
  stroke-width: 4px;
  stroke-linecap: round;
  stroke-linejoin: round;
}
</style>