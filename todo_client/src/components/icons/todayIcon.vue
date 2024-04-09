<script>
export default {
  name: "todayIcon",
  computed: {
    todayDay() { 
      return new Date().getDate();
    }
  }
}
</script>

<template>
  <svg viewBox="-50 -50 100 100">
    <path d="
            M 0 45
            L 43 45
            Q 47 45 47 43
            L 47 -28
            Q 47 -32 42 -32
            L 30 -32

            Q 30 -18 20 -18
            Q 10 -18 10 -32

            L -10 -32

            Q -10 -18 -20 -18
            Q -30 -18 -30 -32

            L -43 -32

            Q -48 -32 -48 -28

            L -48 41

            Q -48 45 -44 45

            L 0 45"
    />
    <path d="M 20 -26 L 20 -38 M -20 -26 L -20 -38"/>
    <text x="0" y="28" text-anchor="middle">{{todayDay}}</text>
    <line x1="20" y1="34" x2="-20" y2="34"/>
    <line x1="-48" y1="-10" x2="47" y2="-10"/>
  </svg>
</template>

<style scoped>
svg {
  stroke: #7a7a7a;
}
path {
  fill: none;
  stroke-width: 5px;
  stroke-linecap: round;
}
text {
  fill: #dc4c3e;
  font-size: 42px;
  font-weight: bolder;
}
line {
  stroke-width: 4px;
  stroke-linecap: round;
}
</style>