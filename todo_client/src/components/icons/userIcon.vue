<script>
export default {
  name: "userIcon"
}
</script>

<template>
  <svg class="userIcon" viewBox="-50 -50 100 100">
    <circle cx="0" cy="0" r="40" fill="none" stroke="#dc4c3e" stroke-width="5px"/>
    <circle cx="0" cy="-10" r="15" fill="none" stroke="#dc4c3e" stroke-width="4px"/>
    <path d="
        M -29 24
        L -18 3
        L 0 12
        L 18 3
        L 29 24" fill="none"
          stroke-width="4"
          stroke-linecap="round"
          stroke="#dc4c3e"
          stroke-linejoin="round"/>
  </svg>
</template>

<style scoped>
svg {
  transform: translate(0, 3px);
}
</style>