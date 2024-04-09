<script>
export default {
  name: "pencilIcon"
}
</script>

<template>
  <svg viewBox="-50 -50 100 100" class="pencilIcon">
    <g>
      <path d="M 0 -35 L 7 -35 L 7 40 L 0 45 L -7 40 L -7 -35 L 0 -35"/>
      <path d="M 0 -40 L -7 -40 L -7 -48 L 7 -48 L 7 -40 L 0 -40"/>
      <polygon points="0,45 -4,41 4,41 0,45"/>
    </g>
    <line x1="-15" y1="35" x2="30" y2="35" stroke-width="4px" stroke-linecap="round"/>
  </svg>
</template>

<style scoped>
g {
  transform: rotate(45deg);
  stroke-width: 3px;
  stroke-linejoin: round;
  fill: none;
}

polygon {
  fill: #7a7a7a;
}

svg {
  stroke: #7a7a7a;
}
</style>