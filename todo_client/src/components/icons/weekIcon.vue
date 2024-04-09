<script>
export default {
  name: "weekIcon"
}
</script>

<template>
  <svg id="weekIcon" class="notSelectedIcon" viewBox="-50 -50 100 100">
    <path d="
            M 0 45
            L 43 45
            Q 47 45 47 42
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
            L 10 45"
    />
    <path d="M 20 -26 L 20 -38 M -20 -26 L -20 -38"/>

    <defs>
      <rect id="dayBox" x="-41" y="-10" width="8" height="10"/>
    </defs>
    <use href="#dayBox" transform="translate(0, 15)"/>
    <use href="#dayBox" transform="translate(12, 15)"/>
    <use href="#dayBox" transform="translate(24, 15)"/>
    <use href="#dayBox" transform="translate(36, 15)"/>
    <use href="#dayBox" transform="translate(48, 15)"/>
    <use href="#dayBox" class="inverted_color" transform="translate(60, 15)"/>
    <use href="#dayBox" class="inverted_color" transform="translate(72, 15)"/>
    <line x1="-48" y1="-10" x2="47" y2="-10" stroke-width="4"/>
  </svg>
</template>

<style scoped>
svg {
  stroke: #7a7a7a;
  fill: none;
  stroke-width: 5px;
  stroke-linecap: round;
}

#dayBox {
  stroke-width: 3px;
}

.inverted_color {
  stroke: #dc4c3e;
}
</style>